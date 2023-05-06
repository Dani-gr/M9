package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("unused")
@Entity
@Table(name = "rol", schema = "bancodb")
public class RolEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idrol", nullable = false)
    private Integer idrol;
    @Basic
    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;
    @OneToMany(mappedBy = "rolByIdrol")
    private List<RolusuarioEntity> rolusuariosByIdrol;

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

        if (!Objects.equals(idrol, rolEntity.idrol)) return false;
        return Objects.equals(nombre, rolEntity.nombre);
    }

    @Override
    public int hashCode() {
        int result = idrol != null ? idrol.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }

    public List<RolusuarioEntity> getRolusuariosByIdrol() {
        return rolusuariosByIdrol;
    }

    public void setRolusuariosByIdrol(List<RolusuarioEntity> rolusuariosByIdrol) {
        this.rolusuariosByIdrol = rolusuariosByIdrol;
    }
}
