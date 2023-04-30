package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;
import java.util.Objects;

@SuppressWarnings("unused")
@Entity
@Table(name = "clientes_empresa", schema = "bancodb")
@IdClass(ClientesEmpresaEntityPK.class)
public class ClientesEmpresaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_empresa", nullable = false)
    private Integer idEmpresa;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_cliente", nullable = false)
    private Integer idCliente;
    @ManyToOne
    @JoinColumn(name = "id_empresa", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private EmpresaEntity empresaByIdEmpresa;
    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente", nullable = false, insertable = false, updatable = false)
    private ClienteEntity clienteByIdCliente;

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientesEmpresaEntity that = (ClientesEmpresaEntity) o;

        if (!Objects.equals(idEmpresa, that.idEmpresa)) return false;
        return Objects.equals(idCliente, that.idCliente);
    }

    @Override
    public int hashCode() {
        int result = idEmpresa != null ? idEmpresa.hashCode() : 0;
        result = 31 * result + (idCliente != null ? idCliente.hashCode() : 0);
        return result;
    }

    public EmpresaEntity getEmpresaByIdEmpresa() {
        return empresaByIdEmpresa;
    }

    public void setEmpresaByIdEmpresa(EmpresaEntity empresaByIdEmpresa) {
        this.empresaByIdEmpresa = empresaByIdEmpresa;
    }

    public ClienteEntity getClienteByIdCliente() {
        return clienteByIdCliente;
    }

    public void setClienteByIdCliente(ClienteEntity clienteByIdCliente) {
        this.clienteByIdCliente = clienteByIdCliente;
    }
}
