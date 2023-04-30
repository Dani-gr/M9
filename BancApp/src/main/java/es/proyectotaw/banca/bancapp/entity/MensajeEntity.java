package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "mensaje", schema = "bancodb", catalog = "")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MensajeEntity that = (MensajeEntity) o;

        if (idMensaje != null ? !idMensaje.equals(that.idMensaje) : that.idMensaje != null) return false;
        if (fechaHora != null ? !fechaHora.equals(that.fechaHora) : that.fechaHora != null) return false;
        if (contenido != null ? !contenido.equals(that.contenido) : that.contenido != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idMensaje != null ? idMensaje.hashCode() : 0;
        result = 31 * result + (fechaHora != null ? fechaHora.hashCode() : 0);
        result = 31 * result + (contenido != null ? contenido.hashCode() : 0);
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
