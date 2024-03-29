package es.proyectotaw.banca.bancapp.entity;

import es.proyectotaw.banca.bancapp.dto.ExtraccionEntityDTO;

import javax.persistence.*;
import java.util.Objects;

@SuppressWarnings("unused")
@Entity
@Table(name = "extraccion", schema = "bancodb")
public class ExtraccionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_extraccion", nullable = false)
    private Integer idExtraccion;
    @Basic
    @Column(name = "cantidad", nullable = false)
    private Double cantidad;
    @ManyToOne
    @JoinColumn(name = "operacion", referencedColumnName = "id_operacion", nullable = false)
    private OperacionEntity operacionByOperacion;

    public Integer getIdExtraccion() {
        return idExtraccion;
    }

    public void setIdExtraccion(Integer idExtraccion) {
        this.idExtraccion = idExtraccion;
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

        ExtraccionEntity that = (ExtraccionEntity) o;

        if (!Objects.equals(idExtraccion, that.idExtraccion)) return false;
        return Objects.equals(cantidad, that.cantidad);
    }

    @Override
    public int hashCode() {
        int result = idExtraccion != null ? idExtraccion.hashCode() : 0;
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
        return "Extracción";
    }

    public ExtraccionEntityDTO toDTO() {
        return new ExtraccionEntityDTO(
                idExtraccion, cantidad, operacionByOperacion.toDTO()
        );
    }
}
