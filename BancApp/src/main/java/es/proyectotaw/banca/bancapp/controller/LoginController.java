package es.proyectotaw.banca.bancapp.controller;

import es.proyectotaw.banca.bancapp.dao.*;
import es.proyectotaw.banca.bancapp.entity.ClienteEntity;
import es.proyectotaw.banca.bancapp.entity.DireccionEntity;
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
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SpringMVCViewInspection")
@Controller
@RequestMapping("/")
public class LoginController {
    @Autowired
    UsuarioEntityRepository usuarioEntityRepository;
    @Autowired
    ClienteEntityRepository clienteEntityRepository;
    @Autowired
    RolEntityRepository rolEntityRepository;
    @Autowired
    RolusuarioEntityRepository rolusuarioEntityRepository;
    @Autowired
    DireccionEntityRepository direccionEntityRepository;

    @GetMapping("/")
    String doLogin(Model model, HttpSession session, @ModelAttribute("entidad") String entidad, @ModelAttribute("cifEmpresa") String cif,
                   @ModelAttribute("user") String email) {
        if (session.getAttribute("usuario") != null) return "redirect:/menu/";
        model.addAttribute("error", "");
        model.addAttribute("entidad",
                "empresa".equals(entidad) ? "empresa" : "persona"
        );
        model.addAttribute("cifEmpresa", cif);
        model.addAttribute("user", email);

        return "login";
    }

    @GetMapping("/registro")
    String doRegistrar(Model model, HttpSession session, @ModelAttribute("entidad") String entidad) {
        // TODO
        model.addAttribute("entidad",
                "empresa".equals(entidad) ? "empresa" : "persona"
        );
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNif("12345678");
        usuario.setPrimerNombre("prueba");
        usuario.setSegundoNombre("amparo");
        usuario.setPrimerApellido("ruiz");
        usuario.setSegundoApellido("sepulveda");
        usuario.setFechaNacimiento(new Date(1852, 12, 25));
        usuario.setEmail("amparopunto@gmail.com");
        usuario.setPassword("yoquieroqueaprobeistodos");
        ClienteEntity cliente = new ClienteEntity();
        usuario.setClienteByCliente(cliente);

        DireccionEntity direccion = new DireccionEntity();
        cliente.setDireccionByDireccion(direccion);
        direccion.setCalle("desamparo");
        direccion.setCiudad("Malaga");
        direccion.setCodpostal("29014");
        direccion.setNumero(4);
        direccion.setPais("Españita");
        direccion.setRegion("Andalucía");
        direccion.setPlantaPuertaOficina("1, 2, 3");

        direccionEntityRepository.save(direccion);
        clienteEntityRepository.save(cliente);
        usuarioEntityRepository.save(usuario);
        session.setAttribute("usuario", usuario);
        return "redirect:/menu";
    }

    @PostMapping("/")
    String doAutenticar(Model model, HttpSession session, @ModelAttribute("entidad") String entidad,
                        @ModelAttribute("cifEmpresa") String cif,
                        @ModelAttribute("user") String email,
                        @ModelAttribute("pass") String password) {
        /* TODO:
            ✔ Buscar en la BBDD
              ✔ y comprobar si los datos son correctos.
            ✔ Asegurarse de que la persona es autorizada / socia de la empresa que se pasa como argumento,
              ✔ si entidad es empresa.
            ✔ Si algún elemento no es correcto,
              ✔ a login
              ✔ y agregar error.
            ✔ Añadir a la jsp de login que se guarden los datos de inicio de sesión (o al menos el usuario y cif)
              - si son erróneos.
         */
        if (session.getAttribute("usuario") != null) return "redirect:/menu/";

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
            if (roles.isEmpty()) {
                model.addAttribute("error", "No tienes permiso en la empresa seleccionada.");
                return "login";
            }
        }
        session.setAttribute("usuario", user);

        return "login";
    }

    @GetMapping("/menu")
    String doMenu(HttpSession session) {
        //TODO session.getAttribute("usuario") == null ? "redirect:/" :
        return "menu";
    }

    @GetMapping("/logout")
    String doLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
