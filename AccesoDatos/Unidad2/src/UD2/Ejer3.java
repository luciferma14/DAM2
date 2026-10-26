package UD2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Scanner;

public class Ejer3 {

    public static void main(String[] args) {

        String url = "jdbc:mysql://192.168.3.176/tienda";
        String user = "lucia";
        String pass = "hola";

        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.US); // los decimales se escriben con punto: 25.50

        try (
            Connection conexion = DriverManager.getConnection(url, user, pass);
        ){

            int opcion = -1;

            while (opcion != 0) {
                System.out.println("\n===== MENÚ =====");
                System.out.println("1. Mostrar todos los productos");
                System.out.println("2. Incrementar stock de un producto");
                System.out.println("3. Modificar precio de un producto");
                System.out.println("4. Eliminar un producto");
                System.out.println("5. Buscar un producto");                
                System.out.println("0. Salir");
                System.out.print("Elige una opción: ");
                opcion = sc.nextInt();

                // Si una operación falla, mostramos el error y el menú sigue funcionando
                try {
                    switch (opcion) {
                        case 1:
                            mostrarProductos(conexion);
                            break;
                        case 2:
                            incrementarStock(conexion, sc);
                            break;
                        case 3:
                            modificarPrecio(conexion, sc);
                            break;
                        case 4:
                            eliminarProducto(conexion, sc);
                            break;
                        case 0:
                            System.out.println("Hasta pronto");
                            break;
                        default:
                            System.out.println("Opción no válida");
                    }
                } catch (SQLException e) {
                    System.out.println("Error de base de datos: " + e.getMessage());
                }
            }

        } catch (SQLException e) {
            System.out.println("No se pudo conectar: " + e.getMessage());
        }

        sc.close();
    }

    // 1) Mostrar todos los productos (ID, nombre, precio, stock)
    static void mostrarProductos(Connection con) throws SQLException {

        String sql = "SELECT id, nombre, precio, stock FROM producto";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- PRODUCTOS ---");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | "
                        + rs.getString("nombre") + " | "
                        + rs.getDouble("precio") + " € | "
                        + rs.getInt("stock") + " uds");
            }
            rs.close();
        }
    }

    // 2) Incrementar en N unidades el stock de un producto
    static void incrementarStock(Connection con, Scanner sc) throws SQLException {

        System.out.print("ID del producto: ");
        int id = sc.nextInt();
        System.out.print("Unidades a añadir: ");
        int n = sc.nextInt();

        String sql = "UPDATE producto SET stock = stock + ? WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, n);
            ps.setInt(2, id);

            int filas = ps.executeUpdate(); // filas modificadas

            if (filas > 0) {
                System.out.println("Stock actualizado correctamente");
            } else {
                System.out.println("No existe ningún producto con ese ID");
            }
        }
    }

    // 3) Modificar el precio de un producto (y mostrar la lista)
    static void modificarPrecio(Connection con, Scanner sc) throws SQLException {

        System.out.print("ID del producto: ");
        int id = sc.nextInt();
        System.out.print("Nuevo precio: ");
        double precio = sc.nextDouble();

        String sql = "UPDATE producto SET precio = ? WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, precio);
            ps.setInt(2, id);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Precio actualizado correctamente");
            } else {
                System.out.println("No existe ningún producto con ese ID");
            }
        }

        mostrarProductos(con); // se vuelve a mostrar la lista
    }

    // 4) Eliminar un producto (y mostrar la lista)
    static void eliminarProducto(Connection con, Scanner sc) throws SQLException {

        System.out.print("ID del producto a eliminar: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM producto WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Producto eliminado correctamente");
            } else {
                System.out.println("No existe ningún producto con ese ID");
            }
        }

        mostrarProductos(con); // se vuelve a mostrar la lista
    }
}