package com.gestionnominas.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:mariadb://localhost:3306/gestion_nominas";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static Connection conectar() throws SQLException {
        try {
            // Requisito indispensable para que Tomcat detecte el driver correctamente
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
