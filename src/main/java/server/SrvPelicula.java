package server;

import com.google.gson.Gson;
import dataaccess.CRUDPelicula;
import model.Pelicula;
import model.StdResponse;
import repo.MimeTypes;

import static spark.Spark.*;

public class SrvPelicula {

    static Gson gson = new Gson();

    public static void listen() {
        try {
            post("/pelicula/new", (request, response) -> {
                response.type(MimeTypes.JSON);

                try {
                    Pelicula pelicula = gson.fromJson(request.body(), Pelicula.class);
                    if (CRUDPelicula.insert(pelicula)) {
                        return gson.toJson( new StdResponse(0, StdResponse.OK, "Película registrada."));
                    } else {
                        return gson.toJson( new StdResponse(1, StdResponse.ERROR, "No se pudo registrar la película."));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return gson.toJson( new StdResponse(1, StdResponse.ERROR, "No se pudo registrar la película."));
                }
            });

            get("/pelicula/get/:id", (request, response) -> {
                response.type(MimeTypes.JSON);
                Pelicula pelicula = CRUDPelicula.get( Integer.parseInt( request.params(":id") ) );
                if (pelicula != null) {
                    return gson.toJson(pelicula);
                } else {
                    return gson.toJson( new StdResponse(1, StdResponse.ERROR, "Película inexistente."));
                }
            } );

            get("/pelicula/all", ((request, response) -> {
                response.type(MimeTypes.JSON);
                return gson.toJson( CRUDPelicula.getAll() );
            }));

            put("/pelicula/update", ((request, response) -> {
                response.type(MimeTypes.JSON);

                try {
                    Pelicula pelicula = gson.fromJson( request.body(), Pelicula.class );

                    if (CRUDPelicula.update(pelicula)) {
                        return gson.toJson( new StdResponse(0, StdResponse.OK, "Datos de película actualizados."));
                    } else {
                        return gson.toJson( new StdResponse(1, StdResponse.ERROR, "No se pudo completar la operación."));
                    }
                } catch (Exception e) {
                    return gson.toJson( new StdResponse(1, StdResponse.ERROR, "No se pudo completar la operación."));
                }
            }));

            delete("/pelicula/delete", ((request, response) -> {
                response.type(MimeTypes.JSON);

                try {
                    Pelicula pelicula = gson.fromJson( request.body(), Pelicula.class );

                    if (CRUDPelicula.delete( pelicula.getId() )) {
                        return gson.toJson( new StdResponse(0, StdResponse.OK, "Película eliminada."));
                    } else {
                        return gson.toJson( new StdResponse(1, StdResponse.ERROR, "No se pudo eliminar la pelicula."));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return gson.toJson( new StdResponse(1, StdResponse.ERROR, "No se pudo eliminar la pelicula."));
                }
            }));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
