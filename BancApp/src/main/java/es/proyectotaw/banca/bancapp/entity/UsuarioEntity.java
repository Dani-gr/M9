package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@SuppressWarnings("unused")
@Entity
@Table(name = "usuario", schema = "bancodb")
public class UsuarioEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "NIF", nullable = false, length = 10)
    private String nif;
    @Basic
    @Column(name = "primerNombre", nullable = false, length = 45)
    private String primerNombre;
    @Basic
    @Column(name = "segundoNombre", length = 45)
    private String segundoNombre;
    @Basic
    @Column(name = "primerApellido", nullable = false, length = 45)
    private String primerApellido;
    @Basic
    @Column(name = "segundoApellido", length = 45)
    private String segundoApellido;
    @Basic
    @Column(name = "fechaNacimiento", nullable = false)
    private Date fechaNacimiento;
    @Basic
    @Column(name = "email", nullable = false, length = 45)
    private String email;
    @Basic
    @Column(name = "password", nullable = false, length = 45)
    private String password;
    @Basic
    @Column(name = "idDireccion", nullable = false)
    private Integer idDireccion;
    @OneToMany(mappedBy = "usuarioByAsistenteId")
    private Collection<ChatEntity> chatsById;
    @OneToOne(mappedBy = "usuarioById")
    private DireccionEntity direccionById;
    @OneToMany(mappedBy = "usuarioByEmisor")
    private Collection<MensajeEntity> mensajesById;
    @OneToMany(mappedBy = "usuarioByIdUsuario")
    private Collection<RolusuarioEntity> rolusuariosById;
    @OneToOne
    @JoinColumn(name = "ID", referencedColumnName = "ID_Cliente", nullable = false)
    private ClienteEntity clienteById;

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

    public Integer getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Integer idDireccion) {
        this.idDireccion = idDireccion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsuarioEntity that = (UsuarioEntity) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(nif, that.nif)) return false;
        if (!Objects.equals(primerNombre, that.primerNombre)) return false;
        if (!Objects.equals(segundoNombre, that.segundoNombre))
            return false;
        if (!Objects.equals(primerApellido, that.primerApellido))
            return false;
        if (!Objects.equals(segundoApellido, that.segundoApellido))
            return false;
        if (!Objects.equals(fechaNacimiento, that.fechaNacimiento))
            return false;
        if (!Objects.equals(email, that.email)) return false;
        if (!Objects.equals(password, that.password)) return false;
        return Objects.equals(idDireccion, that.idDireccion);
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
        result = 31 * result + (idDireccion != null ? idDireccion.hashCode() : 0);
        return result;
    }

    public Collection<ChatEntity> getChatsById() {
        return chatsById;
    }

    public void setChatsById(Collection<ChatEntity> chatsById) {
        this.chatsById = chatsById;
    }

    public DireccionEntity getDireccionById() {
        return direccionById;
    }

    public void setDireccionById(DireccionEntity direccionById) {
        this.direccionById = direccionById;
    }

    public Collection<MensajeEntity> getMensajesById() {
        return mensajesById;
    }

    public void setMensajesById(Collection<MensajeEntity> mensajesById) {
        this.mensajesById = mensajesById;
    }

    public Collection<RolusuarioEntity> getRolusuariosById() {
        return rolusuariosById;
    }

    public void setRolusuariosById(Collection<RolusuarioEntity> rolusuariosById) {
        this.rolusuariosById = rolusuariosById;
    }

    public ClienteEntity getClienteById() {
        return clienteById;
    }

    public void setClienteById(ClienteEntity clienteById) {
        this.clienteById = clienteById;
    }
}
