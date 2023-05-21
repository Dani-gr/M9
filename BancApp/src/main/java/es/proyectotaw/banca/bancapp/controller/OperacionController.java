package es.proyectotaw.banca.bancapp.controller;

import es.proyectotaw.banca.bancapp.dto.*;
import es.proyectotaw.banca.bancapp.service.*;
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
 * Conversión a DTO:
 * <ul>
 *      <li>Daniel García Rodríguez: 100%</li>
 * </ul>
 *
 * General:
 *
 * @author Nuria Rodríguez Tortosa 40%
 * @author Daniel García Rodríguez 60%
 */

@SuppressWarnings("SpringMVCViewInspection")
@Controller
@RequestMapping("/operacion")
public class OperacionController {

    @Autowired
    TransferenciaService transferenciaService;
    @Autowired
    OperacionService operacionService;
    @Autowired
    CuentaService cuentaService;
    @Autowired
    CambDivisaService cambDivisaService;
    @Autowired
    ExtraccionService extraccionService;
    @Autowired
    RolusuarioService rolusuarioService;


    /**
     * Método auxiliar para comprobar si se accede sin permiso.
     *
     * @return {@code false} si el usuario tiene permiso, y {@code true} si no
     */
    private boolean incumplePermisos(HttpSession session) {
        var usuario = (UsuarioEntityDTO) session.getAttribute("usuario");
        if (usuario == null) return true;
        @SuppressWarnings("unchecked")
        var nombresRoles = (List<String>) session.getAttribute("nombresRoles");
        if (nombresRoles.contains("gestor") || nombresRoles.contains("asistente")) return true;
        var empresa = (EmpresaEntityDTO) session.getAttribute("empresa");
        var rolusuarios = usuario.getRolusuariosById();

        if (empresa != null) {
            var bloqueos = rolusuarios.stream().filter(
                    ru -> empresa.equals(ru.getEmpresaByIdempresa())
            ).map(RolusuarioEntityDTO::getBloqueado).toList();
            if (bloqueos.contains((byte) 1) || bloqueos.contains((byte) 2)) return true;
        }

        return rolusuarios.stream().map(RolusuarioEntityDTO::getCuentaAsociada).anyMatch(
                cuenta -> cuenta.getActiva().equals((byte) 0) || cuenta.getActiva().equals((byte) 2)
        );
    }

    @GetMapping("/transferencia")
    public String doTransferencia(Model model, HttpSession session) {
        if (incumplePermisos(session)) return "redirect:/menu";

        TransferenciaEntityDTO transferencia = new TransferenciaEntityDTO();
        transferencia.setCantidad(0.00);
        model.addAttribute("transferenciaARealizar", transferencia);

        model.addAttribute("error", "");

        return "transferencia";
    }

    private OperacionEntityDTO crearOperacion(HttpSession session) {
        UsuarioEntityDTO user = (UsuarioEntityDTO) session.getAttribute("usuario");
        var ru = user.getRolusuariosById();
        EmpresaEntityDTO empresa = (EmpresaEntityDTO) session.getAttribute("empresa");
        ru = ru.stream().filter(
                rolusuario -> empresa == null ?
                        rolusuario.getEmpresaByIdempresa() == null :
                        rolusuario.getEmpresaByIdempresa().equals(empresa)
        ).toList();
        if (ru.isEmpty()) throw new RuntimeException("Permisos no válidos para usuario " + user.getId());
        CuentaEntityDTO cuenta = ru.get(0).getCuentaAsociada();

        OperacionEntityDTO operacion = new OperacionEntityDTO();
        operacion.setCuentaByCuentaRealiza(cuenta);
        operacion.setFecha(new Date(System.currentTimeMillis()));

        return operacion;
    }


    @PostMapping("/guardarTransferencia")
    public String doGuardarTransferencia(Model model, HttpSession session, @ModelAttribute("transferenciaARealizar") TransferenciaEntityDTO transferencia) {
        if (transferencia == null || incumplePermisos(session)) return "redirect:/menu";

        OperacionEntityDTO operacion = crearOperacion(session);
        CuentaEntityDTO mia = operacion.getCuentaByCuentaRealiza(), otro;
        Double cantidad = transferencia.getCantidad();

        if (cantidad == null || cantidad <= 0) {
            model.addAttribute("error", "¡La cantidad debe ser positiva!");
            model.addAttribute("transferenciaARealizar", transferencia);
            return "transferencia";
        }
        try {
            otro = cuentaService.buscaPorIBAN(Integer.valueOf(transferencia.getIbanDestino()));
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
                cuentaService.guardar(mia);
                cuentaService.guardar(otro);

                operacionService.guardar(operacion);
                transferencia.setCuentaByCuentaDestino(otro);
                transferencia.setIbanDestino(null);
                transferencia.setOperacionByOperacion(operacion);
                transferenciaService.guardar(transferencia);

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
            operacionService.guardar(operacion);
            transferencia.setOperacionByOperacion(operacion);
            transferenciaService.guardar(transferencia);
            session.setAttribute("mensaje", "¡Transferencia realizada correctamente!");
            return "redirect:/menu";
        }
    }


    @GetMapping("/cambioDivisa")
    public String doDivisa(Model model, HttpSession session) {
        if (incumplePermisos(session)) return "redirect:/menu";
        CambDivisaEntityDTO cambio = new CambDivisaEntityDTO();
        cambio.setCantidad(0.00);
        model.addAttribute("cambioDivisa", cambio);
        model.addAttribute("error", "");

        return "cambiodivisa";
    }

    @PostMapping("/guardarDivisa")
    public String doGuardarDivisa(Model model, HttpSession session, @ModelAttribute("cambioDivisa") CambDivisaEntityDTO cambioDivisa) {
        if (cambioDivisa == null || incumplePermisos(session)) return "redirect:/menu";
        OperacionEntityDTO operacion = crearOperacion(session);

        Double cantidad = cambioDivisa.getCantidad();
        CuentaEntityDTO mia = operacion.getCuentaByCuentaRealiza();

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
            ExtraccionEntityDTO extra = new ExtraccionEntityDTO();
            extra.setCantidad(cambioDivisa.getCantidad());
            urlTo = guardarExtraccion(model, session, extra);

            if ("extraccion".equalsIgnoreCase(urlTo)) return "cambiodivisa";

            session.setAttribute("mensaje", "Sacando " + cambioDivisa.getCantidad() + " " +
                    cambioDivisa.getOrigen() + " en " + cambioDivisa.getDestino() + "...");
        } else session.setAttribute("mensaje", "¡Cambiados " + cambioDivisa.getCantidad() + " " +
                cambioDivisa.getOrigen() + " a " + cambioDivisa.getDestino() + "!");

        operacionService.guardar(operacion);
        cambioDivisa.setOperacionByOperacion(operacion);
        cambDivisaService.guardar(cambioDivisa);

        return urlTo;
    }

    @GetMapping("/extraccion")
    public String doExtraccion(Model model, HttpSession session, @ModelAttribute("tipo") String tipo) {
        if (incumplePermisos(session) || !("cajero".equalsIgnoreCase((String) session.getAttribute("menu"))))
            return "redirect:/menu";

        ExtraccionEntityDTO extraccion = new ExtraccionEntityDTO();
        extraccion.setCantidad(0.0);
        model.addAttribute("extraer", extraccion);
        model.addAttribute("error", "");

        return "extraccion";
    }

    @PostMapping("/guardarExtraccion")
    public String doGuardarExtraccion(Model model, HttpSession session, @ModelAttribute("extraer") ExtraccionEntityDTO extra) {
        return guardarExtraccion(model, session, extra);
    }

    private String guardarExtraccion(Model model, HttpSession session, ExtraccionEntityDTO extra) {
        if (extra == null || incumplePermisos(session) || !("cajero".equalsIgnoreCase((String) session.getAttribute("menu"))))
            return "redirect:/menu";

        OperacionEntityDTO operacion = crearOperacion(session);
        CuentaEntityDTO mia = operacion.getCuentaByCuentaRealiza();
        Double cantidad = extra.getCantidad();

        if (cantidad <= 0) {
            model.addAttribute("error", "¡La cantidad debe ser positiva!");
            model.addAttribute("extraer", extra);
            return "extraccion";
        } else if (mia.getSaldo() - cantidad >= 0) {
            mia.setSaldo(mia.getSaldo() - cantidad);
            cuentaService.guardar(mia);

            operacionService.guardar(operacion);
            extra.setOperacionByOperacion(operacion);
            extraccionService.guardar(extra);

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
