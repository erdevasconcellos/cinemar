package dataaccess;

import model.Cliente;
import repo.CinemarDB;
import repo.Credencial;
import repo.LoginRequest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Session {

    private static CinemarDB db = null;
    public static void assignDatabase(CinemarDB cinemarDB) {
        db = cinemarDB;
    }

    public static Credencial login(LoginRequest loginRequest) throws SQLException {
        PreparedStatement ps = db.getConnection().prepareStatement(
                "select id, email from cliente where email=? and pwd=?;");
        ps.setString(1, loginRequest.getEmail());
        ps.setString(2, loginRequest.getPassword());
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new Credencial( "", rs.getInt("id"), rs.getString("email"), false );
        } else {
            return null;
        }
    }

}
