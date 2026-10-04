package com.duoc.EFTSeguridad.mascota;

public class MascotaDTO {
    private String nombre;
    private String especie;
    private String raza;
    private Integer edad;
    private String nombreDuenio;

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }

    public String getRaza() { return raza; }
    public void setRaza(String raza) { this.raza = raza; }

    public Integer getEdad() { return edad; }
    public void setEdad(Integer edad) { this.edad = edad; }

    public String getNombreDuenio() { return nombreDuenio; }
    public void setNombreDuenio(String nombreDuenio) { this.nombreDuenio = nombreDuenio; }
}