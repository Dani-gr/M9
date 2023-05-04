package es.proyectotaw.banca.bancapp.ui;

import java.sql.Date;

public class OrdenarOperaciones {

    Date fecha;

    public OrdenarOperaciones(){
        fecha = null;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
