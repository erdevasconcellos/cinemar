package dataaccess;

import app.SystemOut;
import model.Cliente;
import repo.CinemarDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRUDCliente {

    private static CinemarDB database = null;

    private static final String ID = "id";
    private static final String DNI = "dni";
    private static final String APELLIDO = "apellido";
    private static final String NOMBRE = "nombre";
    private static final String FCH_NAC = "fch_nac";
    private static final String TARJ_DESC = "tarj_desc";
    private static final String EMAIL = "email";
    private static final String PWD = "pwd";

    public static void assignDatabase(CinemarDB db) {
        database = db;
    }

    public static void insert(Cliente cliente) throws SQLException {

        database.getConnection().setAutoCommit(false);

        PreparedStatement statement = database.getConnection().prepareStatement(
                "insert into cliente (dni, apellido, nombre, fch_nac, tarj_desc, email, pwd) value (?, ?, ?, ?, ?, ?, ?)");
        statement.setString(1, cliente.getDni());
        statement.setString(2, cliente.getApellido());
        statement.setString(3, cliente.getNombre());
        statement.setString(4, cliente.getFch_nac().toString());
        statement.setString(5, cliente.getTarj_desc());
        statement.setString(6, cliente.getEmail());
        statement.setString(7, cliente.getPwd());
        statement.executeUpdate();

        database.getConnection().commit();
    }

    public static Cliente get(int idCliente) throws SQLException {
        database.getConnection().setAutoCommit(false);

        PreparedStatement statement = database.getConnection().prepareStatement(
                                        "select * from cliente where id = ?;");
        statement.setInt(1, idCliente);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return new Cliente(
                    resultSet.getInt(ID),
                    resultSet.getString(DNI),
                    resultSet.getString(APELLIDO),
                    resultSet.getString(NOMBRE),
                    resultSet.getDate(FCH_NAC),
                    resultSet.getString(TARJ_DESC),
                    resultSet.getString(EMAIL),
                    resultSet.getString(PWD));
        } else {
            return null;
        }

    }

    public static boolean update(Cliente cliente) throws SQLException {
        database.getConnection().setAutoCommit(false);

        PreparedStatement statement = database.getConnection().prepareStatement(
                "update cliente set dni=?, apellido=?, nombre=?, fch_nac=?, tarj_desc=?, email=?, pwd=? where id=?;");

        statement.setString(1, cliente.getDni());
        statement.setString(2, cliente.getApellido());
        statement.setString(3, cliente.getNombre());
        statement.setString(4, cliente.getFch_nac().toString());
        statement.setString(5, cliente.getTarj_desc());
        statement.setString(6, cliente.getEmail());
        statement.setString(7, cliente.getPwd());
        statement.setInt(8, cliente.getId());

        int affrows = statement.executeUpdate();
        database.getConnection().commit();

        return (affrows == 1);
    }

    public static boolean delete(int idCliente) throws SQLException {
        database.getConnection().setAutoCommit(false);
        PreparedStatement statement = database.getConnection().prepareStatement(
                "delete from cliente where id=?;");
        statement.setInt(1, idCliente);
        int delRows = statement.executeUpdate();
        database.getConnection().commit();

        SystemOut.print("Eliminando usuario con id="+idCliente+"\n", false);
        SystemOut.print("Resultado: " + (delRows == 1), false);

        return (delRows == 1);
    }

}
