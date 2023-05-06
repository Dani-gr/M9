package es.proyectotaw.banca.bancapp.controller;

import es.proyectotaw.banca.bancapp.dao.ClienteEntityRepository;
import es.proyectotaw.banca.bancapp.dao.DireccionEntityRepository;
import es.proyectotaw.banca.bancapp.dao.EmpresaEntityRepository;
import es.proyectotaw.banca.bancapp.entity.ClienteEntity;
import es.proyectotaw.banca.bancapp.entity.DireccionEntity;
import es.proyectotaw.banca.bancapp.entity.EmpresaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@SuppressWarnings("SpringMVCViewInspection")
@Controller
@RequestMapping("/gestor")
public class GestorController {

    @Autowired
    protected ClienteEntityRepository clienteEntityRepository;

    @Autowired
    protected EmpresaEntityRepository empresaEntityRepository;

    @Autowired
    protected DireccionEntityRepository direccionEntityRepository;

    @GetMapping("/")
    String doInicializarPantalla(Model model) {
        model.addAttribute("clientes", this.clienteEntityRepository.findAll());

        DireccionEntity d = new DireccionEntity();
        d.setCiudad("dwdew");
        d.setCalle("deww");
        d.setRegion("cdscs");
        d.setPais("frefer");
        d.setPlantaPuertaOficina("deded");
        d.setNumero(656);
        d.setCodpostal("frefrefre");

        this.direccionEntityRepository.save(d);

        ClienteEntity c = new ClienteEntity();
        c.setDireccionByDireccion(d);
        this.clienteEntityRepository.save(c);

        EmpresaEntity e = new EmpresaEntity();
        e.setCif(56456);
        e.setClienteByCliente(c);
        this.empresaEntityRepository.save(e);

        return "gestorView";
    }


    @GetMapping("/delete")
    String doBorrar(Model model) {
        //TODO
        return "redirect:/gestor/";
    }
}
