package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "cliente", schema = "bancodb", catalog = "")
public class ClienteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_Cliente", nullable = false)
    private Integer idCliente;
    @Basic
    @Column(name = "Direcc", nullable = false, length = 45)
    private String direcc;
    @OneToMany(mappedBy = "clienteByClienteIdCliente")
    private Collection<ChatEntity> chatsByIdCliente;
    @ManyToOne
    @JoinColumn(name = "Direccion", referencedColumnName = "ID", nullable = false)
    private DireccionEntity direccionByDireccion;
    @OneToMany(mappedBy = "clienteByIdCliente")
    private Collection<ClientesempresaEntity> clientesempresasByIdCliente;
    @OneToMany(mappedBy = "clienteByCliente")
    private Collection<CuentaEntity> cuentasByIdCliente;
    @OneToMany(mappedBy = "clienteById")
    private Collection<EmpresaEntity> empresasByIdCliente;
    @OneToOne(mappedBy = "clienteById")
    private UsuarioEntity usuarioByIdCliente;

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getDirecc() {
        return direcc;
    }

    public void setDirecc(String direcc) {
        this.direcc = direcc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClienteEntity that = (ClienteEntity) o;

        if (idCliente != null ? !idCliente.equals(that.idCliente) : that.idCliente != null) return false;
        if (direcc != null ? !direcc.equals(that.direcc) : that.direcc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCliente != null ? idCliente.hashCode() : 0;
        result = 31 * result + (direcc != null ? direcc.hashCode() : 0);
        return result;
    }

    public Collection<ChatEntity> getChatsByIdCliente() {
        return chatsByIdCliente;
    }

    public void setChatsByIdCliente(Collection<ChatEntity> chatsByIdCliente) {
        this.chatsByIdCliente = chatsByIdCliente;
    }

    public DireccionEntity getDireccionByDireccion() {
        return direccionByDireccion;
    }

    public void setDireccionByDireccion(DireccionEntity direccionByDireccion) {
        this.direccionByDireccion = direccionByDireccion;
    }

    public Collection<ClientesempresaEntity> getClientesempresasByIdCliente() {
        return clientesempresasByIdCliente;
    }

    public void setClientesempresasByIdCliente(Collection<ClientesempresaEntity> clientesempresasByIdCliente) {
        this.clientesempresasByIdCliente = clientesempresasByIdCliente;
    }

    public Collection<CuentaEntity> getCuentasByIdCliente() {
        return cuentasByIdCliente;
    }

    public void setCuentasByIdCliente(Collection<CuentaEntity> cuentasByIdCliente) {
        this.cuentasByIdCliente = cuentasByIdCliente;
    }

    public Collection<EmpresaEntity> getEmpresasByIdCliente() {
        return empresasByIdCliente;
    }

    public void setEmpresasByIdCliente(Collection<EmpresaEntity> empresasByIdCliente) {
        this.empresasByIdCliente = empresasByIdCliente;
    }

    public UsuarioEntity getUsuarioByIdCliente() {
        return usuarioByIdCliente;
    }

    public void setUsuarioByIdCliente(UsuarioEntity usuarioByIdCliente) {
        this.usuarioByIdCliente = usuarioByIdCliente;
    }
}
