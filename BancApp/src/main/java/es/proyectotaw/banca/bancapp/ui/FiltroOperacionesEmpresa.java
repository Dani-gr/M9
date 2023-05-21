package es.proyectotaw.banca.bancapp.ui;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@SuppressWarnings("unused")
public class FiltroOperacionesEmpresa {

    private String nombreOperacion;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private
    LocalDate fechaFiltro;

    public FiltroOperacionesEmpresa() {
        nombreOperacion = "ninguno";
        fechaFiltro = null;
    }


    public String getNombreOperacion() {
        return nombreOperacion;
    }

    public void setNombreOperacion(String nombreOperacion) {
        this.nombreOperacion = nombreOperacion;
    }

    public LocalDate getFechaFiltro() {
        return fechaFiltro;
    }

    public void setFechaFiltro(LocalDate fechaFiltro) {
        this.fechaFiltro = fechaFiltro;
    }
}
