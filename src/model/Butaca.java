package model;

public class Butaca {
    private char fila;
    private int num;
    private Cliente cliente = null;

    public Butaca(char fila, int num) {
        this.fila = fila;
        this.num = num;
    }

    public char getFila() {
        return fila;
    }

    public void setFila(char fila) {
        this.fila = fila;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return ""+fila+"-"+num;
    }

    public void reservar(Cliente cliente) {
        this.cliente = cliente;
    }

    public void free() {
        this.cliente = null;
    }

    public static char rowName(int rowNumber) {
        return "ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(rowNumber);
    }

}