package es.proyectotaw.banca.bancapp.controller;

import es.proyectotaw.banca.bancapp.dao.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RolController{

    @Autowired
    protected RolRepository rolRepository;

    @GetMapping("/")
    public String doShowRoles(Model model){
        model.addAttribute("roles", this.rolRepository.findAll());
        return "roleView";
    }
}
