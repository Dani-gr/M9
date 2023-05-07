package es.proyectotaw.banca.bancapp.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link es.proyectotaw.banca.bancapp.entity.ClienteEntity} entity
 */
@SuppressWarnings("unused")
public class ClienteEntityDTO implements Serializable {
    private final Integer idCliente;
    private final List<ChatEntityDTO> chatsByIdCliente;
    private final DireccionEntityDTO direccionByDireccion;
    private final List<ClientesEmpresaEntityDTO> clientesEmpresasByIdCliente;
    private final List<CuentaEntityDTO> cuentasByIdCliente;
    private final List<EmpresaEntityDTO> empresasByIdCliente;
    private final List<UsuarioEntityDTO> usuariosByIdCliente;

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

    public List<ChatEntityDTO> getChatsByIdCliente() {
        return chatsByIdCliente;
    }

    public DireccionEntityDTO getDireccionByDireccion() {
        return direccionByDireccion;
    }

    public List<ClientesEmpresaEntityDTO> getClientesEmpresasByIdCliente() {
        return clientesEmpresasByIdCliente;
    }

    public List<CuentaEntityDTO> getCuentasByIdCliente() {
        return cuentasByIdCliente;
    }

    public List<EmpresaEntityDTO> getEmpresasByIdCliente() {
        return empresasByIdCliente;
    }

    public List<UsuarioEntityDTO> getUsuariosByIdCliente() {
        return usuariosByIdCliente;
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
}