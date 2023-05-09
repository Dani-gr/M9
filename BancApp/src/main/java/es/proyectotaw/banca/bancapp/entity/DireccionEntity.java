package es.proyectotaw.banca.bancapp.entity;

import es.proyectotaw.banca.bancapp.dto.DireccionEntityDTO;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("unused")
@Entity
@Table(name = "direccion", schema = "bancodb")
public class DireccionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
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
    @Column(name = "region", length = 45)
    private String region;
    @Basic
    @Column(name = "pais", nullable = false, length = 45)
    private String pais;
    @Basic
    @Column(name = "codpostal", nullable = false, length = 45)
    private String codpostal;
    @OneToMany(mappedBy = "direccionByDireccion")
    private List<ClienteEntity> clientesById;

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

    public String getCodpostal() {
        return codpostal;
    }

    public void setCodpostal(String codpostal) {
        this.codpostal = codpostal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DireccionEntity direccion = (DireccionEntity) o;

        if (!Objects.equals(id, direccion.id)) return false;
        if (!Objects.equals(calle, direccion.calle)) return false;
        if (!Objects.equals(numero, direccion.numero)) return false;
        if (!Objects.equals(plantaPuertaOficina, direccion.plantaPuertaOficina))
            return false;
        if (!Objects.equals(ciudad, direccion.ciudad)) return false;
        if (!Objects.equals(region, direccion.region)) return false;
        if (!Objects.equals(pais, direccion.pais)) return false;
        return Objects.equals(codpostal, direccion.codpostal);
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
        result = 31 * result + (codpostal != null ? codpostal.hashCode() : 0);
        return result;
    }

    public List<ClienteEntity> getClientesById() {
        return clientesById;
    }

    public void setClientesById(List<ClienteEntity> clientesById) {
        this.clientesById = clientesById;
    }

    public void construct(String calle, Integer numero, String plantaPuertaOficina, String ciudad, String region, String pais, String codpostal) {
        setCalle(calle);
        setNumero(numero);
        setPlantaPuertaOficina(plantaPuertaOficina);
        setCiudad(ciudad);
        setRegion(region);
        setPais(pais);
        setCodpostal(codpostal);
    }

    public DireccionEntityDTO toDTO() {
        return new DireccionEntityDTO(
                id, calle, numero, plantaPuertaOficina, ciudad, region, pais, codpostal,
                clientesById.stream().map(ClienteEntity::toDTO).toList()
        );
    }
}
