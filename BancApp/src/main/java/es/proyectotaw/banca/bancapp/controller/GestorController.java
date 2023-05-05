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

    @GetMapping("/")
    public String doInicializarPantallaPrincipal(Model model){
        model.addAttribute("clientes", this.clienteRepository.findAll());

        return "gestorView";
    }




}
