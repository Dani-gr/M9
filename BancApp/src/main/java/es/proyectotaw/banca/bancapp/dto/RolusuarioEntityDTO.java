package es.proyectotaw.banca.bancapp.dto;

import es.proyectotaw.banca.bancapp.entity.RolusuarioEntity;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link es.proyectotaw.banca.bancapp.entity.RolusuarioEntity} entity
 *
 * @author Daniel García Rodríguez (method {@link #toEntity()})
 */
@SuppressWarnings("unused")
public class RolusuarioEntityDTO implements Serializable {
    private Integer id;
    private Byte bloqueado;
    private RolEntityDTO rolByIdrol;
    private UsuarioEntityDTO usuarioByIdusuario;
    private EmpresaEntityDTO empresaByIdempresa;

    public RolusuarioEntityDTO() {
    }

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

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(Byte bloqueado) {
        this.bloqueado = bloqueado;
    }

    public RolEntityDTO getRolByIdrol() {
        return rolByIdrol;
    }

    public void setRolByIdrol(RolEntityDTO rolByIdrol) {
        this.rolByIdrol = rolByIdrol;
    }

    public UsuarioEntityDTO getUsuarioByIdusuario() {
        return usuarioByIdusuario;
    }

    public void setUsuarioByIdusuario(UsuarioEntityDTO usuarioByIdusuario) {
        this.usuarioByIdusuario = usuarioByIdusuario;
    }

    public EmpresaEntityDTO getEmpresaByIdempresa() {
        return empresaByIdempresa;
    }

    public void setEmpresaByIdempresa(EmpresaEntityDTO empresaByIdempresa) {
        this.empresaByIdempresa = empresaByIdempresa;
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

    public RolusuarioEntity toEntity() {
        RolusuarioEntity rolusuarioEntity = new RolusuarioEntity();
        rolusuarioEntity.setId(id);
        rolusuarioEntity.setBloqueado(bloqueado);
        rolusuarioEntity.setEmpresaByIdempresa(empresaByIdempresa.toEntity());
        rolusuarioEntity.setUsuarioByIdusuario(usuarioByIdusuario.toEntity());
        rolusuarioEntity.setRolByIdrol(rolByIdrol.toEntity());

        return rolusuarioEntity;
    }

    public CuentaEntityDTO getCuentaAsociada() {
        return this.toEntity().getCuentaAsociada().toDTO();
    }
}