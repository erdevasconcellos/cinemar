package model;

import java.util.ArrayList;

public class Sala {
    private String nombre;
    private ArrayList<Butaca> butacas;
    private boolean is3D; //2D, 3D
    private int capacidad;

    public Sala(String nombre, boolean is3D, int filas, int butacas_x_fila) {
        this.is3D = is3D;
        this.capacidad = filas * butacas_x_fila;
        butacas = new ArrayList<>();
        crearButacas(filas, butacas_x_fila);
    }

    private void crearButacas(int rows, int cols) {
        for (int row = 0; row < rows; row++) {
            for (int col = 1; col <= cols; col++) {
                butacas.add(new Butaca( Butaca.rowName(row), col ));
            }
        }
    }

    public ArrayList<Butaca> getButacas() {
        return this.butacas;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public boolean is3D() {
        return is3D;
    }

    public Butaca getButaca(char fila, int num) {
        for (Butaca butaca : butacas) {
            if (butaca.toString().compareTo("" + fila + "-" + num) == 0) {
                return butaca;
            }
        }

        return null;
    }

}
