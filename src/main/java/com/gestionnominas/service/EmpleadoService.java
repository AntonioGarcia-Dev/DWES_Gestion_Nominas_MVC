package com.gestionnominas.service;

import com.gestionnominas.dao.EmpleadoDAO;
import com.gestionnominas.dao.IEmpleadoDAO;
import com.gestionnominas.model.Empleado;
import java.util.List;

/**
 * Fachada de negocio (Patrón Façade / Business Delegate) para la gestión de empleados y nóminas.
 * Aplica el Principio de Inversión de Dependencias (DIP) mediante IEmpleadoDAO.
 */
public class EmpleadoService {

    private final IEmpleadoDAO empleadoDAO;

    /**
     * Constructor por defecto que inicializa la implementación DAO.
     */
    public EmpleadoService() {
        this.empleadoDAO = new EmpleadoDAO();
    }

    /**
     * Constructor para inyección de dependencias (DIP).
     *
     * @param empleadoDAO Implementación de la interfaz IEmpleadoDAO.
     */
    public EmpleadoService(IEmpleadoDAO empleadoDAO) {
        this.empleadoDAO = empleadoDAO;
    }

    public List<Empleado> obtenerTodos() {
        return empleadoDAO.obtenerTodos();
    }

    public Empleado obtenerPorDni(String dni) {
        return empleadoDAO.obtenerPorDni(dni);
    }

    public boolean actualizarEmpleado(Empleado emp) {
        return empleadoDAO.actualizarEmpleado(emp);
    }

    public int obtenerSalario(String dni) {
        return empleadoDAO.obtenerSalario(dni);
    }
}
