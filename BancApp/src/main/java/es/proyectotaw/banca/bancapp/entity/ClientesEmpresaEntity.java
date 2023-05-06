package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;
import java.util.Objects;

@SuppressWarnings("unused")
@Entity
@Table(name = "clientes_empresa", schema = "bancodb")
public class ClientesEmpresaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", nullable = false)
    private EmpresaEntity empresaByIdEmpresa;
    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente", nullable = false)
    private ClienteEntity clienteByIdCliente;

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

        ClientesEmpresaEntity that = (ClientesEmpresaEntity) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
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
