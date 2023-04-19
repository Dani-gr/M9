package es.proyectotaw.banca.bancapp.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

/**
 * A DTO for the {@link es.proyectotaw.banca.bancapp.entity.ChatEntity} entity
 */
public class ChatEntityDTO implements Serializable {
    private final Integer id;
    private final UsuarioEntityDTO usuarioByAsistenteId;
    private final ClienteEntityDTO clienteByClienteIdCliente;
    private final Collection<MensajeEntityDTO> mensajesById;

    public ChatEntityDTO(Integer id, UsuarioEntityDTO usuarioByAsistenteId, ClienteEntityDTO clienteByClienteIdCliente, Collection<MensajeEntityDTO> mensajesById) {
        this.id = id;
        this.usuarioByAsistenteId = usuarioByAsistenteId;
        this.clienteByClienteIdCliente = clienteByClienteIdCliente;
        this.mensajesById = mensajesById;
    }

    public Integer getId() {
        return id;
    }

    public UsuarioEntityDTO getUsuarioByAsistenteId() {
        return usuarioByAsistenteId;
    }

    public ClienteEntityDTO getClienteByClienteIdCliente() {
        return clienteByClienteIdCliente;
    }

    public Collection<MensajeEntityDTO> getMensajesById() {
        return mensajesById;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatEntityDTO entity = (ChatEntityDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.usuarioByAsistenteId, entity.usuarioByAsistenteId) &&
                Objects.equals(this.clienteByClienteIdCliente, entity.clienteByClienteIdCliente) &&
                Objects.equals(this.mensajesById, entity.mensajesById);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuarioByAsistenteId, clienteByClienteIdCliente, mensajesById);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "usuarioByAsistenteId = " + usuarioByAsistenteId + ", " +
                "clienteByClienteIdCliente = " + clienteByClienteIdCliente + ", " +
                "mensajesById = " + mensajesById + ")";
    }
}