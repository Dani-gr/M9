package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "chat", schema = "bancodb", catalog = "")
public class ChatEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "Asistente_ID", referencedColumnName = "ID", nullable = false)
    private UsuarioEntity usuarioByAsistenteId;
    @ManyToOne
    @JoinColumn(name = "Cliente_ID_Cliente", referencedColumnName = "ID_Cliente", nullable = false)
    private ClienteEntity clienteByClienteIdCliente;
    @OneToMany(mappedBy = "chatByChat")
    private List<MensajeEntity> mensajesById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatEntity that = (ChatEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
