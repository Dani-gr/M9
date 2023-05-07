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
    @Autowired
    TransferenciaEntityRepository transferenciaEntityRepository;
    @Autowired
    ExtraccionEntityRepository extraccionEntityRepository;

    @GetMapping("/")
    String doLogin(Model model, HttpSession session, @ModelAttribute("entidad") String entidad, @ModelAttribute("cifEmpresa") String cif,
                   @ModelAttribute("user") String email) {
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
        if (usuario != null) {
            List<RolusuarioEntity> ru = usuario.getRolusuariosById();
            // Si pasa, se ha hecho mal alguna inserción
            if (ru == null || ru.isEmpty()) model.addAttribute("error", "Error de BBDD: El usuario no tiene roles");
            else return "redirect:/menu";
        } else model.addAttribute("error", "");
        model.addAttribute("entidad",
                "empresa".equals(entidad) ? "empresa" : "persona"
        );
        model.addAttribute("cifEmpresa", cif);
        model.addAttribute("user", email);

        return "login";
    }

    @GetMapping("/registro")
    String doRegistrar(Model model, HttpSession session, @RequestParam("entidad") String entidad, @ModelAttribute("cifEmpresa") String cif) {
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
        if (usuario != null) {
            List<RolusuarioEntity> ru = usuario.getRolusuariosById();
            // Si pasa, se ha hecho mal alguna inserción
            if (ru == null || ru.isEmpty()) model.addAttribute("error", "Error de BBDD: El usuario no tiene roles");
            else return "redirect:/menu";
        } else model.addAttribute("error", "");

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
                             @ModelAttribute("direccionCalleEmpresa") String calleEmpresa, @ModelAttribute("direccionNumeroEmpresa") String numeroEmpresa,
                             @ModelAttribute("direccionPlantaEmpresa") String plantaEmpresa, @ModelAttribute("direccionCiudadEmpresa") String ciudadEmpresa, @ModelAttribute("direccionRegionEmpresa") String regionEmpresa,
                             @ModelAttribute("direccoinPaisEmpresa") String paisEmpresa, @ModelAttribute("direccionPostalEmpresa") String postalEmpresa,
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

                ClienteEntity clienteEmpresa = new ClienteEntity();
                DireccionEntity direccion = new DireccionEntity();
                direccion.construct(calleEmpresa, Integer.valueOf(numeroEmpresa), plantaEmpresa, ciudadEmpresa, regionEmpresa, paisEmpresa, postalEmpresa);
                clienteEmpresa.setDireccionByDireccion(direccion);
                direccionEntityRepository.save(direccion);
                clienteEmpresa.setDireccionByDireccion(direccion);
                clienteEntityRepository.save(clienteEmpresa);

                empresa.setClienteByCliente(clienteEmpresa);
                empresaEntityRepository.save(empresa);
            }
            if (boton.equals("registrarSocio")) {
                model.addAttribute("entidad", "empresa");
                urlTo = "redirect:/registro?entidad=empresa&cif=" + cif;
            }
            // Creo al socio/autorizado
            UsuarioEntity usuarioEmpresa = new UsuarioEntity();
            usuarioEmpresa.construct(NIF, nombre, segundoNombre, primerApellido, segundoApellido, fechaNacimiento, email, password);

            // TODO check for existing users
            ClienteEntity cliente = new ClienteEntity();
            DireccionEntity direccion = new DireccionEntity();
            direccion.construct(calle, Integer.valueOf(numero), planta, ciudad, region, pais, postal);
            direccionEntityRepository.save(direccion);
            cliente.setDireccionByDireccion(direccion);
            clienteEntityRepository.save(cliente);

            usuarioEmpresa.setClienteByCliente(cliente);
            usuarioEntityRepository.save(usuarioEmpresa);

            RolusuarioEntity rolusuario = new RolusuarioEntity();
            RolEntity rol = rolEntityRepository.findByNombre(rolSeleccionado).orElseThrow(RuntimeException::new);
            rolusuario.setRolByIdrol(rol);
            rolusuario.setUsuarioByIdusuario(usuarioEmpresa);
            rolusuario.setEmpresaByIdempresa(empresa);
            rolusuario.setBloqueado((byte) 0);
            rolusuarioEntityRepository.save(rolusuario);

            session.setAttribute("empresa", empresa);
        } else {
            UsuarioEntity usuario = new UsuarioEntity();
            usuario.construct(NIF, nombre, segundoNombre, primerApellido, segundoApellido, fechaNacimiento, email, password);

            // TODO check for existing users

            ClienteEntity cliente = new ClienteEntity();
            DireccionEntity direccion = new DireccionEntity();
            direccion.construct(calle, Integer.valueOf(numero), planta, ciudad, region, pais, postal);
            direccionEntityRepository.save(direccion);
            cliente.setDireccionByDireccion(direccion);
            clienteEntityRepository.save(cliente);

            usuario.setClienteByCliente(cliente);
            usuarioEntityRepository.save(usuario);

            RolusuarioEntity rolusuario = new RolusuarioEntity();
            RolEntity rol = rolEntityRepository.findByNombre("cliente").orElseThrow(RuntimeException::new);
            rolusuario.setRolByIdrol(rol);
            rolusuario.setUsuarioByIdusuario(usuario);
            rolusuario.setBloqueado((byte) 0);
            rolusuarioEntityRepository.save(rolusuario);

            session.setAttribute("empresa", null);
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
        if (session.getAttribute("usuario") != null) return "redirect:/menu";

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
            List<RolEntity> rolesConPermiso = new ArrayList<>(2);
            rolesConPermiso.add(rolEntityRepository.findByNombre("autorizado").orElseThrow(RuntimeException::new));
            rolesConPermiso.add(rolEntityRepository.findByNombre("socio").orElseThrow(RuntimeException::new));

            var roles = rolusuarioEntityRepository.findRolesByUsuarioAndEmpresaByCif(user, Integer.valueOf(cif));

            roles.retainAll(rolesConPermiso);
            if (roles.isEmpty()) {
                model.addAttribute("error", "No tienes permiso en la empresa seleccionada.");
                return "login";
            }

            session.setAttribute("empresa", empresaEntityRepository.findByCif(Integer.valueOf(cif)).orElseThrow());
        } else {
            List<RolEntity> rolesConPermiso = new ArrayList<>(3);
            rolesConPermiso.add(rolEntityRepository.findByNombre("cliente").orElseThrow(RuntimeException::new));
            rolesConPermiso.add(rolEntityRepository.findByNombre("asistente").orElseThrow(RuntimeException::new));
            rolesConPermiso.add(rolEntityRepository.findByNombre("gestor").orElseThrow(RuntimeException::new));

            var roles = rolusuarioEntityRepository.findRolesByUsuarioNoEmpresa(user);
            roles.retainAll(rolesConPermiso);
            if (roles.isEmpty()) {
                model.addAttribute("error", "Credenciales incorrectas. Si eres socio o autorizado, usa el login de empresa.");
                return "login";
            }
            session.setAttribute("empresa", null);
        }
        session.setAttribute("usuario", user);

        session.setAttribute("nombresRoles", user.getRolusuariosById().stream()
                .map(RolusuarioEntity::getRolByIdrol).map(RolEntity::getNombre).toList());

        return "redirect:/menu";
    }


    @GetMapping("/menu")
    String doMenu(HttpSession session, Model model) {
        UsuarioEntity user = (UsuarioEntity) session.getAttribute("usuario");
        if (user == null) return "redirect:/logout";
        List<RolusuarioEntity> ru = user.getRolusuariosById();

        // Si pasa, se ha hecho mal alguna inserción
        if (ru == null || ru.isEmpty()) return "redirect:/logout";

        if (session.getAttribute("menu") == null)
            session.setAttribute("menu", "normal");
        var nombresRoles = ru.stream().map(RolusuarioEntity::getRolByIdrol).map(RolEntity::getNombre).toList();
        if (nombresRoles.contains("asistente"))
            return "chats";
        String mensaje = (String) session.getAttribute("mensaje");
        if (mensaje != null && !mensaje.isBlank()) {
            model.addAttribute("mensaje", mensaje);
            session.removeAttribute("mensaje");
        } else model.addAttribute("mensaje", "");
        if (nombresRoles.contains("gestor"))
            return "menu";

        return ru.get(0).getCuentaAsociada() == null ? "enespera" : "menu";
    }

    @PostMapping("/menu")
    @SuppressWarnings("unchecked")
    String doToggleMenu(Model model, HttpSession session) {
        var roles = ((List<String>) session.getAttribute("nombresRoles"));
        session.setAttribute("menu",
                roles.contains("gestor") || roles.contains("asistente") ?
                        "normal" :
                        "normal".equals(session.getAttribute("menu")) ? "cajero" : "normal"
        );

        model.addAttribute("mensaje", "");

        return "redirect:/menu";
    }

    @GetMapping("/logout")
    String doLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    /**
     * Método auxiliar para crear entidades iniciales en la BBDD.
     * Se crean:
     * <ul>
     *     <li>
     *         Dos <b>clientes</b> particulares <i>("juan.garcia@bancapp.es", "contraseña")</i> y
     *         <i>("maria.rodriguez@bancapp.es", "contraseña")</i>.
     *     </li>
     *     <li>
     *         Una <b>cuenta</b> para cada uno de los particulares anteriores, estando la primera de ellas <b>inactiva</b>.
     *     </li>
     *     <li>Una <b>empresa</b> con cif <i>1472583690</i>, con su <b>cuenta</b> correspondiente.</li>
     *     <li>
     *         Un <b>socio</b> <i>("juan.sanchez@bancapp.es", "contraseña")</i> y una <b>autorizada</b>
     *         <i>("marta.sanchez@bancapp.es", "contraseña")</i> para dicha empresa, esta última <b>bloqueada</b>.
     *         <br/>Estos usuarios comparten dirección, ya que son hermanos.
     *     </li>
     *     <li>Un <b>asistente</b> <i>("a@bancapp.es", "a")</i>.</li>
     *     <li>Un <b>gestor</b> <i>("kfc@bancapp.es", "pollopollo")</i>.</li>
     * </ul>
     * Además, se crean las siguientes operaciones:
     * <ul>
     *     <li>Un <b>cambio de divisa</b> en la cuenta de Juan Antonio: <i>7.25 EUR -> USD</i>.</li>
     *     <li>Un <b>cambio de divisa</b> en la cuenta de María: <i>20.00 EUR -> GBP</i>.</li>
     *     <li>Una <b>transferencia</b> de Juan Antonio a María por valor de <i>150.00 EUR</i>.</li>
     *     <li>Una <b>extracción</b> de la cuenta de empresa por valor de <i>15.00 EUR</i>.</li>
     * </ul>
     *
     * @return redirección al login
     */
    @GetMapping("/reset")
    String doInitBBDD() {
        // Direcciones
        direccionEntityRepository.deleteAll(direccionEntityRepository.findAll());
        DireccionEntity d1 = new DireccionEntity(), d2 = new DireccionEntity(), d3 = new DireccionEntity(), d4 = new DireccionEntity();
        d1.construct("Calle Falsa", 123, "1ºA", "Madrid", "Madrid", "España", "28001");
        d2.construct("Calle Imaginaria", 456, "5ºC", "Barcelona", "Barcelona", "España", "08001");
        d3.construct("Calle Inventada", 789, "4ºB", "Sevilla", "Sevilla", "España", "41001");
        d4.construct("Calle A", 0, "A-A", "A", "Almería", "España", "12345");
        direccionEntityRepository.save(d1);
        direccionEntityRepository.save(d2);
        direccionEntityRepository.save(d3);
        direccionEntityRepository.save(d4);


        // Clientes
        clienteEntityRepository.deleteAll(clienteEntityRepository.findAll());
        ClienteEntity c1 = new ClienteEntity(), c2 = new ClienteEntity(), c3 = new ClienteEntity(), c4 = new ClienteEntity(), ce1 = new ClienteEntity();
        c1.setDireccionByDireccion(d1);
        c2.setDireccionByDireccion(d2);
        c3.setDireccionByDireccion(d3);
        c4.setDireccionByDireccion(d3); //c3 y c4 viven juntos
        ce1.setDireccionByDireccion(d4);
        clienteEntityRepository.save(c1);
        clienteEntityRepository.save(c2);
        clienteEntityRepository.save(c3);
        clienteEntityRepository.save(c4);
        clienteEntityRepository.save(ce1);


        // Usuarios
        usuarioEntityRepository.deleteAll(usuarioEntityRepository.findAll());
        UsuarioEntity u1 = new UsuarioEntity(), u2 = new UsuarioEntity(), u3 = new UsuarioEntity(),
                u4 = new UsuarioEntity(), u5 = new UsuarioEntity(), u6 = new UsuarioEntity();
        u1.construct("12345678A", "Juan", "Antonio", "García", "Pérez",
                Date.valueOf("1980-01-01"), "juan.garcia@bancapp.es", "contraseña");
        u2.construct("23456789B", "María", null, "Rodríguez", "Fernández",
                Date.valueOf("1990-05-12"), "maria.rodriguez@bancapp.es", "contraseña");
        u3.construct("34567890C", "Juan", null, "Sánchez", "García",
                Date.valueOf("1985-11-21"), "juan.sanchez@bancapp.es", "contraseña");
        u4.construct("99999999A", "Alberto", null, "Álvarez", "Alarcón",
                Date.valueOf("2000-11-22"), "a@bancapp.es", "a");
        u5.construct("98765432R", "Kevin", null, "Fernández", "Carrión",
                Date.valueOf("1952-10-24"), "kfc@bancapp.es", "pollopollo");
        u6.construct("34567890C", "Marta", null, "Sánchez", "García",
                Date.valueOf("1987-04-04"), "marta.sanchez@bancapp.es", "contraseña");
        u1.setClienteByCliente(c1);
        u2.setClienteByCliente(c2);
        u3.setClienteByCliente(c3);
        u6.setClienteByCliente(c4);
        usuarioEntityRepository.save(u1);
        usuarioEntityRepository.save(u2);
        usuarioEntityRepository.save(u3);
        usuarioEntityRepository.save(u4);
        usuarioEntityRepository.save(u5);
        usuarioEntityRepository.save(u6);


        // Empresas
        empresaEntityRepository.deleteAll();
        EmpresaEntity e1 = new EmpresaEntity();
        e1.setClienteByCliente(ce1);
        e1.setCif(1472583690);
        empresaEntityRepository.save(e1);


        // Cuentas
        cuentaEntityRepository.deleteAll(cuentaEntityRepository.findAll());
        CuentaEntity cu1 = new CuentaEntity(), cu2 = new CuentaEntity(), cu3 = new CuentaEntity();
        cu1.setClienteByCliente(c1);
        cu2.setClienteByCliente(c2);
        cu3.setClienteByCliente(ce1);
        cu1.setSaldo(1000.50);
        cu2.setSaldo(500.00);
        cu3.setSaldo(0.00);
        cu1.setActiva((byte) 0);
        cu2.setActiva((byte) 1);
        cu3.setActiva((byte) 1);
        cuentaEntityRepository.save(cu1);
        cuentaEntityRepository.save(cu2);
        cuentaEntityRepository.save(cu3);


        // Operaciones
        operacionEntityRepository.deleteAll(operacionEntityRepository.findAll());
        OperacionEntity o1 = new OperacionEntity(), o2 = new OperacionEntity(),
                o3 = new OperacionEntity(), o4 = new OperacionEntity();

        o1.setCuentaByCuentaRealiza(cu1);
        o2.setCuentaByCuentaRealiza(cu2);
        o3.setCuentaByCuentaRealiza(cu1);
        o4.setCuentaByCuentaRealiza(cu3);
        o1.setFecha(Date.valueOf("2022-04-01"));
        o2.setFecha(Date.valueOf("2022-05-15"));
        o3.setFecha(Date.valueOf("2022-06-30"));
        o4.setFecha(Date.valueOf("2021-05-04"));

        operacionEntityRepository.save(o1);
        operacionEntityRepository.save(o2);
        operacionEntityRepository.save(o3);
        operacionEntityRepository.save(o4);


        // Cambios de divisa
        cambDivisaEntityRepository.deleteAll(cambDivisaEntityRepository.findAll());
        CambDivisaEntity cd1 = new CambDivisaEntity(), cd2 = new CambDivisaEntity();
        cd1.setOperacionByOperacion(o1);
        cd2.setOperacionByOperacion(o2);
        cd1.setOrigen("EUR");
        cd2.setOrigen("EUR");
        cd1.setDestino("USD");
        cd2.setDestino("GBP");
        cd1.setCantidad(7.25);
        cd2.setCantidad(20.00);

        cambDivisaEntityRepository.save(cd1);
        cambDivisaEntityRepository.save(cd2);

        // Transferencias
        transferenciaEntityRepository.deleteAll();
        TransferenciaEntity t1 = new TransferenciaEntity();
        t1.setCuentaByCuentaDestino(cu2);
        t1.setOperacionByOperacion(o3);
        t1.setCantidad(150.00);

        transferenciaEntityRepository.save(t1);


        // Extracciones
        extraccionEntityRepository.deleteAll();
        ExtraccionEntity extra1 = new ExtraccionEntity();
        extra1.setCantidad(15.00);
        extra1.setOperacionByOperacion(o4);

        extraccionEntityRepository.save(extra1);


        // Rolusuarios
        rolusuarioEntityRepository.deleteAll();
        RolusuarioEntity ru1 = new RolusuarioEntity(), ru2 = new RolusuarioEntity(), ru3 = new RolusuarioEntity(),
                ru4 = new RolusuarioEntity(), ru5 = new RolusuarioEntity(), ru6 = new RolusuarioEntity();
        ru1.setUsuarioByIdusuario(u1);
        ru2.setUsuarioByIdusuario(u2);
        ru3.setUsuarioByIdusuario(u3);
        ru4.setUsuarioByIdusuario(u4);
        ru5.setUsuarioByIdusuario(u5);
        ru6.setUsuarioByIdusuario(u6);

        RolEntity rolCliente = rolEntityRepository.findByNombre("cliente").orElseThrow();
        RolEntity rolAsistente = rolEntityRepository.findByNombre("asistente").orElseThrow();
        RolEntity rolGestor = rolEntityRepository.findByNombre("gestor").orElseThrow();
        RolEntity rolSocio = rolEntityRepository.findByNombre("socio").orElseThrow();
        RolEntity rolAutorizado = rolEntityRepository.findByNombre("autorizado").orElseThrow();

        ru1.setRolByIdrol(rolCliente);
        ru2.setRolByIdrol(rolCliente);
        ru3.setRolByIdrol(rolSocio);
        ru3.setEmpresaByIdempresa(e1);
        ru4.setRolByIdrol(rolAsistente);
        ru5.setRolByIdrol(rolGestor);
        ru6.setRolByIdrol(rolAutorizado);
        ru6.setEmpresaByIdempresa(e1);

        ru1.setBloqueado((byte) 0);
        ru2.setBloqueado((byte) 0);
        ru3.setBloqueado((byte) 0);
        ru4.setBloqueado((byte) 0);
        ru5.setBloqueado((byte) 0);
        ru6.setBloqueado((byte) 1);

        rolusuarioEntityRepository.save(ru1);
        rolusuarioEntityRepository.save(ru2);
        rolusuarioEntityRepository.save(ru3);
        rolusuarioEntityRepository.save(ru4);
        rolusuarioEntityRepository.save(ru5);
        rolusuarioEntityRepository.save(ru6);

        return "redirect:/";
    }
}
