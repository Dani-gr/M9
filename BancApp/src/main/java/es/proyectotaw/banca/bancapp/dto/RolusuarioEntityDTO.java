package es.proyectotaw.banca.bancapp.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link es.proyectotaw.banca.bancapp.entity.RolusuarioEntity} entity
 */
public class RolusuarioEntityDTO implements Serializable {
    private final Integer id;
    private final Byte bloqueado;
    private final RolEntityDTO rolByIdrol;
    private final UsuarioEntityDTO usuarioByIdusuario;
    private final EmpresaEntityDTO empresaByIdempresa;

    public RolusuarioEntityDTO(Integer id, Byte bloqueado, RolEntityDTO rolByIdrol, UsuarioEntityDTO usuarioByIdusuario, EmpresaEntityDTO empresaByIdempresa) {
        this.id = id;
        this.bloqueado = bloqueado;
        this.rolByIdrol = rolByIdrol;
        this.usuarioByIdusuario = usuarioByIdusuario;
        this.empresaByIdempresa = empresaByIdempresa;
    }

    public Integer getId() {
        return id;
    }

    public Byte getBloqueado() {
        return bloqueado;
    }

    public RolEntityDTO getRolByIdrol() {
        return rolByIdrol;
    }

    public UsuarioEntityDTO getUsuarioByIdusuario() {
        return usuarioByIdusuario;
    }

    public EmpresaEntityDTO getEmpresaByIdempresa() {
        return empresaByIdempresa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolusuarioEntityDTO entity = (RolusuarioEntityDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.bloqueado, entity.bloqueado) &&
                Objects.equals(this.rolByIdrol, entity.rolByIdrol) &&
                Objects.equals(this.usuarioByIdusuario, entity.usuarioByIdusuario) &&
                Objects.equals(this.empresaByIdempresa, entity.empresaByIdempresa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bloqueado, rolByIdrol, usuarioByIdusuario, empresaByIdempresa);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "bloqueado = " + bloqueado + ", " +
                "rolByIdrol = " + rolByIdrol + ", " +
                "usuarioByIdusuario = " + usuarioByIdusuario + ", " +
                "empresaByIdempresa = " + empresaByIdempresa + ")";
    }
}