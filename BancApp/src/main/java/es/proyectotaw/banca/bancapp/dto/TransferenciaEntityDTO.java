package es.proyectotaw.banca.bancapp.dto;

import es.proyectotaw.banca.bancapp.entity.TransferenciaEntity;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link es.proyectotaw.banca.bancapp.entity.TransferenciaEntity} entity
 *
 * @author Daniel García Rodríguez (method {@link #toEntity()})
 */
public class TransferenciaEntityDTO implements Serializable {
    private final Integer idTransferencia;
    private final Double cantidad;
    private final String ibanDestino;
    private final OperacionEntityDTO operacionByOperacion;
    private final CuentaEntityDTO cuentaByCuentaDestino;

    public TransferenciaEntityDTO(Integer idTransferencia, Double cantidad, String ibanDestino, OperacionEntityDTO operacionByOperacion, CuentaEntityDTO cuentaByCuentaDestino) {
        this.idTransferencia = idTransferencia;
        this.cantidad = cantidad;
        this.ibanDestino = ibanDestino;
        this.operacionByOperacion = operacionByOperacion;
        this.cuentaByCuentaDestino = cuentaByCuentaDestino;
    }

    public Integer getIdTransferencia() {
        return idTransferencia;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public String getIbanDestino() {
        return ibanDestino;
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
        return Objects.equals(this.idTransferencia, entity.idTransferencia) &&
                Objects.equals(this.cantidad, entity.cantidad) &&
                Objects.equals(this.ibanDestino, entity.ibanDestino) &&
                Objects.equals(this.operacionByOperacion, entity.operacionByOperacion) &&
                Objects.equals(this.cuentaByCuentaDestino, entity.cuentaByCuentaDestino);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTransferencia, cantidad, ibanDestino, operacionByOperacion, cuentaByCuentaDestino);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "idTransferencia = " + idTransferencia + ", " +
                "cantidad = " + cantidad + ", " +
                "ibanDestino = " + ibanDestino + ", " +
                "operacionByOperacion = " + operacionByOperacion + ", " +
                "cuentaByCuentaDestino = " + cuentaByCuentaDestino + ")";
    }

    public TransferenciaEntity toEntity() {
        TransferenciaEntity transferenciaEntity = new TransferenciaEntity();
        transferenciaEntity.setIdTransferencia(idTransferencia);
        transferenciaEntity.setCantidad(cantidad);
        transferenciaEntity.setIbanDestino(ibanDestino);
        transferenciaEntity.setOperacionByOperacion(operacionByOperacion.toEntity());
        transferenciaEntity.setCuentaByCuentaDestino(cuentaByCuentaDestino.toEntity());

        return transferenciaEntity;
    }
}