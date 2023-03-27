package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "direccion", schema = "bancodb", catalog = "")
public class DireccionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "calle", nullable = false, length = 45)
    private String calle;
    @Basic
    @Column(name = "numero", nullable = false)
    private Integer numero;
    @Basic
    @Column(name = "planta_puerta_oficina", nullable = false, length = 45)
    private String plantaPuertaOficina;
    @Basic
    @Column(name = "ciudad", nullable = false, length = 45)
    private String ciudad;
    @Basic
    @Column(name = "region", nullable = true, length = 45)
    private String region;
    @Basic
    @Column(name = "pais", nullable = false, length = 45)
    private String pais;
    @Basic
    @Column(name = "codPostal", nullable = false, length = 45)
    private String codPostal;
    @OneToMany(mappedBy = "direccionByDireccion")
    private Collection<ClienteEntity> clientesById;
    @OneToOne
    @JoinColumn(name = "ID", referencedColumnName = "ID", nullable = false)
    private UsuarioEntity usuarioById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getPlantaPuertaOficina() {
        return plantaPuertaOficina;
    }

    public void setPlantaPuertaOficina(String plantaPuertaOficina) {
        this.plantaPuertaOficina = plantaPuertaOficina;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DireccionEntity that = (DireccionEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (calle != null ? !calle.equals(that.calle) : that.calle != null) return false;
        if (numero != null ? !numero.equals(that.numero) : that.numero != null) return false;
        if (plantaPuertaOficina != null ? !plantaPuertaOficina.equals(that.plantaPuertaOficina) : that.plantaPuertaOficina != null)
            return false;
        if (ciudad != null ? !ciudad.equals(that.ciudad) : that.ciudad != null) return false;
        if (region != null ? !region.equals(that.region) : that.region != null) return false;
        if (pais != null ? !pais.equals(that.pais) : that.pais != null) return false;
        if (codPostal != null ? !codPostal.equals(that.codPostal) : that.codPostal != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (calle != null ? calle.hashCode() : 0);
        result = 31 * result + (numero != null ? numero.hashCode() : 0);
        result = 31 * result + (plantaPuertaOficina != null ? plantaPuertaOficina.hashCode() : 0);
        result = 31 * result + (ciudad != null ? ciudad.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (pais != null ? pais.hashCode() : 0);
        result = 31 * result + (codPostal != null ? codPostal.hashCode() : 0);
        return result;
    }

    public Collection<ClienteEntity> getClientesById() {
        return clientesById;
    }

    public void setClientesById(Collection<ClienteEntity> clientesById) {
        this.clientesById = clientesById;
    }

    public UsuarioEntity getUsuarioById() {
        return usuarioById;
    }

    public void setUsuarioById(UsuarioEntity usuarioById) {
        this.usuarioById = usuarioById;
    }
}
