package es.proyectotaw.banca.bancapp.dto;

import es.proyectotaw.banca.bancapp.entity.RolEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link es.proyectotaw.banca.bancapp.entity.RolEntity} entity
 *
 * @author Daniel García Rodríguez (method {@link #toEntity()})
 */
@SuppressWarnings("unused")
public class RolEntityDTO implements Serializable {
    private Integer idrol;
    private String nombre;
    private List<RolusuarioEntityDTO> rolusuariosByIdrol;

    public RolEntityDTO() {
    }

    public RolEntityDTO(Integer idrol, String nombre, List<RolusuarioEntityDTO> rolusuariosByIdrol) {
        this.idrol = idrol;
        this.nombre = nombre;
        this.rolusuariosByIdrol = rolusuariosByIdrol;
    }

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

    public List<RolusuarioEntityDTO> getRolusuariosByIdrol() {
        return rolusuariosByIdrol;
    }

    public void setRolusuariosByIdrol(List<RolusuarioEntityDTO> rolusuariosByIdrol) {
        this.rolusuariosByIdrol = rolusuariosByIdrol;
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

    public RolEntity toEntity() {
        RolEntity rolEntity = new RolEntity();
        rolEntity.setIdrol(idrol);
        rolEntity.setNombre(nombre);
        rolEntity.setRolusuariosByIdrol(rolusuariosByIdrol.stream().map(RolusuarioEntityDTO::toEntity).toList());

        return rolEntity;
    }
}