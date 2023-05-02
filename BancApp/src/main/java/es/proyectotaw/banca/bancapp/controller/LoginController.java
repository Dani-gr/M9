package es.proyectotaw.banca.bancapp.controller;

import es.proyectotaw.banca.bancapp.dao.RolEntityRepository;
import es.proyectotaw.banca.bancapp.dao.RolusuarioEntityRepository;
import es.proyectotaw.banca.bancapp.dao.UsuarioEntityRepository;
import es.proyectotaw.banca.bancapp.entity.*;
import es.proyectotaw.banca.bancapp.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    String doRegistrar(Model model, HttpSession session, @RequestParam("entidad") String entidad) {
        if (session.getAttribute("usuario") != null) return "redirect:/menu/";

        model.addAttribute("error", "");
        model.addAttribute("entidad",
                "empresa".equals(entidad) ? "empresa" : "persona"
        );



        return "registro";
    }

    @PostMapping("/registro")
    String doGuardarRegistro(Model model, HttpSession session, @ModelAttribute("entidad") String entidad,
                             @ModelAttribute("userNif") String NIF,
                             @ModelAttribute("userNombre") String nombre,
                             @ModelAttribute("userNombreSegundo") String segundoNombre,
                             @ModelAttribute("userApellidoPrimero") String primerApellido,
                             @ModelAttribute("userApellidoSegundo") String segundoApellido,
                             @ModelAttribute("userFechaNacimiento") Date fechaNacimiento,
                             @ModelAttribute("userEmail") String email,
                             @ModelAttribute("userPassword") String password,
                             @ModelAttribute("direccionCalle") String calle,
                             @ModelAttribute("direccionNumero") String numero,
                             @ModelAttribute("direccionPlanta") String planta,
                             @ModelAttribute("direccionCiudad") String ciudad,
                             @ModelAttribute("direccionRegion") String region,
                             @ModelAttribute("direccoinPais") String pais,
                             @ModelAttribute("direccionPostal") String postal
                             ){
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

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNif(NIF);
        usuario.setPrimerNombre(nombre);
        usuario.setSegundoNombre(segundoNombre);
        usuario.setPrimerApellido(primerApellido);
        usuario.setSegundoApellido(segundoApellido);
        usuario.setFechaNacimiento(fechaNacimiento);
        usuario.setEmail(email);
        usuario.setPassword(password);
        ClienteEntity cliente = new ClienteEntity();
        DireccionEntity direccion = new DireccionEntity();
        direccion.setCalle(calle);
        direccion.setCiudad(ciudad);
        direccion.setCodpostal(postal);
        direccion.setNumero(Integer.valueOf(numero));
        direccion.setPais(pais);
        direccion.setRegion(region);
        direccion.setPlantaPuertaOficina(planta);
        cliente.setDireccionByDireccion(direccion);
        usuario.setClienteByCliente(cliente);
        direccionEntityRepository.save(direccion);
        clienteEntityRepository.save(cliente);
        RolusuarioEntity rolusuario = new RolusuarioEntity();
        rolusuario.setIdderol(5);
        rolusuario.setIdusuario(usuario.getId());
        rolusuario.setUsuarioByIdusuario(usuario);
        rolusuarioEntityRepository.save(rolusuario);
        List<RolusuarioEntity> lista = new ArrayList<>();
        lista.add(rolusuario);
        usuario.setRolusuariosById(lista);
        usuarioEntityRepository.save(usuario);
        session.setAttribute("usuario", usuario);

        return "enespera";
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
