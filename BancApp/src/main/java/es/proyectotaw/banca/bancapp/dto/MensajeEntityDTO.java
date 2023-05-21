package es.proyectotaw.banca.bancapp.dto;

import es.proyectotaw.banca.bancapp.entity.MensajeEntity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * A DTO for the {@link es.proyectotaw.banca.bancapp.entity.MensajeEntity} entity
 *
 * @author Daniel García Rodríguez (method {@link #toEntity()})
 */
@SuppressWarnings("unused")
public class MensajeEntityDTO implements Serializable {
    private Integer idMensaje;
    private Timestamp fechaHora;
    private String contenido;
    private ChatEntityDTO chatByChat;
    private UsuarioEntityDTO usuarioByEmisor;

    public MensajeEntityDTO() {
    }

    public MensajeEntityDTO(Integer idMensaje, Timestamp fechaHora, String contenido, ChatEntityDTO chatByChat, UsuarioEntityDTO usuarioByEmisor) {
        this.idMensaje = idMensaje;
        this.fechaHora = fechaHora;
        this.contenido = contenido;
        this.chatByChat = chatByChat;
        this.usuarioByEmisor = usuarioByEmisor;
    }

    public Integer getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(Integer idMensaje) {
        this.idMensaje = idMensaje;
    }

    public Timestamp getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Timestamp fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public ChatEntityDTO getChatByChat() {
        return chatByChat;
    }

    public void setChatByChat(ChatEntityDTO chatByChat) {
        this.chatByChat = chatByChat;
    }

    public UsuarioEntityDTO getUsuarioByEmisor() {
        return usuarioByEmisor;
    }

    public void setUsuarioByEmisor(UsuarioEntityDTO usuarioByEmisor) {
        this.usuarioByEmisor = usuarioByEmisor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MensajeEntityDTO entity = (MensajeEntityDTO) o;
        return Objects.equals(this.idMensaje, entity.idMensaje) &&
                Objects.equals(this.fechaHora, entity.fechaHora) &&
                Objects.equals(this.contenido, entity.contenido) &&
                Objects.equals(this.chatByChat, entity.chatByChat) &&
                Objects.equals(this.usuarioByEmisor, entity.usuarioByEmisor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMensaje, fechaHora, contenido, chatByChat, usuarioByEmisor);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "idMensaje = " + idMensaje + ", " +
                "fechaHora = " + fechaHora + ", " +
                "contenido = " + contenido + ", " +
                "chatByChat = " + chatByChat + ", " +
                "usuarioByEmisor = " + usuarioByEmisor + ")";
    }

    public MensajeEntity toEntity() {
        MensajeEntity mensajeEntity = new MensajeEntity();
        mensajeEntity.setIdMensaje(idMensaje);
        mensajeEntity.setChatByChat(chatByChat.toEntity());
        mensajeEntity.setContenido(contenido);
        mensajeEntity.setFechaHora(fechaHora);
        mensajeEntity.setUsuarioByEmisor(usuarioByEmisor.toEntity());

        return mensajeEntity;
    }
}