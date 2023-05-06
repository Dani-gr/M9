package es.proyectotaw.banca.bancapp.controller;

import es.proyectotaw.banca.bancapp.dao.*;
import es.proyectotaw.banca.bancapp.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

@SuppressWarnings("SpringMVCViewInspection")
@Controller
@RequestMapping("/operacion")
public class OperacionController {

    @Autowired
    protected TransferenciaEntityRepository transferenciaEntityRepository;

    @Autowired
    protected OperacionEntityRepository operacionEntityRepository;

    @Autowired
    protected CuentaEntityRepository cuentaEntityRepository;

    @Autowired
    protected CambDivisaEntityRepository cambDivisaEntityRepository;

    @Autowired
    protected ExtraccionEntityRepository extraccionEntityRepository;

    @GetMapping("/")
    public String doAlmacenarOperacion(Model model) {
        model.addAttribute("fecha", new Date(System.currentTimeMillis()));

        return "menu";
    }

    /**
     * Método auxiliar para comprobar si se accede sin permiso.
     *
     * @return {@code false} si el usuario tiene permiso, y {@code true} si no
     */
    private boolean incumplePermisos(HttpSession session) {
        // TODO añadir comprobar bloqueos
        @SuppressWarnings("unchecked")
        var nombresRoles = (List<String>) session.getAttribute("nombresRoles");
        return session.getAttribute("usuario") == null || nombresRoles.contains("gestor") || nombresRoles.contains("asistente");
    }

    @GetMapping("/transferencia")
    public String doTransferencia(Model model, HttpSession session) {
        if (incumplePermisos(session)) return "redirect:/menu";

        TransferenciaEntity transferencia = new TransferenciaEntity();
        transferencia.setCantidad(0.00);
        model.addAttribute("transferenciaARealizar", transferencia);

        model.addAttribute("error", "");

        return "transferencia";
    }

    private OperacionEntity crearOperacion(HttpSession session) {
        // TODO refactor for new method getCuentaAsociada() in Rolusuario
        CuentaEntity cuenta = ((UsuarioEntity) session.getAttribute("usuario")).getClienteByCliente().getCuentasByIdCliente().get(0);

        OperacionEntity operacion = new OperacionEntity();
        operacion.setCuentaByCuentaRealiza(cuenta);
        operacion.setFecha(new Date(System.currentTimeMillis()));

        return operacion;
    }

    @PostMapping("/guardarTransferencia")
    public String doGuardarTransferencia(Model model, HttpSession session, @ModelAttribute("transferenciaARealizar") TransferenciaEntity transferencia) {
        if (transferencia == null || incumplePermisos(session)) return "redirect:/menu";

        OperacionEntity operacion = crearOperacion(session);
        CuentaEntity mia = operacion.getCuentaByCuentaRealiza(), otro;
        Double cantidad = transferencia.getCantidad();

        if (cantidad == null || cantidad <= 0) {
            model.addAttribute("error", "¡La cantidad debe ser positiva!");
            model.addAttribute("transferenciaARealizar", transferencia);
            return "transferencia";
        }
        try {
            otro = cuentaEntityRepository.findById(Integer.valueOf(transferencia.getIbanDestino())).orElse(null);
        } catch (RuntimeException ignored) {
            otro = null;
        }

        if (transferencia.getIbanDestino() == null || transferencia.getIbanDestino().isBlank()) {
            // Campos destino vacíos
            model.addAttribute("error", "¡No has indicado el destino de la transferencia!");
            model.addAttribute("transferenciaARealizar", transferencia);
            return "transferencia";
        } else if (otro != null) {
            // Cuenta interna
            if (mia.getSaldo() - cantidad >= 0) {
                mia.setSaldo(mia.getSaldo() - cantidad);
                otro.setSaldo(otro.getSaldo() + cantidad);
                cuentaEntityRepository.save(mia);
                cuentaEntityRepository.save(otro);

                operacionEntityRepository.save(operacion);
                transferencia.setCuentaByCuentaDestino(otro);
                transferencia.setIbanDestino(null);
                transferencia.setOperacionByOperacion(operacion);
                transferenciaEntityRepository.save(transferencia);

                session.setAttribute("mensaje", "¡Transferencia realizada correctamente!");
                return "redirect:/menu";
            } else {
                // Estás pobre :(
                model.addAttribute("error", "¡No tienes suficiente saldo! Tienes " + mia.getSaldo() + "€.");
                model.addAttribute("transferenciaARealizar", transferencia);
                return "transferencia";
            }
        } else if (mia.getSaldo() - cantidad < 0) {
            // Cuenta externa, pero estás pobre :(
            model.addAttribute("error", "¡No tienes suficiente saldo! Tienes " + mia.getSaldo() + "€.");
            model.addAttribute("transferenciaARealizar", transferencia);
            return "transferencia";
        } else {
            // Cuenta externa, pero tienes suficiente :)
            mia.setSaldo(mia.getSaldo() - cantidad);
            operacionEntityRepository.save(operacion);
            transferencia.setOperacionByOperacion(operacion);
            transferenciaEntityRepository.save(transferencia);
            session.setAttribute("mensaje", "¡Transferencia realizada correctamente!");
            return "redirect:/menu";
        }
    }

    @GetMapping("/cambioDivisa")
    public String doDivisa(Model model, HttpSession session) {
        if (incumplePermisos(session)) return "redirect:/menu";
        CambDivisaEntity cambio = new CambDivisaEntity();
        cambio.setCantidad(0.00);
        model.addAttribute("cambioDivisa", cambio);
        model.addAttribute("error", "");

        return "cambiodivisa";
    }

    @PostMapping("/guardarDivisa")
    public String doGuardarDivisa(HttpSession session, @ModelAttribute("cambioDivisa") CambDivisaEntity cambioDivisa) {
        if (cambioDivisa == null || incumplePermisos(session)) return "redirect:/menu";
        OperacionEntity operacion = crearOperacion(session);
        // Éxito
        operacionEntityRepository.save(operacion);
        cambioDivisa.setOperacionByOperacion(operacion);
        cambDivisaEntityRepository.save(cambioDivisa);

        /*
         * TODO Por hacer porque me tengo que estudiar los cambios de monedas a monedas
         */
        return "menu";
    }

    @GetMapping("/extraccion")
    public String doExtraccion(Model model, HttpSession session) {
        if (incumplePermisos(session)) return "redirect:/menu";

        ExtraccionEntity extraccion = new ExtraccionEntity();
        extraccion.setCantidad(0.0);
        model.addAttribute("extraer", extraccion);
        model.addAttribute("error", "");

        return "extraccion";
    }

    @PostMapping("/guardarExtraccion")
    public String doGuardarExtraccion(Model model, HttpSession session, @ModelAttribute("extraer") ExtraccionEntity extra) {
        if (extra == null || incumplePermisos(session)) return "redirect:/menu";

        OperacionEntity operacion = crearOperacion(session);
        CuentaEntity mia = operacion.getCuentaByCuentaRealiza();
        Double cantidad = extra.getCantidad();

        if (cantidad <= 0) {
            model.addAttribute("error", "¡La cantidad debe ser positiva!");
            model.addAttribute("extraer", extra);
            return "extraccion";
        } else if (mia.getSaldo() - cantidad >= 0) {
            mia.setSaldo(mia.getSaldo() - cantidad);
            cuentaEntityRepository.save(mia);

            operacionEntityRepository.save(operacion);
            extra.setOperacionByOperacion(operacion);
            extraccionEntityRepository.save(extra);
            return "menu";
        } else {
            // Estás pobre :(
            model.addAttribute("error", "¡No tienes suficiente saldo!\nTienes " + mia.getSaldo() + "€.");
            model.addAttribute("extraer", extra);
            return "extraccion";
        }
    }
}
