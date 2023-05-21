package es.proyectotaw.banca.bancapp.entity;

import es.proyectotaw.banca.bancapp.dto.CambDivisaEntityDTO;

import javax.persistence.*;
import java.util.Objects;

@SuppressWarnings("unused")
@Entity
@Table(name = "camb_divisa", schema = "bancodb")
public class CambDivisaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_divisa", nullable = false)
    private Integer idDivisa;
    @Basic
    @Column(name = "origen", nullable = false, length = 45)
    private String origen;
    @Basic
    @Column(name = "destino", nullable = false, length = 45)
    private String destino;
    @Basic
    @Column(name = "cantidad", nullable = false)
    private Double cantidad;
    @ManyToOne
    @JoinColumn(name = "operacion", referencedColumnName = "id_operacion", nullable = false)
    private OperacionEntity operacionByOperacion;

    public Integer getIdDivisa() {
        return idDivisa;
    }

    public void setIdDivisa(Integer idDivisa) {
        this.idDivisa = idDivisa;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
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

        CambDivisaEntity that = (CambDivisaEntity) o;

        if (!Objects.equals(idDivisa, that.idDivisa)) return false;
        if (!Objects.equals(origen, that.origen)) return false;
        if (!Objects.equals(destino, that.destino)) return false;
        return Objects.equals(cantidad, that.cantidad);
    }

    @Override
    public int hashCode() {
        int result = idDivisa != null ? idDivisa.hashCode() : 0;
        result = 31 * result + (origen != null ? origen.hashCode() : 0);
        result = 31 * result + (destino != null ? destino.hashCode() : 0);
        result = 31 * result + (cantidad != null ? cantidad.hashCode() : 0);
        return result;
    }

    public OperacionEntity getOperacionByOperacion() {
        return operacionByOperacion;
    }

    public void setOperacionByOperacion(OperacionEntity operacionByOperacion) {
        this.operacionByOperacion = operacionByOperacion;
    }

    public String nombre() {
        return "Cambio de divisa";
    }

    public CambDivisaEntityDTO toDTO() {
        return new CambDivisaEntityDTO(
                idDivisa, origen, destino, cantidad, operacionByOperacion == null ? null : operacionByOperacion.toDTO()
        );
    }
}
