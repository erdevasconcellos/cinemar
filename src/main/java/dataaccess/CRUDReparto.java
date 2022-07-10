package dataaccess;

import model.Actor;
import repo.CinemarDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CRUDReparto {

    private static CinemarDB db = null;
    public static void assignDatabase(CinemarDB cinemarDB) {
        db = cinemarDB;
    }

    public static void delete(int idPelicula) {
        try {
            db.getStatement().executeUpdate("delete from reparto where id_pelicula="+idPelicula);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insert(int idPelicula, ArrayList<Actor> reparto) throws Exception {
        try {
            db.beginTransaction();
            PreparedStatement ps = db.getConnection().prepareStatement(
                    "insert into reparto (id_pelicula, id_actor) value (?, ?);"
            );
            ps.setInt(1, idPelicula);

            for (Actor actor: reparto) {
                ps.setInt(2, actor.getId());
                ps.executeUpdate();
            }

            db.commit();
        } catch (Exception e) {
            db.rollback();
            throw e;
        };
    }

    public static ArrayList<Actor> get(int idPelicula) throws SQLException {
        ArrayList<Actor> reparto = new ArrayList<>();
        db.autoCommit();
        ResultSet rs = db.getStatement().executeQuery(
                "select id_actor as id, a.nombre as nombre from reparto r " +
                        "inner join actor a on r.id_actor = a.id where id_pelicula = "+idPelicula);

        while (rs.next()) {
            reparto.add( new Actor( rs.getInt("id"), rs.getString("nombre") ) );
        }

        return reparto;
    }

}
