package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "cuenta", schema = "bancodb", catalog = "")
public class CuentaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "numCuenta", nullable = false)
    private Integer numCuenta;
    @Basic
    @Column(name = "saldo", nullable = true, precision = 0)
    private Double saldo;
    @ManyToOne
    @JoinColumn(name = "cliente", referencedColumnName = "ID_Cliente", nullable = false)
    private ClienteEntity clienteByCliente;
    @OneToMany(mappedBy = "cuentaByCuentaRealiza")
    private Collection<OperacionEntity> operacionsByNumCuenta;
    @OneToMany(mappedBy = "cuentaByCuentaDestion")
    private Collection<TransferenciaEntity> transferenciasByNumCuenta;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CuentaEntity that = (CuentaEntity) o;

        if (numCuenta != null ? !numCuenta.equals(that.numCuenta) : that.numCuenta != null) return false;
        if (saldo != null ? !saldo.equals(that.saldo) : that.saldo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numCuenta != null ? numCuenta.hashCode() : 0;
        result = 31 * result + (saldo != null ? saldo.hashCode() : 0);
        return result;
    }

    public ClienteEntity getClienteByCliente() {
        return clienteByCliente;
    }

    public void setClienteByCliente(ClienteEntity clienteByCliente) {
        this.clienteByCliente = clienteByCliente;
    }

    public Collection<OperacionEntity> getOperacionsByNumCuenta() {
        return operacionsByNumCuenta;
    }

    public void setOperacionsByNumCuenta(Collection<OperacionEntity> operacionsByNumCuenta) {
        this.operacionsByNumCuenta = operacionsByNumCuenta;
    }

    public Collection<TransferenciaEntity> getTransferenciasByNumCuenta() {
        return transferenciasByNumCuenta;
    }

    public void setTransferenciasByNumCuenta(Collection<TransferenciaEntity> transferenciasByNumCuenta) {
        this.transferenciasByNumCuenta = transferenciasByNumCuenta;
    }
}
