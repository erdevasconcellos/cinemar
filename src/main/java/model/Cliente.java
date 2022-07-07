package model;

import java.sql.Date;

public class Cliente {
    private int id;
    private String dni;
    private String apellido;
    private String nombre;
    private Date fch_nac;
    private String tarj_desc;
    private String email;
    private String pwd;

    public Cliente(int id, String dni, String apellido, String nombre, Date fch_nac, String tarj_desc, String email, String pwd) {
        this.id = id;
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.fch_nac = fch_nac;
        this.tarj_desc = tarj_desc;
        this.email = email;
        this.pwd = pwd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFch_nac() {
        return fch_nac;
    }

    public void setFch_nac(Date fch_nac) {
        this.fch_nac = fch_nac;
    }

    public String getTarj_desc() {
        return tarj_desc;
    }

    public void setTarj_desc(String tarj_desc) {
        this.tarj_desc = tarj_desc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public boolean tarjDescuento() {
        return tarj_desc.compareTo("y") == 0;
    }
}
