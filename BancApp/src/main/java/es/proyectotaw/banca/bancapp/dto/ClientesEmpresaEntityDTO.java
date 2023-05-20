package es.proyectotaw.banca.bancapp.dto;

import es.proyectotaw.banca.bancapp.entity.ClientesEmpresaEntity;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link es.proyectotaw.banca.bancapp.entity.ClientesEmpresaEntity} entity
 *
 * @author Daniel García Rodríguez (method {@link #toEntity()})
 */
@SuppressWarnings("unused")
public class ClientesEmpresaEntityDTO implements Serializable {
    private Integer id;
    private EmpresaEntityDTO empresaByIdEmpresa;
    private ClienteEntityDTO clienteByIdCliente;

    public ClientesEmpresaEntityDTO() {
    }

    public ClientesEmpresaEntityDTO(Integer id, EmpresaEntityDTO empresaByIdEmpresa, ClienteEntityDTO clienteByIdCliente) {
        this.id = id;
        this.empresaByIdEmpresa = empresaByIdEmpresa;
        this.clienteByIdCliente = clienteByIdCliente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EmpresaEntityDTO getEmpresaByIdEmpresa() {
        return empresaByIdEmpresa;
    }

    public void setEmpresaByIdEmpresa(EmpresaEntityDTO empresaByIdEmpresa) {
        this.empresaByIdEmpresa = empresaByIdEmpresa;
    }

    public ClienteEntityDTO getClienteByIdCliente() {
        return clienteByIdCliente;
    }

    public void setClienteByIdCliente(ClienteEntityDTO clienteByIdCliente) {
        this.clienteByIdCliente = clienteByIdCliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientesEmpresaEntityDTO entity = (ClientesEmpresaEntityDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.empresaByIdEmpresa, entity.empresaByIdEmpresa) &&
                Objects.equals(this.clienteByIdCliente, entity.clienteByIdCliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, empresaByIdEmpresa, clienteByIdCliente);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "empresaByIdEmpresa = " + empresaByIdEmpresa + ", " +
                "clienteByIdCliente = " + clienteByIdCliente + ")";
    }

    public ClientesEmpresaEntity toEntity() {
        ClientesEmpresaEntity clientesEmpresaEntity = new ClientesEmpresaEntity();
        clientesEmpresaEntity.setId(id);
        clientesEmpresaEntity.setClienteByIdCliente(clienteByIdCliente.toEntity());
        clientesEmpresaEntity.setEmpresaByIdEmpresa(empresaByIdEmpresa.toEntity());

        return clientesEmpresaEntity;
    }
}