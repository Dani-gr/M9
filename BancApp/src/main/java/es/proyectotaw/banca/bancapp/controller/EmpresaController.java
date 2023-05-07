package es.proyectotaw.banca.bancapp.controller;


import es.proyectotaw.banca.bancapp.dao.*;
import es.proyectotaw.banca.bancapp.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        List<UsuarioEntity> usuariosAsociados = usuarioEntityRepository.findUsuariosByEmpresa(empresa.getIdEmpresa());
        List<UsuarioEntity> usuariosBloqueados = usuarioEntityRepository.findUsuariosBloqueadosByEmpresa(empresa.getIdEmpresa());
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

    @GetMapping("/datosEmpresa")
    public String soVerDatosEmpresa(Model model, HttpSession session){
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
        EmpresaEntity empresa = usuario.getRolusuariosById().get(0).getEmpresaByIdempresa();
        model.addAttribute("empresa", empresa);
        return "datosEmpresa";
    }

    @PostMapping("/guardar")
    public String doGuardar (@ModelAttribute("empresa") EmpresaEntity empresa) {
        this.empresaEntityRepository.save(empresa);
        return "redirect:/datosEmpresa";
    }

    @GetMapping("/operaciones")
    public String doMostrarOperaciones(Model model, HttpSession session){
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");

        List<RolusuarioEntity> listaRolUsuarioAsociados = usuario.getRolusuariosById();
        EmpresaEntity empresa = listaRolUsuarioAsociados.get(0).getEmpresaByIdempresa();
        List<UsuarioEntity> usuariosAsociados = usuarioEntityRepository.findUsuariosByEmpresa(empresa.getIdEmpresa());
        List<UsuarioEntity> usuariosBloqueados = usuarioEntityRepository.findUsuariosBloqueadosByEmpresa(empresa.getIdEmpresa());
        model.addAttribute("usuariosAsociados", usuariosAsociados);
        model.addAttribute("usuariosBloqueados", usuariosBloqueados);

        return "operaciones";
    }

}
