package es.proyectotaw.banca.bancapp.controller;

import es.proyectotaw.banca.bancapp.dao.ClienteEntityRepository;
import es.proyectotaw.banca.bancapp.dao.DireccionEntityRepository;
import es.proyectotaw.banca.bancapp.dao.EmpresaEntityRepository;
import es.proyectotaw.banca.bancapp.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;
import java.time.Clock;
import java.time.Instant;

@Controller
@RequestMapping("/gestor")
public class GestorController {

    @Autowired
    protected ClienteEntityRepository clienteRepository;

    @Autowired
    protected EmpresaEntityRepository empresaEntityRepository;

    @Autowired
    protected DireccionEntityRepository direccionRepository;

    @GetMapping("/")
    String doInicializarPantalla(Model model){
        model.addAttribute("clientes", this.clienteRepository.findAll());

        DireccionEntity d = new DireccionEntity();
        d.setCiudad("dwdew");
        d.setCalle("deww");
        d.setRegion("cdscs");
        d.setPais("frefer");
        d.setPlantaPuertaOficina("deded");
        d.setNumero(656);
        d.setCodpostal("frefrefre");

        this.direccionRepository.save(d);

        ClienteEntity c = new ClienteEntity();
        c.setDireccionByDireccion(d);
        this.clienteRepository.save(c);

        EmpresaEntity e = new EmpresaEntity();
        e.setCif(56456);
        e.setId(c.getIdCliente());
        this.empresaEntityRepository.save(e);

        return "gestorView";
    }



    @GetMapping("/delete")
    String doBorrar(Model model){

        return "redirect:/gestor/";
    }
}
