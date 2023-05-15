package es.proyectotaw.banca.bancapp.entity;

import es.proyectotaw.banca.bancapp.dto.UsuarioEntityDTO;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("unused")
@Entity
@Table(name = "usuario", schema = "bancodb")
public class UsuarioEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "nif", nullable = false, length = 10)
    private String nif;
    @Basic
    @Column(name = "primer_nombre", nullable = false, length = 45)
    private String primerNombre;
    @Basic
    @Column(name = "segundo_nombre", length = 45)
    private String segundoNombre;
    @Basic
    @Column(name = "primer_apellido", nullable = false, length = 45)
    private String primerApellido;
    @Basic
    @Column(name = "segundo_apellido", length = 45)
    private String segundoApellido;
    @Basic
    @Column(name = "fecha_nacimiento", nullable = false)
    private Date fechaNacimiento;
    @Basic
    @Column(name = "email", nullable = false, length = 45)
    private String email;
    @Basic
    @Column(name = "password", nullable = false, length = 45)
    private String password;
    @OneToMany(mappedBy = "usuarioByAsistenteId")
    private List<ChatEntity> chatsById;
    @OneToMany(mappedBy = "usuarioByEmisor")
    private List<MensajeEntity> mensajesById;
    @OneToMany(mappedBy = "usuarioByIdusuario")
    private List<RolusuarioEntity> rolusuariosById;
    @ManyToOne
    @JoinColumn(name = "cliente", referencedColumnName = "id_cliente")
    private ClienteEntity clienteByCliente;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsuarioEntity usuario = (UsuarioEntity) o;

        if (!Objects.equals(id, usuario.id)) return false;
        if (!Objects.equals(nif, usuario.nif)) return false;
        if (!Objects.equals(primerNombre, usuario.primerNombre))
            return false;
        if (!Objects.equals(segundoNombre, usuario.segundoNombre))
            return false;
        if (!Objects.equals(primerApellido, usuario.primerApellido))
            return false;
        if (!Objects.equals(segundoApellido, usuario.segundoApellido))
            return false;
        if (!Objects.equals(fechaNacimiento, usuario.fechaNacimiento))
            return false;
        if (!Objects.equals(email, usuario.email)) return false;
        return Objects.equals(password, usuario.password);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nif != null ? nif.hashCode() : 0);
        result = 31 * result + (primerNombre != null ? primerNombre.hashCode() : 0);
        result = 31 * result + (segundoNombre != null ? segundoNombre.hashCode() : 0);
        result = 31 * result + (primerApellido != null ? primerApellido.hashCode() : 0);
        result = 31 * result + (segundoApellido != null ? segundoApellido.hashCode() : 0);
        result = 31 * result + (fechaNacimiento != null ? fechaNacimiento.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    public List<ChatEntity> getChatsById() {
        return chatsById;
    }

    public void setChatsById(List<ChatEntity> chatsById) {
        this.chatsById = chatsById;
    }

    public List<MensajeEntity> getMensajesById() {
        return mensajesById;
    }

    public void setMensajesById(List<MensajeEntity> mensajesById) {
        this.mensajesById = mensajesById;
    }

    public List<RolusuarioEntity> getRolusuariosById() {
        return rolusuariosById;
    }

    public void setRolusuariosById(List<RolusuarioEntity> rolusuariosById) {
        this.rolusuariosById = rolusuariosById;
    }

    public ClienteEntity getClienteByCliente() {
        return clienteByCliente;
    }

    public void setClienteByCliente(ClienteEntity clienteByCliente) {
        this.clienteByCliente = clienteByCliente;
    }

    public void construct(String nif, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, Date fechaNacimiento, String email, String password) {
        setNif(nif);
        setPrimerNombre(primerNombre);
        setSegundoNombre(segundoNombre);
        setPrimerApellido(primerApellido);
        setSegundoApellido(segundoApellido);
        setFechaNacimiento(fechaNacimiento);
        setEmail(email);
        setPassword(password);
    }

    public UsuarioEntityDTO toDTO() {
        return new UsuarioEntityDTO(
                id, nif, primerNombre, segundoNombre, primerApellido, segundoApellido, fechaNacimiento, email, password,
                chatsById.stream().map(ChatEntity::toDTO).toList(), mensajesById.stream().map(MensajeEntity::toDTO).toList(),
                rolusuariosById.stream().map(RolusuarioEntity::toDTO).toList(), clienteByCliente.toDTO()
        );
    }
}
