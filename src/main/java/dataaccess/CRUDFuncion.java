package dataaccess;

import model.Funcion;
import repo.CinemarDB;

import java.sql.PreparedStatement;

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

}
