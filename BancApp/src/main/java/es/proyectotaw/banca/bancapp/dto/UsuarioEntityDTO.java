package es.proyectotaw.banca.bancapp.dto;

import es.proyectotaw.banca.bancapp.entity.UsuarioEntity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link es.proyectotaw.banca.bancapp.entity.UsuarioEntity} entity
 *
 * @author Daniel García Rodríguez (method {@link #toEntity()})
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
    private final List<ChatEntityDTO> chatsById;
    private final List<MensajeEntityDTO> mensajesById;
    private final List<RolusuarioEntityDTO> rolusuariosById;
    private final ClienteEntityDTO clienteByCliente;

    public UsuarioEntityDTO(Integer id, String nif, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, Date fechaNacimiento, String email, String password, List<ChatEntityDTO> chatsById, List<MensajeEntityDTO> mensajesById, List<RolusuarioEntityDTO> rolusuariosById, ClienteEntityDTO clienteByCliente) {
        this.id = id;
        this.nif = nif;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.password = password;
        this.chatsById = chatsById;
        this.mensajesById = mensajesById;
        this.rolusuariosById = rolusuariosById;
        this.clienteByCliente = clienteByCliente;
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

    public List<ChatEntityDTO> getChatsById() {
        return chatsById;
    }

    public List<MensajeEntityDTO> getMensajesById() {
        return mensajesById;
    }

    public List<RolusuarioEntityDTO> getRolusuariosById() {
        return rolusuariosById;
    }

    public ClienteEntityDTO getClienteByCliente() {
        return clienteByCliente;
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
                Objects.equals(this.chatsById, entity.chatsById) &&
                Objects.equals(this.mensajesById, entity.mensajesById) &&
                Objects.equals(this.rolusuariosById, entity.rolusuariosById) &&
                Objects.equals(this.clienteByCliente, entity.clienteByCliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nif, primerNombre, segundoNombre, primerApellido, segundoApellido, fechaNacimiento, email, password, chatsById, mensajesById, rolusuariosById, clienteByCliente);
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
                "chatsById = " + chatsById + ", " +
                "mensajesById = " + mensajesById + ", " +
                "rolusuariosById = " + rolusuariosById + ", " +
                "clienteByCliente = " + clienteByCliente + ")";
    }

    public UsuarioEntity toEntity() {
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.construct(
                nif, primerNombre, segundoNombre, primerApellido, segundoApellido, fechaNacimiento, email, password
        );
        usuarioEntity.setId(id);
        usuarioEntity.setClienteByCliente(clienteByCliente.toEntity());
        usuarioEntity.setRolusuariosById(rolusuariosById.stream().map(RolusuarioEntityDTO::toEntity).toList());
        usuarioEntity.setChatsById(chatsById.stream().map(ChatEntityDTO::toEntity).toList());
        usuarioEntity.setMensajesById(mensajesById.stream().map(MensajeEntityDTO::toEntity).toList());

        return usuarioEntity;
    }
}