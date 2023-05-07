package es.proyectotaw.banca.bancapp.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link es.proyectotaw.banca.bancapp.entity.CuentasSospechosasEntity} entity
 */
public class CuentasSospechosasEntityDTO implements Serializable {
    private final Integer idcuentasSospechosas;
    private final String iban;

    public CuentasSospechosasEntityDTO(Integer idcuentasSospechosas, String iban) {
        this.idcuentasSospechosas = idcuentasSospechosas;
        this.iban = iban;
    }

    public Integer getIdcuentasSospechosas() {
        return idcuentasSospechosas;
    }

    public String getIban() {
        return iban;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CuentasSospechosasEntityDTO entity = (CuentasSospechosasEntityDTO) o;
        return Objects.equals(this.idcuentasSospechosas, entity.idcuentasSospechosas) &&
                Objects.equals(this.iban, entity.iban);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcuentasSospechosas, iban);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "idcuentasSospechosas = " + idcuentasSospechosas + ", " +
                "iban = " + iban + ")";
    }
}