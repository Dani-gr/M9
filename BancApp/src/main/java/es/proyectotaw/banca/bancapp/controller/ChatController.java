package es.proyectotaw.banca.bancapp.controller;


import es.proyectotaw.banca.bancapp.dao.ClienteEntityRepository;
import es.proyectotaw.banca.bancapp.dao.UsuarioEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chats")
public class ChatController {
    @Autowired
    protected ClienteEntityRepository clienteEntityRepository;
    @Autowired
    protected UsuarioEntityRepository usuarioEntityRepository;

    @GetMapping("/")
    String doInicializarListaChats(Model model){

        return "chats";
    }
}
