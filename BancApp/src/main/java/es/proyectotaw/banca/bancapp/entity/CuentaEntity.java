package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("unused")
@Entity
@Table(name = "cuenta", schema = "bancodb")
public class CuentaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "num_cuenta")
    private Integer numCuenta;
    @Basic
    @Column(name = "saldo")
    private Double saldo;
    @Basic
    @Column(name = "activa")
    private Byte activa;
    @ManyToOne
    @JoinColumn(name = "cliente", referencedColumnName = "id_cliente", nullable = false)
    private ClienteEntity clienteByCliente;
    @OneToMany(mappedBy = "cuentaByCuentaRealiza")
    private List<OperacionEntity> operacionsByNumCuenta;
    @OneToMany(mappedBy = "cuentaByCuentaDestino")
    private List<TransferenciaEntity> transferenciasByNumCuenta;

    public Integer getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(Integer numCuenta) {
        this.numCuenta = numCuenta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Byte getActiva() {
        return activa;
    }

    public void setActiva(Byte activa) {
        this.activa = activa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CuentaEntity that = (CuentaEntity) o;

        if (!Objects.equals(numCuenta, that.numCuenta)) return false;
        if (!Objects.equals(saldo, that.saldo)) return false;
        return Objects.equals(activa, that.activa);
    }

    @Override
    public int hashCode() {
        int result = numCuenta != null ? numCuenta.hashCode() : 0;
        result = 31 * result + (saldo != null ? saldo.hashCode() : 0);
        result = 31 * result + (activa != null ? activa.hashCode() : 0);
        return result;
    }

    public ClienteEntity getClienteByCliente() {
        return clienteByCliente;
    }

    public void setClienteByCliente(ClienteEntity clienteByCliente) {
        this.clienteByCliente = clienteByCliente;
    }

    public List<OperacionEntity> getOperacionsByNumCuenta() {
        return operacionsByNumCuenta;
    }

    public void setOperacionsByNumCuenta(List<OperacionEntity> operacionsByNumCuenta) {
        this.operacionsByNumCuenta = operacionsByNumCuenta;
    }

    public List<TransferenciaEntity> getTransferenciasByNumCuenta() {
        return transferenciasByNumCuenta;
    }

    public void setTransferenciasByNumCuenta(List<TransferenciaEntity> transferenciasByNumCuenta) {
        this.transferenciasByNumCuenta = transferenciasByNumCuenta;
    }
}
