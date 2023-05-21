package es.proyectotaw.banca.bancapp.dto;

import es.proyectotaw.banca.bancapp.entity.ExtraccionEntity;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link es.proyectotaw.banca.bancapp.entity.ExtraccionEntity} entity
 *
 * @author Daniel García Rodríguez (method {@link #toEntity()})
 */
@SuppressWarnings("unused")
public class ExtraccionEntityDTO implements Serializable {
    private Integer idExtraccion;
    private Double cantidad;
    private OperacionEntityDTO operacionByOperacion;

    public ExtraccionEntityDTO() {
    }

    public ExtraccionEntityDTO(Integer idExtraccion, Double cantidad, OperacionEntityDTO operacionByOperacion) {
        this.idExtraccion = idExtraccion;
        this.cantidad = cantidad;
        this.operacionByOperacion = operacionByOperacion;
    }

    public Integer getIdExtraccion() {
        return idExtraccion;
    }

    public void setIdExtraccion(Integer idExtraccion) {
        this.idExtraccion = idExtraccion;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public OperacionEntityDTO getOperacionByOperacion() {
        return operacionByOperacion;
    }

    public void setOperacionByOperacion(OperacionEntityDTO operacionByOperacion) {
        this.operacionByOperacion = operacionByOperacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExtraccionEntityDTO entity = (ExtraccionEntityDTO) o;
        return Objects.equals(this.idExtraccion, entity.idExtraccion) &&
                Objects.equals(this.cantidad, entity.cantidad) &&
                Objects.equals(this.operacionByOperacion, entity.operacionByOperacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idExtraccion, cantidad, operacionByOperacion);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "idExtraccion = " + idExtraccion + ", " +
                "cantidad = " + cantidad + ", " +
                "operacionByOperacion = " + operacionByOperacion + ")";
    }

    public ExtraccionEntity toEntity() {
        ExtraccionEntity extraccionEntity = new ExtraccionEntity();
        extraccionEntity.setIdExtraccion(idExtraccion);
        extraccionEntity.setCantidad(cantidad);
        extraccionEntity.setOperacionByOperacion(operacionByOperacion.toEntity());

        return extraccionEntity;
    }
}