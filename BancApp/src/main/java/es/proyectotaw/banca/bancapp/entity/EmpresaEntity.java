package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "empresa", schema = "bancodb", catalog = "")
public class EmpresaEntity {
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "cif")
    private Integer cif;
    @OneToMany(mappedBy = "empresaByIdEmpresa")
    private List<ClientesEmpresaEntity> clientesEmpresasById;
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id_cliente", nullable = false)
    private ClienteEntity clienteById;
    @OneToMany(mappedBy = "empresaByIdempresa")
    private List<RolusuarioEntity> rolusuariosById;

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

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (cif != null ? !cif.equals(that.cif) : that.cif != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (cif != null ? cif.hashCode() : 0);
        return result;
    }

    public List<ClientesEmpresaEntity> getClientesEmpresasById() {
        return clientesEmpresasById;
    }

    public void setClientesEmpresasById(List<ClientesEmpresaEntity> clientesEmpresasById) {
        this.clientesEmpresasById = clientesEmpresasById;
    }

    public ClienteEntity getClienteById() {
        return clienteById;
    }

    public void setClienteById(ClienteEntity clienteById) {
        this.clienteById = clienteById;
    }

    public List<RolusuarioEntity> getRolusuariosById() {
        return rolusuariosById;
    }

    public void setRolusuariosById(List<RolusuarioEntity> rolusuariosById) {
        this.rolusuariosById = rolusuariosById;
    }
}
