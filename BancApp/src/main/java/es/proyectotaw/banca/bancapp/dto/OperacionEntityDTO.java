package es.proyectotaw.banca.bancapp.dto;

import es.proyectotaw.banca.bancapp.entity.OperacionEntity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link es.proyectotaw.banca.bancapp.entity.OperacionEntity} entity
 *
 * @author Daniel García Rodríguez (method {@link #toEntity()})
 */
@SuppressWarnings("unused")
public class OperacionEntityDTO implements Serializable {
    private Integer idOperacion;
    private Date fecha;
    private List<CambDivisaEntityDTO> cambDivisasByIdOperacion;
    private List<ExtraccionEntityDTO> extraccionsByIdOperacion;
    private CuentaEntityDTO cuentaByCuentaRealiza;
    private List<TransferenciaEntityDTO> transferenciasByIdOperacion;

    public OperacionEntityDTO() {
    }

    public OperacionEntityDTO(Integer idOperacion, Date fecha, List<CambDivisaEntityDTO> cambDivisasByIdOperacion, List<ExtraccionEntityDTO> extraccionsByIdOperacion, CuentaEntityDTO cuentaByCuentaRealiza, List<TransferenciaEntityDTO> transferenciasByIdOperacion) {
        this.idOperacion = idOperacion;
        this.fecha = fecha;
        this.cambDivisasByIdOperacion = cambDivisasByIdOperacion;
        this.extraccionsByIdOperacion = extraccionsByIdOperacion;
        this.cuentaByCuentaRealiza = cuentaByCuentaRealiza;
        this.transferenciasByIdOperacion = transferenciasByIdOperacion;
    }

    public Integer getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(Integer idOperacion) {
        this.idOperacion = idOperacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<CambDivisaEntityDTO> getCambDivisasByIdOperacion() {
        return cambDivisasByIdOperacion;
    }

    public void setCambDivisasByIdOperacion(List<CambDivisaEntityDTO> cambDivisasByIdOperacion) {
        this.cambDivisasByIdOperacion = cambDivisasByIdOperacion;
    }

    public List<ExtraccionEntityDTO> getExtraccionsByIdOperacion() {
        return extraccionsByIdOperacion;
    }

    public void setExtraccionsByIdOperacion(List<ExtraccionEntityDTO> extraccionsByIdOperacion) {
        this.extraccionsByIdOperacion = extraccionsByIdOperacion;
    }

    public CuentaEntityDTO getCuentaByCuentaRealiza() {
        return cuentaByCuentaRealiza;
    }

    public void setCuentaByCuentaRealiza(CuentaEntityDTO cuentaByCuentaRealiza) {
        this.cuentaByCuentaRealiza = cuentaByCuentaRealiza;
    }

    public List<TransferenciaEntityDTO> getTransferenciasByIdOperacion() {
        return transferenciasByIdOperacion;
    }

    public void setTransferenciasByIdOperacion(List<TransferenciaEntityDTO> transferenciasByIdOperacion) {
        this.transferenciasByIdOperacion = transferenciasByIdOperacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperacionEntityDTO entity = (OperacionEntityDTO) o;
        return Objects.equals(this.idOperacion, entity.idOperacion) &&
                Objects.equals(this.fecha, entity.fecha) &&
                Objects.equals(this.cambDivisasByIdOperacion, entity.cambDivisasByIdOperacion) &&
                Objects.equals(this.extraccionsByIdOperacion, entity.extraccionsByIdOperacion) &&
                Objects.equals(this.cuentaByCuentaRealiza, entity.cuentaByCuentaRealiza) &&
                Objects.equals(this.transferenciasByIdOperacion, entity.transferenciasByIdOperacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOperacion, fecha, cambDivisasByIdOperacion, extraccionsByIdOperacion, cuentaByCuentaRealiza, transferenciasByIdOperacion);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "idOperacion = " + idOperacion + ", " +
                "fecha = " + fecha + ", " +
                "cambDivisasByIdOperacion = " + cambDivisasByIdOperacion + ", " +
                "extraccionsByIdOperacion = " + extraccionsByIdOperacion + ", " +
                "cuentaByCuentaRealiza = " + cuentaByCuentaRealiza + ", " +
                "transferenciasByIdOperacion = " + transferenciasByIdOperacion + ")";
    }

    public OperacionEntity toEntity() {
        OperacionEntity operacionEntity = new OperacionEntity();
        operacionEntity.setIdOperacion(idOperacion);
        operacionEntity.setFecha(fecha);
        operacionEntity.setCuentaByCuentaRealiza(cuentaByCuentaRealiza.toEntity());
        operacionEntity.setExtraccionsByIdOperacion(extraccionsByIdOperacion.stream().map(ExtraccionEntityDTO::toEntity).toList());
        operacionEntity.setTransferenciasByIdOperacion(transferenciasByIdOperacion.stream().map(TransferenciaEntityDTO::toEntity).toList());
        operacionEntity.setCambDivisasByIdOperacion(cambDivisasByIdOperacion.stream().map(CambDivisaEntityDTO::toEntity).toList());

        return operacionEntity;
    }
}