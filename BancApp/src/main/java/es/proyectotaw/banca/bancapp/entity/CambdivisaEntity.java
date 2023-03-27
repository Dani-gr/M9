package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "cambdivisa", schema = "bancodb", catalog = "")
public class CambdivisaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Operacion", nullable = false)
    private Integer operacion;
    @Basic
    @Column(name = "origen", nullable = false, length = 45)
    private String origen;
    @Basic
    @Column(name = "destino", nullable = false, length = 45)
    private String destino;
    @OneToOne
    @JoinColumn(name = "Operacion", referencedColumnName = "idOperacion", nullable = false)
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

        CambdivisaEntity that = (CambdivisaEntity) o;

        if (operacion != null ? !operacion.equals(that.operacion) : that.operacion != null) return false;
        if (origen != null ? !origen.equals(that.origen) : that.origen != null) return false;
        if (destino != null ? !destino.equals(that.destino) : that.destino != null) return false;

        return true;
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
}
