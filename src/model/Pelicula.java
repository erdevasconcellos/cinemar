package model;

import java.util.Locale;

public class Pelicula {
    public static String ATP = "ATP";
    public static String PM13 = "PM13";
    public static String PM16 = "PM16";
    public static String PM18 = "PM18";

    private String titulo;
    private String reparto;
    private String clasificacion;
    private int duracion;

    public Pelicula(String titulo, String reparto, String clasificacion, int duracion) {
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

    public String getReparto() {
        return reparto;
    }

    public void setReparto(String reparto) {
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
        return "Título: "+titulo.toUpperCase(Locale.ROOT)+"\nReparto: "+reparto+"\nClasificación (Arg): "+clasificacion+"\nDuración: "+duracion+" minutos";
    }
}