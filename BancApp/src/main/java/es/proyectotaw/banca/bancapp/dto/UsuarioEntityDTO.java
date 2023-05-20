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
@SuppressWarnings("unused")
public class UsuarioEntityDTO implements Serializable {
    private Integer id;
    private String nif;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private Date fechaNacimiento;
    private String email;
    private String password;
    private List<ChatEntityDTO> chatsById;
    private List<MensajeEntityDTO> mensajesById;
    private List<RolusuarioEntityDTO> rolusuariosById;
    private ClienteEntityDTO clienteByCliente;

    public UsuarioEntityDTO() {
    }

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

    public List<ChatEntityDTO> getChatsById() {
        return chatsById;
    }

    public void setChatsById(List<ChatEntityDTO> chatsById) {
        this.chatsById = chatsById;
    }

    public List<MensajeEntityDTO> getMensajesById() {
        return mensajesById;
    }

    public void setMensajesById(List<MensajeEntityDTO> mensajesById) {
        this.mensajesById = mensajesById;
    }

    public List<RolusuarioEntityDTO> getRolusuariosById() {
        return rolusuariosById;
    }

    public void setRolusuariosById(List<RolusuarioEntityDTO> rolusuariosById) {
        this.rolusuariosById = rolusuariosById;
    }

    public ClienteEntityDTO getClienteByCliente() {
        return clienteByCliente;
    }

    public void setClienteByCliente(ClienteEntityDTO clienteByCliente) {
        this.clienteByCliente = clienteByCliente;
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