package model;

import java.util.ArrayList;
import java.util.Locale;

public class Pelicula {
    public static String ATP = "ATP";
    public static String PM13 = "PM13";
    public static String PM16 = "PM16";
    public static String PM18 = "PM18";

    private String titulo;
    private ArrayList<Actor> reparto;
    private String clasificacion;
    private int duracion;

    public Pelicula(String titulo, ArrayList<Actor> reparto, String clasificacion, int duracion) {
        this.titulo = titulo;
        this.reparto = reparto;
        this.clasificacion = clasificacion;
        this.duracion = duracion;
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

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        return "Título: "+titulo.toUpperCase(Locale.ROOT)+"\nReparto: "+reparto.toString()+"\nClasificación (Arg): "+clasificacion+"\nDuración: "+duracion+" minutos";
    }
}