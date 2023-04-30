package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cliente", schema = "bancodb", catalog = "")
public class ClienteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_cliente")
    private Integer idCliente;
    @OneToMany(mappedBy = "clienteByClienteIdCliente")
    private List<ChatEntity> chatsByIdCliente;
    @ManyToOne
    @JoinColumn(name = "direccion", referencedColumnName = "id", nullable = false)
    private DireccionEntity direccionByDireccion;
    @OneToMany(mappedBy = "clienteByIdCliente")
    private List<ClientesEmpresaEntity> clientesEmpresasByIdCliente;
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

        if (idCliente != null ? !idCliente.equals(that.idCliente) : that.idCliente != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return idCliente != null ? idCliente.hashCode() : 0;
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

    public List<ClientesEmpresaEntity> getClientesEmpresasByIdCliente() {
        return clientesEmpresasByIdCliente;
    }

    public void setClientesEmpresasByIdCliente(List<ClientesEmpresaEntity> clientesEmpresasByIdCliente) {
        this.clientesEmpresasByIdCliente = clientesEmpresasByIdCliente;
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
