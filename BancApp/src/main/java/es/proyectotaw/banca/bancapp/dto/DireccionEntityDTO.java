package es.proyectotaw.banca.bancapp.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

/**
 * A DTO for the {@link es.proyectotaw.banca.bancapp.entity.DireccionEntity} entity
 */
public class DireccionEntityDTO implements Serializable {
    private final Integer id;
    private final String calle;
    private final Integer numero;
    private final String plantaPuertaOficina;
    private final String ciudad;
    private final String region;
    private final String pais;
    private final String codPostal;
    private final Collection<ClienteEntityDTO> clientesById;
    private final UsuarioEntityDTO usuarioById;

    public DireccionEntityDTO(Integer id, String calle, Integer numero, String plantaPuertaOficina, String ciudad, String region, String pais, String codPostal, Collection<ClienteEntityDTO> clientesById, UsuarioEntityDTO usuarioById) {
        this.id = id;
        this.calle = calle;
        this.numero = numero;
        this.plantaPuertaOficina = plantaPuertaOficina;
        this.ciudad = ciudad;
        this.region = region;
        this.pais = pais;
        this.codPostal = codPostal;
        this.clientesById = clientesById;
        this.usuarioById = usuarioById;
    }

    public Integer getId() {
        return id;
    }

    public String getCalle() {
        return calle;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getPlantaPuertaOficina() {
        return plantaPuertaOficina;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getRegion() {
        return region;
    }

    public String getPais() {
        return pais;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public Collection<ClienteEntityDTO> getClientesById() {
        return clientesById;
    }

    public UsuarioEntityDTO getUsuarioById() {
        return usuarioById;
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
                Objects.equals(this.codPostal, entity.codPostal) &&
                Objects.equals(this.clientesById, entity.clientesById) &&
                Objects.equals(this.usuarioById, entity.usuarioById);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, calle, numero, plantaPuertaOficina, ciudad, region, pais, codPostal, clientesById, usuarioById);
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
                "codPostal = " + codPostal + ", " +
                "clientesById = " + clientesById + ", " +
                "usuarioById = " + usuarioById + ")";
    }
}