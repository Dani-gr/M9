package es.proyectotaw.banca.bancapp.dto;

import es.proyectotaw.banca.bancapp.entity.RolEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

/**
 * A DTO for the {@link RolEntity} entity
 */
public class RolEntityDTO implements Serializable {
    private final String nombre;
    private final Collection<RolusuarioEntityDTO> rolusuariosByIdRol;

    public RolEntityDTO(String nombre, Collection<RolusuarioEntityDTO> rolusuariosByIdRol) {
        this.nombre = nombre;
        this.rolusuariosByIdRol = rolusuariosByIdRol;
    }

    public String getNombre() {
        return nombre;
    }

    public Collection<RolusuarioEntityDTO> getRolusuariosByIdRol() {
        return rolusuariosByIdRol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolEntityDTO entity = (RolEntityDTO) o;
        return Objects.equals(this.nombre, entity.nombre) &&
                Objects.equals(this.rolusuariosByIdRol, entity.rolusuariosByIdRol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, rolusuariosByIdRol);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "nombre = " + nombre + ", " +
                "rolusuariosByIdRol = " + rolusuariosByIdRol + ")";
    }
}