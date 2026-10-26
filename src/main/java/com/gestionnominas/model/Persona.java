package com.gestionnominas.model;

public class Persona {
    public String nombre;
    public String dni;
    public char sexo;

    public Persona(String nombre, String dni, char sexo) {
        this.nombre = nombre;
        this.dni = dni;
        this.sexo = sexo;
    }

    public Persona(String nombre, char sexo) {
        this.nombre = nombre;
        this.sexo = sexo;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    // --- GETTERS AÑADIDOS PARA LA VISTA WEB (JSP) ---
    public String getNombre() { return nombre; }
    public String getDni() { return dni; }
    public char getSexo() { return sexo; }

    public void imprime() {
        System.out.println("Nombre: " + this.nombre + " - DNI: " + this.dni);
    }
}
