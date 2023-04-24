package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
@SuppressWarnings("unused")
@Entity
@Table(name = "empresa", schema = "bancodb")
public class EmpresaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "cif", nullable = false)
    private Integer cif;
    @OneToMany(mappedBy = "empresaByIdEmpresa")
    private Collection<ClientesEmpresaEntity> clientesEmpresasById;
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id_cliente", nullable = false)
    private ClienteEntity clienteById;
    @OneToMany(mappedBy = "empresaByIdempresa")
    private Collection<RolusuarioEntity> rolusuariosById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCif() {
        return cif;
    }

    public void setCif(Integer cif) {
        this.cif = cif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmpresaEntity that = (EmpresaEntity) o;

        if (!Objects.equals(id, that.id)) return false;
        return Objects.equals(cif, that.cif);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (cif != null ? cif.hashCode() : 0);
        return result;
    }

    public Collection<ClientesEmpresaEntity> getClientesEmpresasById() {
        return clientesEmpresasById;
    }

    public void setClientesEmpresasById(Collection<ClientesEmpresaEntity> clientesEmpresasById) {
        this.clientesEmpresasById = clientesEmpresasById;
    }

    public ClienteEntity getClienteById() {
        return clienteById;
    }

    public void setClienteById(ClienteEntity clienteById) {
        this.clienteById = clienteById;
    }

    public Collection<RolusuarioEntity> getRolusuariosById() {
        return rolusuariosById;
    }

    public void setRolusuariosById(Collection<RolusuarioEntity> rolusuariosById) {
        this.rolusuariosById = rolusuariosById;
    }
}
