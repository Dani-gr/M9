package es.proyectotaw.banca.bancapp.entity;

import es.proyectotaw.banca.bancapp.dto.ClienteEntityDTO;

import javax.persistence.*;
import java.util.List;
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
    private List<ChatEntity> chatsByIdCliente;
    @ManyToOne
    @JoinColumn(name = "direccion", referencedColumnName = "id", nullable = false)
    private DireccionEntity direccionByDireccion;
    @OneToMany(mappedBy = "clienteByIdCliente")
    private List<ClientesEmpresaEntity> clientesEmpresasByIdCliente;

    @OneToMany(mappedBy = "clienteByCliente", fetch = FetchType.EAGER)
    private List<CuentaEntity> cuentasByIdCliente;
    @OneToMany(mappedBy = "clienteByCliente")
    private List<EmpresaEntity> empresasByIdCliente;
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

    public CuentaEntity getPrimeraCuentaByIdCliente() {
        return (cuentasByIdCliente.isEmpty()) ? null : cuentasByIdCliente.get(0);
    }

    public void setCuentasByIdCliente(List<CuentaEntity> cuentasByIdCliente) {
        this.cuentasByIdCliente = cuentasByIdCliente;
    }

    public List<EmpresaEntity> getEmpresasByIdCliente() {
        return empresasByIdCliente;
    }

    public void setEmpresasByIdCliente(List<EmpresaEntity> empresasByIdCliente) {
        this.empresasByIdCliente = empresasByIdCliente;
    }

    public List<UsuarioEntity> getUsuariosByIdCliente() {
        return usuariosByIdCliente;
    }

    public void setUsuariosByIdCliente(List<UsuarioEntity> usuariosByIdCliente) {
        this.usuariosByIdCliente = usuariosByIdCliente;
    }

    public ClienteEntityDTO toDTO() {
        return new ClienteEntityDTO(
                idCliente, chatsByIdCliente.stream().map(ChatEntity::toDTO).toList(), direccionByDireccion.toDTO(),
                clientesEmpresasByIdCliente.stream().map(ClientesEmpresaEntity::toDTO).toList(),
                cuentasByIdCliente.stream().map(CuentaEntity::toDTO).toList(),
                empresasByIdCliente.stream().map(EmpresaEntity::toDTO).toList(),
                usuariosByIdCliente.stream().map(UsuarioEntity::toDTO).toList()
        );
    }
}
