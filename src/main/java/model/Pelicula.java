package model;

import java.util.ArrayList;
import java.util.Locale;

public class Pelicula {

    private int id;
    private String titulo;
    private ArrayList<Actor> reparto;
    private Clasificacion clasificacion;
    private int duracion;

    public Pelicula(int id, String titulo, ArrayList<Actor> reparto, Clasificacion clasificacion, int duracion) {
        this.id = id;
        this.titulo = titulo;
        this.reparto = reparto;
        this.clasificacion = clasificacion;
        this.duracion = duracion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ArrayList<Actor> getReparto() {
        return reparto;
    }

    public void setReparto(ArrayList<Actor> reparto) {
        this.reparto = reparto;
    }

    public Clasificacion getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(Clasificacion clasificacion) {
        this.clasificacion = clasificacion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

}