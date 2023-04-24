package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cliente", schema = "bancodb", catalog = "")
public class ClienteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_Cliente")
    private Integer idCliente;
    @OneToMany(mappedBy = "clienteByClienteIdCliente")
    private List<ChatEntity> chatsByIdCliente;
    @ManyToOne
    @JoinColumn(name = "Direccion", referencedColumnName = "ID", nullable = false)
    private DireccionEntity direccionByDireccion;
    @OneToMany(mappedBy = "clienteByIdCliente")
    private List<ClientesempresaEntity> clientesempresasByIdCliente;
    @OneToMany(mappedBy = "clienteByCliente")
    private List<CuentaEntity> cuentasByIdCliente;
    @OneToOne(mappedBy = "clienteById")
    private EmpresaEntity empresaByIdCliente;
    @OneToMany(mappedBy = "clienteByCliente")
    private List<UsuarioEntity> usuariosByIdCliente;

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
        ClienteEntity that = (ClienteEntity) o;
        return Objects.equals(idCliente, that.idCliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCliente);
    }

    public List<ChatEntity> getChatsByIdCliente() {
        return chatsByIdCliente;
    }

    public void setChatsByIdCliente(List<ChatEntity> chatsByIdCliente) {
        this.chatsByIdCliente = chatsByIdCliente;
    }

    public DireccionEntity getDireccionByDireccion() {
        return direccionByDireccion;
    }

    public void setDireccionByDireccion(DireccionEntity direccionByDireccion) {
        this.direccionByDireccion = direccionByDireccion;
    }

    public List<ClientesempresaEntity> getClientesempresasByIdCliente() {
        return clientesempresasByIdCliente;
    }

    public void setClientesempresasByIdCliente(List<ClientesempresaEntity> clientesempresasByIdCliente) {
        this.clientesempresasByIdCliente = clientesempresasByIdCliente;
    }

    public List<CuentaEntity> getCuentasByIdCliente() {
        return cuentasByIdCliente;
    }

    public void setCuentasByIdCliente(List<CuentaEntity> cuentasByIdCliente) {
        this.cuentasByIdCliente = cuentasByIdCliente;
    }

    public EmpresaEntity getEmpresaByIdCliente() {
        return empresaByIdCliente;
    }

    public void setEmpresaByIdCliente(EmpresaEntity empresaByIdCliente) {
        this.empresaByIdCliente = empresaByIdCliente;
    }

    public List<UsuarioEntity> getUsuariosByIdCliente() {
        return usuariosByIdCliente;
    }

    public void setUsuariosByIdCliente(List<UsuarioEntity> usuariosByIdCliente) {
        this.usuariosByIdCliente = usuariosByIdCliente;
    }
}
