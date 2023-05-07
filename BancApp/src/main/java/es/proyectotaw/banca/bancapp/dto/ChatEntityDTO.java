package es.proyectotaw.banca.bancapp.dto;

import es.proyectotaw.banca.bancapp.entity.ChatEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link es.proyectotaw.banca.bancapp.entity.ChatEntity} entity
 */
@SuppressWarnings("unused")
public class ChatEntityDTO implements Serializable {
    private final Integer id;
    private final Byte activo;
    private final UsuarioEntityDTO usuarioByAsistenteId;
    private final ClienteEntityDTO clienteByClienteIdCliente;
    private final List<MensajeEntityDTO> mensajesById;

    public ChatEntityDTO(Integer id, Byte activo, UsuarioEntityDTO usuarioByAsistenteId, ClienteEntityDTO clienteByClienteIdCliente, List<MensajeEntityDTO> mensajesById) {
        this.id = id;
        this.activo = activo;
        this.usuarioByAsistenteId = usuarioByAsistenteId;
        this.clienteByClienteIdCliente = clienteByClienteIdCliente;
        this.mensajesById = mensajesById;
    }

    public Integer getId() {
        return id;
    }

    public Byte getActivo() {
        return activo;
    }

    public UsuarioEntityDTO getUsuarioByAsistenteId() {
        return usuarioByAsistenteId;
    }

    public ClienteEntityDTO getClienteByClienteIdCliente() {
        return clienteByClienteIdCliente;
    }

    public List<MensajeEntityDTO> getMensajesById() {
        return mensajesById;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatEntityDTO entity = (ChatEntityDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.activo, entity.activo) &&
                Objects.equals(this.usuarioByAsistenteId, entity.usuarioByAsistenteId) &&
                Objects.equals(this.clienteByClienteIdCliente, entity.clienteByClienteIdCliente) &&
                Objects.equals(this.mensajesById, entity.mensajesById);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, activo, usuarioByAsistenteId, clienteByClienteIdCliente, mensajesById);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "activo = " + activo + ", " +
                "usuarioByAsistenteId = " + usuarioByAsistenteId + ", " +
                "clienteByClienteIdCliente = " + clienteByClienteIdCliente + ", " +
                "mensajesById = " + mensajesById + ")";
    }

    public ChatEntity toEntity() {
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setId(id);
        chatEntity.setActivo(activo);
        chatEntity.setClienteByClienteIdCliente(clienteByClienteIdCliente.toEntity());
        chatEntity.setUsuarioByAsistenteId(usuarioByAsistenteId.toEntity());

        return chatEntity;
    }
}