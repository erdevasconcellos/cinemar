package repo;

import java.sql.*;

public class CinemarDB {
    private Connection connection;
    private Statement statement;

    private static final String host = "localhost";
    private static final String database = "cinemar";
    private static final String user = "root";
    private static final String password = "1234";
    private static final int port = 3307;

    public CinemarDB() throws SQLException {
        String connectionString = "jdbc:mariadb://"+host+":"+port+"/"+database;
        connection = DriverManager.getConnection(connectionString, user, password);
        statement = connection.createStatement();
    }

    public ResultSet execQuery(String sql) {
        try {
            return statement.executeQuery(sql);
        } catch (Exception e) {
            return null;
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void beginTransaction() {
        try {
            this.getConnection().setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void autoCommit() {
        try {
            this.getConnection().setAutoCommit(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void rollback() {
        try {
            this.getConnection().rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void commit() {
        try {
            this.getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
