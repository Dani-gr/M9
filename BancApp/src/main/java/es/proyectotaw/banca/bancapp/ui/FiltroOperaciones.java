package es.proyectotaw.banca.bancapp.ui;

public class FiltroOperaciones {

    float cantidadFiltro;

    String nombreOperacion;

    public FiltroOperaciones(){
        cantidadFiltro = 0;
        nombreOperacion = "ninguno";
    }

    public float getCantidadFiltro() {
        return cantidadFiltro;
    }

    public void setCantidadFiltro(float cantidadFiltro) {
        this.cantidadFiltro = cantidadFiltro;
    }

    public String getNombreOperacion() {
        return nombreOperacion;
    }

    public void setNombreOperacion(String nombreOperacion) {
        this.nombreOperacion = nombreOperacion;
    }
}
