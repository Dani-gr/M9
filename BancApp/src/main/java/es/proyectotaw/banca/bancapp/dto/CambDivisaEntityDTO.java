package es.proyectotaw.banca.bancapp.dto;

import es.proyectotaw.banca.bancapp.entity.CambDivisaEntity;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link CambDivisaEntity} entity
 *
 * @author Daniel García Rodríguez (method {@link #toEntity()})
 */
@SuppressWarnings("unused")
public class CambDivisaEntityDTO implements Serializable {
    private Integer idDivisa;
    private String origen;
    private String destino;
    private Double cantidad;
    private OperacionEntityDTO operacionByOperacion;

    public CambDivisaEntityDTO() {
    }

    public CambDivisaEntityDTO(Integer idDivisa, String origen, String destino, Double cantidad, OperacionEntityDTO operacionByOperacion) {
        this.idDivisa = idDivisa;
        this.origen = origen;
        this.destino = destino;
        this.cantidad = cantidad;
        this.operacionByOperacion = operacionByOperacion;
    }

    public Integer getIdDivisa() {
        return idDivisa;
    }

    public void setIdDivisa(Integer idDivisa) {
        this.idDivisa = idDivisa;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
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
        CambDivisaEntityDTO entity = (CambDivisaEntityDTO) o;
        return Objects.equals(this.idDivisa, entity.idDivisa) &&
                Objects.equals(this.origen, entity.origen) &&
                Objects.equals(this.destino, entity.destino) &&
                Objects.equals(this.cantidad, entity.cantidad) &&
                Objects.equals(this.operacionByOperacion, entity.operacionByOperacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDivisa, origen, destino, cantidad, operacionByOperacion);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "idDivisa = " + idDivisa + ", " +
                "origen = " + origen + ", " +
                "destino = " + destino + ", " +
                "cantidad = " + cantidad + ", " +
                "operacionByOperacion = " + operacionByOperacion + ")";
    }

    public CambDivisaEntity toEntity() {
        CambDivisaEntity cambDivisaEntity = new CambDivisaEntity();
        cambDivisaEntity.setIdDivisa(idDivisa);
        cambDivisaEntity.setCantidad(cantidad);
        cambDivisaEntity.setOrigen(origen);
        cambDivisaEntity.setDestino(destino);
        cambDivisaEntity.setOperacionByOperacion(operacionByOperacion.toEntity());

        return cambDivisaEntity;
    }
}