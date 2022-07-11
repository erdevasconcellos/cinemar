package server;

import com.google.gson.Gson;
import dataaccess.CRUDMedioPago;
import repo.MimeTypes;

import static spark.Spark.*;

public class SrvMedioPago {

    static Gson gson = new Gson();

    public static void listen() {
        try {

            get("/mediosdepago/getall", (request, response) -> {
                response.type(MimeTypes.JSON);
                return gson.toJson(CRUDMedioPago.getAll());
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
