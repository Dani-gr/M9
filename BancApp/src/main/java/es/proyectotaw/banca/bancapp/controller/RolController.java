package es.proyectotaw.banca.bancapp.controller;

import es.proyectotaw.banca.bancapp.dao.RolEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

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
    String doLogin(Model model, @ModelAttribute("entidad") String entidad, @ModelAttribute("cifEmpresa") String cif,
                   @ModelAttribute("user") String email) {
        model.addAttribute("entidad",
                "empresa".equals(entidad) ? "empresa" : "persona"
        );
        model.addAttribute("cifEmpresa", cif);
        model.addAttribute("user", email);

        return "login";
    }

    @GetMapping("/registro")
    String doRegistrar(Model model, @ModelAttribute("entidad") String entidad) {
        // TODO
        model.addAttribute("entidad",
                "empresa".equals(entidad) ? "empresa" : "persona"
        );
        return "";
    }

    @PostMapping("/")
    String doAutenticar(Model model, HttpSession session, @ModelAttribute("entidad") String entidad,
                        @ModelAttribute("cifEmpresa") String cif,
                        @ModelAttribute("user") String email,
                        @ModelAttribute("pass") String password) {
        /* TODO:
            - Buscar en la BBDD
              - y comprobar si los datos son correctos.
            - Asegurarse de que la persona es autorizada / socia de la empresa que se pasa como argumento,
              - si entidad es empresa.
            - Si algún elemento no es correcto,
              ✔ a login
              ✔ y agregar error.
            ✔ Añadir a la jsp de login que se guarden los datos de inicio de sesión (o al menos el usuario y cif)
              - si son erróneos.
         */
        model.addAttribute("error", "Credenciales incorrectas");
        model.addAttribute("entidad", entidad);
        model.addAttribute("cifEmpresa", cif);
        model.addAttribute("user", email);
        return "login";
    }
}
