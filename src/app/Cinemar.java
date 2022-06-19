package app;

import model.*;

import java.sql.Date;
import java.sql.Time;

public class Cinemar {

    public static void main(String[] args) {

        Sala sala = new Sala("Sala 1", false, 10, 10);
        Pelicula peli = new Pelicula("Rambo", "Sylvester Stallone", Pelicula.PM16, 115);
        Funcion funcion = new Funcion(sala, peli, Date.valueOf("2022-06-20"), Time.valueOf("22:00:00"), 2000.0f);
        Cliente cliente = new Cliente("32043649", "López", "Pedro", Date.valueOf("1986-12-03"), false);



        for ( Butaca butaca : funcion.getSala().getButacas() ) {
            print(butaca.toString()+"\n");
        }

    }

    public static void print(Object o) {
        System.out.print(o);
    }
}
