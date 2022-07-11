package model;

public class Funcion {
    private int id;
    private Sala sala;
    private Pelicula pelicula;
    private boolean is3D;
    private String fecha;
    private String hora;
    private float precio;

    public Funcion(int id, Sala sala, Pelicula pelicula, boolean is3D, String fecha, String hora, float precio) {
        this.id = id;
        this.sala = sala;
        this.pelicula = pelicula;
        this.is3D = is3D;
        this.fecha = fecha;
        this.hora = hora;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean is3D() {
        return is3D;
    }

    public void setIs3D(boolean is3D) {
        this.is3D = is3D;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}