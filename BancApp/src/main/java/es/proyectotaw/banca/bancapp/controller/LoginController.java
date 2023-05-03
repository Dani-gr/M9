package es.proyectotaw.banca.bancapp.controller;

import es.proyectotaw.banca.bancapp.dao.RolEntityRepository;
import es.proyectotaw.banca.bancapp.dao.RolusuarioEntityRepository;
import es.proyectotaw.banca.bancapp.dao.UsuarioEntityRepository;
import es.proyectotaw.banca.bancapp.entity.*;
import es.proyectotaw.banca.bancapp.dao.*;
import es.proyectotaw.banca.bancapp.entity.RolEntity;
import es.proyectotaw.banca.bancapp.entity.RolusuarioEntity;
import es.proyectotaw.banca.bancapp.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Date;
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
    @Autowired
    EmpresaEntityRepository empresaEntityRepository;

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
    String doRegistrar(Model model, HttpSession session, @RequestParam("entidad") String entidad, @ModelAttribute("cifEmpresa") String cif) {
        if (session.getAttribute("usuario") != null) return "redirect:/menu/";

        model.addAttribute("error", "");
        model.addAttribute("entidad",
                "empresa".equals(entidad) ? "empresa" : "persona"
        );

        model.addAttribute("cifEmpresa", cif);

        return "registro";
    }

    @PostMapping("/registro")
    String doGuardarRegistro(Model model, HttpSession session, @ModelAttribute("entidad") String entidad, @ModelAttribute("userNif") String NIF,
                             @ModelAttribute("userNombre") String nombre, @ModelAttribute("userNombreSegundo") String segundoNombre, @ModelAttribute("userApellidoPrimero") String primerApellido,
                             @ModelAttribute("userApellidoSegundo") String segundoApellido, @ModelAttribute("userFechaNacimiento") Date fechaNacimiento, @ModelAttribute("userEmail") String email,
                             @ModelAttribute("userPassword") String password, @ModelAttribute("direccionCalle") String calle, @ModelAttribute("direccionNumero") String numero,
                             @ModelAttribute("direccionPlanta") String planta, @ModelAttribute("direccionCiudad") String ciudad, @ModelAttribute("direccionRegion") String region,
                             @ModelAttribute("direccoinPais") String pais, @ModelAttribute("direccionPostal") String postal,
                             @ModelAttribute("cif") String cif, @ModelAttribute("rol") String rolSeleccionado){

        if (email == null || password == null || email.isBlank() || password.isBlank()) return "registro";

        if ("empresa".equals(entidad)) {
            if (cif == null || cif.isBlank()) return "registro";
            EmpresaEntity empresa = empresaEntityRepository.findByCif(Integer.valueOf(cif)).orElse(null);
            if(empresa == null) {
                empresa = new EmpresaEntity();
                empresa.setCif(Integer.valueOf(cif));
            }
            //Creo al socio/autorizado
            UsuarioEntity usuarioEmpresa = new UsuarioEntity();
            usuarioEmpresa.setNif(NIF);
            usuarioEmpresa.setPrimerNombre(nombre);
            usuarioEmpresa.setSegundoNombre(segundoNombre);
            usuarioEmpresa.setPrimerApellido(primerApellido);
            usuarioEmpresa.setSegundoApellido(segundoApellido);
            usuarioEmpresa.setFechaNacimiento(fechaNacimiento);
            usuarioEmpresa.setEmail(email);
            usuarioEmpresa.setPassword(password);

            /*Diferencia entre cliente de la empresa
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
            direccionEntityRepository.save(direccion);*/

            RolusuarioEntity rolusuario = new RolusuarioEntity();
            RolEntity rol = rolEntityRepository.findByNombre(rolSeleccionado).orElseThrow(RuntimeException::new);

            rolusuario.setIdrol(rol.getIdrol());
            rolusuario.setRolByIdrol(rol);
            rolusuario.setUsuarioByIdusuario(usuarioEmpresa);
            Integer id = usuarioEmpresa.getId();
            rolusuario.setIdusuario(id);
            rolusuarioEntityRepository.save(rolusuario);
            List<RolusuarioEntity> lista = new ArrayList<>();
            lista.add(rolusuario);
            usuarioEmpresa.setRolusuariosById(lista);
            /*List<UsuarioEntity> usuarios = new ArrayList<>();
            usuarios.add(usuarioEmpresa);
            cliente.setUsuariosByIdCliente(usuarios);
            usuarioEmpresa.setClienteByCliente(cliente);
            clienteEntityRepository.save(cliente);*/
            usuarioEntityRepository.save(usuarioEmpresa);

            session.setAttribute("empresa", empresa);
        } else {
            session.setAttribute("empresa", null);
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
            direccionEntityRepository.save(direccion);

            RolusuarioEntity rolusuario = new RolusuarioEntity();
            RolEntity rol = rolEntityRepository.findByNombre("cliente").orElseThrow(RuntimeException::new);

            rolusuario.setIdrol(rol.getIdrol());
            rolusuario.setRolByIdrol(rol);
            rolusuario.setUsuarioByIdusuario(usuario);
            Integer id = usuario.getId();
            rolusuario.setIdusuario(id);
            rolusuarioEntityRepository.save(rolusuario);
            List<RolusuarioEntity> lista = new ArrayList<>();
            lista.add(rolusuario);
            usuario.setRolusuariosById(lista);
            List<UsuarioEntity> usuarios = new ArrayList<>();
            usuarios.add(usuario);
            cliente.setUsuariosByIdCliente(usuarios);
            usuario.setClienteByCliente(cliente);
            clienteEntityRepository.save(cliente);
            usuarioEntityRepository.save(usuario);
        }
        session.setAttribute("usuario", entidad);

        model.addAttribute("cifEmpresa", cif);

        return "enespera";
    }

    @PostMapping("/")
    String doAutenticar(Model model, HttpSession session, @ModelAttribute("entidad") String entidad,
                        @ModelAttribute("cifEmpresa") String cif,
                        @ModelAttribute("user") String email,
                        @ModelAttribute("pass") String password) {
        /* TODO:
            - Testear algún gestor / socio
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

            var roles = rolusuarioEntityRepository.findRolesByUsuarioAndEmpresaByCif(user, Integer.valueOf(cif));

            roles.retainAll(rolesConPermiso);
            if (roles.isEmpty()) {
                model.addAttribute("error", "No tienes permiso en la empresa seleccionada.");
                return "login";
            }

            session.setAttribute("empresa", empresaEntityRepository.findByCif(Integer.valueOf(cif)));
        } else session.setAttribute("empresa", null);
        session.setAttribute("usuario", user);

        session.setAttribute("roles", user.getRolusuariosById().stream().map(RolusuarioEntity::getRolByIdrol).toList());

        return "redirect:/menu";
    }


    @GetMapping("/menu")
    String doMenu(HttpSession session) {
        return session.getAttribute("usuario") == null ? "redirect:/" : "menu";
    }

    @GetMapping("/logout")
    String doLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
