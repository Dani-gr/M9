package es.proyectotaw.banca.bancapp.ui;

import java.sql.Date;

public class OrdenarOperaciones {

    boolean cantidad;

    public OrdenarOperaciones() {
        cantidad = false;
    }

    public boolean getCantidad() {
        return cantidad;
    }

    public void setCantidad(boolean cantidad) {
        this.cantidad = cantidad;
    }
}
