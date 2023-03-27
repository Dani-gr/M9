package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "transferencia", schema = "bancodb", catalog = "")
public class TransferenciaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Operacion", nullable = false)
    private Integer operacion;
    @Basic
    @Column(name = "cantidad", nullable = false, precision = 0)
    private Double cantidad;
    @OneToOne
    @JoinColumn(name = "Operacion", referencedColumnName = "idOperacion", nullable = false)
    private OperacionEntity operacionByOperacion;
    @ManyToOne
    @JoinColumn(name = "CuentaDestion", referencedColumnName = "numCuenta", nullable = false)
    private CuentaEntity cuentaByCuentaDestion;

    public Integer getOperacion() {
        return operacion;
    }

    public void setOperacion(Integer operacion) {
        this.operacion = operacion;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransferenciaEntity that = (TransferenciaEntity) o;

        if (operacion != null ? !operacion.equals(that.operacion) : that.operacion != null) return false;
        if (cantidad != null ? !cantidad.equals(that.cantidad) : that.cantidad != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = operacion != null ? operacion.hashCode() : 0;
        result = 31 * result + (cantidad != null ? cantidad.hashCode() : 0);
        return result;
    }

    public OperacionEntity getOperacionByOperacion() {
        return operacionByOperacion;
    }

    public void setOperacionByOperacion(OperacionEntity operacionByOperacion) {
        this.operacionByOperacion = operacionByOperacion;
    }

    public CuentaEntity getCuentaByCuentaDestion() {
        return cuentaByCuentaDestion;
    }

    public void setCuentaByCuentaDestion(CuentaEntity cuentaByCuentaDestion) {
        this.cuentaByCuentaDestion = cuentaByCuentaDestion;
    }
}
