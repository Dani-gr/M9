package es.proyectotaw.banca.bancapp.controller;

import es.proyectotaw.banca.bancapp.dao.*;
import es.proyectotaw.banca.bancapp.entity.*;
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
    @Autowired
    CuentaEntityRepository cuentaEntityRepository;
    @Autowired
    OperacionEntityRepository operacionEntityRepository;
    @Autowired
    CambDivisaEntityRepository cambDivisaEntityRepository;

    @GetMapping("/")
    String doLogin(Model model, HttpSession session, @ModelAttribute("entidad") String entidad, @ModelAttribute("cifEmpresa") String cif,
                   @ModelAttribute("user") String email) {
        //if (session.getAttribute("usuario") != null) return "redirect:/menu/"; TODO quitar
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
        //if (session.getAttribute("usuario") != null) return "redirect:/menu/"; TODO quitar

        model.addAttribute("entidad",
                "empresa".equals(entidad) ? "empresa" : "persona"
        );

        model.addAttribute("cifEmpresa", cif);

        return "registro";
    }

    @PostMapping("/registro")
    String doGuardarRegistro(Model model, HttpSession session, @ModelAttribute("entidad") String entidad, @ModelAttribute("userNIF") String NIF,
                             @ModelAttribute("userNombre") String nombre, @ModelAttribute("userNombreSegundo") String segundoNombre, @ModelAttribute("userApellidoPrimero") String primerApellido,
                             @ModelAttribute("userApellidoSegundo") String segundoApellido, @ModelAttribute("userFechaNacimiento") Date fechaNacimiento, @ModelAttribute("userEmail") String email,
                             @ModelAttribute("userPassword") String password, @ModelAttribute("direccionCalle") String calle, @ModelAttribute("direccionNumero") String numero,
                             @ModelAttribute("direccionPlanta") String planta, @ModelAttribute("direccionCiudad") String ciudad, @ModelAttribute("direccionRegion") String region,
                             @ModelAttribute("direccoinPais") String pais, @ModelAttribute("direccionPostal") String postal,
                             @ModelAttribute("cifEmpresa") String cif, @ModelAttribute("rol") String rolSeleccionado, @RequestParam("btnRegistro") String boton) {

        if (email == null || password == null || email.isBlank() || password.isBlank()) return "registro";
        //TODO añadir control de errores para los parámetros que no deberían ser nulos
        String urlTo = "enespera";
        if ("empresa".equals(entidad)) {
            if (cif == null || cif.isBlank()) return "registro";
            EmpresaEntity empresa = empresaEntityRepository.findByCif(Integer.valueOf(cif)).orElse(null);
            if (empresa == null) {
                empresa = new EmpresaEntity();
                empresa.setCif(Integer.valueOf(cif));

                ClienteEntity cliente = new ClienteEntity();
                DireccionEntity direccion = new DireccionEntity();
                direccion.construct(calle, Integer.valueOf(numero), planta, ciudad, region, pais, postal);
                cliente.setDireccionByDireccion(direccion);
                direccionEntityRepository.save(direccion);
                cliente.setDireccionByDireccion(direccion);
                clienteEntityRepository.save(cliente);
                empresa.setId(cliente.getIdCliente());
            }
            if(boton.equals("registrarSocio")) {
                model.addAttribute("entidad", "empresa");
                urlTo = "redirect:/registro?entidad=empresa";
            }
            //Creo al socio/autorizado
            UsuarioEntity usuarioEmpresa = new UsuarioEntity();
            usuarioEmpresa.construct(NIF, nombre, segundoNombre, primerApellido, segundoApellido, fechaNacimiento, email, password);

            RolusuarioEntity rolusuario = new RolusuarioEntity();
            RolEntity rol = rolEntityRepository.findByNombre(rolSeleccionado).orElseThrow(RuntimeException::new);
            rolusuario.setRolByIdrol(rol);
            usuarioEntityRepository.save(usuarioEmpresa);
            rolusuario.setUsuarioByIdusuario(usuarioEmpresa);
            empresaEntityRepository.save(empresa);
            rolusuario.setEmpresaByIdempresa(empresa);
            rolusuario.setBloqueado((byte) 0);
            rolusuarioEntityRepository.save(rolusuario);
            List<RolusuarioEntity> rolUsuario = new ArrayList<>();
            rolUsuario.add(rolusuario);
            usuarioEmpresa.setRolusuariosById(rolUsuario);
            List<RolusuarioEntity> lista = new ArrayList<>();
            if(empresa.getRolusuariosById() != null) {
                lista.addAll(empresa.getRolusuariosById());
            }
            lista.add(rolusuario);
            empresa.setRolusuariosById(lista);
            usuarioEntityRepository.save(usuarioEmpresa);
            empresaEntityRepository.save(empresa);
        } else {
            session.setAttribute("empresa", null);
            UsuarioEntity usuario = new UsuarioEntity();
            usuario.construct(NIF, nombre, segundoNombre, primerApellido, segundoApellido, fechaNacimiento, email, password);
            usuarioEntityRepository.save(usuario);

            ClienteEntity cliente = new ClienteEntity();
            DireccionEntity direccion = new DireccionEntity();
            direccion.construct(calle, Integer.valueOf(numero), planta, ciudad, region, pais, postal);
            direccionEntityRepository.save(direccion);
            cliente.setDireccionByDireccion(direccion);
            clienteEntityRepository.save(cliente);

            RolusuarioEntity rolusuario = new RolusuarioEntity();
            RolEntity rol = rolEntityRepository.findByNombre("cliente").orElseThrow(RuntimeException::new);
            rolusuario.setRolByIdrol(rol);
            rolusuario.setUsuarioByIdusuario(usuario);
            rolusuario.setBloqueado((byte) 0);
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

        model.addAttribute("cifEmpresa", cif);

        return urlTo;
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
        UsuarioEntity user = (UsuarioEntity) session.getAttribute("usuario");
        return user == null ? "redirect:/" :
                (user.getClienteByCliente().getCuentasByIdCliente().isEmpty() ? "enespera" : "menu");
    }

    @GetMapping("/logout")
    String doLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    // TODO eliminar
    @GetMapping("/reset")
    String doInitBBDD() {
        // Direcciones
        direccionEntityRepository.deleteAll(direccionEntityRepository.findAll());
        DireccionEntity d1 = new DireccionEntity(), d2 = new DireccionEntity(), d3 = new DireccionEntity();
        d1.construct("Calle Falsa", 123, "1ºA", "Madrid", "Madrid", "España", "28001");
        d2.construct("Calle Imaginaria", 456, "5ºC", "Barcelona", "Barcelona", "España", "08001");
        d3.construct("Calle Inventada", 789, "4ºB", "Sevilla", "Sevilla", "España", "41001");
        direccionEntityRepository.save(d1);
        direccionEntityRepository.save(d2);
        direccionEntityRepository.save(d3);

        // Clientes
        clienteEntityRepository.deleteAll(clienteEntityRepository.findAll());
        ClienteEntity c1 = new ClienteEntity(), c2 = new ClienteEntity(), c3 = new ClienteEntity();
        c1.setDireccionByDireccion(d1);
        c2.setDireccionByDireccion(d2);
        c3.setDireccionByDireccion(d3);
        clienteEntityRepository.save(c1);
        clienteEntityRepository.save(c2);
        clienteEntityRepository.save(c3);

        // Usuarios
        usuarioEntityRepository.deleteAll(usuarioEntityRepository.findAll());
        UsuarioEntity u1 = new UsuarioEntity(), u2 = new UsuarioEntity(), u3 = new UsuarioEntity();
        u1.construct("12345678A", "Juan", "Antonio", "García", "Pérez", Date.valueOf("1980-01-01"), "juan.garcia@bancapp.es", "contraseña");
        u2.construct("23456789B", "María", null, "Rodríguez", "Fernández", Date.valueOf("1990-05-12"), "maria.rodriguez@bancapp.es", "contraseña");
        u3.construct("34567890C", "Juan", null, "Sánchez", "García", Date.valueOf("1985-11-21"), "juan.sanchez@bancapp.es", "contraseña");
        u1.setClienteByCliente(c1);
        u2.setClienteByCliente(c2);
        u3.setClienteByCliente(c3);
        usuarioEntityRepository.save(u1);
        usuarioEntityRepository.save(u2);
        usuarioEntityRepository.save(u3);

        // Cuentas
        cuentaEntityRepository.deleteAll(cuentaEntityRepository.findAll());
        CuentaEntity cu1 = new CuentaEntity(), cu2 = new CuentaEntity(), cu3 = new CuentaEntity();
        cu1.setClienteByCliente(c1);
        cu2.setClienteByCliente(c2);
        cu3.setClienteByCliente(c3);
        cu1.setSaldo(1000.50);
        cu2.setSaldo(500.00);
        cu3.setSaldo(0.00);
        cu1.setActiva((byte) 1);
        cu2.setActiva((byte) 1);
        cu3.setActiva((byte) 0);
        cuentaEntityRepository.save(cu1);
        cuentaEntityRepository.save(cu2);
        cuentaEntityRepository.save(cu3);

        // Cambios de divisa
        cambDivisaEntityRepository.deleteAll(cambDivisaEntityRepository.findAll());
        CambDivisaEntity cd1 = new CambDivisaEntity(), cd2 = new CambDivisaEntity();
        /*cd1.setOperacion(o1.getIdOperacion());
        cd2.setOperacion(o2.getIdOperacion());*/
        cd1.setOrigen("EUR");
        cd2.setOrigen("EUR");
        cd1.setDestino("USD");
        cd2.setDestino("GBP");

        // Operaciones
        operacionEntityRepository.deleteAll(operacionEntityRepository.findAll());
        OperacionEntity o1 = new OperacionEntity(), o2 = new OperacionEntity(), o3 = new OperacionEntity();

        o1.setCuentaByCuentaRealiza(cu1);
        o2.setCuentaByCuentaRealiza(cu2);
        o3.setCuentaByCuentaRealiza(cu3);
        o1.setFecha(Date.valueOf("2022-04-01"));
        o2.setFecha(Date.valueOf("2022-05-15"));
        o3.setFecha(Date.valueOf("2022-06-30"));


        operacionEntityRepository.save(o1);
        operacionEntityRepository.save(o2);
        operacionEntityRepository.save(o3);

        cd1.setOperacionByOperacion(o1);
        cd2.setOperacionByOperacion(o2);

        cambDivisaEntityRepository.save(cd1);
        cambDivisaEntityRepository.save(cd2);

        return "redirect:/";
    }
}
