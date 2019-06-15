package com.example.pccom.androidappbuceopedrena;

public class User {
    private int id_user;
    private String nombre_user;
    private String apellido_user;
    private String telefono_user;
    private String dni_user;
    private String titulacion_user;

    public int getId_user() {
        return id_user;
    }

    public String getNombre_user() {
        return nombre_user;
    }

    public String getApellido_user() {
        return apellido_user;
    }

    public String getTelefono_user() {
        return telefono_user;
    }

    public String getDni_user() {
        return dni_user;
    }

    public String getTitulacion_user() {
        return titulacion_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setNombre_user(String nombre_user) {
        this.nombre_user = nombre_user;
    }

    public void setApellido_user(String apellido_user) {
        this.apellido_user = apellido_user;
    }

    public void setTelefono_user(String telefono_user) {
        this.telefono_user = telefono_user;
    }

    public void setDni_user(String dni_user) {
        this.dni_user = dni_user;
    }

    public void setTitulacion_user(String titulacion_user) {
        this.titulacion_user = titulacion_user;
    }
}
