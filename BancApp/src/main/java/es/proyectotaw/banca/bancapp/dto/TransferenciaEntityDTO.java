package es.proyectotaw.banca.bancapp.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link es.proyectotaw.banca.bancapp.entity.TransferenciaEntity} entity
 */
public class TransferenciaEntityDTO implements Serializable {
    private final Integer operacion;
    private final Double cantidad;
    private final OperacionEntityDTO operacionByOperacion;
    private final CuentaEntityDTO cuentaByCuentaDestino;

    public TransferenciaEntityDTO(Integer operacion, Double cantidad, OperacionEntityDTO operacionByOperacion, CuentaEntityDTO cuentaByCuentaDestino) {
        this.operacion = operacion;
        this.cantidad = cantidad;
        this.operacionByOperacion = operacionByOperacion;
        this.cuentaByCuentaDestino = cuentaByCuentaDestino;
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

    public CuentaEntityDTO getCuentaByCuentaDestino() {
        return cuentaByCuentaDestino;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferenciaEntityDTO entity = (TransferenciaEntityDTO) o;
        return Objects.equals(this.operacion, entity.operacion) &&
                Objects.equals(this.cantidad, entity.cantidad) &&
                Objects.equals(this.operacionByOperacion, entity.operacionByOperacion) &&
                Objects.equals(this.cuentaByCuentaDestino, entity.cuentaByCuentaDestino);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operacion, cantidad, operacionByOperacion, cuentaByCuentaDestino);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "operacion = " + operacion + ", " +
                "cantidad = " + cantidad + ", " +
                "operacionByOperacion = " + operacionByOperacion + ", " +
                "cuentaByCuentaDestion = " + cuentaByCuentaDestino + ")";
    }
}