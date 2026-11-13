package com.gestionnominas.controller;

import javax.servlet.http.HttpServletRequest;

/**
 * Clase de soporte que preprocesa la petición HTTP y solicita la acción a la factoría.
 */
public class AyudaSolicitud {

    private final HttpServletRequest request;

    public AyudaSolicitud(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * Extrae el parámetro de acción de la petición y delega su creación a FactoriaAcciones.
     *
     * @return Objeto Accion listo para ser ejecutado por el controlador.
     */
    public Accion getAccion() {
        String accion = request.getParameter("accion");
        return FactoriaAcciones.creaAccion(accion);
    }
}
