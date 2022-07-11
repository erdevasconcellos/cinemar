package server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dataaccess.CRUDCliente;
import dataaccess.Session;
import model.Cliente;
import model.StdResponse;
import repo.Credencial;
import repo.LoginRequest;
import repo.MimeTypes;

import static spark.Spark.*;

public class SrvCliente {

    static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    public static void listen() {
        try {
            post("/cliente/register", (request, response) -> {
                response.type(MimeTypes.JSON);
                try {
                    Cliente cliente = gson.fromJson(request.body(), Cliente.class);
                    CRUDCliente.insert(cliente);
                    return gson.toJson( new StdResponse(0, StdResponse.OK, "Usuario registrado."));
                } catch (Exception e) {
                    e.printStackTrace();
                    return gson.toJson( new StdResponse(1, StdResponse.ERROR, "No se pudo registrar el usuario."));
                }
            });

            post("/login", (request, response) -> {
                response.type(MimeTypes.JSON);

                try {
                    LoginRequest loginRequest = gson.fromJson( request.body(), LoginRequest.class );
                    Credencial credencial = Session.login( loginRequest );

                    if (credencial != null) {
                        request.session(true);
                        credencial.setToken( request.session().id() );
                        return gson.toJson( credencial );
                    } else {
                        return gson.toJson( new StdResponse(1, StdResponse.ERROR, "Usuario o contraseña incorrectos."));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    return gson.toJson( new StdResponse(1, StdResponse.ERROR, "Error en la petición."));
                }
            });

            get("/cliente/get/:id", ((request, response) -> {
                response.type(MimeTypes.JSON);
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
                response.type(MimeTypes.JSON);

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

            delete("cliente/delete", ((request, response) -> {
                response.type(MimeTypes.JSON);

                try {
                    Cliente cliente = gson.fromJson( request.body(), Cliente.class );

                    if ( CRUDCliente.delete( cliente.getId() ) ) {
                        return gson.toJson( new StdResponse(0, StdResponse.OK, "Usuario eliminado.")) ;
                    } else {
                        return gson.toJson( new StdResponse(1, StdResponse.ERROR, "No se pudo eliminar el usuario.") );
                    }
                } catch (Exception e) {
                    return gson.toJson( new StdResponse(1, StdResponse.ERROR, "Se produjo un error.") );
                }
            }));

            post("/login", ((request, response) -> {
                response.type(MimeTypes.JSON);

                return "{}";
            }));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
