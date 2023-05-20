package es.proyectotaw.banca.bancapp.dto;

import es.proyectotaw.banca.bancapp.entity.ChatEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link es.proyectotaw.banca.bancapp.entity.ChatEntity} entity
 *
 * @author Daniel García Rodríguez (method {@link #toEntity()})
 */
@SuppressWarnings("unused")
public class ChatEntityDTO implements Serializable {
    private Integer id;
    private Byte activo;
    private UsuarioEntityDTO usuarioByAsistenteId;
    private ClienteEntityDTO clienteByClienteIdCliente;
    private List<MensajeEntityDTO> mensajesById;

    public ChatEntityDTO() {
    }

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

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getActivo() {
        return activo;
    }

    public void setActivo(Byte activo) {
        this.activo = activo;
    }

    public UsuarioEntityDTO getUsuarioByAsistenteId() {
        return usuarioByAsistenteId;
    }

    public void setUsuarioByAsistenteId(UsuarioEntityDTO usuarioByAsistenteId) {
        this.usuarioByAsistenteId = usuarioByAsistenteId;
    }

    public ClienteEntityDTO getClienteByClienteIdCliente() {
        return clienteByClienteIdCliente;
    }

    public void setClienteByClienteIdCliente(ClienteEntityDTO clienteByClienteIdCliente) {
        this.clienteByClienteIdCliente = clienteByClienteIdCliente;
    }

    public List<MensajeEntityDTO> getMensajesById() {
        return mensajesById;
    }

    public void setMensajesById(List<MensajeEntityDTO> mensajesById) {
        this.mensajesById = mensajesById;
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