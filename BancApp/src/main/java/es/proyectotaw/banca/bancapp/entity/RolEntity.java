package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "rol", schema = "bancodb", catalog = "")
public class RolEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idRol")
    private Integer idRol;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "rolByIdRol")
    private List<RolusuarioEntity> rolusuariosByIdRol;

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
        return Objects.equals(idRol, rolEntity.idRol) && Objects.equals(nombre, rolEntity.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRol, nombre);
    }

    public List<RolusuarioEntity> getRolusuariosByIdRol() {
        return rolusuariosByIdRol;
    }

    public void setRolusuariosByIdRol(List<RolusuarioEntity> rolusuariosByIdRol) {
        this.rolusuariosByIdRol = rolusuariosByIdRol;
    }
}
