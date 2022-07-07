package server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dataaccess.CRUDCliente;
import model.Cliente;
import model.StdResponse;
import repo.CinemarDB;

import static spark.Spark.*;
import static spark.Spark.delete;

public class SrvCliente {

    static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    static CinemarDB cinemarDB;

    public static void listen() {
        try {
            cinemarDB = new CinemarDB();
            CRUDCliente.assignDatabase(cinemarDB);

            //Clientes
            post("/cliente/register", (request, response) -> {
                response.type("application/json");
                try {
                    Cliente cliente = gson.fromJson(request.body(), Cliente.class);
                    CRUDCliente.insert(cliente);
                    return gson.toJson( new StdResponse(0, StdResponse.OK, "Usuario registrado."));
                } catch (Exception e) {
                    e.printStackTrace();
                    return gson.toJson( new StdResponse(1, StdResponse.ERROR, "No se pudo registrar el usuario."));
                }
            });

            get("/cliente/get/:id", ((request, response) -> {
                response.type("application/json");
                try {
                    Cliente cliente = CRUDCliente.get( Integer.parseInt( request.params(":id") ) );

                    if (cliente != null) {
                        return gson.toJson(cliente);
                    } else {
                        return gson.toJson( new StdResponse(1, StdResponse.ERROR, "Usuario inexistente."));
                    }

                } catch (Exception e) {
                    return gson.toJson( new StdResponse(1, StdResponse.ERROR, "Usuario inexistente."));
                }

            }));

            put("/cliente/update", ((request, response) -> {
                response.type("application/json");

                try {
                    Cliente cliente = gson.fromJson(request.body(), Cliente.class);

                    if ( CRUDCliente.update(cliente) ) {
                        return gson.toJson( new StdResponse(0, StdResponse.OK, "Usuario actualizado.")) ;
                    } else {
                        return gson.toJson( new StdResponse(1, StdResponse.ERROR, "No se pudo actualizar el usuario.") );
                    }
                } catch (Exception e) {
                    return gson.toJson( new StdResponse(1, StdResponse.ERROR, "Se produjo un error.") );
                }
            }));

            delete("cliente/delete/:id", ((request, response) -> {
                response.type("application/json");

                try {
                    if ( CRUDCliente.delete( Integer.parseInt( request.params(":id") ) ) ) {
                        return gson.toJson( new StdResponse(0, StdResponse.OK, "Usuario eliminado.")) ;
                    } else {
                        return gson.toJson( new StdResponse(1, StdResponse.ERROR, "No se pudo eliminar el usuario.") );
                    }
                } catch (Exception e) {
                    return gson.toJson( new StdResponse(1, StdResponse.ERROR, "Se produjo un error.") );
                }
            }));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
