package UD2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejer1{
     public static void main(String[] args) {
        
        String url = "jdbc:mysql://192.168.3.176/tienda";
        String user = "lucia";
        String pass = "hola";

        try (
            Connection conexion = DriverManager.getConnection(url, user, pass);
            Statement stmt = conexion.createStatement();
        ){
            stmt.executeUpdate("INSERT INTO producto (nombre, precio, stock) VALUES ('Teclado mecánico', 59.90, 15)");
            System.out.println("Teclado mecánico insertado correctamente");
 
            stmt.executeUpdate("INSERT INTO producto (nombre, precio, stock) VALUES ('Ratón inalámbrico', 24.50, 30)");
            System.out.println("Ratón inalámbrico insertado correctamente");
 
            stmt.executeUpdate("INSERT INTO producto (nombre, precio, stock) VALUES ('Monitor 24 pulgadas', 149.99, 8)");
            System.out.println("Monitor 24 pulgadas insertado correctamente");
 
            stmt.executeUpdate("INSERT INTO producto (nombre, precio, stock) VALUES ('Auriculares Bluetooth', 39.95, 20)");
            System.out.println("Auriculares Bluetooth insertado correctamente");

            System.out.println("Conexión realizada correctamente");
        } catch (SQLException e) {
            System.out.println("Error al conectar con MySQL");
        }
    }
}