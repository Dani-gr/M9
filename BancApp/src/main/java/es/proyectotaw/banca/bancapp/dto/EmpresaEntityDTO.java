package es.proyectotaw.banca.bancapp.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

/**
 * A DTO for the {@link es.proyectotaw.banca.bancapp.entity.EmpresaEntity} entity
 */
public class EmpresaEntityDTO implements Serializable {
    private final Integer cif;
    private final Integer id;
    private final Collection<ClientesempresaEntityDTO> clientesempresas;
    private final ClienteEntityDTO clienteById;
    private final Collection<RolusuarioEntityDTO> rolusuarios;

    public EmpresaEntityDTO(Integer cif, Integer id, Collection<ClientesempresaEntityDTO> clientesempresas, ClienteEntityDTO clienteById, Collection<RolusuarioEntityDTO> rolusuarios) {
        this.cif = cif;
        this.id = id;
        this.clientesempresas = clientesempresas;
        this.clienteById = clienteById;
        this.rolusuarios = rolusuarios;
    }

    public Integer getCif() {
        return cif;
    }

    public Integer getId() {
        return id;
    }

    public Collection<ClientesempresaEntityDTO> getClientesempresas() {
        return clientesempresas;
    }

    public ClienteEntityDTO getClienteById() {
        return clienteById;
    }

    public Collection<RolusuarioEntityDTO> getRolusuarios() {
        return rolusuarios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpresaEntityDTO entity = (EmpresaEntityDTO) o;
        return Objects.equals(this.cif, entity.cif) &&
                Objects.equals(this.id, entity.id) &&
                Objects.equals(this.clientesempresas, entity.clientesempresas) &&
                Objects.equals(this.clienteById, entity.clienteById) &&
                Objects.equals(this.rolusuarios, entity.rolusuarios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cif, id, clientesempresas, clienteById, rolusuarios);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "cif = " + cif + ", " +
                "id = " + id + ", " +
                "clientesempresas = " + clientesempresas + ", " +
                "clienteById = " + clienteById + ", " +
                "rolusuarios = " + rolusuarios + ")";
    }
}