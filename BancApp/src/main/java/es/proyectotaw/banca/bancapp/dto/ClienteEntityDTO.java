package es.proyectotaw.banca.bancapp.dto;

import es.proyectotaw.banca.bancapp.entity.ClienteEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link es.proyectotaw.banca.bancapp.entity.ClienteEntity} entity
 *
 * @author Daniel García Rodríguez (method {@link #toEntity()})
 */
@SuppressWarnings("unused")
public class ClienteEntityDTO implements Serializable {
    private Integer idCliente;
    private List<ChatEntityDTO> chatsByIdCliente;
    private DireccionEntityDTO direccionByDireccion;
    private List<ClientesEmpresaEntityDTO> clientesEmpresasByIdCliente;
    private List<CuentaEntityDTO> cuentasByIdCliente;
    private List<EmpresaEntityDTO> empresasByIdCliente;
    private List<UsuarioEntityDTO> usuariosByIdCliente;

    public ClienteEntityDTO() {
    }

    public ClienteEntityDTO(Integer idCliente, List<ChatEntityDTO> chatsByIdCliente, DireccionEntityDTO direccionByDireccion, List<ClientesEmpresaEntityDTO> clientesEmpresasByIdCliente, List<CuentaEntityDTO> cuentasByIdCliente, List<EmpresaEntityDTO> empresasByIdCliente, List<UsuarioEntityDTO> usuariosByIdCliente) {
        this.idCliente = idCliente;
        this.chatsByIdCliente = chatsByIdCliente;
        this.direccionByDireccion = direccionByDireccion;
        this.clientesEmpresasByIdCliente = clientesEmpresasByIdCliente;
        this.cuentasByIdCliente = cuentasByIdCliente;
        this.empresasByIdCliente = empresasByIdCliente;
        this.usuariosByIdCliente = usuariosByIdCliente;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public List<ChatEntityDTO> getChatsByIdCliente() {
        return chatsByIdCliente;
    }

    public void setChatsByIdCliente(List<ChatEntityDTO> chatsByIdCliente) {
        this.chatsByIdCliente = chatsByIdCliente;
    }

    public DireccionEntityDTO getDireccionByDireccion() {
        return direccionByDireccion;
    }

    public void setDireccionByDireccion(DireccionEntityDTO direccionByDireccion) {
        this.direccionByDireccion = direccionByDireccion;
    }

    public List<ClientesEmpresaEntityDTO> getClientesEmpresasByIdCliente() {
        return clientesEmpresasByIdCliente;
    }

    public void setClientesEmpresasByIdCliente(List<ClientesEmpresaEntityDTO> clientesEmpresasByIdCliente) {
        this.clientesEmpresasByIdCliente = clientesEmpresasByIdCliente;
    }

    public List<CuentaEntityDTO> getCuentasByIdCliente() {
        return cuentasByIdCliente;
    }

    public void setCuentasByIdCliente(List<CuentaEntityDTO> cuentasByIdCliente) {
        this.cuentasByIdCliente = cuentasByIdCliente;
    }

    public List<EmpresaEntityDTO> getEmpresasByIdCliente() {
        return empresasByIdCliente;
    }

    public void setEmpresasByIdCliente(List<EmpresaEntityDTO> empresasByIdCliente) {
        this.empresasByIdCliente = empresasByIdCliente;
    }

    public List<UsuarioEntityDTO> getUsuariosByIdCliente() {
        return usuariosByIdCliente;
    }

    public void setUsuariosByIdCliente(List<UsuarioEntityDTO> usuariosByIdCliente) {
        this.usuariosByIdCliente = usuariosByIdCliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteEntityDTO entity = (ClienteEntityDTO) o;
        return Objects.equals(this.idCliente, entity.idCliente) &&
                Objects.equals(this.chatsByIdCliente, entity.chatsByIdCliente) &&
                Objects.equals(this.direccionByDireccion, entity.direccionByDireccion) &&
                Objects.equals(this.clientesEmpresasByIdCliente, entity.clientesEmpresasByIdCliente) &&
                Objects.equals(this.cuentasByIdCliente, entity.cuentasByIdCliente) &&
                Objects.equals(this.empresasByIdCliente, entity.empresasByIdCliente) &&
                Objects.equals(this.usuariosByIdCliente, entity.usuariosByIdCliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCliente, chatsByIdCliente, direccionByDireccion, clientesEmpresasByIdCliente, cuentasByIdCliente, empresasByIdCliente, usuariosByIdCliente);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "idCliente = " + idCliente + ", " +
                "chatsByIdCliente = " + chatsByIdCliente + ", " +
                "direccionByDireccion = " + direccionByDireccion + ", " +
                "clientesEmpresasByIdCliente = " + clientesEmpresasByIdCliente + ", " +
                "cuentasByIdCliente = " + cuentasByIdCliente + ", " +
                "empresasByIdCliente = " + empresasByIdCliente + ", " +
                "usuariosByIdCliente = " + usuariosByIdCliente + ")";
    }

    public ClienteEntity toEntity() {
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setIdCliente(idCliente);
        clienteEntity.setClientesEmpresasByIdCliente(clientesEmpresasByIdCliente.stream().map(ClientesEmpresaEntityDTO::toEntity).toList());
        clienteEntity.setChatsByIdCliente(chatsByIdCliente.stream().map(ChatEntityDTO::toEntity).toList());
        clienteEntity.setCuentasByIdCliente(cuentasByIdCliente.stream().map(CuentaEntityDTO::toEntity).toList());
        clienteEntity.setEmpresasByIdCliente(empresasByIdCliente.stream().map(EmpresaEntityDTO::toEntity).toList());
        clienteEntity.setDireccionByDireccion(direccionByDireccion.toEntity());
        clienteEntity.setUsuariosByIdCliente(usuariosByIdCliente.stream().map(UsuarioEntityDTO::toEntity).toList());

        return clienteEntity;
    }
}