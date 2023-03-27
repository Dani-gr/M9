package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "rol", schema = "bancodb", catalog = "")
public class RolEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idRol", nullable = false)
    private Integer idRol;
    @Basic
    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;
    @OneToMany(mappedBy = "rolByIdRol")
    private Collection<RolusuarioEntity> rolusuariosByIdRol;

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
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

        if (idRol != null ? !idRol.equals(rolEntity.idRol) : rolEntity.idRol != null) return false;
        if (nombre != null ? !nombre.equals(rolEntity.nombre) : rolEntity.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRol != null ? idRol.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }

    public Collection<RolusuarioEntity> getRolusuariosByIdRol() {
        return rolusuariosByIdRol;
    }

    public void setRolusuariosByIdRol(Collection<RolusuarioEntity> rolusuariosByIdRol) {
        this.rolusuariosByIdRol = rolusuariosByIdRol;
    }
}
