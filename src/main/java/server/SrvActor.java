package server;

import com.google.gson.Gson;
import dataaccess.CRUDActor;
import model.Actor;
import model.StdResponse;
import repo.MimeTypes;

import static spark.Spark.*;

public class SrvActor {

    static Gson gson = new Gson();

    public static void listen() {
        try {
            get("/actores/getall", ((request, response) -> {
                response.type(MimeTypes.JSON);
                return gson.toJson(CRUDActor.getAll());
            }));

            get("/actores/get/:id", ((request, response) -> {
                response.type(MimeTypes.JSON);

                try {
                    int idActor = Integer.parseInt( request.params(":id") );
                    Actor actor = CRUDActor.get( idActor );
                    if ( actor != null ) {
                        return gson.toJson( actor );
                    } else {
                        return gson.toJson(new StdResponse(1, StdResponse.ERROR, "No se pudo registrar el actor."));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return gson.toJson(new StdResponse(1, StdResponse.ERROR, "Id de actor no válido."));
                }
            }));

            post("/actores/new", ((request, response) -> {
                response.type(MimeTypes.JSON);

                try {
                    Actor actor = gson.fromJson( request.body(), Actor.class );
                    if (CRUDActor.newActor(actor)) {
                        return gson.toJson(new StdResponse(0, StdResponse.OK, "Actor registrado correctamente."));
                    } else {
                        return gson.toJson(new StdResponse(1, StdResponse.ERROR, "No se pudo registrar el actor."));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return gson.toJson(new StdResponse(1, StdResponse.ERROR, "No se pudo registrar el actor."));
                }
            }));

            put("/actores/edit", ((request, response) -> {
                response.type(MimeTypes.JSON);

                try {
                    Actor actor = gson.fromJson( request.body(), Actor.class );
                    if (CRUDActor.update(actor)) {
                        return gson.toJson(new StdResponse(0, StdResponse.OK, "Actor modificado correctamente."));
                    } else {
                        return gson.toJson(new StdResponse(1, StdResponse.ERROR, "No se pudo modificar el actor."));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return gson.toJson(new StdResponse(1, StdResponse.ERROR, "No se pudo modificar el actor."));
                }
            }));

            delete("/actores/delete", (request, response) -> {
                response.type(MimeTypes.JSON);

                try {
                    Actor actor = gson.fromJson( request.body(), Actor.class );
                    if (CRUDActor.delete(actor)) {
                        return gson.toJson(new StdResponse(0, StdResponse.OK, "Actor eliminado."));
                    } else {
                        return gson.toJson(new StdResponse(1, StdResponse.ERROR, "No se pudo eliminar el actor."));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return gson.toJson(new StdResponse(1, StdResponse.ERROR, "No se pudo eliminar el actor."));
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
