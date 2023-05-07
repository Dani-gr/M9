package es.proyectotaw.banca.bancapp.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link es.proyectotaw.banca.bancapp.entity.CuentaEntity} entity
 */
public class CuentaEntityDTO implements Serializable {
    private final Integer numCuenta;
    private final Double saldo;
    private final Byte activa;
    private final ClienteEntityDTO clienteByCliente;
    private final List<OperacionEntityDTO> operacionsByNumCuenta;
    private final List<TransferenciaEntityDTO> transferenciasRecibidasByNumCuenta;

    public CuentaEntityDTO(Integer numCuenta, Double saldo, Byte activa, ClienteEntityDTO clienteByCliente, List<OperacionEntityDTO> operacionsByNumCuenta, List<TransferenciaEntityDTO> transferenciasRecibidasByNumCuenta) {
        this.numCuenta = numCuenta;
        this.saldo = saldo;
        this.activa = activa;
        this.clienteByCliente = clienteByCliente;
        this.operacionsByNumCuenta = operacionsByNumCuenta;
        this.transferenciasRecibidasByNumCuenta = transferenciasRecibidasByNumCuenta;
    }

    public Integer getNumCuenta() {
        return numCuenta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public Byte getActiva() {
        return activa;
    }

    public ClienteEntityDTO getClienteByCliente() {
        return clienteByCliente;
    }

    public List<OperacionEntityDTO> getOperacionsByNumCuenta() {
        return operacionsByNumCuenta;
    }

    public List<TransferenciaEntityDTO> getTransferenciasRecibidasByNumCuenta() {
        return transferenciasRecibidasByNumCuenta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CuentaEntityDTO entity = (CuentaEntityDTO) o;
        return Objects.equals(this.numCuenta, entity.numCuenta) &&
                Objects.equals(this.saldo, entity.saldo) &&
                Objects.equals(this.activa, entity.activa) &&
                Objects.equals(this.clienteByCliente, entity.clienteByCliente) &&
                Objects.equals(this.operacionsByNumCuenta, entity.operacionsByNumCuenta) &&
                Objects.equals(this.transferenciasRecibidasByNumCuenta, entity.transferenciasRecibidasByNumCuenta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numCuenta, saldo, activa, clienteByCliente, operacionsByNumCuenta, transferenciasRecibidasByNumCuenta);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "numCuenta = " + numCuenta + ", " +
                "saldo = " + saldo + ", " +
                "activa = " + activa + ", " +
                "clienteByCliente = " + clienteByCliente + ", " +
                "operacionsByNumCuenta = " + operacionsByNumCuenta + ", " +
                "transferenciasRecibidasByNumCuenta = " + transferenciasRecibidasByNumCuenta + ")";
    }
}