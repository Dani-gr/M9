package es.proyectotaw.banca.bancapp.dto;

import es.proyectotaw.banca.bancapp.entity.EmpresaEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link es.proyectotaw.banca.bancapp.entity.EmpresaEntity} entity
 *
 * @author Daniel García Rodríguez (method {@link #toEntity()})
 */
@SuppressWarnings("unused")
public class EmpresaEntityDTO implements Serializable {
    private final Integer idEmpresa;
    private final Integer cif;
    private final List<ClientesEmpresaEntityDTO> clientesEmpresasByIdEmpresa;
    private final ClienteEntityDTO clienteByCliente;
    private final List<RolusuarioEntityDTO> rolusuariosByIdEmpresa;

    public EmpresaEntityDTO(Integer idEmpresa, Integer cif, List<ClientesEmpresaEntityDTO> clientesEmpresasByIdEmpresa, ClienteEntityDTO clienteByCliente, List<RolusuarioEntityDTO> rolusuariosByIdEmpresa) {
        this.idEmpresa = idEmpresa;
        this.cif = cif;
        this.clientesEmpresasByIdEmpresa = clientesEmpresasByIdEmpresa;
        this.clienteByCliente = clienteByCliente;
        this.rolusuariosByIdEmpresa = rolusuariosByIdEmpresa;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public Integer getCif() {
        return cif;
    }

    public List<ClientesEmpresaEntityDTO> getClientesEmpresasByIdEmpresa() {
        return clientesEmpresasByIdEmpresa;
    }

    public ClienteEntityDTO getClienteByCliente() {
        return clienteByCliente;
    }

    public List<RolusuarioEntityDTO> getRolusuariosByIdEmpresa() {
        return rolusuariosByIdEmpresa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpresaEntityDTO entity = (EmpresaEntityDTO) o;
        return Objects.equals(this.idEmpresa, entity.idEmpresa) &&
                Objects.equals(this.cif, entity.cif) &&
                Objects.equals(this.clientesEmpresasByIdEmpresa, entity.clientesEmpresasByIdEmpresa) &&
                Objects.equals(this.clienteByCliente, entity.clienteByCliente) &&
                Objects.equals(this.rolusuariosByIdEmpresa, entity.rolusuariosByIdEmpresa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEmpresa, cif, clientesEmpresasByIdEmpresa, clienteByCliente, rolusuariosByIdEmpresa);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "idEmpresa = " + idEmpresa + ", " +
                "cif = " + cif + ", " +
                "clientesEmpresasByIdEmpresa = " + clientesEmpresasByIdEmpresa + ", " +
                "clienteByCliente = " + clienteByCliente + ", " +
                "rolusuariosByIdEmpresa = " + rolusuariosByIdEmpresa + ")";
    }

    public EmpresaEntity toEntity() {
        EmpresaEntity empresaEntity = new EmpresaEntity();
        empresaEntity.setIdEmpresa(idEmpresa);
        empresaEntity.setCif(cif);
        empresaEntity.setClienteByCliente(clienteByCliente.toEntity());
        empresaEntity.setClientesEmpresasByIdEmpresa(clientesEmpresasByIdEmpresa.stream().map(ClientesEmpresaEntityDTO::toEntity).toList());
        empresaEntity.setRolusuariosByIdEmpresa(rolusuariosByIdEmpresa.stream().map(RolusuarioEntityDTO::toEntity).toList());

        return empresaEntity;
    }
}