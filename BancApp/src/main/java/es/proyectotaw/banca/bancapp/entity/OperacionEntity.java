package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

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
    @OneToMany(mappedBy = "operacionByOperacion")
    private List<CambDivisaEntity> cambDivisasByIdOperacion;
    @OneToMany(mappedBy = "operacionByOperacion")
    private List<ExtraccionEntity> extraccionsByIdOperacion;
    @ManyToOne
    @JoinColumn(name = "cuenta_realiza", referencedColumnName = "num_cuenta", nullable = false)
    private CuentaEntity cuentaByCuentaRealiza;
    @OneToMany(mappedBy = "operacionByOperacion")
    private List<TransferenciaEntity> transferenciasByIdOperacion;

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

        if (idOperacion != null ? !idOperacion.equals(that.idOperacion) : that.idOperacion != null) return false;
        if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idOperacion != null ? idOperacion.hashCode() : 0;
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        return result;
    }

    public List<CambDivisaEntity> getCambDivisasByIdOperacion() {
        return cambDivisasByIdOperacion;
    }

    public void setCambDivisasByIdOperacion(List<CambDivisaEntity> cambDivisasByIdOperacion) {
        this.cambDivisasByIdOperacion = cambDivisasByIdOperacion;
    }

    public List<ExtraccionEntity> getExtraccionsByIdOperacion() {
        return extraccionsByIdOperacion;
    }

    public void setExtraccionsByIdOperacion(List<ExtraccionEntity> extraccionsByIdOperacion) {
        this.extraccionsByIdOperacion = extraccionsByIdOperacion;
    }

    public CuentaEntity getCuentaByCuentaRealiza() {
        return cuentaByCuentaRealiza;
    }

    public void setCuentaByCuentaRealiza(CuentaEntity cuentaByCuentaRealiza) {
        this.cuentaByCuentaRealiza = cuentaByCuentaRealiza;
    }

    public List<TransferenciaEntity> getTransferenciasByIdOperacion() {
        return transferenciasByIdOperacion;
    }

    public void setTransferenciasByIdOperacion(List<TransferenciaEntity> transferenciasByIdOperacion) {
        this.transferenciasByIdOperacion = transferenciasByIdOperacion;
    }
}
