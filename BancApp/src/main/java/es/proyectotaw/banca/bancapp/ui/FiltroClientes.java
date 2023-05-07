package es.proyectotaw.banca.bancapp.ui;

public class FiltroClientes {

    private Double limInfSaldo;
    private String ciudad;

    public FiltroClientes(){
        this.limInfSaldo = null;
        this.ciudad = null;
    }

    public Double getLimInfSaldo() {
        return limInfSaldo;
    }

    public void setLimInfSaldo(Double limInfSaldo) {
        this.limInfSaldo = limInfSaldo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

}
