package com.gestionnominas.dao;

import com.gestionnominas.model.Empleado;
import java.util.List;

/**
 * Interfaz que define las operaciones de persistencia para Empleado.
 * Aplica el Principio de Inversión de Dependencias (DIP).
 */
public interface IEmpleadoDAO {
    List<Empleado> obtenerTodos();
    Empleado obtenerPorDni(String dni);
    boolean actualizarEmpleado(Empleado emp);
    int obtenerSalario(String dni);
}
