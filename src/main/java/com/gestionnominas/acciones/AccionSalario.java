package com.gestionnominas.acciones;

import com.gestionnominas.controller.Accion;
import com.gestionnominas.service.EmpleadoService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Acción comando que gestiona el caso de uso de consulta de salario por DNI.
 */
public class AccionSalario implements Accion {

    private String vista;
    private Object modelo;
    private Exception error;
    private final EmpleadoService empleadoService = new EmpleadoService();

    @Override
    public boolean ejecutar(HttpServletRequest request, HttpServletResponse response) {
        String accion = request.getParameter("accion");
        try {
            if ("buscarSalario".equals(accion)) {
                this.vista = "/views/buscarSalario.jsp";
                return true;
            } else if ("mostrarSalario".equals(accion)) {
                String dni = request.getParameter("dni");
                int salario = empleadoService.obtenerSalario(dni);

                this.modelo = salario;
                request.setAttribute("dni", dni);
                request.setAttribute("salario", salario);
                this.vista = "/views/mostrarSalario.jsp";
                return true;
            }
            return false;
        } catch (Exception e) {
            this.error = e;
            this.vista = "/views/error.jsp";
            return false;
        }
    }

    @Override
    public String getVista() {
        return vista;
    }

    @Override
    public Object getModelo() {
        return modelo;
    }

    @Override
    public Exception getError() {
        return error;
    }
}
