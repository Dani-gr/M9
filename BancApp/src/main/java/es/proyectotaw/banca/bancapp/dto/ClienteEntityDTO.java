package es.proyectotaw.banca.bancapp.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

/**
 * A DTO for the {@link es.proyectotaw.banca.bancapp.entity.ClienteEntity} entity
 */
public class ClienteEntityDTO implements Serializable {
    private final Integer idCliente;
    private final String direcc;
    private final Collection<ChatEntityDTO> chatsByIdCliente;
    private final DireccionEntityDTO direccionByDireccion;
    private final Collection<ClientesempresaEntityDTO> clientesempresasByIdCliente;
    private final Collection<CuentaEntityDTO> cuentasByIdCliente;
    private final Collection<EmpresaEntityDTO> empresasByIdCliente;
    private final UsuarioEntityDTO usuarioByIdCliente;

    public ClienteEntityDTO(Integer idCliente, String direcc, Collection<ChatEntityDTO> chatsByIdCliente, DireccionEntityDTO direccionByDireccion, Collection<ClientesempresaEntityDTO> clientesempresasByIdCliente, Collection<CuentaEntityDTO> cuentasByIdCliente, Collection<EmpresaEntityDTO> empresasByIdCliente, UsuarioEntityDTO usuarioByIdCliente) {
        this.idCliente = idCliente;
        this.direcc = direcc;
        this.chatsByIdCliente = chatsByIdCliente;
        this.direccionByDireccion = direccionByDireccion;
        this.clientesempresasByIdCliente = clientesempresasByIdCliente;
        this.cuentasByIdCliente = cuentasByIdCliente;
        this.empresasByIdCliente = empresasByIdCliente;
        this.usuarioByIdCliente = usuarioByIdCliente;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public String getDirecc() {
        return direcc;
    }

    public Collection<ChatEntityDTO> getChatsByIdCliente() {
        return chatsByIdCliente;
    }

    public DireccionEntityDTO getDireccionByDireccion() {
        return direccionByDireccion;
    }

    public Collection<ClientesempresaEntityDTO> getClientesempresasByIdCliente() {
        return clientesempresasByIdCliente;
    }

    public Collection<CuentaEntityDTO> getCuentasByIdCliente() {
        return cuentasByIdCliente;
    }

    public Collection<EmpresaEntityDTO> getEmpresasByIdCliente() {
        return empresasByIdCliente;
    }

    public UsuarioEntityDTO getUsuarioByIdCliente() {
        return usuarioByIdCliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteEntityDTO entity = (ClienteEntityDTO) o;
        return Objects.equals(this.idCliente, entity.idCliente) &&
                Objects.equals(this.direcc, entity.direcc) &&
                Objects.equals(this.chatsByIdCliente, entity.chatsByIdCliente) &&
                Objects.equals(this.direccionByDireccion, entity.direccionByDireccion) &&
                Objects.equals(this.clientesempresasByIdCliente, entity.clientesempresasByIdCliente) &&
                Objects.equals(this.cuentasByIdCliente, entity.cuentasByIdCliente) &&
                Objects.equals(this.empresasByIdCliente, entity.empresasByIdCliente) &&
                Objects.equals(this.usuarioByIdCliente, entity.usuarioByIdCliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCliente, direcc, chatsByIdCliente, direccionByDireccion, clientesempresasByIdCliente, cuentasByIdCliente, empresasByIdCliente, usuarioByIdCliente);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "idCliente = " + idCliente + ", " +
                "direcc = " + direcc + ", " +
                "chatsByIdCliente = " + chatsByIdCliente + ", " +
                "direccionByDireccion = " + direccionByDireccion + ", " +
                "clientesempresasByIdCliente = " + clientesempresasByIdCliente + ", " +
                "cuentasByIdCliente = " + cuentasByIdCliente + ", " +
                "empresasByIdCliente = " + empresasByIdCliente + ", " +
                "usuarioByIdCliente = " + usuarioByIdCliente + ")";
    }
}