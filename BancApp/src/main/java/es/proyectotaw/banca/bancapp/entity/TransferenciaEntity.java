package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "transferencia", schema = "bancodb", catalog = "")
public class TransferenciaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_transferencia")
    private Integer idTransferencia;
    @Basic
    @Column(name = "cantidad")
    private Double cantidad;
    @Basic
    @Column(name = "IBAN_destino")
    private String ibanDestino;
    @ManyToOne
    @JoinColumn(name = "operacion", referencedColumnName = "id_operacion", nullable = false)
    private OperacionEntity operacionByOperacion;
    @ManyToOne
    @JoinColumn(name = "cuenta_destino", referencedColumnName = "num_cuenta")
    private CuentaEntity cuentaByCuentaDestino;

    public Integer getIdTransferencia() {
        return idTransferencia;
    }

    public void setIdTransferencia(Integer idTransferencia) {
        this.idTransferencia = idTransferencia;
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

        if (idTransferencia != null ? !idTransferencia.equals(that.idTransferencia) : that.idTransferencia != null)
            return false;
        if (cantidad != null ? !cantidad.equals(that.cantidad) : that.cantidad != null) return false;
        if (ibanDestino != null ? !ibanDestino.equals(that.ibanDestino) : that.ibanDestino != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTransferencia != null ? idTransferencia.hashCode() : 0;
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

    public String nombre(){
        return "Transferencia";
    }
}
