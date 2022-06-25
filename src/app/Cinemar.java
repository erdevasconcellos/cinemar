package app;

import model.*;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Cinemar {

    public static void main(String[] args) {

        Sala sala = new Sala("Sala 1", 10, 10);

        ArrayList<Actor> reparto = new ArrayList<>();
        reparto.add( new Actor(1, "Sylvester Stallone") );

        Pelicula peli = new Pelicula("Rambo", reparto , Pelicula.PM16, 115);
        Funcion funcion = new Funcion(sala, peli, true, Date.valueOf("2022-06-25"), Time.valueOf("22:00:00"), 2000.0f);
        Cliente cliente = new Cliente("32043649", "López", "Pedro", Date.valueOf("1986-12-03"), false);

        print("Descuento de hoy: " + funcion.getDescuento() + "%" );

    }

    public static void print(Object o) {
        System.out.print(o);
    }
}
