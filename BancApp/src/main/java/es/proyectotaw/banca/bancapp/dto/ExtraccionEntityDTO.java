package es.proyectotaw.banca.bancapp.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link es.proyectotaw.banca.bancapp.entity.ExtraccionEntity} entity
 */
public class ExtraccionEntityDTO implements Serializable {
    private final Integer operacion;
    private final Double cantidad;
    private final OperacionEntityDTO operacionByOperacion;

    public ExtraccionEntityDTO(Integer operacion, Double cantidad, OperacionEntityDTO operacionByOperacion) {
        this.operacion = operacion;
        this.cantidad = cantidad;
        this.operacionByOperacion = operacionByOperacion;
    }

    public Integer getOperacion() {
        return operacion;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public OperacionEntityDTO getOperacionByOperacion() {
        return operacionByOperacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExtraccionEntityDTO entity = (ExtraccionEntityDTO) o;
        return Objects.equals(this.operacion, entity.operacion) &&
                Objects.equals(this.cantidad, entity.cantidad) &&
                Objects.equals(this.operacionByOperacion, entity.operacionByOperacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operacion, cantidad, operacionByOperacion);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "operacion = " + operacion + ", " +
                "cantidad = " + cantidad + ", " +
                "operacionByOperacion = " + operacionByOperacion + ")";
    }
}