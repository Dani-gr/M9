package es.proyectotaw.banca.bancapp.controller;


import es.proyectotaw.banca.bancapp.dao.ChatEntityRepository;
import es.proyectotaw.banca.bancapp.dao.ClienteEntityRepository;
import es.proyectotaw.banca.bancapp.dao.MensajeEntityRepository;
import es.proyectotaw.banca.bancapp.dao.UsuarioEntityRepository;
import es.proyectotaw.banca.bancapp.entity.ChatEntity;
import es.proyectotaw.banca.bancapp.entity.MensajeEntity;
import es.proyectotaw.banca.bancapp.entity.UsuarioEntity;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
    @Autowired
    protected MensajeEntityRepository mensajeEntityRepository;

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

    @GetMapping("/busquedaChatsPorNombre")
    String doMostrarChatsPorNombre(@RequestParam("nombre") String nombre, Model model){
        List<ChatEntity> chats = chatEntityRepository.findByClienteByClienteIdCliente_UsuariosByIdCliente_PrimerNombre(nombre);
        model.addAttribute("chats", chats);
        return "chats";
    }
    @PostMapping("/crearMensaje")
    String doEnviarMensaje(@RequestParam("mensaje") String mensaje,
                           @RequestParam("idChat") int chat,
                           @RequestParam("idUsuario") int user,
                           Model model){
        MensajeEntity mens = new MensajeEntity();
        UsuarioEntity userEmisor = (UsuarioEntity) usuarioEntityRepository.findById(user).get();
        mens.setUsuarioByEmisor(userEmisor);
        mens.setContenido(mensaje);
        ChatEntity chatDestino = (ChatEntity) chatEntityRepository.findById(chat).get();
        mens.setChatByChat(chatDestino);
        LocalDateTime time = java.time.LocalDateTime.now();
        mens.setFechaHora(Timestamp.valueOf(time));
        mens.setUsuarioByEmisor(userEmisor);
        mensajeEntityRepository.saveAndFlush(mens);
        return "redirect:/chats/detallesChat/" + chat;
    }
}
