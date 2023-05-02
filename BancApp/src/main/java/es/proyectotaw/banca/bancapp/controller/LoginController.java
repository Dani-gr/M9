package es.proyectotaw.banca.bancapp.controller;

import es.proyectotaw.banca.bancapp.dao.RolEntityRepository;
import es.proyectotaw.banca.bancapp.dao.RolusuarioEntityRepository;
import es.proyectotaw.banca.bancapp.dao.UsuarioEntityRepository;
import es.proyectotaw.banca.bancapp.entity.RolEntity;
import es.proyectotaw.banca.bancapp.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SpringMVCViewInspection")
@Controller
@RequestMapping("/")
public class LoginController {
    @Autowired
    UsuarioEntityRepository usuarioEntityRepository;
    @Autowired
    RolEntityRepository rolEntityRepository;
    @Autowired
    RolusuarioEntityRepository rolusuarioEntityRepository;

    @GetMapping("/")
    String doLogin(Model model, @ModelAttribute("entidad") String entidad, @ModelAttribute("cifEmpresa") String cif,
                   @ModelAttribute("user") String email) {
        model.addAttribute("error", "");
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
            ✔ Buscar en la BBDD
              ✔ y comprobar si los datos son correctos.
            - Asegurarse de que la persona es autorizada / socia de la empresa que se pasa como argumento,
              ✔ si entidad es empresa.
            ✔ Si algún elemento no es correcto,
              ✔ a login
              ✔ y agregar error.
            ✔ Añadir a la jsp de login que se guarden los datos de inicio de sesión (o al menos el usuario y cif)
              - si son erróneos.
         */

        model.addAttribute("error", "Credenciales incorrectas");
        model.addAttribute("entidad", entidad);
        model.addAttribute("cifEmpresa", cif);
        model.addAttribute("user", email);

        if (email == null || password == null || email.isBlank() || password.isBlank()) return "login";

        UsuarioEntity user = usuarioEntityRepository.findByEmailIgnoreCase(email).orElse(null);
        if (user == null) return "login";
        if (!user.getPassword().equals(password)) return "login";

        if ("empresa".equals(entidad)) {
            if (cif == null || cif.isBlank()) return "login";
            List<RolEntity> rolesConPermiso = new ArrayList<>(4);
            rolesConPermiso.add(rolEntityRepository.findByNombre("autorizado").orElseThrow(RuntimeException::new));
            rolesConPermiso.add(rolEntityRepository.findByNombre("autorizadoBloqueado").orElseThrow(RuntimeException::new));
            rolesConPermiso.add(rolEntityRepository.findByNombre("socio").orElseThrow(RuntimeException::new));
            rolesConPermiso.add(rolEntityRepository.findByNombre("socioBloqueado").orElseThrow(RuntimeException::new));

            var roles = rolusuarioEntityRepository.findRolesByUsuarioAndEmpresaByCif(user, cif);

            roles.retainAll(rolesConPermiso);
            if(roles.isEmpty()) {
                model.addAttribute("error", "No tienes permiso en la empresa seleccionada.");
                return "login";
            } 
            
            
        }


        return "login";
    }
}
