package es.proyectotaw.banca.bancapp.ui;

public class FiltroClientes {

    private Float limInfSaldo;
    private String ciudad;
    private Boolean cuentaActiva;

    public FiltroClientes(){
        this.limInfSaldo = null;
        this.ciudad = null;
        this.cuentaActiva = null;
    }

    public Float getLimInfSaldo() {
        return limInfSaldo;
    }

    public void setLimInfSaldo(Float limInfSaldo) {
        this.limInfSaldo = limInfSaldo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Boolean getCuentaActiva() {
        return cuentaActiva;
    }

    public void setCuentaActiva(Boolean cuentaActiva) {
        this.cuentaActiva = cuentaActiva;
    }
}
