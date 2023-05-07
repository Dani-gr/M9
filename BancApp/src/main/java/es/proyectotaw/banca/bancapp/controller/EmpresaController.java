package es.proyectotaw.banca.bancapp.controller;


import es.proyectotaw.banca.bancapp.dao.EmpresaEntityRepository;
import es.proyectotaw.banca.bancapp.dao.RolEntityRepository;
import es.proyectotaw.banca.bancapp.dao.RolusuarioEntityRepository;
import es.proyectotaw.banca.bancapp.dao.UsuarioEntityRepository;
import es.proyectotaw.banca.bancapp.entity.EmpresaEntity;
import es.proyectotaw.banca.bancapp.entity.RolEntity;
import es.proyectotaw.banca.bancapp.entity.RolusuarioEntity;
import es.proyectotaw.banca.bancapp.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
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
}
