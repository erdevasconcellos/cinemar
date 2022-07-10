package dataaccess;

import model.Sala;
import repo.CinemarDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CRUDSala {
    private static CinemarDB db = null;
    public static void assignDatabase(CinemarDB cinemarDB) {
        db = cinemarDB;
    }

    public static boolean create(Sala sala) {
        try {
            db.beginTransaction();
            PreparedStatement ps = db.getConnection().prepareStatement(
                    "insert into sala (nombre, capacidad) value (?, ?);"
            );
            ps.setString(1, sala.getNombre());
            ps.setInt(2, sala.getCapacidad());
            ps.executeUpdate();
            db.commit();
            return true;
        } catch (Exception e) {
            db.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public static Sala get(int idSala) throws SQLException {
        db.autoCommit();
        ResultSet rs = db.getStatement().executeQuery("select * from sala where id="+idSala);
        if (rs.next()) {
            return new Sala(
                    rs.getInt(Columns.ID),
                    rs.getString(Columns.NOMBRE),
                    rs.getInt(Columns.CAPACIDAD)
            );
        } else {
            return null;
        }
    }

    public static boolean delete(Sala sala) throws SQLException {
        db.autoCommit();
        int delRows = db.getStatement().executeUpdate("delete from sala where id="+sala.getId());
        return (delRows == 1);
    }

    public static boolean update(Sala sala) {
        try {
            db.beginTransaction();
            PreparedStatement ps = db.getConnection().prepareStatement(
                    "update sala set nombre=?, capacidad=? where id=?;"
            );
            ps.setString(1, sala.getNombre());
            ps.setInt(2, sala.getCapacidad());
            ps.setInt(3, sala.getId());
            int affRows = ps.executeUpdate();
            db.commit();
            return (affRows == 1);
        } catch (Exception e) {
            db.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public static ArrayList<Sala> getAll() throws SQLException {
        ArrayList<Sala> salas = new ArrayList<>();
        db.autoCommit();
        ResultSet rs = db.getStatement().executeQuery("select * from sala;");
        while (rs.next()) {
            salas.add(
                    new Sala(
                            rs.getInt(Columns.ID),
                            rs.getString(Columns.NOMBRE),
                            rs.getInt(Columns.CAPACIDAD)
                    )
            );
        }
        return salas;
    }

    static class Columns {
        private static String ID = "id";
        private static String NOMBRE = "nombre";
        private static String CAPACIDAD = "capacidad";
    }

}
