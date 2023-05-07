package es.proyectotaw.banca.bancapp.controller;


import es.proyectotaw.banca.bancapp.dao.ChatEntityRepository;
import es.proyectotaw.banca.bancapp.dao.ClienteEntityRepository;
import es.proyectotaw.banca.bancapp.dao.MensajeEntityRepository;
import es.proyectotaw.banca.bancapp.dao.UsuarioEntityRepository;
import es.proyectotaw.banca.bancapp.entity.ChatEntity;
import es.proyectotaw.banca.bancapp.entity.ClienteEntity;
import es.proyectotaw.banca.bancapp.entity.MensajeEntity;
import es.proyectotaw.banca.bancapp.entity.UsuarioEntity;
import net.bytebuddy.TypeCache;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    @PostMapping("/solicitudAsistencia")
    String doInicializarChatConAsistente(@RequestParam("usuario") int usuarioId, Model model){
        //TODO: Crear el chat con el asistente
        UsuarioEntity user = (UsuarioEntity) usuarioEntityRepository.findById(usuarioId).get();
        //TODO: Crear chat
            // TODO: asignar asistente en base a algun criterio
            // TODO: asignar valores de un chat
            // TODO: guardar este chat creado
        //model.addAttribute("chats",chats);
        return "chats"; //TODO: Redirigir al chat (vista cliente??)
    }
    @GetMapping("/")
    String doInicializarListaChats(@RequestParam("cliente") int id, Model model){
        List<ChatEntity> chats = (List<ChatEntity>) chatEntityRepository.findByClienteByClienteIdCliente_IdCliente(id);
        model.addAttribute("chats", chats);
        return "chats";
    }
    @GetMapping("/detallesChat/{id}")
    String doMostrarChat(@PathVariable(value = "id") int id, Model model){
        Sort sortByFechaDesc = Sort.by("fechaHora").ascending();
        ChatEntity chat = chatEntityRepository.getById(id);
        List<MensajeEntity> msgs = mensajeEntityRepository.findByChatByChat_Id(chat.getId(),sortByFechaDesc);
        model.addAttribute("mensajes", msgs);
        model.addAttribute("chat",chat);
        return "chatDetails";
    }
    @GetMapping("/cerrarChat/{id}")
    String doCerrarChat(@PathVariable(value = "id") int id, Model model){
        chatEntityRepository.updateActivoById((byte) 0, id);
        return "redirect:/chats/?cliente=" + chatEntityRepository.findById(id).get().getClienteByClienteIdCliente().getIdCliente();
    }

    @GetMapping("/busquedaChatsPorNombre")
    String doMostrarChatsPorNombre(@RequestParam("nombre") String nombre, Model model){
        List<ChatEntity> chats = chatEntityRepository.findByClienteByClienteIdCliente_UsuariosByIdCliente_PrimerNombre(nombre);
        model.addAttribute("chats", chats);
        return "chats";
    }

    @GetMapping("/filtrarPorActivo")
    String doFiltrarChatsPorActividad(@RequestParam("filtro") String filtro, Model model){
        List<ChatEntity> chats = null;
        if(filtro.equals("Abiertos")){
            chats = (List<ChatEntity>) chatEntityRepository.findAllByActivo((byte) 1);
        }else if (filtro.equals("Cerrados")){
            chats = (List<ChatEntity>) chatEntityRepository.findAllByActivo((byte) 0);
        } else if (filtro.equals("OrdenPrimeroAbiertos")) {
            Sort sortByActivo = Sort.by("activo").descending();
            chats = (List<ChatEntity>) chatEntityRepository.findAll(sortByActivo);
        } else if (filtro.equals("OrdenPrimeroCerrados")) {
            Sort sortByActivo = Sort.by("activo").ascending();
            chats = (List<ChatEntity>) chatEntityRepository.findAll(sortByActivo);
        } else if (filtro.equals("OrdenAlfabeticoAsistente")) {
            Sort sortByAsistente = Sort.by("usuarioByAsistenteId.primerNombre").ascending();
            chats = (List<ChatEntity>)  chatEntityRepository.findAll(sortByAsistente);
        } else {
            return "redirect:/chats/asistente";
        }
        model.addAttribute("chats", chats);
        return "chats";
    }
    @PostMapping("/crearMensaje")
    String doEnviarMensaje(@RequestParam("mensaje") String mensaje,
                           @RequestParam("idChat") int chat,
                           @RequestParam("idUsuario") int user,
                           @RequestParam("rol") String rol,
                           Model model){
        MensajeEntity mens = new MensajeEntity();
        if(rol.equals("Asistente")){
            UsuarioEntity userEmisor = (UsuarioEntity) usuarioEntityRepository.findById(user).get();
            mens.setUsuarioByEmisor(userEmisor);
        }else{
            ClienteEntity cliente = (ClienteEntity) clienteEntityRepository.findById(user).get();
            mens.setUsuarioByEmisor(cliente.getUsuariosByIdCliente().get(0));
        }
        mens.setContenido(mensaje);
        ChatEntity chatDestino = (ChatEntity) chatEntityRepository.findById(chat).get();
        mens.setChatByChat(chatDestino);
        LocalDateTime time = java.time.LocalDateTime.now();
        mens.setFechaHora(Timestamp.valueOf(time));
        mensajeEntityRepository.saveAndFlush(mens);
        return "redirect:/chats/detallesChat/" + chat;
    }
}
