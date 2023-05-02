package es.proyectotaw.banca.bancapp.controller;

import es.proyectotaw.banca.bancapp.dao.ClienteEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gestor")
public class GestorController {

    @Autowired
    ClienteEntityRepository clienteRepository;

    @GetMapping("/")
    String doInicializarPantalla(Model model){
        model.addAttribute("clientes", this.clienteRepository.findAll());
        return "gestorView";
    }

}
