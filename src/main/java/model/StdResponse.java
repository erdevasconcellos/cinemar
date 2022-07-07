package model;

public class StdResponse {
    public static final String OK = "OK";
    public static final String ERROR = "ERROR";
    public int errCode;
    public String status;
    public String mensaje;

    public StdResponse(int errCode, String status, String errMsg) {
        this.errCode = errCode;
        this.status = status;
        this.mensaje = errMsg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
