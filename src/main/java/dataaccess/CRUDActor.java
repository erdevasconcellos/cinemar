package dataaccess;

import model.Actor;
import repo.CinemarDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CRUDActor {

    private static CinemarDB db = null;
    public static void assignDatabase(CinemarDB cinemarDB) {
        db = cinemarDB;
    }

    public static boolean newActor(Actor actor) {
        try {
            db.autoCommit();
            PreparedStatement ps = db.getConnection().prepareStatement("insert into actor (nombre) value (?);");
            ps.setString(1, actor.getNombre());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean update(Actor actor) {
        try {
            db.autoCommit();
            PreparedStatement ps = db.getConnection().prepareStatement(
                    "update actor set nombre = ? where id = ?;");
            ps.setString(1, actor.getNombre());
            ps.setInt(2, actor.getId());
            int delRows = ps.executeUpdate();
            return (delRows == 1);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean delete(Actor actor) {
        try {
            db.autoCommit();
            int d = db.getStatement().executeUpdate("delete from actor where id="+actor.getId());
            return (d == 1);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Actor get(int id) {
        try {
            db.autoCommit();
            ResultSet rs = db.getStatement().executeQuery("select * from actor where id="+id);

            if (rs.next()) {
                return new Actor( rs.getInt("id"), rs.getString("nombre") );
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Actor> getAll() {
        ArrayList<Actor> actores = new ArrayList<>();

        try {
            db.autoCommit();
            ResultSet rs = db.getStatement().executeQuery("select * from actor;");

            while (rs.next()) {
                actores.add(new Actor( rs.getInt("id"), rs.getString("nombre") ));
            }

            return actores;
        } catch (Exception e) {
            actores.clear();
            return actores;
        }
    }

}
