package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;
import java.util.Objects;

@SuppressWarnings("unused")
@Entity
@Table(name = "cuentas_sospechosas", schema = "bancodb")
public class CuentasSospechosasEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idcuentas_sospechosas", nullable = false)
    private Integer idcuentasSospechosas;
    @Basic
    @Column(name = "IBAN", nullable = false, length = 24)
    private String iban;

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

        CuentasSospechosasEntity that = (CuentasSospechosasEntity) o;

        if (!Objects.equals(idcuentasSospechosas, that.idcuentasSospechosas))
            return false;
        return Objects.equals(iban, that.iban);
    }

    @Override
    public int hashCode() {
        int result = idcuentasSospechosas != null ? idcuentasSospechosas.hashCode() : 0;
        result = 31 * result + (iban != null ? iban.hashCode() : 0);
        return result;
    }
}
