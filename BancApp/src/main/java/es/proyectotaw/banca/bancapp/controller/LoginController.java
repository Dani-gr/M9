package es.proyectotaw.banca.bancapp.controller;


import es.proyectotaw.banca.bancapp.dto.*;
import es.proyectotaw.banca.bancapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

/**
 * @author Daniel García Rodríguez 70%
 * @author Nuria Rodríguez Tortosa 30%
 */
@SuppressWarnings("SpringMVCViewInspection")
@Controller
@RequestMapping("/")
public class LoginController {
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    ClienteService clienteService;
    @Autowired
    RolService rolService;
    @Autowired
    RolusuarioService rolusuarioService;
    @Autowired
    DireccionService direccionService;
    @Autowired
    EmpresaService empresaService;
    @Autowired
    CuentaService cuentaService;
    @Autowired
    OperacionService operacionService;
    @Autowired
    CambDivisaService cambDivisaService;
    @Autowired
    TransferenciaService transferenciaService;
    @Autowired
    ExtraccionService extraccionService;

    @GetMapping("/")
    String doLogin(Model model, HttpSession session, @ModelAttribute("entidad") String entidad, @ModelAttribute("cifEmpresa") String cif,
                   @ModelAttribute("user") String email) {
        if (comprobarRoles(model, session, entidad, cif)) return "redirect:/menu";
        model.addAttribute("user", email);

        return "login";
    }

    private boolean comprobarRoles(Model model, HttpSession session, @ModelAttribute("entidad") String entidad, @ModelAttribute("cifEmpresa") String cif) {
        UsuarioEntityDTO usuario = (UsuarioEntityDTO) session.getAttribute("usuario");
        if (usuario != null) {
            List<RolusuarioEntityDTO> ru = usuario.getRolusuariosById();
            // Si pasa, se ha hecho mal alguna inserción
            if (ru == null || ru.isEmpty()) model.addAttribute("error", "Error de BBDD: El usuario no tiene roles");
            else return true;
        } else model.addAttribute("error", "");
        model.addAttribute("entidad",
                "empresa".equals(entidad) ? "empresa" : "persona"
        );
        model.addAttribute("cifEmpresa", cif);
        return false;
    }

    @GetMapping("/registro")
    String doRegistrar(Model model, HttpSession session, @RequestParam("entidad") String entidad, @ModelAttribute("cifEmpresa") String cif) {
        if (comprobarRoles(model, session, entidad, cif)) return "redirect:/menu";

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
                             @ModelAttribute("cifEmpresa") String cif, @ModelAttribute("rol") String rolSeleccionado,
                             @RequestParam("btnRegistro") String boton) {

        Date sqlFechaNacimiento = new Date(fechaNacimiento.getTime());

        if (email == null || password == null || email.isBlank() || password.isBlank()) return "registro";
        //TODO añadir control de errores para los parámetros que no deberían ser nulos
        String urlTo = "enespera";
        if ("empresa".equals(entidad)) {
            if (cif == null || cif.isBlank()) return "registro";
            EmpresaEntityDTO empresa = empresaService.buscaCif(Integer.valueOf(cif));
            if (empresa == null) {
                empresa = new EmpresaEntityDTO();
                empresa.setCif(Integer.valueOf(cif));

                ClienteEntityDTO clienteEmpresa = new ClienteEntityDTO();
                DireccionEntityDTO direccion = direccionService.creaDireccion(calleEmpresa, Integer.valueOf(numeroEmpresa), plantaEmpresa, ciudadEmpresa, regionEmpresa, paisEmpresa, postalEmpresa);
                clienteEmpresa.setDireccionByDireccion(direccion);
                direccionService.guardar(direccion);
                clienteEmpresa.setDireccionByDireccion(direccion);
                clienteService.guardar(clienteEmpresa);

                empresa.setClienteByCliente(clienteEmpresa);
                empresaService.guardar(empresa);
            }
            if (boton.equals("registrarSocio")) {
                model.addAttribute("entidad", "empresa");
                urlTo = "redirect:/registro?entidad=empresa&cif=" + cif;
            }

            // Creo al socio/autorizado
            UsuarioEntityDTO usuarioEmpresa = usuarioService.creaUsuario(NIF, nombre, segundoNombre, primerApellido, segundoApellido, sqlFechaNacimiento, email, password);

            // TODO check for existing users
            ClienteEntityDTO cliente = new ClienteEntityDTO();
            DireccionEntityDTO direccion = direccionService.creaDireccion(calle, Integer.valueOf(numero), planta, ciudad, region, pais, postal);
            direccionService.guardar(direccion);
            cliente.setDireccionByDireccion(direccion);
            clienteService.guardar(cliente);

            usuarioEmpresa.setClienteByCliente(cliente);
            usuarioService.guardar(usuarioEmpresa);

            RolusuarioEntityDTO rolusuario = new RolusuarioEntityDTO();
            RolEntityDTO rol = rolService.buscaNombre(rolSeleccionado);
            rolusuario.setRolByIdrol(rol);
            rolusuario.setUsuarioByIdusuario(usuarioEmpresa);
            rolusuario.setEmpresaByIdempresa(empresa);
            rolusuario.setBloqueado((byte) 0);
            rolusuarioService.guardar(rolusuario);

            session.setAttribute("empresa", empresa);
        } else {
            UsuarioEntityDTO usuario = usuarioService.creaUsuario(NIF, nombre, segundoNombre, primerApellido, segundoApellido, sqlFechaNacimiento, email, password);

            // TODO check for existing users

            ClienteEntityDTO cliente = new ClienteEntityDTO();
            DireccionEntityDTO direccion = direccionService.creaDireccion(calle, Integer.valueOf(numero), planta, ciudad, region, pais, postal);
            direccionService.guardar(direccion);
            cliente.setDireccionByDireccion(direccion);
            clienteService.guardar(cliente);

            usuario.setClienteByCliente(cliente);
            usuarioService.guardar(usuario);

            RolusuarioEntityDTO rolusuario = new RolusuarioEntityDTO();
            RolEntityDTO rol = rolService.buscaNombre("cliente");
            rolusuario.setRolByIdrol(rol);
            rolusuario.setUsuarioByIdusuario(usuario);
            rolusuario.setBloqueado((byte) 0);
            rolusuarioService.guardar(rolusuario);

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

        UsuarioEntityDTO user = usuarioService.buscaEmail(email);
        if (user == null) return "login";
        if (!user.getPassword().equals(password)) return "login";

        if ("empresa".equals(entidad)) {
            if (cif == null || cif.isBlank()) return "login";
            List<RolEntityDTO> rolesConPermiso = rolService.buscaNombres("socio", "autorizado");

            var roles = rolService.buscaNombresPorUsuarioYCif(user, Integer.valueOf(cif));

            roles.retainAll(rolesConPermiso);
            if (roles.isEmpty()) {
                model.addAttribute("error", "No tienes permiso en la empresa seleccionada.");
                return "login";
            }

            session.setAttribute("empresa", empresaService.buscaCif(Integer.valueOf(cif)));
        } else {
            List<RolEntityDTO> rolesConPermiso = rolService.buscaNombres("cliente", "asistente", "gestor");

            var roles = rolService.buscaNombresPorUsuarioSinEmpresa(user);
            roles.retainAll(rolesConPermiso);
            if (roles.isEmpty()) {
                model.addAttribute("error", "Credenciales incorrectas. Si eres socio o autorizado, usa el login de empresa.");
                return "login";
            }
            session.setAttribute("empresa", null);
        }
        session.setAttribute("usuario", user);

        session.setAttribute("nombresRoles", rolService.getNombresRoles(user));


        return "redirect:/menu";
    }


    @GetMapping("/menu")
    String doMenu(HttpSession session, Model model) {
        UsuarioEntityDTO user = (UsuarioEntityDTO) session.getAttribute("usuario");
        if (user == null) return "redirect:/logout";
        List<RolusuarioEntityDTO> ru = user.getRolusuariosById();

        // Si pasa, se ha hecho mal alguna inserción
        if (ru == null || ru.isEmpty()) return "redirect:/logout";

        if (session.getAttribute("menu") == null)
            session.setAttribute("menu", "normal");
        var nombresRoles = rolService.getNombresRoles(user);

        if (nombresRoles.contains("asistente"))
            return "redirect:/chats/asistente";
        String mensaje = (String) session.getAttribute("mensaje");
        if (mensaje != null && !mensaje.isBlank()) {
            model.addAttribute("mensaje", mensaje);
            session.removeAttribute("mensaje");
        } else model.addAttribute("mensaje", "");
        if (nombresRoles.contains("gestor")) return "redirect:/gestor/";

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
        direccionService.vaciarBBDD();
        DireccionEntityDTO d1, d2, d3, d4;
        d1 = direccionService.creaDireccion("Calle Falsa", 123, "1ºA", "Madrid", "Madrid", "España", "28001");
        d2 = direccionService.creaDireccion("Calle Imaginaria", 456, "5ºC", "Barcelona", "Barcelona", "España", "08001");
        d3 = direccionService.creaDireccion("Calle Inventada", 789, "4ºB", "Sevilla", "Sevilla", "España", "41001");
        d4 = direccionService.creaDireccion("Calle A", 0, "A-A", "A", "Almería", "España", "12345");
        direccionService.guardar(d1, d2, d3, d4);


        // Clientes
        clienteService.vaciarBBDD();
        ClienteEntityDTO c1 = new ClienteEntityDTO(), c2 = new ClienteEntityDTO(), c3 = new ClienteEntityDTO(), c4 = new ClienteEntityDTO(), ce1 = new ClienteEntityDTO();
        c1.setDireccionByDireccion(d1);
        c2.setDireccionByDireccion(d2);
        c3.setDireccionByDireccion(d3);
        c4.setDireccionByDireccion(d3); //c3 y c4 viven juntos
        ce1.setDireccionByDireccion(d4);
        clienteService.guardar(c1, c2, c3, c4, ce1);


        // Usuarios
        usuarioService.vaciarBBDD();
        UsuarioEntityDTO u1, u2, u3, u4, u5, u6;
        u1 = usuarioService.creaUsuario("12345678A", "Juan", "Antonio", "García", "Pérez",
                Date.valueOf("1980-01-01"), "juan.garcia@bancapp.es", "contraseña");
        u2 = usuarioService.creaUsuario("23456789B", "María", null, "Rodríguez", "Fernández",
                Date.valueOf("1990-05-12"), "maria.rodriguez@bancapp.es", "contraseña");
        u3 = usuarioService.creaUsuario("34567890C", "Juan", null, "Sánchez", "García",
                Date.valueOf("1985-11-21"), "juan.sanchez@bancapp.es", "contraseña");
        u4 = usuarioService.creaUsuario("99999999A", "Alberto", null, "Álvarez", "Alarcón",
                Date.valueOf("2000-11-22"), "a@bancapp.es", "a");
        u5 = usuarioService.creaUsuario("98765432R", "Kevin", null, "Fernández", "Carrión",
                Date.valueOf("1952-10-24"), "kfc@bancapp.es", "pollopollo");
        u6 = usuarioService.creaUsuario("34567890C", "Marta", null, "Sánchez", "García",
                Date.valueOf("1987-04-04"), "marta.sanchez@bancapp.es", "contraseña");
        u1.setClienteByCliente(c1);
        u2.setClienteByCliente(c2);
        u3.setClienteByCliente(c3);
        u6.setClienteByCliente(c4);
        usuarioService.guardar(u1, u2, u3, u4, u5, u6);


        // Empresas
        empresaService.vaciarBBDD();
        EmpresaEntityDTO e1 = new EmpresaEntityDTO();
        e1.setClienteByCliente(ce1);
        e1.setCif(1472583690);
        empresaService.guardar(e1);


        // Cuentas
        cuentaService.vaciarBBDD();
        CuentaEntityDTO cu1 = new CuentaEntityDTO(), cu2 = new CuentaEntityDTO(), cu3 = new CuentaEntityDTO();
        cu1.setClienteByCliente(c1);
        cu2.setClienteByCliente(c2);
        cu3.setClienteByCliente(ce1);
        cu1.setSaldo(1000.50);
        cu2.setSaldo(500.00);
        cu3.setSaldo(0.00);
        cu1.setActiva((byte) 0);
        cu2.setActiva((byte) 1);
        cu3.setActiva((byte) 1);
        cuentaService.guardar(cu1, cu2, cu3);


        // Operaciones
        operacionService.vaciarBBDD();
        OperacionEntityDTO o1 = new OperacionEntityDTO(), o2 = new OperacionEntityDTO(),
                o3 = new OperacionEntityDTO(), o4 = new OperacionEntityDTO();

        o1.setCuentaByCuentaRealiza(cu1);
        o2.setCuentaByCuentaRealiza(cu2);
        o3.setCuentaByCuentaRealiza(cu1);
        o4.setCuentaByCuentaRealiza(cu3);
        o1.setFecha(Date.valueOf("2022-04-01"));
        o2.setFecha(Date.valueOf("2022-05-15"));
        o3.setFecha(Date.valueOf("2022-06-30"));
        o4.setFecha(Date.valueOf("2021-05-04"));

        operacionService.guardar(o1, o2, o3, o4);


        // Cambios de divisa todo test
        cambDivisaService.vaciarBBDD();
        CambDivisaEntityDTO cd1 = new CambDivisaEntityDTO(), cd2 = new CambDivisaEntityDTO();
        cd1.setOperacionByOperacion(o1);
        cd2.setOperacionByOperacion(o2);
        cd1.setOrigen("EUR");
        cd2.setOrigen("EUR");
        cd1.setDestino("USD");
        cd2.setDestino("GBP");
        cd1.setCantidad(7.25);
        cd2.setCantidad(20.00);

        cambDivisaService.guardar(cd1, cd2);

        // Transferencias
        transferenciaService.vaciarBBDD();
        TransferenciaEntityDTO t1 = new TransferenciaEntityDTO();
        t1.setCuentaByCuentaDestino(cu2);
        t1.setOperacionByOperacion(o3);
        t1.setCantidad(150.00);

        transferenciaService.guardar(t1);


        // Extracciones
        extraccionService.vaciarBBDD();
        ExtraccionEntityDTO extra1 = new ExtraccionEntityDTO();
        extra1.setCantidad(15.00);
        extra1.setOperacionByOperacion(o4);

        extraccionService.guardar(extra1);


        // Rolusuarios
        rolusuarioService.vaciarBBDD();
        RolusuarioEntityDTO ru1 = new RolusuarioEntityDTO(), ru2 = new RolusuarioEntityDTO(), ru3 = new RolusuarioEntityDTO(),
                ru4 = new RolusuarioEntityDTO(), ru5 = new RolusuarioEntityDTO(), ru6 = new RolusuarioEntityDTO();
        ru1.setUsuarioByIdusuario(u1);
        ru2.setUsuarioByIdusuario(u2);
        ru3.setUsuarioByIdusuario(u3);
        ru4.setUsuarioByIdusuario(u4);
        ru5.setUsuarioByIdusuario(u5);
        ru6.setUsuarioByIdusuario(u6);

        List<RolEntityDTO> roles = rolService.buscaNombres("cliente", "asistente", "gestor", "socio", "autorizado");
        RolEntityDTO rolCliente = roles.get(0);
        RolEntityDTO rolAsistente = roles.get(1);
        RolEntityDTO rolGestor = roles.get(2);
        RolEntityDTO rolSocio = roles.get(3);
        RolEntityDTO rolAutorizado = roles.get(4);

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

        rolusuarioService.guardar(ru1, ru2, ru3, ru4, ru5, ru6);

        return "redirect:/";
    }
}
