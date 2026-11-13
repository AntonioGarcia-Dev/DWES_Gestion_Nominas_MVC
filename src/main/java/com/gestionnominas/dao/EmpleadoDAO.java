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

/**
 * Implementación del patrón DAO para la entidad Empleado en MariaDB.
 */
public class EmpleadoDAO implements IEmpleadoDAO {

    /**
     * Obtiene todos los empleados registrados en la base de datos.
     *
     * @return Lista de empleados.
     */
    @Override
    public List<Empleado> obtenerTodos() {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT dni, nombre, sexo, categoria, anyos FROM empleados";
        try (Connection conn = Conexion.obtenerConexion();
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

    /**
     * Obtiene un empleado específico a partir de su DNI.
     *
     * @param dni DNI del empleado a buscar.
     * @return Objeto Empleado o null si no existe.
     */
    @Override
    public Empleado obtenerPorDni(String dni) {
        String sql = "SELECT dni, nombre, sexo, categoria, anyos FROM empleados WHERE dni = ?";
        try (Connection conn = Conexion.obtenerConexion();
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

    /**
     * Actualiza la categoría y años trabajados de un empleado y recalcula su nómina.
     *
     * @param emp Objeto Empleado con los datos modificados.
     * @return true si la transacción tuvo éxito; false en caso contrario.
     */
    @Override
    public boolean actualizarEmpleado(Empleado emp) {
        String sqlUpdateEmp = "UPDATE empleados SET categoria = ?, anyos = ? WHERE dni = ?";
        String sqlUpdateNom = "UPDATE nominas SET sueldo = ? WHERE dni = ?";
        boolean exito = false;

        try (Connection conn = Conexion.obtenerConexion()) {
            conn.setAutoCommit(false);

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
                conn.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exito;
    }

    /**
     * Obtiene el salario de un empleado consultando la tabla nominas.
     *
     * @param dni DNI del empleado.
     * @return Sueldo del empleado o -1 si no se encuentra.
     */
    @Override
    public int obtenerSalario(String dni) {
        String sql = "SELECT sueldo FROM nominas WHERE dni = ?";
        try (Connection conn = Conexion.obtenerConexion();
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
