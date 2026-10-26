package com.gestionnominas.dao;

import com.gestionnominas.conexion.Conexion;
import com.gestionnominas.model.Empleado;
import com.gestionnominas.model.Nomina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    // 1. Obtener todos los empleados (Para la opción 1 del menú)
    public List<Empleado> obtenerTodos() {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT dni, nombre, sexo, categoria, anyos FROM empleados";
        try (Connection conn = Conexion.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new Empleado(
                        rs.getString("nombre"),
                        rs.getString("dni"),
                        rs.getString("sexo").charAt(0),
                        rs.getInt("categoria"),
                        rs.getInt("anyos")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    // 2. Obtener un empleado por su DNI (Para cargar el formulario de modificación)
    public Empleado obtenerPorDni(String dni) {
        String sql = "SELECT dni, nombre, sexo, categoria, anyos FROM empleados WHERE dni = ?";
        try (Connection conn = Conexion.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, dni);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Empleado(
                            rs.getString("nombre"),
                            rs.getString("dni"),
                            rs.getString("sexo").charAt(0),
                            rs.getInt("categoria"),
                            rs.getInt("anyos")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 3. Actualizar categoría y años. Simultáneamente calcula y guarda el nuevo sueldo
    public boolean actualizarEmpleado(Empleado emp) {
        String sqlUpdateEmp = "UPDATE empleados SET categoria = ?, anyos = ? WHERE dni = ?";
        String sqlUpdateNom = "UPDATE nominas SET sueldo = ? WHERE dni = ?";
        boolean exito = false;

        try (Connection conn = Conexion.conectar()) {
            conn.setAutoCommit(false); // Transacción para asegurar que ambas tablas se actualizan juntas

            try (PreparedStatement pstmtEmp = conn.prepareStatement(sqlUpdateEmp);
                 PreparedStatement pstmtNom = conn.prepareStatement(sqlUpdateNom)) {

                pstmtEmp.setInt(1, emp.getCategoria());
                pstmtEmp.setInt(2, emp.anyos);
                pstmtEmp.setString(3, emp.dni);
                pstmtEmp.executeUpdate();

                int nuevoSueldo = Nomina.sueldo(emp);
                pstmtNom.setInt(1, nuevoSueldo);
                pstmtNom.setString(2, emp.dni);
                pstmtNom.executeUpdate();

                conn.commit();
                exito = true;
            } catch (SQLException e) {
                conn.rollback(); // Deshacer cambios si hay error
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exito;
    }

    // 4. Obtener únicamente el salario de la tabla nominas dado un DNI (Para la opción 2 del menú)
    public int obtenerSalario(String dni) {
        String sql = "SELECT sueldo FROM nominas WHERE dni = ?";
        try (Connection conn = Conexion.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, dni);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("sueldo");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
