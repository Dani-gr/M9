package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("unused")
@Entity
@Table(name = "chat", schema = "bancodb")
public class ChatEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "activo")
    private Byte activo;
    @ManyToOne
    @JoinColumn(name = "asistente_id", referencedColumnName = "id", nullable = false)
    private UsuarioEntity usuarioByAsistenteId;
    @ManyToOne
    @JoinColumn(name = "cliente_id_cliente", referencedColumnName = "id_cliente", nullable = false)
    private ClienteEntity clienteByClienteIdCliente;
    @OneToMany(mappedBy = "chatByChat")
    private List<MensajeEntity> mensajesById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getActivo() {
        return activo;
    }

    public void setActivo(Byte activo) {
        this.activo = activo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatEntity that = (ChatEntity) o;

        if (!Objects.equals(id, that.id)) return false;
        return Objects.equals(activo, that.activo);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (activo != null ? activo.hashCode() : 0);
        return result;
    }

    public UsuarioEntity getUsuarioByAsistenteId() {
        return usuarioByAsistenteId;
    }

    public void setUsuarioByAsistenteId(UsuarioEntity usuarioByAsistenteId) {
        this.usuarioByAsistenteId = usuarioByAsistenteId;
    }

    public ClienteEntity getClienteByClienteIdCliente() {
        return clienteByClienteIdCliente;
    }

    public void setClienteByClienteIdCliente(ClienteEntity clienteByClienteIdCliente) {
        this.clienteByClienteIdCliente = clienteByClienteIdCliente;
    }

    public List<MensajeEntity> getMensajesById() {
        return mensajesById;
    }

    public void setMensajesById(List<MensajeEntity> mensajesById) {
        this.mensajesById = mensajesById;
    }
}
