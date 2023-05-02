package es.proyectotaw.banca.bancapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GestorController {

    @GetMapping("/gestor")
    public String doInicializarPantalla(){
        return "gestorView";
    }

}
