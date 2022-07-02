package model;

import java.util.ArrayList;
import java.util.Date;

public class Cliente {
    private String dni;
    private String apellido;
    private String nombre;
    private Date fch_nac;
    private boolean discountCard;

    public Cliente(String dni, String apellido, String nombre, Date fch_nac, boolean discountCard) {
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.fch_nac = fch_nac;
        this.discountCard = discountCard;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFch_nac() {
        return fch_nac;
    }

    public void setFch_nac(Date fch_nac) {
        this.fch_nac = fch_nac;
    }

    @Override
    public String toString() {
        return "Cliente: "+apellido+", "+nombre;
    }

    /*
    public boolean getTarjDescuento(ArrayList<Reserva> r) {

    }
     */
}
