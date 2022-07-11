package repo;

public class Credencial {
    private String token;
    private int idCliente;
    private String username;
    private boolean isAdmin;

    public Credencial(String token, int idCliente, String username, boolean isAdmin) {
        this.token = token;
        this.idCliente = idCliente;
        this.username = username;
        this.isAdmin = isAdmin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
