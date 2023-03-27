package es.proyectotaw.banca.bancapp.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class ClientesempresaEntityPK implements Serializable {
    @Column(name = "CIF", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cif;
    @Column(name = "ID_Empresa", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmpresa;
    @Column(name = "ID_Cliente", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;

    public Integer getCif() {
        return cif;
    }

    public void setCif(Integer cif) {
        this.cif = cif;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientesempresaEntityPK that = (ClientesempresaEntityPK) o;

        if (cif != null ? !cif.equals(that.cif) : that.cif != null) return false;
        if (idEmpresa != null ? !idEmpresa.equals(that.idEmpresa) : that.idEmpresa != null) return false;
        if (idCliente != null ? !idCliente.equals(that.idCliente) : that.idCliente != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cif != null ? cif.hashCode() : 0;
        result = 31 * result + (idEmpresa != null ? idEmpresa.hashCode() : 0);
        result = 31 * result + (idCliente != null ? idCliente.hashCode() : 0);
        return result;
    }
}
