package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "empresa", schema = "bancodb", catalog = "")
public class EmpresaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Integer id;
    @Basic
    @Column(name = "CIF")
    private Integer cif;
    @OneToMany(mappedBy = "empresaByIdEmpresa")
    private List<ClientesempresaEntity> clientesempresasById;
    @OneToOne
    @JoinColumn(name = "ID", referencedColumnName = "ID_Cliente", nullable = false)
    private ClienteEntity clienteById;
    @OneToMany(mappedBy = "empresaByIdEmpresa")
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
        return Objects.equals(id, that.id) && Objects.equals(cif, that.cif);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cif);
    }

    public List<ClientesempresaEntity> getClientesempresasById() {
        return clientesempresasById;
    }

    public void setClientesempresasById(List<ClientesempresaEntity> clientesempresasById) {
        this.clientesempresasById = clientesempresasById;
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
