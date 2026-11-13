package com.gestionnominas.acciones;

import com.gestionnominas.controller.Accion;
import com.gestionnominas.excepciones.DatosNoCorrectosException;
import com.gestionnominas.model.Empleado;
import com.gestionnominas.service.EmpleadoService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Acción comando que gestiona la búsqueda, carga de formulario y actualización de un empleado.
 */
public class AccionModificar implements Accion {

    private String vista;
    private Object modelo;
    private Exception error;
    private final EmpleadoService empleadoService = new EmpleadoService();

    @Override
    public boolean ejecutar(HttpServletRequest request, HttpServletResponse response) {
        String accion = request.getParameter("accion");
        try {
            if ("buscarModificar".equals(accion)) {
                this.vista = "/views/buscarModificar.jsp";
                return true;
            } else if ("mostrarModificar".equals(accion)) {
                String dni = request.getParameter("dni");
                Empleado emp = empleadoService.obtenerPorDni(dni);

                if (emp != null) {
                    this.modelo = emp;
                    request.setAttribute("empleado", emp);
                    this.vista = "/views/formularioModificar.jsp";
                    return true;
                } else {
                    this.error = new DatosNoCorrectosException("No se ha encontrado un empleado con el DNI: " + dni);
                    this.vista = "/views/error.jsp";
                    return false;
                }
            } else if ("actualizar".equals(accion)) {
                String dni = request.getParameter("dni");
                int categoria = Integer.parseInt(request.getParameter("categoria"));
                int anyos = Integer.parseInt(request.getParameter("anyos"));

                Empleado emp = empleadoService.obtenerPorDni(dni);
                if (emp != null) {
                    emp.setCategoria(categoria);
                    emp.anyos = anyos;
                    boolean actualizado = empleadoService.actualizarEmpleado(emp);

                    if (actualizado) {
                        List<Empleado> lista = empleadoService.obtenerTodos();
                        this.modelo = lista;
                        request.setAttribute("empleados", lista);
                        this.vista = "/views/listar.jsp";
                        return true;
                    } else {
                        throw new Exception("Error al actualizar la base de datos.");
                    }
                } else {
                    throw new DatosNoCorrectosException("No se ha encontrado un empleado con el DNI: " + dni);
                }
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
