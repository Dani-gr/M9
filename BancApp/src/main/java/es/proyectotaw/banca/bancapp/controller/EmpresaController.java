package es.proyectotaw.banca.bancapp.controller;


import es.proyectotaw.banca.bancapp.dao.*;
import es.proyectotaw.banca.bancapp.entity.*;
import es.proyectotaw.banca.bancapp.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// María
@Controller
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    UsuarioEntityRepository usuarioEntityRepository;

    @Autowired
    RolEntityRepository rolEntityRepository;
    @Autowired
    private RolusuarioEntityRepository rolusuarioEntityRepository;
    @Autowired
    private EmpresaEntityRepository empresaEntityRepository;

    @Autowired
    private DireccionEntityRepository direccionEntityRepository;

    @Autowired
    private ClienteEntityRepository clienteEntityRepository;

    @Autowired
    private CambDivisaEntityRepository cambDivisaEntityRepository;

    @Autowired
    private ExtraccionEntityRepository extraccionEntityRepository;
    @Autowired
    private TransferenciaEntityRepository transferenciaEntityRepository;

    @Autowired
    private OperacionEntityRepository operacionEntityRepository;

    @GetMapping("/")
    public String doMostrar(Model model, HttpSession session) {
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
        if (usuario == null) return "redirect:/logout";

        List<RolusuarioEntity> listaRolUsuarioAsociados = usuario.getRolusuariosById();
        EmpresaEntity empresa = listaRolUsuarioAsociados.get(0).getEmpresaByIdempresa();
        List<UsuarioEntity> usuariosAsociados = usuarioEntityRepository.findUsuariosByEmpresa(empresa.getIdEmpresa());
        List<UsuarioEntity> usuariosBloqueados = usuarioEntityRepository.findUsuariosBloqueadosByEmpresa(empresa.getIdEmpresa());
        model.addAttribute("usuariosAsociados", usuariosAsociados);
        model.addAttribute("usuariosBloqueados", usuariosBloqueados);
        model.addAttribute("filtroAsociadosEmpresa", new FiltroAsociadosEmpresa());


        return "gestionSociosYAut";
    }

    @PostMapping("/filtrarAsociados")
    public String doFiltrarAsociados(@ModelAttribute("filtroAsociadosEmpresa")FiltroAsociadosEmpresa filtro,
                                     Model model, HttpSession session){
        return procesarFiltradoAsociados(filtro, model, session);
    }

    @GetMapping("/filtrarAsociados")
    public String procesarFiltradoAsociados(FiltroAsociadosEmpresa filtro, Model model, HttpSession session){
        EmpresaEntity empresa = (EmpresaEntity) session.getAttribute("empresa");
        List<UsuarioEntity> usuariosAsociados = usuarioEntityRepository.findUsuariosByEmpresa(empresa.getIdEmpresa());
        List<UsuarioEntity> usuariosBloqueados = usuarioEntityRepository.findUsuariosBloqueadosByEmpresa(empresa.getIdEmpresa());
        model.addAttribute("filtroAsociadosEmpresa", filtro);

        if (filtro == null || filtro.getNombreRol().equals("ninguno")){
            return "redirect:/empresa/";
        } else if (filtro.getNombreRol().equals("socio")){
            RolEntity rolSocio = rolEntityRepository.findByNombre("socio").orElseThrow();
            usuariosAsociados = usuarioEntityRepository.findUsuariosByEmpresaAndRol(empresa.getIdEmpresa(), rolSocio);
        } else if (filtro.getNombreRol().equals("autorizado")){
            RolEntity rolAutorizado = rolEntityRepository.findByNombre("autorizado").orElseThrow();
            usuariosAsociados = usuarioEntityRepository.findUsuariosByEmpresaAndRol(empresa.getIdEmpresa(), rolAutorizado);
        }

        model.addAttribute("usuariosAsociados", usuariosAsociados);


        return "gestionSociosYAut";
    }

    @GetMapping("/cambiarRol")
    public String doCambiarRoles(@RequestParam("id") Integer id, @RequestParam("rol") Integer idRol, HttpSession session) {
        UsuarioEntity user = (UsuarioEntity) session.getAttribute("usuario");
        if (user == null) return "redirect:/logout";

        UsuarioEntity usuario = usuarioEntityRepository.getById(id);
        RolusuarioEntity rolusuario = usuario.getRolusuariosById().get(0);
        RolEntity rolActual = rolEntityRepository.getById(idRol);
        RolEntity rolNuevo = null;
        Optional<RolEntity> rolOptional = rolActual.getNombre().equals("socio") ?
                rolEntityRepository.findByNombre("autorizado") :
                rolEntityRepository.findByNombre("socio");
        if (rolOptional.isPresent()) rolNuevo = rolOptional.get();
        rolusuario.setRolByIdrol(rolNuevo);
        rolusuarioEntityRepository.save(rolusuario);
        return "redirect:/empresa/";
    }

    @GetMapping("/bloquearUsuario")
    public String doBloquearUsuario(@RequestParam("id") Integer id, HttpSession session) {
        UsuarioEntity user = (UsuarioEntity) session.getAttribute("usuario");
        if (user == null) return "redirect:/logout";

        UsuarioEntity usuario = usuarioEntityRepository.getById(id);
        usuario.getRolusuariosById().get(0).setBloqueado((byte) 1);
        usuarioEntityRepository.save(usuario);
        return "redirect:/empresa/";
    }

    @GetMapping("/datosEmpresa")
    public String doVerDatosEmpresa(Model model, HttpSession session) {
        UsuarioEntity user = (UsuarioEntity) session.getAttribute("usuario");
        if (user == null) return "redirect:/logout";

        EmpresaEntity empresa = (EmpresaEntity) session.getAttribute("empresa");
        if (empresa == null) return "redirect:/menu";
        model.addAttribute("empresaAEditar", empresa);

        Double saldo = empresa.getClienteByCliente().getCuentasByIdCliente().get(0).getSaldo();
        model.addAttribute("saldo", saldo);

        return "datosEmpresa";
    }

    @PostMapping("/guardar")
    public String doGuardar(Model model, HttpSession session, @ModelAttribute("empresaAEditar") EmpresaEntity empresa, RedirectAttributes redirectAttributes) {
        UsuarioEntity user = (UsuarioEntity) session.getAttribute("usuario");
        if (user == null) return "redirect:/logout";

        this.empresaEntityRepository.save(empresa);
        this.direccionEntityRepository.save(empresa.getClienteByCliente().getDireccionByDireccion());
        redirectAttributes.addFlashAttribute("mensaje", "Los datos se han guardado correctamente");

        model.addAttribute("empresaAEditar", empresa);
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
        session.setAttribute("empresa", empresa);
        return "redirect:/empresa/datosEmpresa";
    }

    @GetMapping("/operaciones")
    public String doMostrarOperaciones(Model model, HttpSession session) {
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
        if (usuario == null) return "redirect:/logout";
        EmpresaEntity empresa = (EmpresaEntity) session.getAttribute("empresa");
        if (empresa == null)
            return "redirect:/cliente/verOperaciones";

        Integer numCuenta = empresa.getClienteByCliente().getCuentasByIdCliente().get(0).getNumCuenta();
        List<OperacionEntity> operaciones = operacionEntityRepository.getOperacionesByNumeroCuenta(numCuenta);


        model.addAttribute("filtroOpEmpresa", new FiltroOperacionesEmpresa());
        model.addAttribute("ordenarOpEmpresa", new OrdenarOperacionesEmpresa());
        model.addAttribute("operaciones", operaciones);

        return "operaciones";
    }

    @PostMapping("/filtrar")
    public String doFiltrar(@ModelAttribute("ordenarOpEmpresa") OrdenarOperacionesEmpresa orden, @ModelAttribute("filtroOpEmpresa") FiltroOperacionesEmpresa filtro,
                            Model model, HttpSession session) {
        return this.procesarFiltrado(orden, filtro, model, session);
    }

    @GetMapping("/filtrar")
    protected String procesarFiltrado(OrdenarOperacionesEmpresa orden, FiltroOperacionesEmpresa filtro, Model model, HttpSession session) {
        EmpresaEntity empresa = (EmpresaEntity) session.getAttribute("empresa");
        Integer numCuenta = empresa.getClienteByCliente().getCuentasByIdCliente().get(0).getNumCuenta();
        List<OperacionEntity> operaciones = operacionEntityRepository.getOperacionesByNumeroCuenta(numCuenta);
        String urlTo = "operaciones";

        if (filtro == null || (filtro.getFechaFiltro() == null && filtro.getNombreOperacion().equals("ninguno"))) {
            model.addAttribute("operaciones",operaciones);
            return "redirect:/empresa/operaciones";
        } else if (!filtro.getNombreOperacion().equals("ninguno") && filtro.getFechaFiltro() == null) {
            String nombre = filtro.getNombreOperacion();
                if (nombre.equals("Transferencia")) {
                    List<OperacionEntity> transferencias = new ArrayList<>();
                    for (OperacionEntity operacion : operaciones) {
                        if (!operacion.getTransferenciasByIdOperacion().isEmpty()) {
                            transferencias.add(operacion);
                        }
                    }
                    operaciones.clear();
                    model.addAttribute("operaciones", transferencias);
                } else if (nombre.equals("Cambio de divisa")) {
                    List<OperacionEntity> cambioDivisas = new ArrayList<>();
                    for (OperacionEntity operacion : operaciones) {
                        if (!operacion.getCambDivisasByIdOperacion().isEmpty()) {
                            cambioDivisas.add(operacion);
                        }
                    }
                    operaciones.clear();
                    model.addAttribute("operaciones", cambioDivisas);
                } else {
                    List<OperacionEntity> extracciones = new ArrayList<>();
                    for (OperacionEntity operacion : operaciones) {
                        if (!operacion.getExtraccionsByIdOperacion().isEmpty()) {
                            extracciones.add(operacion);
                        }
                    }
                    operaciones.clear();
                    model.addAttribute("operaciones", extracciones);
                }

        } else if (!filtro.getNombreOperacion().equals("ninguno") && filtro.getFechaFiltro() != null) {
            operaciones = operacionEntityRepository.getOperacionesByNumeroCuentaAndFecha(numCuenta, Date.valueOf(filtro.getFechaFiltro()));
            String nombre = filtro.getNombreOperacion();
            if (nombre.equals("Transferencia")) {
                List<OperacionEntity> transferencias = new ArrayList<>();
                for (OperacionEntity operacion : operaciones) {
                    if (!operacion.getTransferenciasByIdOperacion().isEmpty()) {
                        transferencias.add(operacion);
                    }
                }
                operaciones.clear();
                model.addAttribute("operaciones", transferencias);
            } else if (nombre.equals("Cambio de divisa")) {
                List<OperacionEntity> cambioDivisas = new ArrayList<>();
                for (OperacionEntity operacion : operaciones) {
                    if (!operacion.getCambDivisasByIdOperacion().isEmpty()) {
                        cambioDivisas.add(operacion);
                    }
                }
                operaciones.clear();
                model.addAttribute("operaciones", cambioDivisas);
            } else {
                List<OperacionEntity> extracciones = new ArrayList<>();
                for (OperacionEntity operacion : operaciones) {
                    if (!operacion.getExtraccionsByIdOperacion().isEmpty()) {
                        extracciones.add(operacion);
                    }
                }
                operaciones.clear();
                model.addAttribute("operaciones", extracciones);
            }
        } else if (filtro.getNombreOperacion().equals("ninguno") && filtro.getFechaFiltro() != null) {
            List<OperacionEntity> operacionesFecha = operacionEntityRepository.getOperacionesByNumeroCuentaAndFecha(numCuenta, Date.valueOf(filtro.getFechaFiltro()));
            operaciones.clear();
            model.addAttribute("operaciones", operacionesFecha);
        }

        model.addAttribute("filtroOpEmpresa", filtro);
        model.addAttribute("ordenOpEmpresa", orden);
        return urlTo;
    }


    @PostMapping("/ordenar")
    public String doOrdenar(@ModelAttribute("filtroOpEmpresa") FiltroOperacionesEmpresa filtro, @ModelAttribute("ordenarOpEmpresa") OrdenarOperacionesEmpresa orden,
                            Model model, HttpSession session) {
        return this.procesarOrdenado(filtro, orden, model, session);
    }

    protected String procesarOrdenado(FiltroOperacionesEmpresa filtro, OrdenarOperacionesEmpresa orden,
                                      Model model, HttpSession session) {

        EmpresaEntity empresa = (EmpresaEntity) session.getAttribute("empresa");
        Integer numCuenta = empresa.getClienteByCliente().getCuentasByIdCliente().get(0).getNumCuenta();
        List<OperacionEntity> operaciones = operacionEntityRepository.getOperacionesByNumeroCuenta(numCuenta);

        if (orden == null || orden.getOpcionSeleccionada().equals("ninguno")){
            model.addAttribute("operaciones",operaciones);
            return "redirect:/empresa/operaciones";
        } else if (orden.getOpcionSeleccionada().equals("cantidad")){
            List<OperacionEntity> operacionesOrdenadas = operacionEntityRepository.getOperacionesByNumeroCuentaOrderByCantidad(numCuenta);
            operaciones.clear();
            model.addAttribute("operaciones", operacionesOrdenadas);
        }

        model.addAttribute("ordenarOpEmpresa", orden);
        model.addAttribute("filtroOpEmpresa", filtro);

        return "operaciones";
    }

    @GetMapping("/solicitarDesbloqueo")
    public String doSolicitudDesbloqueo(Model model, HttpSession session) {
        UsuarioEntity user = (UsuarioEntity) session.getAttribute("usuario");
        if (user == null) return "redirect:/logout";

        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
        usuario.getRolusuariosById().get(0).setBloqueado((byte) 2);
        usuarioEntityRepository.saveAndFlush(usuario);
        return "redirect:/menu";
    }

}
