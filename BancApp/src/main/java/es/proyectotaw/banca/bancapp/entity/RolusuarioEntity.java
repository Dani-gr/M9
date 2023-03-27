package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "rolusuario", schema = "bancodb", catalog = "")
@IdClass(RolusuarioEntityPK.class)
public class RolusuarioEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idRol", nullable = false)
    private Integer idRol;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idUsuario", nullable = false)
    private Integer idUsuario;
    @ManyToOne
    @JoinColumn(name = "idRol", referencedColumnName = "idRol", nullable = false)
    private RolEntity rolByIdRol;
    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "ID", nullable = false)
    private UsuarioEntity usuarioByIdUsuario;
    @ManyToOne
    @JoinColumns({@JoinColumn(name = "cifEmpresa", referencedColumnName = "CIF"), @JoinColumn(name = "idEmpresa", referencedColumnName = "ID")})
    private EmpresaEntity empresa;

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolusuarioEntity that = (RolusuarioEntity) o;

        if (idRol != null ? !idRol.equals(that.idRol) : that.idRol != null) return false;
        if (idUsuario != null ? !idUsuario.equals(that.idUsuario) : that.idUsuario != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRol != null ? idRol.hashCode() : 0;
        result = 31 * result + (idUsuario != null ? idUsuario.hashCode() : 0);
        return result;
    }

    public RolEntity getRolByIdRol() {
        return rolByIdRol;
    }

    public void setRolByIdRol(RolEntity rolByIdRol) {
        this.rolByIdRol = rolByIdRol;
    }

    public UsuarioEntity getUsuarioByIdUsuario() {
        return usuarioByIdUsuario;
    }

    public void setUsuarioByIdUsuario(UsuarioEntity usuarioByIdUsuario) {
        this.usuarioByIdUsuario = usuarioByIdUsuario;
    }

    public EmpresaEntity getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaEntity empresa) {
        this.empresa = empresa;
    }
}
