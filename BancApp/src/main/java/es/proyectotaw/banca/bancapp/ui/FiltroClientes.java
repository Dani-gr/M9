package es.proyectotaw.banca.bancapp.ui;

public class FiltroClientes {

    private Integer limInfSaldo;
    private String ciudad;
    private Integer estadoCuenta;

    public FiltroClientes(){
        this.limInfSaldo = null;
        this.ciudad = null;
        this.estadoCuenta = null;
    }

    public Integer getLimInfSaldo() {
        return limInfSaldo;
    }

    public void setLimInfSaldo(Integer limInfSaldo) {
        this.limInfSaldo = limInfSaldo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Integer getEstadoCuenta() {
        return estadoCuenta;
    }

    public void setEstadoCuenta(Integer estadoCuenta) {
        this.estadoCuenta = estadoCuenta;
    }
}
