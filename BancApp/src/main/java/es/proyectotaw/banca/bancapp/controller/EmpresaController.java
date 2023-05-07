package es.proyectotaw.banca.bancapp.controller;


import es.proyectotaw.banca.bancapp.dao.*;
import es.proyectotaw.banca.bancapp.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    UsuarioEntityRepository usuarioEntityRepository;

    @Autowired
    RolEntityRepository rolEntityRepository;
    @Autowired
    private RolusuarioEntityRepository rolusuarioEntityRepository;
    @Autowired
    private EmpresaEntityRepository empresaEntityRepository;

    @Autowired
    private DireccionEntityRepository direccionEntityRepository;

    @Autowired
    private ClienteEntityRepository clienteEntityRepository;
    @GetMapping("/")
    public String doMostrar(Model model, HttpSession session){
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");

        List<RolusuarioEntity> listaRolUsuarioAsociados = usuario.getRolusuariosById();
        EmpresaEntity empresa = listaRolUsuarioAsociados.get(0).getEmpresaByIdempresa();
        Optional<UsuarioEntity> usuariosAsociados = usuarioEntityRepository.findUsuariosByEmpresa(empresa.getIdEmpresa());
        Optional<UsuarioEntity> usuariosBloqueados = usuarioEntityRepository.findUsuariosBloqueadosByEmpresa(empresa.getIdEmpresa());
        model.addAttribute("usuariosAsociados", usuariosAsociados);
        model.addAttribute("usuariosBloqueados", usuariosBloqueados);


        return "gestionSociosYAut";
    }

    @GetMapping("/cambiarRol")
    public String doCambiarRoles(@RequestParam("id") Integer id, @RequestParam("rol") Integer idRol){

        UsuarioEntity usuario = usuarioEntityRepository.getById(id);
        RolusuarioEntity rolusuario = usuario.getRolusuariosById().get(0);
        RolEntity rolActual = rolEntityRepository.getById(idRol);
        RolEntity rolNuevo = null;
        if(rolActual.getNombre().equals("socio")){
            Optional<RolEntity> rolOptional = rolEntityRepository.findByNombre("autorizado");
            if(rolOptional.isPresent()){
                rolNuevo = rolOptional.get();
            }
        } else {
            Optional<RolEntity> rolOptional = rolEntityRepository.findByNombre("socio");
            if(rolOptional.isPresent()) {
                rolNuevo = rolOptional.get();
            }
        }
        rolusuario.setRolByIdrol(rolNuevo);
        rolusuarioEntityRepository.save(rolusuario);
        return "redirect:/empresa/";
    }

    @GetMapping("/bloquearUsuario")
    public String doBloquearUsuario(@RequestParam("id") Integer id){
        UsuarioEntity usuario = usuarioEntityRepository.getById(id);
        usuario.getRolusuariosById().get(0).setBloqueado((byte) 1);
        usuarioEntityRepository.save(usuario);
        return "redirect:/empresa/";
    }

    @GetMapping("/addSocios")
    public String doAñadirSocios(Model model, HttpSession session, @ModelAttribute("entidad") String entidad, @ModelAttribute("userNIF") String NIF,
                                 @ModelAttribute("userNombre") String nombre, @ModelAttribute("userNombreSegundo") String segundoNombre, @ModelAttribute("userApellidoPrimero") String primerApellido,
                                 @ModelAttribute("userApellidoSegundo") String segundoApellido, @ModelAttribute("userFechaNacimiento") java.util.Date fechaNacimiento, @ModelAttribute("userEmail") String email,
                                 @ModelAttribute("userPassword") String password, @ModelAttribute("direccionCalle") String calle, @ModelAttribute("direccionNumero") String numero,
                                 @ModelAttribute("direccionPlanta") String planta, @ModelAttribute("direccionCiudad") String ciudad, @ModelAttribute("direccionRegion") String region,
                                 @ModelAttribute("direccoinPais") String pais, @ModelAttribute("direccionPostal") String postal, @RequestParam("btnRegistro") String boton, @ModelAttribute("rol") String rolSeleccionado){

        java.sql.Date sqlFechaNacimiento = new java.sql.Date(fechaNacimiento.getTime());
        UsuarioEntity usuarioEmpresa = new UsuarioEntity();
        usuarioEmpresa.construct(NIF, nombre, segundoNombre, primerApellido, segundoApellido, sqlFechaNacimiento, email, password);

        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");

        List<RolusuarioEntity> listaRolUsuarioAsociados = usuario.getRolusuariosById();
        EmpresaEntity empresa = listaRolUsuarioAsociados.get(0).getEmpresaByIdempresa();

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
        return "registroNuevoSocio";
    }
}
