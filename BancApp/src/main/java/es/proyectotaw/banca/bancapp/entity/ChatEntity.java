package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "chat", schema = "bancodb", catalog = "")
public class ChatEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatEntity that = (ChatEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
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
