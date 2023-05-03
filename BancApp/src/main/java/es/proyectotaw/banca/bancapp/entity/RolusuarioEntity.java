package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "rolusuario", schema = "bancodb", catalog = "")
public class RolusuarioEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "bloqueado")
    private Byte bloqueado;
    @ManyToOne
    @JoinColumn(name = "idrol", referencedColumnName = "idrol", nullable = false)
    private RolEntity rolByIdrol;
    @ManyToOne
    @JoinColumn(name = "idusuario", referencedColumnName = "id", nullable = false)
    private UsuarioEntity usuarioByIdusuario;
    @ManyToOne
    @JoinColumn(name = "idempresa", referencedColumnName = "id")
    private EmpresaEntity empresaByIdempresa;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolusuarioEntity that = (RolusuarioEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (bloqueado != null ? !bloqueado.equals(that.bloqueado) : that.bloqueado != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (bloqueado != null ? bloqueado.hashCode() : 0);
        return result;
    }

    public RolEntity getRolByIdrol() {
        return rolByIdrol;
    }

    public void setRolByIdrol(RolEntity rolByIdrol) {
        this.rolByIdrol = rolByIdrol;
    }

    public UsuarioEntity getUsuarioByIdusuario() {
        return usuarioByIdusuario;
    }

    public void setUsuarioByIdusuario(UsuarioEntity usuarioByIdusuario) {
        this.usuarioByIdusuario = usuarioByIdusuario;
    }

    public EmpresaEntity getEmpresaByIdempresa() {
        return empresaByIdempresa;
    }

    public void setEmpresaByIdempresa(EmpresaEntity empresaByIdempresa) {
        this.empresaByIdempresa = empresaByIdempresa;
    }
}
