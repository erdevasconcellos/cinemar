package repo;

import java.sql.*;

public class CinemarDB {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

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

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }
}
