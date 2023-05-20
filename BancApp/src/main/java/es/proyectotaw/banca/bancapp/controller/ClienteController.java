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
        if (session.getAttribute("usuario") == null) return "redirect:/";
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
        ClienteEntity cliente = usuario.getClienteByCliente();
        CuentaEntity cuenta = cliente.getCuentasByIdCliente().get(0);
        List<OperacionEntity> operaciones = cuenta.getOperacionsByNumCuenta();

        List<CambDivisaEntity> cambios = new ArrayList<>();
        List<TransferenciaEntity> transs = new ArrayList<>();
        List<ExtraccionEntity> extras = new ArrayList<>();

        for (OperacionEntity ope : operaciones) {
            CambDivisaEntity cambio = cambDivisaEntityRepository.findByOperation(ope.getIdOperacion());
            TransferenciaEntity trans = transferenciaEntityRepository.findByOperation(ope.getIdOperacion());
            ExtraccionEntity extra = extraccionEntityRepository.findByOperation(ope.getIdOperacion());
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
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
        List<OperacionEntity> operaciones = new ArrayList<>();
        List<CambDivisaEntity> cambios = new ArrayList<>();
        List<TransferenciaEntity> transs = new ArrayList<>();
        List<ExtraccionEntity> extras = new ArrayList<>();
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
                        for (OperacionEntity ope : usuario.getClienteByCliente().getCuentasByIdCliente().get(0).getOperacionsByNumCuenta()) {
                            if (transferenciaEntityRepository.findByOperation(ope.getIdOperacion()) != null) {
                                transs.add(transferenciaEntityRepository.findByOperation(ope.getIdOperacion()));
                            }
                        }
                    } else {
                        if (nombre.equals("Cambio de divisa")) {
                            for (OperacionEntity ope : usuario.getClienteByCliente().getCuentasByIdCliente().get(0).getOperacionsByNumCuenta()) {
                                if (cambDivisaEntityRepository.findByOperation(ope.getIdOperacion()) != null) {
                                    cambios.add(cambDivisaEntityRepository.findByOperation(ope.getIdOperacion()));
                                }
                            }
                        } else {
                            for (OperacionEntity ope : usuario.getClienteByCliente().getCuentasByIdCliente().get(0).getOperacionsByNumCuenta()) {
                                if (extraccionEntityRepository.findByOperation(ope.getIdOperacion()) != null) {
                                    extras.add(extraccionEntityRepository.findByOperation(ope.getIdOperacion()));
                                }
                            }
                        }
                    }
                } else {
                    if (nombre.equals("Transferencia")) {
                        List<TransferenciaEntity> transferencias = transferenciaEntityRepository.filtrarPorCantidad(filtro.getCantidadFiltro());
                        for (OperacionEntity ope : usuario.getClienteByCliente().getCuentasByIdCliente().get(0).getOperacionsByNumCuenta()) {
                            for (TransferenciaEntity trans : transferencias) {
                                if (transferenciaEntityRepository.findByOperation(ope.getIdOperacion()) != null && ope.getIdOperacion() == trans.getOperacionByOperacion().getIdOperacion()) {
                                    transs.add(transferenciaEntityRepository.findByOperation(ope.getIdOperacion()));
                                }
                            }
                        }
                    } else {
                        if (nombre.equals("Cambio de divisa")) {
                            List<CambDivisaEntity> cambiose = cambDivisaEntityRepository.filtrarPorCantidad(filtro.getCantidadFiltro());
                            for (OperacionEntity ope : usuario.getClienteByCliente().getCuentasByIdCliente().get(0).getOperacionsByNumCuenta()) {
                                for (CambDivisaEntity cambio : cambiose) {
                                    if (cambDivisaEntityRepository.findByOperation(ope.getIdOperacion()) != null && ope.getIdOperacion() == cambio.getOperacionByOperacion().getIdOperacion()) {
                                        cambios.add(cambDivisaEntityRepository.findByOperation(ope.getIdOperacion()));
                                    }
                                }
                            }
                        } else {
                            List<ExtraccionEntity> extracciones = extraccionEntityRepository.filtrarPorCantidad(filtro.getCantidadFiltro());
                            for (OperacionEntity ope : usuario.getClienteByCliente().getCuentasByIdCliente().get(0).getOperacionsByNumCuenta()) {
                                for (ExtraccionEntity extraccion : extracciones) {
                                    if (extraccionEntityRepository.findByOperation(ope.getIdOperacion()) != null && ope.getIdOperacion() == extraccion.getOperacionByOperacion().getIdOperacion()) {
                                        extras.add(extraccionEntityRepository.findByOperation(ope.getIdOperacion()));
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                if (filtro.getCantidadFiltro() != 0) {
                    List<TransferenciaEntity> transferencias = transferenciaEntityRepository.filtrarPorCantidad(filtro.getCantidadFiltro());
                    for (OperacionEntity ope : usuario.getClienteByCliente().getCuentasByIdCliente().get(0).getOperacionsByNumCuenta()) {
                        for (TransferenciaEntity trans : transferencias) {
                            if (transferenciaEntityRepository.findByOperation(ope.getIdOperacion()) != null && ope.getIdOperacion() == trans.getOperacionByOperacion().getIdOperacion()) {
                                transs.add(transferenciaEntityRepository.findByOperation(ope.getIdOperacion()));
                            }
                        }
                    }

                    List<CambDivisaEntity> cambiose = cambDivisaEntityRepository.filtrarPorCantidad(filtro.getCantidadFiltro());
                    for (OperacionEntity ope : usuario.getClienteByCliente().getCuentasByIdCliente().get(0).getOperacionsByNumCuenta()) {
                        for (CambDivisaEntity cambio : cambiose) {
                            if (cambDivisaEntityRepository.findByOperation(ope.getIdOperacion()) != null && ope.getIdOperacion() == cambio.getOperacionByOperacion().getIdOperacion()) {
                                cambios.add(cambDivisaEntityRepository.findByOperation(ope.getIdOperacion()));
                            }
                        }
                    }

                    List<ExtraccionEntity> extracciones = extraccionEntityRepository.filtrarPorCantidad(filtro.getCantidadFiltro());
                    for (OperacionEntity ope : usuario.getClienteByCliente().getCuentasByIdCliente().get(0).getOperacionsByNumCuenta()) {
                        for (ExtraccionEntity extraccion : extracciones) {
                            if (extraccionEntityRepository.findByOperation(ope.getIdOperacion()) != null && ope.getIdOperacion() == extraccion.getOperacionByOperacion().getIdOperacion()) {
                                extras.add(extraccionEntityRepository.findByOperation(ope.getIdOperacion()));
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
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
        List<CambDivisaEntity> cambios = new ArrayList<>();
        List<TransferenciaEntity> transs = new ArrayList<>();
        List<ExtraccionEntity> extras = new ArrayList<>();
        String urlTo = "operacionesCliente";

        if(orden == null || !orden.getCantidad()) {
            orden = new OrdenarOperaciones();
            urlTo = "redirect:/cliente/verOperaciones";
        } else {
            List<OperacionEntity> operaciones = operacionEntityRepository.getOperacionesOrdenadasPorCantidad(usuario.getClienteByCliente().getCuentasByIdCliente().get(0));

            for (OperacionEntity ope : operaciones) {
                CambDivisaEntity cambio = cambDivisaEntityRepository.findByOperation(ope.getIdOperacion());
                TransferenciaEntity trans = transferenciaEntityRepository.findByOperation(ope.getIdOperacion());
                ExtraccionEntity extra = extraccionEntityRepository.findByOperation(ope.getIdOperacion());
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
        //si es particular
        usuarioEntityRepository.saveAndFlush(usur);
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
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
        usuario.getClienteByCliente().getCuentasByIdCliente().get(0).setActiva((byte) 2);
        usuarioEntityRepository.saveAndFlush(usuario);
        CuentaEntity cuenta = usuario.getClienteByCliente().getCuentasByIdCliente().get(0);
        cuenta.setActiva((byte) 2);
        cuentaEntityRepository.saveAndFlush(cuenta);
        model.addAttribute("usuario", usuario);
        return "redirect:/menu";
    }

}
