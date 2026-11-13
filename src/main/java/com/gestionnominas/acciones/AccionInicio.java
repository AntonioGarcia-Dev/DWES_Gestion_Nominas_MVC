package com.gestionnominas.acciones;

import com.gestionnominas.controller.Accion;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Acción comando encargada de redirigir a la página de inicio.
 */
public class AccionInicio implements Accion {

    private String vista = "/index.jsp";

    @Override
    public boolean ejecutar(HttpServletRequest request, HttpServletResponse response) {
        return true;
    }

    @Override
    public String getVista() {
        return vista;
    }

    @Override
    public Object getModelo() {
        return null;
    }

    @Override
    public Exception getError() {
        return null;
    }
}
