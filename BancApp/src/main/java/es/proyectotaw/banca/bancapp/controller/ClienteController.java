package es.proyectotaw.banca.bancapp.controller;

import es.proyectotaw.banca.bancapp.dao.*;
import es.proyectotaw.banca.bancapp.entity.*;
import es.proyectotaw.banca.bancapp.ui.FiltroOperaciones;
import es.proyectotaw.banca.bancapp.ui.OrdenarOperaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// María (5%)
import javax.servlet.http.HttpSession;

import java.util.ArrayList;

import java.util.List;

/*
* Nuria Rodríguez Tortosa 90%
* */

@SuppressWarnings("SpringMVCViewInspection")
@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    protected ClienteEntityRepository clienteEntityRepository;

    @Autowired
    protected UsuarioEntityRepository usuarioEntityRepository;

    @Autowired
    protected CuentaEntityRepository cuentaEntityRepository;

    @Autowired
    protected TransferenciaEntityRepository transferenciaEntityRepository;

    @Autowired
    protected OperacionEntityRepository operacionEntityRepository;

    @Autowired
    protected CambDivisaEntityRepository cambDivisaEntityRepository;

    @Autowired
    protected ExtraccionEntityRepository extraccionEntityRepository;


    @GetMapping("/")
    public String doPasarAlMenu() {
        return "redirect:/menu";
    }

    @GetMapping("/verOperaciones")
    public String doVerOperaciones(Model model, HttpSession session) {
        if(session.getAttribute("usuario") == null) return "redirect:/";
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
        CuentaEntity cuenta = usuario.getClienteByCliente().getCuentasByIdCliente().get(0);
        model.addAttribute("operaciones", cuenta.getOperacionsByNumCuenta());
        model.addAttribute("filtro", new FiltroOperaciones());
        model.addAttribute("ordenar", new OrdenarOperaciones());
        model.addAttribute("usuario", new UsuarioEntity());
        return "operacionesCliente";
    }

    @PostMapping("/filtrar")
    public String doFiltrar(@ModelAttribute("filtro") FiltroOperaciones filtro,
                            Model model, HttpSession session) {
        return this.procesarFiltrado(filtro, model, session);
    }

    @GetMapping("/filtrar")
    protected String procesarFiltrado(FiltroOperaciones filtro,
                                      Model model, HttpSession session) {
        List<OperacionEntity> operaciones = new ArrayList<>();
        String urlTo = "cliente";
        //lo de la session
        if (filtro == null || (filtro.getCantidadFiltro() == 0 && filtro.getNombreOperacion().equals("ninguno"))) {
            filtro = new FiltroOperaciones();
            operaciones = operacionEntityRepository.findAll();
        } else {
            if (!filtro.getNombreOperacion().equals("ninguno")) {
                String nombre = filtro.getNombreOperacion();
                if (filtro.getCantidadFiltro() == 0) {
                    if (nombre.equals("Transferencia")) {
                        List<TransferenciaEntity> transferencias = transferenciaEntityRepository.findAll();
                        for (TransferenciaEntity trans : transferencias) {
                            operaciones.add(trans.getOperacionByOperacion());
                        }
                    } else {
                        if (nombre.equals("Cambio de divisa")) {
                            List<CambDivisaEntity> cambios = cambDivisaEntityRepository.findAll();
                            for (CambDivisaEntity cambio : cambios) {
                                operaciones.add(cambio.getOperacionByOperacion());
                            }
                        } else {
                            List<ExtraccionEntity> extracciones = extraccionEntityRepository.findAll();
                            for (ExtraccionEntity extraccion : extracciones) {
                                operaciones.add(extraccion.getOperacionByOperacion());
                            }
                        }
                    }
                } else {
                    if (nombre.equals("Transferencia")) {
                        List<TransferenciaEntity> transferencias = transferenciaEntityRepository.filtrarPorCantidad(filtro.getCantidadFiltro());
                        for (TransferenciaEntity trans : transferencias) {
                            operaciones.add(trans.getOperacionByOperacion());
                        }
                    } else {
                        if (nombre.equals("Cambio de divisa")) {
                            List<CambDivisaEntity> cambios = cambDivisaEntityRepository.filtrarPorCantidad(filtro.getCantidadFiltro());
                            for (CambDivisaEntity cambio : cambios) {
                                operaciones.add(cambio.getOperacionByOperacion());
                            }
                        } else {
                            List<ExtraccionEntity> extracciones = extraccionEntityRepository.filtrarPorCantidad(filtro.getCantidadFiltro());
                            for (ExtraccionEntity extraccion : extracciones) {
                                operaciones.add(extraccion.getOperacionByOperacion());
                            }
                        }
                    }
                }
            } else {
                if (filtro.getCantidadFiltro() != 0) {
                    List<TransferenciaEntity> transferencias = transferenciaEntityRepository.filtrarPorCantidad(filtro.getCantidadFiltro());
                    for (TransferenciaEntity trans : transferencias) {
                        operaciones.add(trans.getOperacionByOperacion());
                    }
                    List<CambDivisaEntity> cambios = cambDivisaEntityRepository.filtrarPorCantidad(filtro.getCantidadFiltro());
                    for (CambDivisaEntity cambio : cambios) {
                        operaciones.add(cambio.getOperacionByOperacion());
                    }
                    List<ExtraccionEntity> extracciones = extraccionEntityRepository.filtrarPorCantidad(filtro.getCantidadFiltro());
                    for (ExtraccionEntity extraccion : extracciones) {
                        operaciones.add(extraccion.getOperacionByOperacion());
                    }
                }
            }
        }

        model.addAttribute("filtro", filtro);
        model.addAttribute("operaciones", operaciones);

        return urlTo;
    }

    @PostMapping("/ordenar")
    public String doOrdenar(@ModelAttribute("ordenar") OrdenarOperaciones orden,
                            Model model, HttpSession session) {
        return this.procesarOrdenado(orden, model, session);
    }

    protected String procesarOrdenado(OrdenarOperaciones orden,
                                      Model model, HttpSession session) {

        model.addAttribute("ordenar", orden);

        return "cliente";
    }

    @PostMapping("/guardar")
    public String doGuardarPerfil(Model model,  HttpSession session, RedirectAttributes redirectAttributes, @ModelAttribute("usuario") UsuarioEntity usur) {
        //si es particular
        usuarioEntityRepository.saveAndFlush(usur);
        redirectAttributes.addFlashAttribute("mensaje", "Los datos se han guardado correctamente");
        session.setAttribute("usuario", usur);
        return "redirect:/cliente/datosUsuario";
    }

    @GetMapping("/datosUsuario")
    public String doVerMisDatos(HttpSession session, Model model){;
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);

        return "datosUsuario";
    }

    @GetMapping("/solicitarDesbloqueo")
    public String doSolicitudDesbloqueo(Model model, HttpSession session){
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
        usuario.getClienteByCliente().getCuentasByIdCliente().get(0).getActiva(2);
        usuarioEntityRepository.saveAndFlush(usuario);
        model.addAttribute("usuario", usuario);
        return "redirect:/menu";
    }

}
