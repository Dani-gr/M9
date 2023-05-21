package es.proyectotaw.banca.bancapp.controller;

import es.proyectotaw.banca.bancapp.dao.*;
import es.proyectotaw.banca.bancapp.dto.*;
import es.proyectotaw.banca.bancapp.entity.*;
import es.proyectotaw.banca.bancapp.service.*;
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
    ClienteService clienteService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    CuentaService cuentaService;

    @Autowired
    TransferenciaService transferenciaService;

    @Autowired
    OperacionService operacionService;
    @Autowired
    CambDivisaService cambDivisaService;
    @Autowired
    ExtraccionService extraccionService;

    @GetMapping("/")
    public String doPasarAlMenu() {
        return "redirect:/menu";
    }

    @GetMapping("/verOperaciones")
    public String doVerOperaciones(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) return "redirect:/";
        UsuarioEntityDTO usuario = (UsuarioEntityDTO) session.getAttribute("usuario");
        ClienteEntityDTO cliente = usuario.getClienteByCliente();
        CuentaEntityDTO cuenta = cliente.getCuentasByIdCliente().get(0);
        List<OperacionEntityDTO> operaciones = cuenta.getOperacionsByNumCuenta();

        List<CambDivisaEntityDTO> cambios = new ArrayList<>();
        List<TransferenciaEntityDTO> transs = new ArrayList<>();
        List<ExtraccionEntityDTO> extras = new ArrayList<>();

        for (OperacionEntityDTO ope : operaciones) {
            CambDivisaEntityDTO cambio = cambDivisaService.encontrarPorOperacion(ope);
            TransferenciaEntityDTO trans = transferenciaService.encontrarPorOperacion(ope);
            ExtraccionEntityDTO extra = extraccionService.encontrarPorOperacion(ope);
            if (cambio != null) {
                cambios.add(cambio);
            } else {
                if (trans != null) {
                    transs.add(trans);
                } else {
                    if (extra != null) extras.add(extra);
                }
            }
        }

        model.addAttribute("filtro", new FiltroOperaciones());
        model.addAttribute("ordenar", new OrdenarOperaciones());
        model.addAttribute("cambios", cambios);
        model.addAttribute("transs", transs);
        model.addAttribute("extras", extras);

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
        if (session.getAttribute("usuario") == null) return "redirect:/";
        UsuarioEntityDTO usuario = (UsuarioEntityDTO) session.getAttribute("usuario");
        List<OperacionEntityDTO> operaciones = new ArrayList<>();
        List<CambDivisaEntityDTO> cambios = new ArrayList<>();
        List<TransferenciaEntityDTO> transs = new ArrayList<>();
        List<ExtraccionEntityDTO> extras = new ArrayList<>();
        String urlTo = "operacionesCliente";
        //lo de la session
        if (filtro == null || (filtro.getCantidadFiltro() == 0 && filtro.getNombreOperacion().equals("ninguno"))) {
            filtro = new FiltroOperaciones();
            urlTo = "redirect:/cliente/verOperaciones";
        } else {
            if (!filtro.getNombreOperacion().equals("ninguno")) {
                String nombre = filtro.getNombreOperacion();
                if (filtro.getCantidadFiltro() == 0) {
                    if (nombre.equals("Transferencia")) {
                        for (OperacionEntityDTO ope : usuario.getClienteByCliente().getCuentasByIdCliente().get(0).getOperacionsByNumCuenta()) {
                            if (transferenciaService.encontrarPorOperacion(ope) != null) {
                                transs.add(transferenciaService.encontrarPorOperacion(ope));
                            }
                        }
                    } else {
                        if (nombre.equals("Cambio de divisa")) {
                            for (OperacionEntityDTO ope : usuario.getClienteByCliente().getCuentasByIdCliente().get(0).getOperacionsByNumCuenta()) {
                                if (cambDivisaService.encontrarPorOperacion(ope) != null) {
                                    cambios.add(cambDivisaService.encontrarPorOperacion(ope));
                                }
                            }
                        } else {
                            for (OperacionEntityDTO ope : usuario.getClienteByCliente().getCuentasByIdCliente().get(0).getOperacionsByNumCuenta()) {
                                if (extraccionService.encontrarPorOperacion(ope) != null) {
                                    extras.add(extraccionService.encontrarPorOperacion(ope));
                                }
                            }
                        }
                    }
                } else {
                    if (nombre.equals("Transferencia")) {
                        List<TransferenciaEntityDTO> transferencias = transferenciaService.filtrarPorCantidad(filtro.getCantidadFiltro());
                        for (OperacionEntityDTO ope : usuario.getClienteByCliente().getCuentasByIdCliente().get(0).getOperacionsByNumCuenta()) {
                            for (TransferenciaEntityDTO trans : transferencias) {
                                if (transferenciaService.encontrarPorOperacion(ope) != null && ope.getIdOperacion() == trans.getOperacionByOperacion().getIdOperacion()) {
                                    transs.add(transferenciaService.encontrarPorOperacion(ope));
                                }
                            }
                        }
                    } else {
                        if (nombre.equals("Cambio de divisa")) {
                            List<CambDivisaEntityDTO> cambiose = cambDivisaService.filtrarPorCantidad(filtro.getCantidadFiltro());
                            for (OperacionEntityDTO ope : usuario.getClienteByCliente().getCuentasByIdCliente().get(0).getOperacionsByNumCuenta()) {
                                for (CambDivisaEntityDTO cambio : cambiose) {
                                    if (cambDivisaService.encontrarPorOperacion(ope) != null && ope.getIdOperacion() == cambio.getOperacionByOperacion().getIdOperacion()) {
                                        cambios.add(cambDivisaService.encontrarPorOperacion(ope));
                                    }
                                }
                            }
                        } else {
                            List<ExtraccionEntityDTO> extracciones = extraccionService.filtrarPorCantidad(filtro.getCantidadFiltro());
                            for (OperacionEntityDTO ope : usuario.getClienteByCliente().getCuentasByIdCliente().get(0).getOperacionsByNumCuenta()) {
                                for (ExtraccionEntityDTO extraccion : extracciones) {
                                    if (extraccionService.encontrarPorOperacion(ope) != null && ope.getIdOperacion() == extraccion.getOperacionByOperacion().getIdOperacion()) {
                                        extras.add(extraccionService.encontrarPorOperacion(ope));
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                if (filtro.getCantidadFiltro() != 0) {
                    List<TransferenciaEntityDTO> transferencias = transferenciaService.filtrarPorCantidad(filtro.getCantidadFiltro());
                    for (OperacionEntityDTO ope : usuario.getClienteByCliente().getCuentasByIdCliente().get(0).getOperacionsByNumCuenta()) {
                        for (TransferenciaEntityDTO trans : transferencias) {
                            if (transferenciaService.encontrarPorOperacion(ope) != null && ope.getIdOperacion() == trans.getOperacionByOperacion().getIdOperacion()) {
                                transs.add(transferenciaService.encontrarPorOperacion(ope));
                            }
                        }
                    }

                    List<CambDivisaEntityDTO> cambiose = cambDivisaService.filtrarPorCantidad(filtro.getCantidadFiltro());
                    for (OperacionEntityDTO ope : usuario.getClienteByCliente().getCuentasByIdCliente().get(0).getOperacionsByNumCuenta()) {
                        for (CambDivisaEntityDTO cambio : cambiose) {
                            if (cambDivisaService.encontrarPorOperacion(ope) != null && ope.getIdOperacion() == cambio.getOperacionByOperacion().getIdOperacion()) {
                                cambios.add(cambDivisaService.encontrarPorOperacion(ope));
                            }
                        }
                    }

                    List<ExtraccionEntityDTO> extracciones = extraccionService.filtrarPorCantidad(filtro.getCantidadFiltro());
                    for (OperacionEntityDTO ope : usuario.getClienteByCliente().getCuentasByIdCliente().get(0).getOperacionsByNumCuenta()) {
                        for (ExtraccionEntityDTO extraccion : extracciones) {
                            if (extraccionService.encontrarPorOperacion(ope) != null && ope.getIdOperacion() == extraccion.getOperacionByOperacion().getIdOperacion()) {
                                extras.add(extraccionService.encontrarPorOperacion(ope));
                            }
                        }
                    }
                }
            }
        }

        model.addAttribute("filtro", filtro);
        model.addAttribute("ordenar", new OrdenarOperaciones());
        model.addAttribute("cambios", cambios);
        model.addAttribute("transs", transs);
        model.addAttribute("extras", extras);

        return urlTo;
    }

    @PostMapping("/ordenar")
    public String doOrdenar(@ModelAttribute("ordenar") OrdenarOperaciones orden,
                            Model model, HttpSession session) {
        return this.procesarOrdenado(orden, model, session);
    }

    protected String procesarOrdenado(OrdenarOperaciones orden,
                                      Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) return "redirect:/";
        UsuarioEntityDTO usuario = (UsuarioEntityDTO) session.getAttribute("usuario");
        List<CambDivisaEntityDTO> cambios = new ArrayList<>();
        List<TransferenciaEntityDTO> transs = new ArrayList<>();
        List<ExtraccionEntityDTO> extras = new ArrayList<>();
        String urlTo = "operacionesCliente";

        if(orden == null || !orden.getCantidad()) {
            orden = new OrdenarOperaciones();
            urlTo = "redirect:/cliente/verOperaciones";
        } else {
            List<OperacionEntityDTO> operaciones = operacionService.ordenarPorCantidad(usuario.getClienteByCliente().getCuentasByIdCliente().get(0));

            for (OperacionEntityDTO ope : operaciones) {
                CambDivisaEntityDTO cambio = cambDivisaService.encontrarPorOperacion(ope);
                TransferenciaEntityDTO trans = transferenciaService.encontrarPorOperacion(ope);
                ExtraccionEntityDTO extra = extraccionService.encontrarPorOperacion(ope);
                if (cambio != null) {
                    cambios.add(cambio);
                } else {
                    if (trans != null) {
                        transs.add(trans);
                    } else {
                        if (extra != null) extras.add(extra);
                    }
                }
            }
        }

        model.addAttribute("filtro", new FiltroOperaciones());
        model.addAttribute("ordenar", orden);
        model.addAttribute("cambios", cambios);
        model.addAttribute("transs", transs);
        model.addAttribute("extras", extras);

        return urlTo;
    }

    @PostMapping("/guardar")
    public String doGuardarPerfil(Model model, HttpSession session, RedirectAttributes redirectAttributes, @ModelAttribute("usuario") UsuarioEntity usur) {
        usuarioService.guardar(usur.toDTO());
        redirectAttributes.addFlashAttribute("mensaje", "Los datos se han guardado correctamente");
        session.setAttribute("usuario", usur);
        return "redirect:/cliente/datosUsuario";
    }

    @GetMapping("/datosUsuario")
    public String doVerMisDatos(HttpSession session, Model model) {
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);

        return "datosUsuario";
    }

    @GetMapping("/solicitarDesbloqueo")
    public String doSolicitudDesbloqueo(Model model, HttpSession session) {
        UsuarioEntityDTO usuario = (UsuarioEntityDTO) session.getAttribute("usuario");
        usuario.getClienteByCliente().getCuentasByIdCliente().get(0).setActiva((byte) 2);
        usuarioService.guardar(usuario);
        CuentaEntityDTO cuenta = usuario.getClienteByCliente().getCuentasByIdCliente().get(0);
        cuenta.setActiva((byte) 2);
        cuentaService.guardar(cuenta);
        model.addAttribute("usuario", usuario);
        return "redirect:/menu";
    }

}
