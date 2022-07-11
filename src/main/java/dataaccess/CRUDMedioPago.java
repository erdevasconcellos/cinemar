package dataaccess;

import model.MedioPago;
import repo.CinemarDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CRUDMedioPago {

    private static CinemarDB db = null;
    public static void assignDatabase(CinemarDB cinemarDB) {
        db = cinemarDB;
    }

    public static boolean insert(MedioPago mp) {
        try {
            db.beginTransaction();
            PreparedStatement ps = db.getConnection().prepareStatement(
                    "insert into medio_pago (nombre) value (?);"
            );
            ps.setString( 1, mp.getNombre() );
            ps.executeUpdate();
            db.commit();
            return true;
        } catch (Exception e){
            db.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delete(MedioPago mp) throws SQLException {
        db.autoCommit();
        int delMP = db.getStatement().executeUpdate("delete from medio_pago where id="+mp.getId());

        return (delMP == 1);
    }

    public static ArrayList<MedioPago> getAll() throws SQLException {
        ArrayList<MedioPago> mp = new ArrayList<>();
        ResultSet rs = db.getStatement().executeQuery("select * from medio_pago");

        while (rs.next()) {
            mp.add( new MedioPago(rs.getInt("id"), rs.getString("nombre") ) );
        }

        return mp;
    }

}
