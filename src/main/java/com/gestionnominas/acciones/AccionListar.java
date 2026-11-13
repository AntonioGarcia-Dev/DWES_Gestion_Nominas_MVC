package com.gestionnominas.acciones;

import com.gestionnominas.controller.Accion;
import com.gestionnominas.model.Empleado;
import com.gestionnominas.service.EmpleadoService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Acción comando para obtener y mostrar el listado completo de empleados.
 */
public class AccionListar implements Accion {

    private String vista = "/views/listar.jsp";
    private Object modelo;
    private Exception error;
    private final EmpleadoService empleadoService = new EmpleadoService();

    @Override
    public boolean ejecutar(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Empleado> lista = empleadoService.obtenerTodos();
            this.modelo = lista;
            request.setAttribute("empleados", lista);
            return true;
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
