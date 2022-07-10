package app;

import dataaccess.*;
import repo.CinemarDB;
import server.*;

import java.sql.SQLException;

import static app.SystemOut.print;
import static spark.Spark.*;

public class Cinemar {

    public static void main(String[] args) throws SQLException {
        try {
            CinemarDB cinemarDB = new CinemarDB();

            assignDB(cinemarDB);
            port(8080);

            SrvCliente.listen();
            SrvPelicula.listen();
            SrvActor.listen();
            SrvSala.listen();
            SrvClasificacion.listen();

            print("Server is running...", false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void assignDB(CinemarDB cinemarDB) {
        CRUDCliente.assignDatabase(cinemarDB);
        CRUDPelicula.assignDatabase(cinemarDB);
        CRUDClasificacion.assignDatabase(cinemarDB);
        CRUDReparto.assignDatabase(cinemarDB);
        CRUDActor.assignDatabase(cinemarDB);
        CRUDSala.assignDatabase(cinemarDB);
    }
}
