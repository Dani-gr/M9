package es.proyectotaw.banca.bancapp.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link es.proyectotaw.banca.bancapp.entity.ClientesempresaEntity} entity
 */
public class ClientesempresaEntityDTO implements Serializable {
    private final Integer cif;
    private final Integer idEmpresa;
    private final Integer idCliente;
    private final EmpresaEntityDTO empresa;
    private final ClienteEntityDTO clienteByIdCliente;

    public ClientesempresaEntityDTO(Integer cif, Integer idEmpresa, Integer idCliente, EmpresaEntityDTO empresa, ClienteEntityDTO clienteByIdCliente) {
        this.cif = cif;
        this.idEmpresa = idEmpresa;
        this.idCliente = idCliente;
        this.empresa = empresa;
        this.clienteByIdCliente = clienteByIdCliente;
    }

    public Integer getCif() {
        return cif;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public EmpresaEntityDTO getEmpresa() {
        return empresa;
    }

    public ClienteEntityDTO getClienteByIdCliente() {
        return clienteByIdCliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientesempresaEntityDTO entity = (ClientesempresaEntityDTO) o;
        return Objects.equals(this.cif, entity.cif) &&
                Objects.equals(this.idEmpresa, entity.idEmpresa) &&
                Objects.equals(this.idCliente, entity.idCliente) &&
                Objects.equals(this.empresa, entity.empresa) &&
                Objects.equals(this.clienteByIdCliente, entity.clienteByIdCliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cif, idEmpresa, idCliente, empresa, clienteByIdCliente);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "cif = " + cif + ", " +
                "idEmpresa = " + idEmpresa + ", " +
                "idCliente = " + idCliente + ", " +
                "empresa = " + empresa + ", " +
                "clienteByIdCliente = " + clienteByIdCliente + ")";
    }
}