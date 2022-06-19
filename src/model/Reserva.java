package model;

import java.sql.Date;

public class Reserva {
    private Date fecha;
    private Cliente cliente;
    private Funcion funcion;

    public Reserva(Cliente cliente, Funcion funcion, Date fecha) {
        this.cliente = cliente;
        this.funcion = funcion;
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
