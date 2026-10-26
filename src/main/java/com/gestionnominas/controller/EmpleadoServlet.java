package com.gestionnominas.controller;

import com.gestionnominas.dao.EmpleadoDAO;
import com.gestionnominas.model.Empleado;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/empleado")
public class EmpleadoServlet extends HttpServlet {

    private EmpleadoDAO empleadoDAO;

    @Override
    public void init() {
        // Instanciamos el DAO cuando arranca el Servlet
        empleadoDAO = new EmpleadoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null) accion = "inicio";

        try {
            switch (accion) {
                case "listar":
                    // 1. Mostrar todos los empleados
                    List<Empleado> lista = empleadoDAO.obtenerTodos();
                    request.setAttribute("empleados", lista);
                    request.getRequestDispatcher("/views/listar.jsp").forward(request, response);
                    break;
                case "buscarSalario":
                    // 2. Redirige al formulario para pedir el DNI
                    request.getRequestDispatcher("/views/buscarSalario.jsp").forward(request, response);
                    break;
                case "buscarModificar":
                    // 3. Redirige al formulario para buscar el empleado a modificar
                    request.getRequestDispatcher("/views/buscarModificar.jsp").forward(request, response);
                    break;
                default:
                    response.sendRedirect("index.jsp");
                    break;
            }
        } catch (Exception e) {
            // Manejo de errores (0,5 puntos en la rúbrica)
            request.setAttribute("error", "Error procesando la solicitud: " + e.getMessage());
            request.getRequestDispatcher("/views/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Establecer codificación para evitar problemas con acentos (ñ, á, etc.)
        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");

        try {
            if ("mostrarSalario".equals(accion)) {
                String dni = request.getParameter("dni");
                int salario = empleadoDAO.obtenerSalario(dni);

                request.setAttribute("dni", dni);
                request.setAttribute("salario", salario);
                request.getRequestDispatcher("/views/mostrarSalario.jsp").forward(request, response);
            }
            else if ("mostrarModificar".equals(accion)) {
                String dni = request.getParameter("dni");
                Empleado emp = empleadoDAO.obtenerPorDni(dni);

                if (emp != null) {
                    request.setAttribute("empleado", emp);
                    request.getRequestDispatcher("/views/formularioModificar.jsp").forward(request, response);
                } else {
                    request.setAttribute("error", "No se ha encontrado un empleado con el DNI: " + dni);
                    request.getRequestDispatcher("/views/error.jsp").forward(request, response);
                }
            }
            else if ("actualizar".equals(accion)) {
                String dni = request.getParameter("dni");
                int categoria = Integer.parseInt(request.getParameter("categoria"));
                int anyos = Integer.parseInt(request.getParameter("anyos"));

                Empleado emp = empleadoDAO.obtenerPorDni(dni);
                if (emp != null) {
                    emp.setCategoria(categoria);
                    emp.anyos = anyos;
                    boolean actualizado = empleadoDAO.actualizarEmpleado(emp);

                    if (actualizado) {
                        // Si se actualiza correctamente, volvemos a la lista para ver el cambio
                        List<Empleado> lista = empleadoDAO.obtenerTodos();
                        request.setAttribute("empleados", lista);
                        request.getRequestDispatcher("/views/listar.jsp").forward(request, response);
                    } else {
                        throw new Exception("Error al actualizar la base de datos.");
                    }
                }
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/views/error.jsp").forward(request, response);
        }
    }
}
