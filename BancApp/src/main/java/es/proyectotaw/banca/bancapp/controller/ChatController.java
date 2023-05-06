package es.proyectotaw.banca.bancapp.controller;


import es.proyectotaw.banca.bancapp.dao.ChatEntityRepository;
import es.proyectotaw.banca.bancapp.dao.ClienteEntityRepository;
import es.proyectotaw.banca.bancapp.dao.UsuarioEntityRepository;
import es.proyectotaw.banca.bancapp.entity.ChatEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/chats")
public class ChatController {
    @Autowired
    protected ClienteEntityRepository clienteEntityRepository;
    @Autowired
    protected UsuarioEntityRepository usuarioEntityRepository;
    @Autowired
    protected ChatEntityRepository chatEntityRepository;

    @GetMapping("/asistente")
    String doInicializarListaChatsAsistente(Model model){
        List<ChatEntity> chats = chatEntityRepository.findAll();
        model.addAttribute("chats", chats);
        return "chats";
    }
    @GetMapping("/cliente")
    String doInicializarListaChats(Model model){
        //TODO: Extraer el id del cliente para buscar chats del mismo
        //model.addAttribute("chats", chats);
        return "chats";
    }
    @GetMapping("/detallesChat/{id}")
    String doMostrarChat(@PathVariable(value = "id") int id, Model model){
        ChatEntity chat = chatEntityRepository.getById(id);
        model.addAttribute("chat",chat);
        return "chatDetails";
    }
}
