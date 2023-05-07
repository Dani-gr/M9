package es.proyectotaw.banca.bancapp.controller;

import es.proyectotaw.banca.bancapp.dao.ClienteEntityRepository;
import es.proyectotaw.banca.bancapp.dao.CuentaEntityRepository;
import es.proyectotaw.banca.bancapp.dao.CuentasSospechosasRepository;
import es.proyectotaw.banca.bancapp.dao.TransferenciaEntityRepository;
import es.proyectotaw.banca.bancapp.entity.ClienteEntity;
import es.proyectotaw.banca.bancapp.entity.CuentaEntity;
import es.proyectotaw.banca.bancapp.ui.FiltroClientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@SuppressWarnings("SpringMVCViewInspection")
@Controller
@RequestMapping("/gestor")
public class GestorController {

    private final int DIAS_INACTIVIDAD = 30; //Segun la politica del banco (US-21)

    @Autowired
    protected ClienteEntityRepository clienteEntityRepository;

    @Autowired
    protected CuentaEntityRepository cuentaEntityRepository;

    @Autowired
    protected CuentasSospechosasRepository cuentasSospechosasRepository;

    @Autowired
    protected TransferenciaEntityRepository transferenciaEntityRepository;


    /*Zona destinada a controlar la pantalla de gestor*/
    @GetMapping("/")
    public String doInicializarPantallaPrincipal(Model model, HttpSession session){
        return  procesarFiltradoClientes(model, null, session);
    }

    @PostMapping("/filtrar")
    public String doFiltrar(@ModelAttribute("filtroClientes") FiltroClientes filtroClientes,
                            Model model, HttpSession session){
        return procesarFiltradoClientes(model, filtroClientes, session);
    }

    private String procesarFiltradoClientes(Model model, FiltroClientes filtroClientes,  HttpSession session){

        if(session.getAttribute("usuario") == null) return "redirect:/";

        List<ClienteEntity> clientes = this.clienteEntityRepository.findAll();
        filtroClientes = new FiltroClientes();

        /*if(filtroClientes == null || (filtroClientes.getLimInfSaldo() == null && filtroClientes.getCiudad() == null) ){
            filtroClientes = new FiltroClientes();
            clientes = this.clienteEntityRepository.findAll();
        } else if (filtroClientes.getCiudad() == null) {
            clientes = this.clienteEntityRepository.obtenerClientesPorSaldoMinimo(filtroClientes.getLimInfSaldo());
        } else if (filtroClientes.getLimInfSaldo() == null) {
            clientes = this.clienteEntityRepository.obtenerClientesPorCiudad(filtroClientes.getCiudad().toLowerCase());
        }else{
            clientes = this.clienteEntityRepository
                    .obtenerCLientesPorSaldoMinimoYCiudad(filtroClientes.getLimInfSaldo(), filtroClientes.getCiudad());
        }*/

        model.addAttribute("clientes", clientes);
        model.addAttribute("filtroCLientes", filtroClientes);
        return "gestorView";
    }

    /*Zona destinada a los botones de acciones*/
    @GetMapping("/solicitudesAlta")
    public String doMostrarSolicitudesAlta(Model model, HttpSession session){
        if(session.getAttribute("usuario") == null) return "redirect:/";

        model.addAttribute("solicitantes", this.clienteEntityRepository.obtenerAspirantesCliente());
        return "solicitudesAltaGestorView";
    }

    @GetMapping("/solicitudesAlta/verSolicitante")
    public String doMostrarSolicitanteACliente(@Param("id") Integer id, Model model, HttpSession session){
        if(session.getAttribute("usuario") == null) return "redirect:/";

        ClienteEntity solicitante = this.clienteEntityRepository.findById(id).orElse(null);

        model.addAttribute("isEmpresa", !solicitante.getEmpresasByIdCliente().isEmpty());
        model.addAttribute("solicitante", solicitante);
        return "solicitanteGestorView";
    }

    @GetMapping("/asignarCuenta")
    public String doAsignarCuenta(@RequestParam("id") Integer id, HttpSession session){
        if(session.getAttribute("usuario") == null) return "redirect:/";

        ClienteEntity c = this.clienteEntityRepository.findById(id).orElse(null);

        CuentaEntity cuenta = new CuentaEntity();
        cuenta.setClienteByCliente(c);
        cuenta.setSaldo(0.0);
        cuenta.setActiva((byte) 1);
        this.cuentaEntityRepository.save(cuenta);

        return "redirect:/gestor/solicitudesAlta";
    }

    @GetMapping("/borrarCliente")
    public String doBorrarCliente(@RequestParam("id") Integer id, HttpSession session){
        if(session.getAttribute("usuario") == null) return "redirect:/";

        ClienteEntity c = this.clienteEntityRepository.findById(id).orElse(null);
        this.clienteEntityRepository.delete(c);

        return "redirect:/gestor/solicitudesAlta";
    }

    @GetMapping("/cuentasInactivas")
    public String doMostrarCuentasInactivas(HttpSession session, Model model){
        if(session.getAttribute("usuario") == null) return "redirect:/";

        model.addAttribute("inactivas", this.cuentaEntityRepository.findAll());
        return "cuentasInactivasGestorView";
    }

    @GetMapping("/seguridadTransferencias")
    public String doMostrarSeguridadCuentas(Model model, HttpSession session){
        if(session.getAttribute("usuario") == null) return "redirect:/";

        List<String> sospechosas = this.cuentasSospechosasRepository.obtenerIbans();
        model.addAttribute("sospechosas", this.cuentasSospechosasRepository.findAll());
        model.addAttribute("transferencias", this.transferenciaEntityRepository.filtrarPorDestinoSospechoso(sospechosas));

        return "seguridadGestorView";
    }

    @GetMapping("/bloquearPorTransferencia")
    public String bloquearSospechosa(@Param("cuenta") Integer cuenta){
        setEstadoCuenta(cuenta,  (byte)0);
        return "redirect:/gestor/seguridadTransferencias";
    }

    /*Bloqueo/desbloqueo según la vista a la que se quiera retornar*/

    private void setEstadoCuenta(Integer id, byte estado){
        CuentaEntity cuenta = this.cuentaEntityRepository.findById(id).orElse(null);
        cuenta.setActiva(estado);
        this.cuentaEntityRepository.save(cuenta);
    }



    /*Zona destinada a la información de los clientes en tabla*/
    @GetMapping("/particular")
    public String doMostrarParticular(@RequestParam("id") Integer id, Model model, HttpSession session){
        if(session.getAttribute("usuario") == null) return "redirect:/";

        ClienteEntity c = this.clienteEntityRepository.findById(id).orElse(null);

        model.addAttribute("particular", c);
        return "gestorParticularView";
    }

    @GetMapping("/empresa")
    public String doMostrarEmpresa(@RequestParam("id") Integer id, Model model, HttpSession session){
        if(session.getAttribute("usuario") == null) return "redirect:/";

        ClienteEntity c = this.clienteEntityRepository.findById(id).orElse(null);

        model.addAttribute("empresa", c);
        return "gestorEmpresaView";
    }
}
