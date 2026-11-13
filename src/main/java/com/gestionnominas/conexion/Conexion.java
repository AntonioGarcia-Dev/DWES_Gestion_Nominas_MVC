package com.gestionnominas.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gestor de conexiones a MariaDB implementando el patrón Singleton.
 */
public class Conexion {

    private static Conexion instancia;
    private static final String URL = "jdbc:mariadb://localhost:3306/gestion_nominas";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "123456";

    /**
     * Constructor privado para evitar instanciación externa (Patrón Singleton).
     */
    private Conexion() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error al cargar el driver de MariaDB", e);
        }
    }

    /**
     * Devuelve la instancia única de la clase Conexion.
     *
     * @return Instancia única de Conexion.
     */
    public static synchronized Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    /**
     * Crea y devuelve una conexión activa a la base de datos.
     *
     * @return Objeto Connection activo.
     * @throws SQLException Si ocurre un error de acceso a la base de datos.
     */
    public Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }

    /**
     * Método estático de conveniencia que delega en la instancia Singleton.
     *
     * @return Objeto Connection activo.
     * @throws SQLException Si ocurre un error de conexión.
     */
    public static Connection obtenerConexion() throws SQLException {
        return getInstancia().getConexion();
    }
}
