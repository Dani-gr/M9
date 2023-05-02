package es.proyectotaw.banca.bancapp.controller;

import es.proyectotaw.banca.bancapp.dao.ClienteRepository;
import es.proyectotaw.banca.bancapp.dao.UsuarioRepository;
import es.proyectotaw.banca.bancapp.entity.ClienteEntity;
import es.proyectotaw.banca.bancapp.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    protected ClienteRepository clienteRepository;

    @Autowired
    protected UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public String doPasarAlMenu(Model model){
        //se pasa al menú solo y solo si tenemos el email y la contraseña almacenado
        String urlTo="menu";
        model.addAttribute("usuario", new UsuarioEntity());
        //un cliente se pasa por el model
        return urlTo;
    }

    @GetMapping("/perfil")
    public String doVerPerfil(Model model, @RequestParam("idUsuario") Integer id) {
        UsuarioEntity usuario = usuarioRepository.buscarPorID(id);
        model.addAttribute("usuario", usuario);
        return "cliente";
    }
}
