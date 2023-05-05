package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "rol", schema = "bancodb", catalog = "")
public class RolEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idrol")
    private Integer idrol;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "rolByIdrol")
    private Collection<RolusuarioEntity> rolusuariosByIdrol;

    public Integer getIdrol() {
        return idrol;
    }

    public void setIdrol(Integer idrol) {
        this.idrol = idrol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolEntity rolEntity = (RolEntity) o;

        if (idrol != null ? !idrol.equals(rolEntity.idrol) : rolEntity.idrol != null) return false;
        if (nombre != null ? !nombre.equals(rolEntity.nombre) : rolEntity.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idrol != null ? idrol.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }

    public Collection<RolusuarioEntity> getRolusuariosByIdrol() {
        return rolusuariosByIdrol;
    }

    public void setRolusuariosByIdrol(Collection<RolusuarioEntity> rolusuariosByIdrol) {
        this.rolusuariosByIdrol = rolusuariosByIdrol;
    }
}
