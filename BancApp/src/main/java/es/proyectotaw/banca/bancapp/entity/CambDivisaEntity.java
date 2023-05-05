package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "camb_divisa", schema = "bancodb", catalog = "")
public class CambDivisaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_divisa")
    private Integer idDivisa;
    @Basic
    @Column(name = "origen")
    private String origen;
    @Basic
    @Column(name = "destino")
    private String destino;
    @Basic
    @Column(name = "cantidad")
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

        if (idDivisa != null ? !idDivisa.equals(that.idDivisa) : that.idDivisa != null) return false;
        if (origen != null ? !origen.equals(that.origen) : that.origen != null) return false;
        if (destino != null ? !destino.equals(that.destino) : that.destino != null) return false;
        if (cantidad != null ? !cantidad.equals(that.cantidad) : that.cantidad != null) return false;

        return true;
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

    public String nombre(){
        return "Cambio de divisa";
    }
}
