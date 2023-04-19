package es.proyectotaw.banca.bancapp.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link es.proyectotaw.banca.bancapp.entity.CambdivisaEntity} entity
 */
public class CambdivisaEntityDTO implements Serializable {
    private final Integer operacion;
    private final String origen;
    private final String destino;
    private final OperacionEntityDTO operacionByOperacion;

    public CambdivisaEntityDTO(Integer operacion, String origen, String destino, OperacionEntityDTO operacionByOperacion) {
        this.operacion = operacion;
        this.origen = origen;
        this.destino = destino;
        this.operacionByOperacion = operacionByOperacion;
    }

    public Integer getOperacion() {
        return operacion;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public OperacionEntityDTO getOperacionByOperacion() {
        return operacionByOperacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CambdivisaEntityDTO entity = (CambdivisaEntityDTO) o;
        return Objects.equals(this.operacion, entity.operacion) &&
                Objects.equals(this.origen, entity.origen) &&
                Objects.equals(this.destino, entity.destino) &&
                Objects.equals(this.operacionByOperacion, entity.operacionByOperacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operacion, origen, destino, operacionByOperacion);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "operacion = " + operacion + ", " +
                "origen = " + origen + ", " +
                "destino = " + destino + ", " +
                "operacionByOperacion = " + operacionByOperacion + ")";
    }
}