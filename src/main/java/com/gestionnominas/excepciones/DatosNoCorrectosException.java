package com.gestionnominas.excepciones;

public class DatosNoCorrectosException extends Exception {
    public DatosNoCorrectosException(String mensaje) {
        super(mensaje);
    }
}
