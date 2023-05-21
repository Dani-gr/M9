package es.proyectotaw.banca.bancapp.dto;

import es.proyectotaw.banca.bancapp.entity.DireccionEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link es.proyectotaw.banca.bancapp.entity.DireccionEntity} entity
 *
 * @author Daniel García Rodríguez (method {@link #toEntity()})
 */
@SuppressWarnings("unused")
public class DireccionEntityDTO implements Serializable {
    private Integer id;
    private String calle;
    private Integer numero;
    private String plantaPuertaOficina;
    private String ciudad;
    private String region;
    private String pais;
    private String codpostal;
    private List<ClienteEntityDTO> clientesById;

    public DireccionEntityDTO() {
    }

    public DireccionEntityDTO(Integer id, String calle, Integer numero, String plantaPuertaOficina, String ciudad, String region, String pais, String codpostal, List<ClienteEntityDTO> clientesById) {
        this.id = id;
        this.calle = calle;
        this.numero = numero;
        this.plantaPuertaOficina = plantaPuertaOficina;
        this.ciudad = ciudad;
        this.region = region;
        this.pais = pais;
        this.codpostal = codpostal;
        this.clientesById = clientesById;
    }

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

    public List<ClienteEntityDTO> getClientesById() {
        return clientesById;
    }

    public void setClientesById(List<ClienteEntityDTO> clientesById) {
        this.clientesById = clientesById;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DireccionEntityDTO entity = (DireccionEntityDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.calle, entity.calle) &&
                Objects.equals(this.numero, entity.numero) &&
                Objects.equals(this.plantaPuertaOficina, entity.plantaPuertaOficina) &&
                Objects.equals(this.ciudad, entity.ciudad) &&
                Objects.equals(this.region, entity.region) &&
                Objects.equals(this.pais, entity.pais) &&
                Objects.equals(this.codpostal, entity.codpostal) &&
                Objects.equals(this.clientesById, entity.clientesById);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, calle, numero, plantaPuertaOficina, ciudad, region, pais, codpostal, clientesById);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "calle = " + calle + ", " +
                "numero = " + numero + ", " +
                "plantaPuertaOficina = " + plantaPuertaOficina + ", " +
                "ciudad = " + ciudad + ", " +
                "region = " + region + ", " +
                "pais = " + pais + ", " +
                "codpostal = " + codpostal + ", " +
                "clientesById = " + clientesById + ")";
    }

    public DireccionEntity toEntity() {
        DireccionEntity direccionEntity = new DireccionEntity();
        direccionEntity.construct(
                calle, numero, plantaPuertaOficina, ciudad, region, pais, codpostal
        );
        direccionEntity.setId(id);
        direccionEntity.setClientesById(clientesById.stream().map(ClienteEntityDTO::toEntity).toList());

        return direccionEntity;
    }
}