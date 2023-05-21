package es.proyectotaw.banca.bancapp.dto;

import es.proyectotaw.banca.bancapp.entity.CuentaEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link es.proyectotaw.banca.bancapp.entity.CuentaEntity} entity
 *
 * @author Daniel García Rodríguez (method {@link #toEntity()})
 */
@SuppressWarnings("unused")
public class CuentaEntityDTO implements Serializable {
    private Integer numCuenta;
    private Double saldo;
    private Byte activa;
    private ClienteEntityDTO clienteByCliente;
    private List<OperacionEntityDTO> operacionsByNumCuenta;
    private List<TransferenciaEntityDTO> transferenciasRecibidasByNumCuenta;

    public CuentaEntityDTO() {
    }

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

    public ClienteEntityDTO getClienteByCliente() {
        return clienteByCliente;
    }

    public void setClienteByCliente(ClienteEntityDTO clienteByCliente) {
        this.clienteByCliente = clienteByCliente;
    }

    public List<OperacionEntityDTO> getOperacionsByNumCuenta() {
        return operacionsByNumCuenta;
    }

    public void setOperacionsByNumCuenta(List<OperacionEntityDTO> operacionsByNumCuenta) {
        this.operacionsByNumCuenta = operacionsByNumCuenta;
    }

    public List<TransferenciaEntityDTO> getTransferenciasRecibidasByNumCuenta() {
        return transferenciasRecibidasByNumCuenta;
    }

    public void setTransferenciasRecibidasByNumCuenta(List<TransferenciaEntityDTO> transferenciasRecibidasByNumCuenta) {
        this.transferenciasRecibidasByNumCuenta = transferenciasRecibidasByNumCuenta;
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

    public CuentaEntity toEntity() {
        CuentaEntity cuentaEntity = new CuentaEntity();
        cuentaEntity.setNumCuenta(numCuenta);
        cuentaEntity.setActiva(activa);
        cuentaEntity.setSaldo(saldo);
        cuentaEntity.setOperacionsByNumCuenta(operacionsByNumCuenta.stream().map(OperacionEntityDTO::toEntity).toList());

        return cuentaEntity;
    }
}