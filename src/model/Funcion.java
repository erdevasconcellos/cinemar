package model;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

public class Funcion {
    private Sala sala;
    private Pelicula pelicula;
    private Date fecha;
    private Time hora;
    private Float precio;

    public Funcion(Sala sala, Pelicula pelicula, Date fecha, Time hora, Float precio) {
        this.sala = sala;
        this.pelicula = pelicula;
        this.fecha = fecha;
        this.hora = hora;
        this.precio = precio;
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

    public int getDescuento() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);



        return 0;
    }

}
