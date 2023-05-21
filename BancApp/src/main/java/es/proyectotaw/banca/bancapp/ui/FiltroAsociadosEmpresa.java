package es.proyectotaw.banca.bancapp.ui;

import java.util.List;

public class FiltroAsociadosEmpresa {


    private String nombreRol;

    private String contieneCadena;


    private String ordenAsociados;

    public FiltroAsociadosEmpresa(){
        this.nombreRol = null; this.contieneCadena = null; this.ordenAsociados = null;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getContieneCadena() {
        return contieneCadena;
    }

    public void setContieneCadena(String contieneCadena) {
        this.contieneCadena = contieneCadena;
    }

    public String getOrdenAsociados() {
        return ordenAsociados;
    }

    public void setOrdenAsociados(String ordenAsociados) {
        this.ordenAsociados = ordenAsociados;
    }

}
