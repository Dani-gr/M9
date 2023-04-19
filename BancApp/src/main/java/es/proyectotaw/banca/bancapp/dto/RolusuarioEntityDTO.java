package es.proyectotaw.banca.bancapp.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link es.proyectotaw.banca.bancapp.entity.RolusuarioEntity} entity
 */
public class RolusuarioEntityDTO implements Serializable {
    private final Integer idRol;
    private final Integer idUsuario;
    private final RolEntityDTO rolByIdRol;
    private final UsuarioEntityDTO usuarioByIdUsuario;
    private final EmpresaEntityDTO empresa;

    public RolusuarioEntityDTO(Integer idRol, Integer idUsuario, RolEntityDTO rolByIdRol, UsuarioEntityDTO usuarioByIdUsuario, EmpresaEntityDTO empresa) {
        this.idRol = idRol;
        this.idUsuario = idUsuario;
        this.rolByIdRol = rolByIdRol;
        this.usuarioByIdUsuario = usuarioByIdUsuario;
        this.empresa = empresa;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public RolEntityDTO getRolByIdRol() {
        return rolByIdRol;
    }

    public UsuarioEntityDTO getUsuarioByIdUsuario() {
        return usuarioByIdUsuario;
    }

    public EmpresaEntityDTO getEmpresa() {
        return empresa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolusuarioEntityDTO entity = (RolusuarioEntityDTO) o;
        return Objects.equals(this.idRol, entity.idRol) &&
                Objects.equals(this.idUsuario, entity.idUsuario) &&
                Objects.equals(this.rolByIdRol, entity.rolByIdRol) &&
                Objects.equals(this.usuarioByIdUsuario, entity.usuarioByIdUsuario) &&
                Objects.equals(this.empresa, entity.empresa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRol, idUsuario, rolByIdRol, usuarioByIdUsuario, empresa);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "idRol = " + idRol + ", " +
                "idUsuario = " + idUsuario + ", " +
                "rolByIdRol = " + rolByIdRol + ", " +
                "usuarioByIdUsuario = " + usuarioByIdUsuario + ", " +
                "empresa = " + empresa + ")";
    }
}