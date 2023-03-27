package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "empresa", schema = "bancodb", catalog = "")
@IdClass(EmpresaEntityPK.class)
public class EmpresaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CIF", nullable = false)
    private Integer cif;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @OneToMany(mappedBy = "empresa")
    private Collection<ClientesempresaEntity> clientesempresas;
    @ManyToOne
    @JoinColumn(name = "ID", referencedColumnName = "ID_Cliente", nullable = false)
    private ClienteEntity clienteById;
    @OneToMany(mappedBy = "empresa")
    private Collection<RolusuarioEntity> rolusuarios;

    public Integer getCif() {
        return cif;
    }

    public void setCif(Integer cif) {
        this.cif = cif;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmpresaEntity that = (EmpresaEntity) o;

        if (cif != null ? !cif.equals(that.cif) : that.cif != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cif != null ? cif.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    public Collection<ClientesempresaEntity> getClientesempresas() {
        return clientesempresas;
    }

    public void setClientesempresas(Collection<ClientesempresaEntity> clientesempresas) {
        this.clientesempresas = clientesempresas;
    }

    public ClienteEntity getClienteById() {
        return clienteById;
    }

    public void setClienteById(ClienteEntity clienteById) {
        this.clienteById = clienteById;
    }

    public Collection<RolusuarioEntity> getRolusuarios() {
        return rolusuarios;
    }

    public void setRolusuarios(Collection<RolusuarioEntity> rolusuarios) {
        this.rolusuarios = rolusuarios;
    }
}
