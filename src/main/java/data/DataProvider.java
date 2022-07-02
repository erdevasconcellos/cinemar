package data;

import model.Cliente;
import model.Pelicula;

import java.sql.Date;
import java.util.ArrayList;

public class DataProvider {

    public static ArrayList<Cliente> createClientes() {
        ArrayList<Cliente> c = new ArrayList<>();

        c.add(new Cliente("32043649", "de Vasconcellos", "Ezequiel", Date.valueOf("1986-12-03"), false));
        c.add(new Cliente("18365789", "Pérez", "Jorge", Date.valueOf("1968-05-13"), false));
        c.add(new Cliente("20325788", "Rocco", "Lucas", Date.valueOf("1971-10-09"), false));
        c.add(new Cliente("23456987", "Mamaní", "Gustavo", Date.valueOf("1972-12-23"), false));
        c.add(new Cliente("8521478", "Ceballos", "Juana", Date.valueOf("1936-03-11"), false));
        c.add(new Cliente("45000124", "López", "Darío", Date.valueOf("1998-05-01"), false));

        return c;
    }

    public static ArrayList<Pelicula> createPeliculas() {



        return null;
    }

}
