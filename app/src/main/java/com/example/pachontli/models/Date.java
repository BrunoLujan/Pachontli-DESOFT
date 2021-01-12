package com.example.pachontli.models;

public class Date {
    private int id;
    private int veterinario_id;
    private int mascota_id;
    private String fecha;
    private String hora;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVeterinario_id() {
        return veterinario_id;
    }

    public void setVeterinario_id(int veterinario_id) {
        this.veterinario_id = veterinario_id;
    }

    public int getMascota_id() {
        return mascota_id;
    }

    public void setMascota_id(int mascota_id) {
        this.mascota_id = mascota_id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
