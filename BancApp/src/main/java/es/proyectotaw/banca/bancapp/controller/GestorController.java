package es.proyectotaw.banca.bancapp.controller;

import es.proyectotaw.banca.bancapp.dao.ClienteEntityRepository;
import es.proyectotaw.banca.bancapp.dao.DireccionEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gestor")
public class GestorController {

    @Autowired
    protected ClienteEntityRepository clienteRepository;

    @Autowired
    protected DireccionEntityRepository direccionRepository;

    @GetMapping("/")
    String doInicializarPantalla(Model model){
        model.addAttribute("clientes", this.clienteRepository.findAll());
        return "gestorView";
    }



    @GetMapping("/delete")
    String doBorrar(Model model){

        return "redirect:/gestor/";
    }
}
