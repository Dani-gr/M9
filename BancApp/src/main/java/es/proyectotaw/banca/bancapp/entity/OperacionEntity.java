package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@SuppressWarnings("unused")
@Entity
@Table(name = "operacion", schema = "bancodb", catalog = "")
public class OperacionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_operacion")
    private Integer idOperacion;
    @Basic
    @Column(name = "fecha")
    private Date fecha;
    @OneToOne(mappedBy = "operacionByOperacion")
    private CambDivisaEntity cambDivisaByIdOperacion;
    @OneToOne(mappedBy = "operacionByOperacion")
    private ExtraccionEntity extraccionByIdOperacion;
    @ManyToOne
    @JoinColumn(name = "cuenta_realiza", referencedColumnName = "num_cuenta", nullable = false)
    private CuentaEntity cuentaByCuentaRealiza;
    @OneToOne(mappedBy = "operacionByOperacion")
    private TransferenciaEntity transferenciaByIdOperacion;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OperacionEntity that = (OperacionEntity) o;

        if (!Objects.equals(idOperacion, that.idOperacion)) return false;
        return Objects.equals(fecha, that.fecha);
    }

    @Override
    public int hashCode() {
        int result = idOperacion != null ? idOperacion.hashCode() : 0;
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        return result;
    }

    public CambDivisaEntity getCambDivisaByIdOperacion() {
        return cambDivisaByIdOperacion;
    }

    public void setCambDivisaByIdOperacion(CambDivisaEntity cambDivisaByIdOperacion) {
        this.cambDivisaByIdOperacion = cambDivisaByIdOperacion;
    }

    public ExtraccionEntity getExtraccionByIdOperacion() {
        return extraccionByIdOperacion;
    }

    public void setExtraccionByIdOperacion(ExtraccionEntity extraccionByIdOperacion) {
        this.extraccionByIdOperacion = extraccionByIdOperacion;
    }

    public CuentaEntity getCuentaByCuentaRealiza() {
        return cuentaByCuentaRealiza;
    }

    public void setCuentaByCuentaRealiza(CuentaEntity cuentaByCuentaRealiza) {
        this.cuentaByCuentaRealiza = cuentaByCuentaRealiza;
    }

    public TransferenciaEntity getTransferenciaByIdOperacion() {
        return transferenciaByIdOperacion;
    }

    public void setTransferenciaByIdOperacion(TransferenciaEntity transferenciaByIdOperacion) {
        this.transferenciaByIdOperacion = transferenciaByIdOperacion;
    }
}
