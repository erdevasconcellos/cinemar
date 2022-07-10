package model;

public class Clasificacion {
    public static String ATP = "ATP";
    public static String PM13 = "PM13";
    public static String PM16 = "PM16";
    public static String PM18 = "PM18";

    private int id;
    private String cod;
    private String descripcion;

    public Clasificacion(int id, String cod, String descripcion) {
        this.id = id;
        this.cod = cod;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
