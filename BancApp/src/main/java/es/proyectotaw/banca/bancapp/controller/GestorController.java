package es.proyectotaw.banca.bancapp.controller;

import es.proyectotaw.banca.bancapp.dao.*;
import es.proyectotaw.banca.bancapp.entity.*;
import es.proyectotaw.banca.bancapp.ui.FiltroClientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Carlos Castaño Moreno
 */

@SuppressWarnings({"ClassWithTooManyMethods", "SpringMVCViewInspection"})
@Controller
@RequestMapping("/gestor")
public class GestorController {
    @Autowired
    protected ClienteEntityRepository clienteEntityRepository;

    @Autowired
    protected CuentaEntityRepository cuentaEntityRepository;

    @Autowired
    protected CuentasSospechosasRepository cuentasSospechosasRepository;

    @Autowired
    protected TransferenciaEntityRepository transferenciaEntityRepository;

    @Autowired
    protected RolusuarioEntityRepository rolusuarioEntityRepository;

    @Autowired
    protected UsuarioEntityRepository usuarioEntityRepository;


    /*Zona destinada a controlar la pantalla de gestor*/
    @GetMapping("/")
    public String doInicializarPantallaPrincipal(Model model, HttpSession session) {
        return procesarFiltradoClientes(model, null, session);
    }

    @PostMapping("/filtrar")
    public String doFiltrar(@ModelAttribute("filtroClientes") FiltroClientes filtroClientes,
                            Model model, HttpSession session) {
        if (filtroClientes.getLimInfSaldo() == null && filtroClientes.getCiudad().isEmpty()) {
            return "redirect:/gestor/";
        }
        return procesarFiltradoClientes(model, filtroClientes, session);
    }

    private String procesarFiltradoClientes(Model model, FiltroClientes filtroClientes, HttpSession session) {

        if (session.getAttribute("usuario") == null) return "redirect:/logout";

        List<ClienteEntity> clientes;

        if (filtroClientes == null || (filtroClientes.getLimInfSaldo() == null && filtroClientes.getCiudad().isEmpty())) {
            filtroClientes = new FiltroClientes();
            clientes = this.clienteEntityRepository.obtenerClientesDadosDeAlta();
        } else if (filtroClientes.getCiudad().isEmpty()) {
            clientes = this.clienteEntityRepository.obtenerCLientesPorSaldoMinimo(filtroClientes.getLimInfSaldo());
        } else if (filtroClientes.getLimInfSaldo() == null) {
            clientes = this.clienteEntityRepository.obtenerClientesPorCiudad(filtroClientes.getCiudad());
        } else {
            clientes = this.clienteEntityRepository
                    .obtenerCLientesPorSaldoMinimoYCiudad(filtroClientes.getLimInfSaldo(), filtroClientes.getCiudad());
        }

        model.addAttribute("clientes", clientes);
        model.addAttribute("filtroCLientes", filtroClientes);
        return "gestorView";
    }

    /*Zona destinada a los botones de acciones*/
    @GetMapping("/solicitudesAlta")
    public String doMostrarSolicitudesAlta(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) return "redirect:/logout";

        model.addAttribute("solicitantes", this.clienteEntityRepository.obtenerAspirantesCliente());
        return "solicitudesAltaGestorView";
    }

    @GetMapping("/verSolicitante")
    public String doMostrarSolicitanteACliente(@RequestParam("id") Integer id, Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) return "redirect:/logout";

        ClienteEntity solicitante = this.clienteEntityRepository.findById(id).orElse(null);

        boolean isEmpresa = false;
        if (solicitante != null) {
            isEmpresa = !solicitante.getEmpresasByIdCliente().isEmpty();
        }
        model.addAttribute("isEmpresa", isEmpresa);
        model.addAttribute("solicitante", solicitante);
        return "solicitanteGestorView";
    }

    @GetMapping("/asignarCuenta")
    public String doAsignarCuenta(@RequestParam("id") Integer id, HttpSession session) {
        if (session.getAttribute("usuario") == null) return "redirect:/logout";

        ClienteEntity c = this.clienteEntityRepository.findById(id).orElse(null);

        CuentaEntity cuenta = new CuentaEntity();
        cuenta.setClienteByCliente(c);
        cuenta.setSaldo(0.0);
        cuenta.setActiva((byte) 1);
        this.cuentaEntityRepository.save(cuenta);

        return "redirect:/gestor/solicitudesAlta";
    }

    @GetMapping("/borrarCliente")
    public String doBorrarCliente(@RequestParam("id") Integer id, HttpSession session) {
        if (session.getAttribute("usuario") == null) return "redirect:/logout";

        ClienteEntity c = this.clienteEntityRepository.findById(id).orElse(null);

        if (c != null) {
            List<UsuarioEntity> usuariosAsociados = c.getUsuariosByIdCliente().stream()
                    .filter(u -> u.getRolusuariosById() != null && !u.getRolusuariosById().isEmpty() &&
                            u.getRolusuariosById().stream().anyMatch(r -> r.getEmpresaByIdempresa() != null) ).toList();
            for (UsuarioEntity u : usuariosAsociados) {
                u.setClienteByCliente(null);
            }
            usuarioEntityRepository.saveAll(usuariosAsociados);

            this.clienteEntityRepository.delete(c);
        }

        return "redirect:/gestor/solicitudesAlta";
    }

    @GetMapping("/cuentasInactivas")
    public String doMostrarCuentasInactivas(HttpSession session, Model model) {
        if (session.getAttribute("usuario") == null) return "redirect:/logout";

        model.addAttribute("inactivas", this.cuentaEntityRepository.getCuentasConUltimaOperacionHace30Dias());
        return "cuentasInactivasGestorView";
    }

    @GetMapping("/bloquearInactiva")
    public String doBloquearInactiva(HttpSession session, @RequestParam("id") Integer id) {
        if (session.getAttribute("usuario") == null) return "redirect:/logout";

        this.setEstadoCuenta(id, (byte) 0);
        return "redirect:/gestor/cuentasInactivas";
    }

    @GetMapping("/seguridadTransferencias")
    public String doMostrarSeguridadCuentas(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) return "redirect:/logout";

        List<String> sospechosas = this.cuentasSospechosasRepository.obtenerIbans();
        model.addAttribute("sospechosas", this.cuentasSospechosasRepository.findAll());
        model.addAttribute("transferencias", this.transferenciaEntityRepository.filtrarPorDestinoSospechoso(sospechosas));

        return "seguridadGestorView";
    }

    @PostMapping("/addSospechosa")
    public String addSospechosa(@RequestParam("ibanSospechoso") String ibanSospechoso, HttpSession session) {
        if (session.getAttribute("usuario") == null) return "redirect:/logout";

        CuentasSospechosasEntity sospechosa = new CuentasSospechosasEntity();
        sospechosa.setIban(ibanSospechoso);
        this.cuentasSospechosasRepository.save(sospechosa);
        return "redirect:/gestor/seguridadTransferencias";
    }

    @GetMapping("/borrarSospechosa")
    public String removeSospechosa(@RequestParam("id") Integer id, HttpSession session) {
        if (session.getAttribute("usuario") == null) return "redirect:/logout";

        this.cuentasSospechosasRepository.findById(id).ifPresent(sospechosa -> this.cuentasSospechosasRepository.delete(sospechosa));
        return "redirect:/gestor/seguridadTransferencias";
    }

    @GetMapping("/bloquearPorTransferencia")
    public String bloquearSospechosa(@RequestParam("cuenta") Integer cuenta, HttpSession session) {
        if (session.getAttribute("usuario") == null) return "redirect:/logout";

        setEstadoCuenta(cuenta, (byte) 0);
        return "redirect:/gestor/seguridadTransferencias";
    }

    /*Bloqueo/desbloqueo según la vista a la que se quiera retornar*/

    private void setEstadoCuenta(Integer id, byte estado) {
        CuentaEntity cuenta = this.cuentaEntityRepository.findById(id).orElse(null);
        if (cuenta != null) {
            cuenta.setActiva(estado);
            this.cuentaEntityRepository.save(cuenta);
        }
    }

    @GetMapping("/solicitudesActivacion")
    public String doMostrarSolicitudesActivacion(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) return "redirect:/logout";

        model.addAttribute("cuentas", this.cuentaEntityRepository.getCuentasPendientesDeActivar());
        return "solicitudesActivacionGestorView";
    }

    @GetMapping("/activar")
    public String doActivarCuenta(@RequestParam("cuenta") Integer cuenta, HttpSession session) {
        if (session.getAttribute("usuario") == null) return "redirect:/logout";

        setEstadoCuenta(cuenta, (byte) 1);
        return "redirect:/gestor/solicitudesActivacion";
    }

    @GetMapping("/solicitudesDesbloqueoEmpresa")
    public String doMostrarSolicitudesDesbloqueoEmpresa(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) return "redirect:/logout";

        model.addAttribute("rolesSolicitantes", this.rolusuarioEntityRepository.findRolesSolicitantesDeActivar());
        return "solicitudesDesbloqueoEmpresaGestorView";
    }

    @GetMapping("/desbloquearEnEmpresa")
    public String doDesbloquearUsuarioEmpresa(@RequestParam("rolUsuario") Integer rolUsuario, HttpSession session) {
        if (session.getAttribute("usuario") == null) return "redirect:/logout";

        RolusuarioEntity r = this.rolusuarioEntityRepository.findById(rolUsuario).orElse(null);
        if (r != null) {
            r.setBloqueado((byte) 0);
            this.rolusuarioEntityRepository.save(r);
        }
        return "redirect:/gestor/solicitudesDesbloqueoEmpresa";
    }

    /*Zona destinada a la información de los clientes en tabla*/
    @GetMapping("/particular")
    public String doMostrarParticular(@RequestParam("id") Integer id, Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) return "redirect:/logout";

        ClienteEntity c = this.clienteEntityRepository.findById(id).orElse(null);

        model.addAttribute("particular", c);
        return "gestorParticularView";
    }

    @GetMapping("/empresa")
    public String doMostrarEmpresa(@RequestParam("id") Integer id, Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) return "redirect:/logout";

        ClienteEntity c = this.clienteEntityRepository.findById(id).orElse(null);

        model.addAttribute("empresa", c);
        return "gestorEmpresaView";
    }

    @GetMapping("/operaciones")
    public String doMostrarOperaciones(@RequestParam("id") Integer id, Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) return "redirect:/logout";

        ClienteEntity c = this.clienteEntityRepository.findById(id).orElse(null);
        model.addAttribute("cliente", c);
        model.addAttribute("operaciones", c.getCuentasByIdCliente().get(0).getOperacionsByNumCuenta());
        return "OperacionesClienteGestorView";
    }


    @GetMapping("/operaciones/ordenar")
    public String doMostrarOperacionesOrdenadas(@RequestParam("id") Integer id, Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) return "redirect:/logout";

        ClienteEntity c = this.clienteEntityRepository.findById(id).orElse(null);
        model.addAttribute("cliente", c);

        List<OperacionEntity> operaciones = c.getCuentasByIdCliente().get(0).getOperacionsByNumCuenta();
        operaciones.sort((o1, o2) -> o2.getFecha().compareTo(o1.getFecha()));

        model.addAttribute("operaciones", operaciones);
        return "OperacionesClienteGestorView";
    }

    @PostMapping("/operaciones/filtrar")
    public String doFiltrarOperaciones(@RequestParam("id") Integer id, Model model, HttpSession session,
                                       @RequestParam("tipo") String tipo) {
        if (session.getAttribute("usuario") == null) return "redirect:/logout";

        ClienteEntity c = this.clienteEntityRepository.findById(id).orElse(null);

        List<OperacionEntity> ops = c.getCuentasByIdCliente().get(0).getOperacionsByNumCuenta();
        List<OperacionEntity> filtrada = switch (tipo) {
            case "t" -> ops.stream().filter(op -> op.getTransferenciasByIdOperacion() != null &&
                    !op.getTransferenciasByIdOperacion().isEmpty() && op.getTransferenciasByIdOperacion().get(0) != null).toList();
            case "cd" -> ops.stream().filter(op -> op.getCambDivisasByIdOperacion() != null &&
                    !op.getCambDivisasByIdOperacion().isEmpty() && op.getCambDivisasByIdOperacion().get(0) != null).toList();
            case "e" -> ops.stream().filter(op -> op.getExtraccionsByIdOperacion() != null &&
                    !op.getExtraccionsByIdOperacion().isEmpty() && op.getExtraccionsByIdOperacion().get(0) != null).toList();
            default -> ops;
        };

        model.addAttribute("cliente", c);
        model.addAttribute("operaciones", filtrada);
        return "OperacionesClienteGestorView";
    }

}
