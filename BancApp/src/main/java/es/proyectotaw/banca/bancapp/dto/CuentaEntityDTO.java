package es.proyectotaw.banca.bancapp.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

/**
 * A DTO for the {@link es.proyectotaw.banca.bancapp.entity.CuentaEntity} entity
 */
public class CuentaEntityDTO implements Serializable {
    private final Integer numCuenta;
    private final Double saldo;
    private final ClienteEntityDTO clienteByCliente;
    private final Collection<OperacionEntityDTO> operacionsByNumCuenta;
    private final Collection<TransferenciaEntityDTO> transferenciasByNumCuenta;

    public CuentaEntityDTO(Integer numCuenta, Double saldo, ClienteEntityDTO clienteByCliente, Collection<OperacionEntityDTO> operacionsByNumCuenta, Collection<TransferenciaEntityDTO> transferenciasByNumCuenta) {
        this.numCuenta = numCuenta;
        this.saldo = saldo;
        this.clienteByCliente = clienteByCliente;
        this.operacionsByNumCuenta = operacionsByNumCuenta;
        this.transferenciasByNumCuenta = transferenciasByNumCuenta;
    }

    public Integer getNumCuenta() {
        return numCuenta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public ClienteEntityDTO getClienteByCliente() {
        return clienteByCliente;
    }

    public Collection<OperacionEntityDTO> getOperacionsByNumCuenta() {
        return operacionsByNumCuenta;
    }

    public Collection<TransferenciaEntityDTO> getTransferenciasByNumCuenta() {
        return transferenciasByNumCuenta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CuentaEntityDTO entity = (CuentaEntityDTO) o;
        return Objects.equals(this.numCuenta, entity.numCuenta) &&
                Objects.equals(this.saldo, entity.saldo) &&
                Objects.equals(this.clienteByCliente, entity.clienteByCliente) &&
                Objects.equals(this.operacionsByNumCuenta, entity.operacionsByNumCuenta) &&
                Objects.equals(this.transferenciasByNumCuenta, entity.transferenciasByNumCuenta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numCuenta, saldo, clienteByCliente, operacionsByNumCuenta, transferenciasByNumCuenta);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "numCuenta = " + numCuenta + ", " +
                "saldo = " + saldo + ", " +
                "clienteByCliente = " + clienteByCliente + ", " +
                "operacionsByNumCuenta = " + operacionsByNumCuenta + ", " +
                "transferenciasByNumCuenta = " + transferenciasByNumCuenta + ")";
    }
}