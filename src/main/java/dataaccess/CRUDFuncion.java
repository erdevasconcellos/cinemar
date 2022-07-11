package dataaccess;

import model.Funcion;
import repo.CinemarDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CRUDFuncion {

    private static CinemarDB db = null;
    public static void assignDatabase(CinemarDB cinemarDB) {
        db = cinemarDB;
    }

    public static boolean create(Funcion funcion) {
        try {
            db.beginTransaction();
            PreparedStatement ps = db.getConnection().prepareStatement(
                    "insert into funcion (id_sala, id_pelicula, is3d, fecha, hora, precio) " +
                    "value (?, ?, ?, ?, ?, ?)");
            ps.setInt(1, funcion.getSala().getId());
            ps.setInt(2, funcion.getPelicula().getId());
            ps.setBoolean(3, funcion.is3D());
            ps.setString(4, funcion.getFecha());
            ps.setString(5, funcion.getHora());
            ps.setFloat(6, funcion.getPrecio());
            ps.executeUpdate();
            db.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            db.rollback();
            return false;
        }
    }

    public static ArrayList<Funcion> getAll() throws SQLException {
        ArrayList<Funcion> funciones = new ArrayList<>();

        ResultSet rs = db.getStatement().executeQuery("select * from funcion");

        while (rs.next()) {
            funciones.add(
                    new Funcion(
                            rs.getInt("id"),
                            CRUDSala.get( rs.getInt("id_sala") ),
                            CRUDPelicula.get( rs.getInt("id_pelicula") ),
                            rs.getBoolean("is3d"),
                            rs.getString("fecha"),
                            rs.getString("hora"),
                            rs.getFloat("precio")
                    )
            );
        }

        return funciones;
    }

}
