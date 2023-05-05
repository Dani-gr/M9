package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "cuentas_sospechosas", schema = "bancodb", catalog = "")
public class CuentasSospechosasEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idcuentas_sospechosas")
    private Integer idcuentasSospechosas;
    @Basic
    @Column(name = "IBAN")
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

        if (idcuentasSospechosas != null ? !idcuentasSospechosas.equals(that.idcuentasSospechosas) : that.idcuentasSospechosas != null)
            return false;
        if (iban != null ? !iban.equals(that.iban) : that.iban != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idcuentasSospechosas != null ? idcuentasSospechosas.hashCode() : 0;
        result = 31 * result + (iban != null ? iban.hashCode() : 0);
        return result;
    }
}
