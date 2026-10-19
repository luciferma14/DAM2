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
            System.out.println("Conexión realizada correctamente");

            // Insertar
            stmt.executeUpdate("INSERT INTO producto (nombre, precio, stock) VALUES ('Teclado mecánico', 59.90, 15)");
            System.out.println("Teclado mecánico insertado correctamente");
 
            stmt.executeUpdate("INSERT INTO producto (nombre, precio, stock) VALUES ('Ratón inalámbrico', 24.50, 30)");
            System.out.println("Ratón inalámbrico insertado correctamente");
 
            stmt.executeUpdate("INSERT INTO producto (nombre, precio, stock) VALUES ('Monitor 24 pulgadas', 149.99, 8)");
            System.out.println("Monitor 24 pulgadas insertado correctamente");
 
            stmt.executeUpdate("INSERT INTO producto (nombre, precio, stock) VALUES ('Auriculares Bluetooth', 39.95, 20)");
            System.out.println("Auriculares Bluetooth insertado correctamente");

            System.out.println("\nProductos en la tabla:");
            mostrarConsulta(stmt, "SELECT * FROM producto");


            // Actualizar
            stmt.executeUpdate("UPDATE producto SET precio = 29.90 WHERE nombre = 'Ratón inalámbrico'");
            stmt.executeUpdate("UPDATE producto SET stock = 10 WHERE nombre = 'Auriculares Bluetooth'");
            System.err.println("Productos actualizados correctamente");

            mostrarConsulta(stmt, "SELECT * FROM producto WHERE nombre = 'Ratón inalámbrico' OR nombre = 'Auriculares Bluetooth'");


            // Borrar
            stmt.executeUpdate("DELETE FROM producto WHERE nombre = 'Teclado mecánico'");
            System.out.println("Teclado mecánico eliminado del catálogo");
 
            System.out.println("\nProductos a la venta:");
            mostrarConsulta(stmt, "SELECT * FROM producto");


            
            System.out.println("\nConsulta final");
 
            ResultSet rs = stmt.executeQuery("SELECT nombre, stock FROM producto");
            while (rs.next()) {
                System.out.println(rs.getString("nombre") + " --> stock: " + rs.getInt("stock"));
            }
            rs.close();
 
            rs = stmt.executeQuery("SELECT SUM(stock) AS total_stock, SUM(precio * stock) AS valor_total FROM producto");
            if (rs.next()) {
                System.out.println("\nStock total de productos: " + rs.getInt("total_stock"));
                System.out.println("Valor total de venta: " + rs.getDouble("valor_total") + " €");
            }
            rs.close();
 
        } catch (SQLException e) {
            System.out.println("Error de base de datos: " + e.getMessage());
        }
    }
 
    static void mostrarConsulta(Statement st, String sql) throws SQLException {
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getInt("id") + " | "
                    + rs.getString("nombre") + " | "
                    + rs.getDouble("precio") + " € | "
                    + rs.getInt("stock") + " uds");
        }
        rs.close();
    }
}