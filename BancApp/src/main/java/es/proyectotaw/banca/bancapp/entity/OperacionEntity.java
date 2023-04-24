package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "operacion", schema = "bancodb", catalog = "")
public class OperacionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_operacion", nullable = false)
    private Integer idOperacion;
    @Basic
    @Column(name = "fecha", nullable = false)
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
        return Objects.equals(idOperacion, that.idOperacion) && Objects.equals(fecha, that.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOperacion, fecha);
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
