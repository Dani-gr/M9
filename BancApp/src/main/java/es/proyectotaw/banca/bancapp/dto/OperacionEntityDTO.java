package es.proyectotaw.banca.bancapp.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

/**
 * A DTO for the {@link es.proyectotaw.banca.bancapp.entity.OperacionEntity} entity
 */
public class OperacionEntityDTO implements Serializable {
    private final Integer idOperacion;
    private final Date fecha;
    private final CambdivisaEntityDTO cambdivisaByIdOperacion;
    private final ExtraccionEntityDTO extraccionByIdOperacion;
    private final CuentaEntityDTO cuentaByCuentaRealiza;
    private final TransferenciaEntityDTO transferenciaByIdOperacion;

    public OperacionEntityDTO(Integer idOperacion, Date fecha, CambdivisaEntityDTO cambdivisaByIdOperacion, ExtraccionEntityDTO extraccionByIdOperacion, CuentaEntityDTO cuentaByCuentaRealiza, TransferenciaEntityDTO transferenciaByIdOperacion) {
        this.idOperacion = idOperacion;
        this.fecha = fecha;
        this.cambdivisaByIdOperacion = cambdivisaByIdOperacion;
        this.extraccionByIdOperacion = extraccionByIdOperacion;
        this.cuentaByCuentaRealiza = cuentaByCuentaRealiza;
        this.transferenciaByIdOperacion = transferenciaByIdOperacion;
    }

    public Integer getIdOperacion() {
        return idOperacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public CambdivisaEntityDTO getCambdivisaByIdOperacion() {
        return cambdivisaByIdOperacion;
    }

    public ExtraccionEntityDTO getExtraccionByIdOperacion() {
        return extraccionByIdOperacion;
    }

    public CuentaEntityDTO getCuentaByCuentaRealiza() {
        return cuentaByCuentaRealiza;
    }

    public TransferenciaEntityDTO getTransferenciaByIdOperacion() {
        return transferenciaByIdOperacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperacionEntityDTO entity = (OperacionEntityDTO) o;
        return Objects.equals(this.idOperacion, entity.idOperacion) &&
                Objects.equals(this.fecha, entity.fecha) &&
                Objects.equals(this.cambdivisaByIdOperacion, entity.cambdivisaByIdOperacion) &&
                Objects.equals(this.extraccionByIdOperacion, entity.extraccionByIdOperacion) &&
                Objects.equals(this.cuentaByCuentaRealiza, entity.cuentaByCuentaRealiza) &&
                Objects.equals(this.transferenciaByIdOperacion, entity.transferenciaByIdOperacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOperacion, fecha, cambdivisaByIdOperacion, extraccionByIdOperacion, cuentaByCuentaRealiza, transferenciaByIdOperacion);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "idOperacion = " + idOperacion + ", " +
                "fecha = " + fecha + ", " +
                "cambdivisaByIdOperacion = " + cambdivisaByIdOperacion + ", " +
                "extraccionByIdOperacion = " + extraccionByIdOperacion + ", " +
                "cuentaByCuentaRealiza = " + cuentaByCuentaRealiza + ", " +
                "transferenciaByIdOperacion = " + transferenciaByIdOperacion + ")";
    }
}