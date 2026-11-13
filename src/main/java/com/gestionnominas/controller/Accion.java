package com.gestionnominas.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interfaz del patrón Command que estandariza la ejecución de acciones desde el controlador frontal.
 */
public interface Accion {

    boolean ejecutar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    String getVista();

    Object getModelo();

    Exception getError();
}
