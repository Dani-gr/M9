package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "usuario", schema = "bancodb", catalog = "")
public class UsuarioEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "nif")
    private String nif;
    @Basic
    @Column(name = "primer_nombre")
    private String primerNombre;
    @Basic
    @Column(name = "segundo_nombre")
    private String segundoNombre;
    @Basic
    @Column(name = "primer_apellido")
    private String primerApellido;
    @Basic
    @Column(name = "segundo_apellido")
    private String segundoApellido;
    @Basic
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "password")
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

        if (id != null ? !id.equals(usuario.id) : usuario.id != null) return false;
        if (nif != null ? !nif.equals(usuario.nif) : usuario.nif != null) return false;
        if (primerNombre != null ? !primerNombre.equals(usuario.primerNombre) : usuario.primerNombre != null)
            return false;
        if (segundoNombre != null ? !segundoNombre.equals(usuario.segundoNombre) : usuario.segundoNombre != null)
            return false;
        if (primerApellido != null ? !primerApellido.equals(usuario.primerApellido) : usuario.primerApellido != null)
            return false;
        if (segundoApellido != null ? !segundoApellido.equals(usuario.segundoApellido) : usuario.segundoApellido != null)
            return false;
        if (fechaNacimiento != null ? !fechaNacimiento.equals(usuario.fechaNacimiento) : usuario.fechaNacimiento != null)
            return false;
        if (email != null ? !email.equals(usuario.email) : usuario.email != null) return false;
        if (password != null ? !password.equals(usuario.password) : usuario.password != null) return false;

        return true;
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
}
