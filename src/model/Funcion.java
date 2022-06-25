package model;

import java.sql.Date;
import java.sql.Time;

public class Funcion {
    private Sala sala;
    private Pelicula pelicula;
    private boolean is3D;
    private Date fecha;
    private Time hora;
    private Float precio;
    private Descuentos desc;

    public Funcion(Sala sala, Pelicula pelicula, boolean is3D, Date fecha, Time hora, Float precio) {
        this.sala = sala;
        this.pelicula = pelicula;
        this.is3D = is3D;
        this.fecha = fecha;
        this.hora = hora;
        this.precio = precio;
        this.desc = new Descuentos();
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public float getDescuento() {
        return desc.getDescuento(this.fecha);
    }

}
