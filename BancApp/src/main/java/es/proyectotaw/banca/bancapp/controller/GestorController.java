package es.proyectotaw.banca.bancapp.controller;

import es.proyectotaw.banca.bancapp.dao.ClienteEntityRepository;
import es.proyectotaw.banca.bancapp.dao.DireccionEntityRepository;
import es.proyectotaw.banca.bancapp.dao.EmpresaEntityRepository;
import es.proyectotaw.banca.bancapp.entity.ClienteEntity;
import es.proyectotaw.banca.bancapp.entity.DireccionEntity;
import es.proyectotaw.banca.bancapp.entity.EmpresaEntity;
import es.proyectotaw.banca.bancapp.ui.FiltroClientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@SuppressWarnings("SpringMVCViewInspection")
@Controller
@RequestMapping("/gestor")
public class GestorController {

    private final int DIAS_INACTIVIDAD = 30; //Segun la politica del banco (US-21)

    @Autowired
    protected ClienteEntityRepository clienteRepository;

    /*Zona destinada a controlar la pantalla de gestor*/
    @GetMapping("/")
    public String doInicializarPantallaPrincipal(Model model){
        model.addAttribute("clientes", this.clienteRepository.obtenerClientesDadosDeAlta());
        FiltroClientes filtroClientes = null;

        return  procesarFiltradoClientes(model, filtroClientes);
    }

    @PostMapping("/filtrar")
    public String doFiltrar(@ModelAttribute("filtroClientes") FiltroClientes filtroClientes,
                            Model model){
        return procesarFiltradoClientes(model, filtroClientes);
    }

    private String procesarFiltradoClientes(Model model, FiltroClientes filtroClientes){
        model.addAttribute("filtroCLientes", new FiltroClientes());
        return "gestorView";
    }

    /*Zona destinada a los botones de acciones*/
    @GetMapping("/solicitudesAlta")
    public String doMostrarSolicitudesAlta(){
        return "solicitudesAltaGestorView";
    }

    @GetMapping("/cuentasInactivas")
    public String doMostrarCuentasInactivas(){
        return "solicitudesAltaGestorView";
    }

    @GetMapping("/seguridadTransferencias")
    public String doMostrarSeguridadCuentas(){
        return "seguridadGestorView";
    }

    /*Zona destinada a la información de los clientes en tabla*/
    @GetMapping("/particular")
    public String doMostrarParticular(){
        return "gestorParticularView";
    }

    @GetMapping("/empresa")
    public String doMostrarEmpresa(){
        return "gestorEmpresaView";
    }
}
