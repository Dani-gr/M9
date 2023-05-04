package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;
import java.util.Objects;

@SuppressWarnings("unused")
@Entity
@Table(name = "camb_divisa", schema = "bancodb")
public class CambDivisaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "operacion")
    private Integer operacion;
    @Basic
    @Column(name = "origen")
    private String origen;
    @Basic
    @Column(name = "destino")
    private String destino;
    @OneToOne
    @JoinColumn(name = "operacion", referencedColumnName = "id_operacion", nullable = false)
    private OperacionEntity operacionByOperacion;

    public Integer getOperacion() {
        return operacion;
    }

    public void setOperacion(Integer operacion) {
        this.operacion = operacion;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CambDivisaEntity that = (CambDivisaEntity) o;

        if (!Objects.equals(operacion, that.operacion)) return false;
        if (!Objects.equals(origen, that.origen)) return false;
        return Objects.equals(destino, that.destino);
    }

    @Override
    public int hashCode() {
        int result = operacion != null ? operacion.hashCode() : 0;
        result = 31 * result + (origen != null ? origen.hashCode() : 0);
        result = 31 * result + (destino != null ? destino.hashCode() : 0);
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
}
