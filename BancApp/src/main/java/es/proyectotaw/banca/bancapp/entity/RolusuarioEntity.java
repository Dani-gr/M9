package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "rolusuario", schema = "bancodb")
@IdClass(RolusuarioEntityPK.class)
public class RolusuarioEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idderol", nullable = false)
    private Integer idderol;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idusuario", nullable = false)
    private Integer idusuario;
    @ManyToOne
    @JoinColumn(name = "idderol", referencedColumnName = "idrol", nullable = false, insertable = false, updatable = false)
    private RolEntity rolByIdrol;
    @ManyToOne
    @JoinColumn(name = "idusuario", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private UsuarioEntity usuarioByIdusuario;
    @ManyToOne
    @JoinColumn(name = "idempresa", referencedColumnName = "id", insertable = false, updatable = false)
    private EmpresaEntity empresaByIdempresa;

    public Integer getIdderol() {
        return idderol;
    }

    public void setIdderol(Integer idrol) {
        this.idderol = idrol;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolusuarioEntity that = (RolusuarioEntity) o;

        if (idderol != null ? !idderol.equals(that.idderol) : that.idderol != null) return false;
        if (idusuario != null ? !idusuario.equals(that.idusuario) : that.idusuario != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idderol != null ? idderol.hashCode() : 0;
        result = 31 * result + (idusuario != null ? idusuario.hashCode() : 0);
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
