package es.proyectotaw.banca.bancapp.ui;

public class FiltroOperaciones {

    Double cantidadFiltro;

    String nombreOperacion;

    public FiltroOperaciones() {
        cantidadFiltro = (double) 0;
        nombreOperacion = "ninguno";
    }

    public Double getCantidadFiltro() {
        return cantidadFiltro;
    }

    public void setCantidadFiltro(Double cantidadFiltro) {
        this.cantidadFiltro = cantidadFiltro;
    }

    public String getNombreOperacion() {
        return nombreOperacion;
    }

    public void setNombreOperacion(String nombreOperacion) {
        this.nombreOperacion = nombreOperacion;
    }
}
