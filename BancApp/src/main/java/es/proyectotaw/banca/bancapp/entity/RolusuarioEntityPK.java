package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;
import java.io.Serializable;

public class RolusuarioEntityPK implements Serializable {
    @Column(name = "idrol")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idrol;
    @Column(name = "idusuario")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idusuario;

    public Integer getIdrol() {
        return idrol;
    }

    public void setIdrol(Integer idrol) {
        this.idrol = idrol;
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

        RolusuarioEntityPK that = (RolusuarioEntityPK) o;

        if (idrol != null ? !idrol.equals(that.idrol) : that.idrol != null) return false;
        if (idusuario != null ? !idusuario.equals(that.idusuario) : that.idusuario != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idrol != null ? idrol.hashCode() : 0;
        result = 31 * result + (idusuario != null ? idusuario.hashCode() : 0);
        return result;
    }
}
