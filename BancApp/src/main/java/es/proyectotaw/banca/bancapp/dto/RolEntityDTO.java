package es.proyectotaw.banca.bancapp.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link es.proyectotaw.banca.bancapp.entity.RolEntity} entity
 */
public class RolEntityDTO implements Serializable {
    private final Integer idrol;
    private final String nombre;
    private final List<RolusuarioEntityDTO> rolusuariosByIdrol;

    public RolEntityDTO(Integer idrol, String nombre, List<RolusuarioEntityDTO> rolusuariosByIdrol) {
        this.idrol = idrol;
        this.nombre = nombre;
        this.rolusuariosByIdrol = rolusuariosByIdrol;
    }

    public Integer getIdrol() {
        return idrol;
    }

    public String getNombre() {
        return nombre;
    }

    public List<RolusuarioEntityDTO> getRolusuariosByIdrol() {
        return rolusuariosByIdrol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolEntityDTO entity = (RolEntityDTO) o;
        return Objects.equals(this.idrol, entity.idrol) &&
                Objects.equals(this.nombre, entity.nombre) &&
                Objects.equals(this.rolusuariosByIdrol, entity.rolusuariosByIdrol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idrol, nombre, rolusuariosByIdrol);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "idrol = " + idrol + ", " +
                "nombre = " + nombre + ", " +
                "rolusuariosByIdrol = " + rolusuariosByIdrol + ")";
    }
}