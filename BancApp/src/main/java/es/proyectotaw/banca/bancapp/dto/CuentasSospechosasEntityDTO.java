package es.proyectotaw.banca.bancapp.dto;

import es.proyectotaw.banca.bancapp.entity.CuentasSospechosasEntity;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link es.proyectotaw.banca.bancapp.entity.CuentasSospechosasEntity} entity
 *
 * @author Daniel García Rodríguez (method {@link #toEntity()})
 */
@SuppressWarnings("unused")
public class CuentasSospechosasEntityDTO implements Serializable {
    private Integer idcuentasSospechosas;
    private String iban;

    public CuentasSospechosasEntityDTO() {
    }

    public CuentasSospechosasEntityDTO(Integer idcuentasSospechosas, String iban) {
        this.idcuentasSospechosas = idcuentasSospechosas;
        this.iban = iban;
    }

    public Integer getIdcuentasSospechosas() {
        return idcuentasSospechosas;
    }

    public void setIdcuentasSospechosas(Integer idcuentasSospechosas) {
        this.idcuentasSospechosas = idcuentasSospechosas;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
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

    public CuentasSospechosasEntity toEntity() {
        CuentasSospechosasEntity cuentasSospechosasEntity = new CuentasSospechosasEntity();
        cuentasSospechosasEntity.setIdcuentasSospechosas(idcuentasSospechosas);
        cuentasSospechosasEntity.setIban(iban);

        return cuentasSospechosasEntity;
    }
}