package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;
import java.util.Objects;

@SuppressWarnings("unused")
@Entity
@Table(name = "transferencia", schema = "bancodb")
public class TransferenciaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "operacion")
    private Integer operacion;
    @Basic
    @Column(name = "cantidad")
    private Double cantidad;
    @Basic
    @Column(name = "IBAN_destino")
    private String ibanDestino;
    @OneToOne
    @JoinColumn(name = "operacion", referencedColumnName = "id_operacion", nullable = false)
    private OperacionEntity operacionByOperacion;
    @ManyToOne
    @JoinColumn(name = "cuenta_destino", referencedColumnName = "num_cuenta")
    private CuentaEntity cuentaByCuentaDestino;

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

    public String getIbanDestino() {
        return ibanDestino;
    }

    public void setIbanDestino(String ibanDestino) {
        this.ibanDestino = ibanDestino;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransferenciaEntity that = (TransferenciaEntity) o;

        if (!Objects.equals(operacion, that.operacion)) return false;
        if (!Objects.equals(cantidad, that.cantidad)) return false;
        return Objects.equals(ibanDestino, that.ibanDestino);
    }

    @Override
    public int hashCode() {
        int result = operacion != null ? operacion.hashCode() : 0;
        result = 31 * result + (cantidad != null ? cantidad.hashCode() : 0);
        result = 31 * result + (ibanDestino != null ? ibanDestino.hashCode() : 0);
        return result;
    }

    public OperacionEntity getOperacionByOperacion() {
        return operacionByOperacion;
    }

    public void setOperacionByOperacion(OperacionEntity operacionByOperacion) {
        this.operacionByOperacion = operacionByOperacion;
    }

    public CuentaEntity getCuentaByCuentaDestino() {
        return cuentaByCuentaDestino;
    }

    public void setCuentaByCuentaDestino(CuentaEntity cuentaByCuentaDestino) {
        this.cuentaByCuentaDestino = cuentaByCuentaDestino;
    }

    public String nombre() {
        return "Transferencia";
    }
}
