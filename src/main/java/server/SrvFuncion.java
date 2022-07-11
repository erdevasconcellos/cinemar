package server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dataaccess.CRUDFuncion;
import model.Funcion;
import model.StdResponse;
import repo.MimeTypes;

import static spark.Spark.*;

public class SrvFuncion {

    static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    public static void listen() {
        try {

            post("/funcion/new", (request, response) -> {
                response.type(MimeTypes.JSON);

                try {
                    Funcion funcion = gson.fromJson(request.body(), Funcion.class);

                    System.out.println(funcion);

                    if (CRUDFuncion.create( funcion )){
                        return gson.toJson(new StdResponse(0, StdResponse.OK, "Función creada con éxito."));
                    } else {
                        return gson.toJson(new StdResponse(1, StdResponse.ERROR, "Error al registrar la función"));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    return gson.toJson(new StdResponse(1, StdResponse.ERROR, "Error al registrar la función"));
                }
            } );

            get("/funcion/getall", (request, response) -> {
                response.type(MimeTypes.JSON);

                return gson.toJson( CRUDFuncion.getAll() );
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
