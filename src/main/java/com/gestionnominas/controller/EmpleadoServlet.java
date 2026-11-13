package com.gestionnominas.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet único que implementa el patrón Front Controller para centralizar
 * la recepción de todas las peticiones HTTP de la aplicación.
 */
@WebServlet(name = "EmpleadoServlet", urlPatterns = {"/empleado", "/controlador"})
public class EmpleadoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    /**
     * Procesa las peticiones GET reenviándolas al método doPost para evitar código redundante.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Centraliza el procesamiento de todas las peticiones mediante AyudaSolicitud y Accion.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        AyudaSolicitud ayudaSol = new AyudaSolicitud(request);
        Accion accion = ayudaSol.getAccion();

        if (accion.ejecutar(request, response)) {
            String vista = accion.getVista();
            request.setAttribute("modelo", accion.getModelo());
            RequestDispatcher rd = request.getRequestDispatcher(vista);
            rd.forward(request, response);
        } else {
            gesError(accion.getVista(), accion.getError(), request, response);
        }
    }

    /**
     * Gestor centralizado de errores que redirige hacia la vista de error.
     */
    private void gesError(String vistaError, Exception excepcion,
                          HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mensaje = (excepcion != null && excepcion.getMessage() != null)
                ? excepcion.getMessage()
                : "Error procesando la solicitud.";
        request.setAttribute("error", mensaje);
        request.setAttribute("excepcion", excepcion);
        RequestDispatcher rd = request.getRequestDispatcher(vistaError);
        rd.forward(request, response);
    }
}
