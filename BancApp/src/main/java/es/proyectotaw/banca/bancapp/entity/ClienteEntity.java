package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@SuppressWarnings("unused")
@Entity
@Table(name = "cliente", schema = "bancodb")
public class ClienteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_cliente", nullable = false)
    private Integer idCliente;
    @OneToMany(mappedBy = "clienteByClienteIdCliente")
    private Collection<ChatEntity> chatsByIdCliente;
    @ManyToOne
    @JoinColumn(name = "direccion", referencedColumnName = "id", nullable = false)
    private DireccionEntity direccionByDireccion;
    @OneToMany(mappedBy = "clienteByIdCliente")
    private Collection<ClientesEmpresaEntity> clientesEmpresasByIdCliente;
    @OneToMany(mappedBy = "clienteByCliente")
    private Collection<CuentaEntity> cuentasByIdCliente;
    @OneToOne(mappedBy = "clienteById")
    private EmpresaEntity empresaByIdCliente;
    @OneToMany(mappedBy = "clienteByCliente")
    private Collection<UsuarioEntity> usuariosByIdCliente;

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
        return idCliente != null ? idCliente.hashCode() : 0;
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

    public Collection<ClientesEmpresaEntity> getClientesEmpresasByIdCliente() {
        return clientesEmpresasByIdCliente;
    }

    public void setClientesEmpresasByIdCliente(Collection<ClientesEmpresaEntity> clientesEmpresasByIdCliente) {
        this.clientesEmpresasByIdCliente = clientesEmpresasByIdCliente;
    }

    public Collection<CuentaEntity> getCuentasByIdCliente() {
        return cuentasByIdCliente;
    }

    public void setCuentasByIdCliente(Collection<CuentaEntity> cuentasByIdCliente) {
        this.cuentasByIdCliente = cuentasByIdCliente;
    }

    public EmpresaEntity getEmpresaByIdCliente() {
        return empresaByIdCliente;
    }

    public void setEmpresaByIdCliente(EmpresaEntity empresaByIdCliente) {
        this.empresaByIdCliente = empresaByIdCliente;
    }

    public Collection<UsuarioEntity> getUsuariosByIdCliente() {
        return usuariosByIdCliente;
    }

    public void setUsuariosByIdCliente(Collection<UsuarioEntity> usuariosByIdCliente) {
        this.usuariosByIdCliente = usuariosByIdCliente;
    }
}
