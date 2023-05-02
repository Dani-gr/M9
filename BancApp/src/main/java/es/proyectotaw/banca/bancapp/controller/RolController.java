package es.proyectotaw.banca.bancapp.controller;

import es.proyectotaw.banca.bancapp.dao.RolEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RolController {
    @Autowired
    RolEntityRepository rolEntityRepository;

    @GetMapping("/")
    String doTest(Model model){
        model.addAttribute("roles", rolEntityRepository.findAll());
        return "vistaRoles";
    }
}
