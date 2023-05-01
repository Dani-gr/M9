package es.proyectotaw.banca.bancapp.controller;

import es.proyectotaw.banca.bancapp.dao.RolEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@SuppressWarnings("SpringMVCViewInspection")
@Controller
@RequestMapping("/")
public class RolController {
    @Autowired
    RolEntityRepository rolEntityRepository;

    // TODO remove
    @GetMapping("/testRoles")
    String doTest(Model model) {
        model.addAttribute("roles", rolEntityRepository.findAll());
        return "vistaRoles";
    }

    @GetMapping("/")
    String doLogin(Model model, @ModelAttribute("entidad") String entidad) {
        model.addAttribute("entidad",
                "empresa".equals(entidad) ? "empresa" : "persona"
        );
        return "login";
    }

    @GetMapping("/registro")
    String doRegistrar(Model model) {

        return ""; //todo
    }

}
