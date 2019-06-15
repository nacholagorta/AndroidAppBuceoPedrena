package com.example.pccom.androidappbuceopedrena;

public class Salida {
    private String nombre_instructor;
    private String lugar;
    private String hora;

   /* public Salida(String nombre_instructor, String lugar, String hora){
        this.nombre_instructor = nombre_instructor;
        this.lugar = lugar;
        this.hora = hora;
    }*/

    public String getNombre_instructor() {
        return nombre_instructor;
    }

    public String getLugar() {
        return lugar;
    }

    public String getHora() {
        return hora;
    }

    public void setNombre_instructor(String nombre_instructor) {
        this.nombre_instructor = nombre_instructor;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
