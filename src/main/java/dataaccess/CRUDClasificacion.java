package dataaccess;

import model.Clasificacion;
import repo.CinemarDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CRUDClasificacion {

    private static CinemarDB db = null;
    public static void assignDatabase(CinemarDB cinemarDB) {
        db = cinemarDB;
    }


    public static Clasificacion get(int idClasificacion) throws SQLException {
        db.autoCommit();

        PreparedStatement statement = db.getConnection().prepareStatement(
                                        "select * from clasificacion where id = ?;");
        statement.setInt(1, idClasificacion);
        ResultSet resultSet = statement.executeQuery();

        if ( resultSet.next() ) {
            return new Clasificacion(
                    resultSet.getInt( Columns.ID ),
                    resultSet.getString( Columns.COD ),
                    resultSet.getString( Columns.DESCRIPCION )
            );
        } else {
            return null;
        }
    }

    public static ArrayList<Clasificacion> getAll() throws SQLException {
        ArrayList<Clasificacion> clasificacions = new ArrayList<>();
        ResultSet rs = db.getStatement().executeQuery("select * from clasificacion;");

        while ( rs.next() ) {
            clasificacions.add(
                    new Clasificacion(
                        rs.getInt( Columns.ID ),
                        rs.getString( Columns.COD ),
                        rs.getString( Columns.DESCRIPCION )
                    )
            );
        }

        return clasificacions;
    }

    public static boolean create(Clasificacion clasificacion) {
        try {
            db.beginTransaction();
            PreparedStatement ps = db.getConnection().prepareStatement(
                    "insert into clasificacion (cod, descripcion) value (?, ?);"
            );
            ps.setString(1, clasificacion.getCod());
            ps.setString(2, clasificacion.getDescripcion());
            ps.executeUpdate();
            db.commit();
            return true;
        } catch (Exception e) {
            db.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public static boolean update(Clasificacion clasificacion){
        try {
            db.beginTransaction();
            PreparedStatement ps = db.getConnection().prepareStatement(
                    "update clasificacion set cod=?, descripcion=? where id=?;"
            );
            ps.setString(1, clasificacion.getCod());
            ps.setString(2, clasificacion.getDescripcion());
            ps.setInt(3, clasificacion.getId());
            int upRows = ps.executeUpdate();
            db.commit();
            return ( upRows == 1 );
        } catch (Exception e) {
            db.rollback();
            e.printStackTrace();
            return false;
        }
    }

    static class Columns {
        private static final String ID = "id";
        private static final String COD = "cod";
        private static final String DESCRIPCION = "descripcion";
    }

}
