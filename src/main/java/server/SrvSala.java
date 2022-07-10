package server;

import com.google.gson.Gson;
import dataaccess.CRUDSala;
import model.Sala;
import model.StdResponse;
import repo.MimeTypes;

import static spark.Spark.*;

public class SrvSala {

    static Gson gson = new Gson();

    public static void listen() {
        try {
            post("/sala/create", (request, response) -> {
                response.type(MimeTypes.JSON);

                try {
                    Sala sala = gson.fromJson( request.body(), Sala.class );
                    if (CRUDSala.create( sala )) {
                        return gson.toJson( new StdResponse(0, StdResponse.OK, "Sala creada."));
                    } else {
                        return gson.toJson( new StdResponse(1, StdResponse.ERROR, "No se pudo crear la sala."));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return gson.toJson( new StdResponse(1, StdResponse.ERROR, "No se pudo crear la sala."));
                }
            });

            get("/sala/get/:id", (request, response) -> {
                response.type(MimeTypes.JSON);

                try {
                    int idSala = Integer.parseInt( request.params(":id") );
                    Sala sala = CRUDSala.get(idSala);

                    if (sala != null) {
                        return gson.toJson( sala );
                    } else {
                        return gson.toJson( new StdResponse(1, StdResponse.ERROR, "La sala no existe."));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return gson.toJson( new StdResponse(1, StdResponse.ERROR, "Ocurrió un error."));
                }
            });

            get("/sala/getall", (request, response) -> {
               response.type(MimeTypes.JSON);

               try {
                    return gson.toJson( CRUDSala.getAll() );
               } catch (Exception e) {
                   return gson.toJson( new StdResponse(1, StdResponse.ERROR, "Error al listar las salas."));
               }
            });

            put("/sala/update", (request, response) -> {
                response.type(MimeTypes.JSON);

                try {
                    Sala sala = gson.fromJson( request.body(), Sala.class );
                    if (CRUDSala.update(sala)) {
                        return gson.toJson( new StdResponse(0, StdResponse.OK, "Información de sala actualizada."));
                    } else {
                        return gson.toJson( new StdResponse(1, StdResponse.ERROR, "Error al modificar la sala."));
                    }
                } catch (Exception e) {
                    return gson.toJson( new StdResponse(1, StdResponse.ERROR, "Error al modificar la sala."));
                }
            });

            delete("/sala/delete", (request, response) -> {
                response.type(MimeTypes.JSON);

                try {
                    Sala sala = gson.fromJson( request.body(), Sala.class );

                    if (CRUDSala.delete( sala )) {
                        return gson.toJson( new StdResponse(0, StdResponse.OK, "Sala eliminada."));
                    } else {
                        return gson.toJson( new StdResponse(1, StdResponse.ERROR, "No se pudo eliminar la sala."));
                    }

                } catch (Exception e) {
                    return gson.toJson( new StdResponse(1, StdResponse.ERROR, "No se pudo eliminar la sala."));
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
