package server;

import com.google.gson.Gson;
import dataaccess.CRUDClasificacion;
import dataaccess.CRUDPelicula;
import model.Clasificacion;
import model.Pelicula;
import model.StdResponse;
import repo.MimeTypes;

import static spark.Spark.*;

public class SrvClasificacion {

    static Gson gson = new Gson();

    public static void listen() {
        try {

            get("/clasificacion/get/:id", (request, response) -> {
                response.type(MimeTypes.JSON);

                try {
                    int idClasificacion = Integer.parseInt( request.params(":id") );
                    Clasificacion clasif = CRUDClasificacion.get( idClasificacion );
                    if (clasif != null) {
                        return gson.toJson( clasif );
                    } else {
                        return gson.toJson(new StdResponse(1, StdResponse.ERROR, "Id de clasificación inexistente."));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return gson.toJson(new StdResponse(1, StdResponse.ERROR, "Error en el id de clasificación."));
                }

            });

            get("/clasificacion/getall", (request, response) -> {
                response.type(MimeTypes.JSON);

                try {
                    return gson.toJson( CRUDClasificacion.getAll() );
                } catch (Exception e) {
                    e.printStackTrace();
                    return gson.toJson(new StdResponse(1, StdResponse.ERROR, "Error al listar las clasificaciones."));
                }
            });

            post("/clasificacion/new", (request, response) -> {
               response.type(MimeTypes.JSON);

               try {
                   Clasificacion clasif = gson.fromJson( request.body(), Clasificacion.class );

                   if ( CRUDClasificacion.create(clasif) ) {
                       return gson.toJson(new StdResponse(0, StdResponse.OK, "Clasificación registrada."));
                   } else {
                       return gson.toJson(new StdResponse(1, StdResponse.ERROR, "Error al registrar la clasificación."));
                   }

               } catch (Exception e) {
                   return gson.toJson(new StdResponse(1, StdResponse.ERROR, "Error al registrar la clasificación."));
               }
            });

            put("/clasificacion/update", (request, response) -> {
                response.type(MimeTypes.JSON);

                try {
                    Clasificacion clasif = gson.fromJson( request.body(), Clasificacion.class );

                    if ( CRUDClasificacion.update(clasif) ) {
                        return gson.toJson(new StdResponse(0, StdResponse.OK, "Clasificación actualizada."));
                    } else {
                        return gson.toJson(new StdResponse(1, StdResponse.ERROR, "No se pudo actualizar la clasificación."));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return gson.toJson(new StdResponse(1, StdResponse.ERROR, "Error al actualizar la clasificación."));
                }

            });

            delete("/clasificacion/delete", (request, response) -> {
                response.type(MimeTypes.JSON);

                try {
                    Clasificacion clasif = gson.fromJson( request.body(), Clasificacion.class );

                    if (CRUDClasificacion.delete( clasif )) {
                        return gson.toJson( new StdResponse(0, StdResponse.OK, "Clasificación eliminada."));
                    } else {
                        return gson.toJson( new StdResponse(1, StdResponse.ERROR, "No se pudo eliminar la clasificación."));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return gson.toJson( new StdResponse(1, StdResponse.ERROR, "No se pudo eliminar la clasificación."));
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
