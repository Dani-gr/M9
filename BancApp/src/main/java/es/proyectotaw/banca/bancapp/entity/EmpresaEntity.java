package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("unused")
@Entity
@Table(name = "empresa", schema = "bancodb")
public class EmpresaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_empresa", nullable = false)
    private Integer idEmpresa;
    @Basic
    @Column(name = "cif", nullable = false)
    private Integer cif;
    @OneToMany(mappedBy = "empresaByIdEmpresa")
    private List<ClientesEmpresaEntity> clientesEmpresasByIdEmpresa;
    @ManyToOne
    @JoinColumn(name = "cliente", referencedColumnName = "id_cliente", nullable = false)
    private ClienteEntity clienteByCliente;
    @OneToMany(mappedBy = "empresaByIdempresa")
    private List<RolusuarioEntity> rolusuariosByIdEmpresa;

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
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

        if (!Objects.equals(idEmpresa, that.idEmpresa)) return false;
        return Objects.equals(cif, that.cif);
    }

    @Override
    public int hashCode() {
        int result = idEmpresa != null ? idEmpresa.hashCode() : 0;
        result = 31 * result + (cif != null ? cif.hashCode() : 0);
        return result;
    }

    public List<ClientesEmpresaEntity> getClientesEmpresasByIdEmpresa() {
        return clientesEmpresasByIdEmpresa;
    }

    public void setClientesEmpresasByIdEmpresa(List<ClientesEmpresaEntity> clientesEmpresasByIdEmpresa) {
        this.clientesEmpresasByIdEmpresa = clientesEmpresasByIdEmpresa;
    }

    public ClienteEntity getClienteByCliente() {
        return clienteByCliente;
    }

    public void setClienteByCliente(ClienteEntity clienteByCliente) {
        this.clienteByCliente = clienteByCliente;
    }

    public List<RolusuarioEntity> getRolusuariosByIdEmpresa() {
        return rolusuariosByIdEmpresa;
    }

    public void setRolusuariosByIdEmpresa(List<RolusuarioEntity> rolusuariosByIdEmpresa) {
        this.rolusuariosByIdEmpresa = rolusuariosByIdEmpresa;
    }
}
