package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@SuppressWarnings("unused")
@Entity
@Table(name = "mensaje", schema = "bancodb")
public class MensajeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_mensaje")
    private Integer idMensaje;
    @Basic
    @Column(name = "fecha_hora")
    private Date fechaHora;
    @Basic
    @Column(name = "contenido")
    private String contenido;
    @Basic
    @Column(name = "emisor", insertable=false, updatable=false)
    private Integer emisor;
    @ManyToOne
    @JoinColumn(name = "chat", referencedColumnName = "id", nullable = false)
    private ChatEntity chatByChat;
    @ManyToOne
    @JoinColumn(name = "emisor", referencedColumnName = "id", nullable = false)
    private UsuarioEntity usuarioByEmisor;

    public Integer getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(Integer idMensaje) {
        this.idMensaje = idMensaje;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Integer getEmisor() {
        return emisor;
    }

    public void setEmisor(Integer emisor) {
        this.emisor = emisor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MensajeEntity that = (MensajeEntity) o;

        if (!Objects.equals(idMensaje, that.idMensaje)) return false;
        if (!Objects.equals(fechaHora, that.fechaHora)) return false;
        if (!Objects.equals(contenido, that.contenido)) return false;
        return Objects.equals(emisor, that.emisor);
    }

    @Override
    public int hashCode() {
        int result = idMensaje != null ? idMensaje.hashCode() : 0;
        result = 31 * result + (fechaHora != null ? fechaHora.hashCode() : 0);
        result = 31 * result + (contenido != null ? contenido.hashCode() : 0);
        result = 31 * result + (emisor != null ? emisor.hashCode() : 0);
        return result;
    }

    public ChatEntity getChatByChat() {
        return chatByChat;
    }

    public void setChatByChat(ChatEntity chatByChat) {
        this.chatByChat = chatByChat;
    }

    public UsuarioEntity getUsuarioByEmisor() {
        return usuarioByEmisor;
    }

    public void setUsuarioByEmisor(UsuarioEntity usuarioByEmisor) {
        this.usuarioByEmisor = usuarioByEmisor;
    }
}
