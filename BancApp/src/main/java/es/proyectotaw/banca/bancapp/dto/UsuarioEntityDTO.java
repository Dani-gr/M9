package es.proyectotaw.banca.bancapp.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

/**
 * A DTO for the {@link es.proyectotaw.banca.bancapp.entity.UsuarioEntity} entity
 */
public class UsuarioEntityDTO implements Serializable {
    private final Integer id;
    private final String nif;
    private final String primerNombre;
    private final String segundoNombre;
    private final String primerApellido;
    private final String segundoApellido;
    private final Date fechaNacimiento;
    private final String email;
    private final String password;
    private final Integer idDireccion;
    private final Collection<ChatEntityDTO> chatsById;
    private final DireccionEntityDTO direccionById;
    private final Collection<MensajeEntityDTO> mensajesById;
    private final Collection<RolusuarioEntityDTO> rolusuariosById;
    private final ClienteEntityDTO clienteById;

    public UsuarioEntityDTO(Integer id, String nif, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, Date fechaNacimiento, String email, String password, Integer idDireccion, Collection<ChatEntityDTO> chatsById, DireccionEntityDTO direccionById, Collection<MensajeEntityDTO> mensajesById, Collection<RolusuarioEntityDTO> rolusuariosById, ClienteEntityDTO clienteById) {
        this.id = id;
        this.nif = nif;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.password = password;
        this.idDireccion = idDireccion;
        this.chatsById = chatsById;
        this.direccionById = direccionById;
        this.mensajesById = mensajesById;
        this.rolusuariosById = rolusuariosById;
        this.clienteById = clienteById;
    }

    public Integer getId() {
        return id;
    }

    public String getNif() {
        return nif;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Integer getIdDireccion() {
        return idDireccion;
    }

    public Collection<ChatEntityDTO> getChatsById() {
        return chatsById;
    }

    public DireccionEntityDTO getDireccionById() {
        return direccionById;
    }

    public Collection<MensajeEntityDTO> getMensajesById() {
        return mensajesById;
    }

    public Collection<RolusuarioEntityDTO> getRolusuariosById() {
        return rolusuariosById;
    }

    public ClienteEntityDTO getClienteById() {
        return clienteById;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioEntityDTO entity = (UsuarioEntityDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.nif, entity.nif) &&
                Objects.equals(this.primerNombre, entity.primerNombre) &&
                Objects.equals(this.segundoNombre, entity.segundoNombre) &&
                Objects.equals(this.primerApellido, entity.primerApellido) &&
                Objects.equals(this.segundoApellido, entity.segundoApellido) &&
                Objects.equals(this.fechaNacimiento, entity.fechaNacimiento) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.password, entity.password) &&
                Objects.equals(this.idDireccion, entity.idDireccion) &&
                Objects.equals(this.chatsById, entity.chatsById) &&
                Objects.equals(this.direccionById, entity.direccionById) &&
                Objects.equals(this.mensajesById, entity.mensajesById) &&
                Objects.equals(this.rolusuariosById, entity.rolusuariosById) &&
                Objects.equals(this.clienteById, entity.clienteById);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nif, primerNombre, segundoNombre, primerApellido, segundoApellido, fechaNacimiento, email, password, idDireccion, chatsById, direccionById, mensajesById, rolusuariosById, clienteById);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "nif = " + nif + ", " +
                "primerNombre = " + primerNombre + ", " +
                "segundoNombre = " + segundoNombre + ", " +
                "primerApellido = " + primerApellido + ", " +
                "segundoApellido = " + segundoApellido + ", " +
                "fechaNacimiento = " + fechaNacimiento + ", " +
                "email = " + email + ", " +
                "password = " + password + ", " +
                "idDireccion = " + idDireccion + ", " +
                "chatsById = " + chatsById + ", " +
                "direccionById = " + direccionById + ", " +
                "mensajesById = " + mensajesById + ", " +
                "rolusuariosById = " + rolusuariosById + ", " +
                "clienteById = " + clienteById + ")";
    }
}