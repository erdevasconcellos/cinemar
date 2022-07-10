package dataaccess;

import model.Actor;
import model.Pelicula;
import repo.CinemarDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CRUDPelicula {

    private static CinemarDB db = null;
    public static void assignDatabase(CinemarDB cinemarDB) {
        db = cinemarDB;
    }

    public static boolean insert(Pelicula pelicula) throws SQLException {

        db.beginTransaction();

        try {
            PreparedStatement statement = db.getConnection().prepareStatement(
                    "insert into pelicula (titulo, id_clasificacion, duracion) value (?, ?, ?);");
            statement.setString(1, pelicula.getTitulo());
            statement.setInt(2, pelicula.getClasificacion().getId());
            statement.setInt(3, pelicula.getDuracion());
            statement.executeUpdate();

            ResultSet rs = db.getStatement().executeQuery("select last_insert_id() as id_pelicula;");

            int id_pelicula = rs.next() ? rs.getInt("id_pelicula") : 0;

            if (id_pelicula != 0) {
                PreparedStatement psReparto = db.getConnection().prepareStatement(
                        "insert into reparto (id_pelicula, id_actor) value (?, ?)");

                for (Actor actor : pelicula.getReparto()) {
                    psReparto.setInt(1, id_pelicula);
                    psReparto.setInt(2, actor.getId());
                    psReparto.executeUpdate();
                }
            }

            db.commit();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            db.rollback();
            return false;
        }
    }

    public static Pelicula get(int idPelicula) throws SQLException {
        db.autoCommit();
        PreparedStatement statement = db.getConnection().prepareStatement("select * from pelicula where id = ?;");
        statement.setInt(1, idPelicula);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            try {
                return new Pelicula(
                        rs.getInt(Column.ID),
                        rs.getString(Column.TITULO),
                        CRUDReparto.get(idPelicula),
                        CRUDClasificacion.get( rs.getInt(Column.ID_CLASIFICACION) ),
                        rs.getInt(Column.DURACION)
                );
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        } else {
            return null;
        }
    }

    public static boolean delete(int idPelicula) throws SQLException {
        try {
            db.autoCommit();
            int delRows = db.getStatement().executeUpdate("delete from pelicula where id = "+idPelicula);
            return (delRows == 1);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ArrayList<Pelicula> getAll() throws SQLException {
        ArrayList<Pelicula> listaPeliculas = new ArrayList<>();
        ResultSet rs = db.getStatement().executeQuery("select id from pelicula;");

        while (rs.next()) {
            listaPeliculas.add( get( rs.getInt(Column.ID) ) );
        }

        return listaPeliculas;
    }

    public static boolean update(Pelicula pelicula) throws SQLException {
        try {
            db.beginTransaction();

            PreparedStatement statement = db.getConnection().prepareStatement(
                    "update pelicula set titulo=?, id_clasificacion=?, duracion=? where id=?;");
            statement.setString(1, pelicula.getTitulo() );
            statement.setInt(2, pelicula.getClasificacion().getId());
            statement.setInt(3, pelicula.getDuracion());
            statement.setInt(4, pelicula.getId());
            int affrows = statement.executeUpdate();

            CRUDReparto.delete(pelicula.getId());
            CRUDReparto.insert(pelicula.getId(), pelicula.getReparto());

            db.commit();

            return (affrows == 1);

        } catch (Exception e) {
            db.rollback();
            return false;
        }
    }

    static class Column {
        private static final String ID = "id";
        private static final String TITULO = "titulo";
        private static final String ID_CLASIFICACION = "id_clasificacion";
        private static final String DURACION = "duracion";
    }

}
