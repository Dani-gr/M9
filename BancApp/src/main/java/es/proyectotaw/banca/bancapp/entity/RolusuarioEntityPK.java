package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class RolusuarioEntityPK implements Serializable {
    @Column(name = "idderol", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idderol;
    @Column(name = "idusuario", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idusuario;

    public Integer getIdderol() {
        return idderol;
    }

    public void setIdderol(Integer idderol) {
        this.idderol = idderol;
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
}
