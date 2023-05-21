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

/**
 * @author Nuria Rodríguez Tortosa 40%
 * @author Daniel García Rodríguez 60%
 */

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
        var usuario = (UsuarioEntity) session.getAttribute("usuario");
        if (usuario == null) return true;
        @SuppressWarnings("unchecked")
        var nombresRoles = (List<String>) session.getAttribute("nombresRoles");
        if (nombresRoles.contains("gestor") || nombresRoles.contains("asistente")) return true;
        var empresa = (EmpresaEntity) session.getAttribute("empresa");
        var rolusuarios = usuario.getRolusuariosById();

        if (empresa != null) {
            var bloqueos = rolusuarios.stream().filter(
                    ru -> empresa.equals(ru.getEmpresaByIdempresa())
            ).map(RolusuarioEntity::getBloqueado).toList();
            if (bloqueos.contains((byte) 1) || bloqueos.contains((byte) 2)) return true;
        }

        return rolusuarios.stream().map(RolusuarioEntity::getCuentaAsociada).anyMatch(
                cuenta -> cuenta.getActiva().equals((byte) 0) || cuenta.getActiva().equals((byte) 2)
        );
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
        UsuarioEntity user = (UsuarioEntity) session.getAttribute("usuario");
        var ru = user.getRolusuariosById();
        EmpresaEntity empresa = (EmpresaEntity) session.getAttribute("empresa");
        ru = ru.stream().filter(
                rolusuario -> empresa == null ?
                        rolusuario.getEmpresaByIdempresa() == null :
                        rolusuario.getEmpresaByIdempresa().equals(empresa)
        ).toList();
        if (ru.isEmpty()) throw new RuntimeException("Permisos no válidos para usuario " + user.getId());
        CuentaEntity cuenta = ru.get(0).getCuentaAsociada();

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
    public String doGuardarDivisa(Model model, HttpSession session, @ModelAttribute("cambioDivisa") CambDivisaEntity cambioDivisa) {
        if (cambioDivisa == null || incumplePermisos(session)) return "redirect:/menu";
        OperacionEntity operacion = crearOperacion(session);

        Double cantidad = cambioDivisa.getCantidad();
        CuentaEntity mia = operacion.getCuentaByCuentaRealiza();

        if (cantidad <= 0) {
            model.addAttribute("error", "¡La cantidad debe ser positiva!");
            model.addAttribute("cambioDivisa", cambioDivisa);
            return "cambiodivisa";
        } else if (mia.getSaldo() - cantidad < 0) {
            // Estás pobre :(
            model.addAttribute("error", "¡No tienes suficiente saldo!\nTienes " + mia.getSaldo() + "€.");
            model.addAttribute("cambioDivisa", cambioDivisa);
            return "cambiodivisa";
        }

        // Éxito

        String urlTo = "redirect:/menu";
        if ("cajero".equalsIgnoreCase((String) session.getAttribute("menu"))) {
            ExtraccionEntity extra = new ExtraccionEntity();
            extra.setCantidad(cambioDivisa.getCantidad());
            urlTo = guardarExtraccion(model, session, extra);

            if ("extraccion".equalsIgnoreCase(urlTo)) return "cambiodivisa";

            session.setAttribute("mensaje", "Sacando " + cambioDivisa.getCantidad() + " " +
                    cambioDivisa.getOrigen() + " en " + cambioDivisa.getDestino() + "...");
        } else session.setAttribute("mensaje", "¡Cambiados " + cambioDivisa.getCantidad() + " " +
                cambioDivisa.getOrigen() + " a " + cambioDivisa.getDestino() + "!");

        operacionEntityRepository.save(operacion);
        cambioDivisa.setOperacionByOperacion(operacion);
        cambDivisaEntityRepository.save(cambioDivisa);

        return urlTo;
    }

    @GetMapping("/extraccion")
    public String doExtraccion(Model model, HttpSession session, @ModelAttribute("tipo") String tipo) {
        if (incumplePermisos(session) || !("cajero".equalsIgnoreCase((String) session.getAttribute("menu"))))
            return "redirect:/menu";

        ExtraccionEntity extraccion = new ExtraccionEntity();
        extraccion.setCantidad(0.0);
        model.addAttribute("extraer", extraccion);
        model.addAttribute("error", "");

        return "extraccion";
    }

    @PostMapping("/guardarExtraccion")
    public String doGuardarExtraccion(Model model, HttpSession session, @ModelAttribute("extraer") ExtraccionEntity extra) {
        return guardarExtraccion(model, session, extra);
    }

    private String guardarExtraccion(Model model, HttpSession session, ExtraccionEntity extra) {
        if (extra == null || incumplePermisos(session) || !("cajero".equalsIgnoreCase((String) session.getAttribute("menu"))))
            return "redirect:/menu";

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

            session.setAttribute("mensaje", "Sacando " + cantidad + " EUR en efectivo...");
            return "redirect:/menu";
        } else {
            // Estás pobre :(
            model.addAttribute("error", "¡No tienes suficiente saldo!\nTienes " + mia.getSaldo() + "€.");
            model.addAttribute("extraer", extra);
            return "extraccion";
        }
    }
}
