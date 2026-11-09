package UD2;

import java.sql.*;
import java.util.Locale;
import java.util.Scanner;

public class Ejer7 {

    public static void main(String[] args) {

        String url = "jdbc:mysql://192.168.3.176/banco";
        String user = "lucia";
        String pass = "hola";

        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.US); // los decimales se escriben con punto: 25.50

        try (Connection conexion = DriverManager.getConnection(url, user, pass);) {

            System.out.print("Número de cuenta: ");
            int id = sc.nextInt();
            System.out.print("Cantidad a ingresar: ");
            double cantidad = sc.nextDouble();

            // 1) Consultar y mostrar el saldo actual
            System.out.println("\nSaldo antes del ingreso:");
            mostrarSaldo(conexion, id);

            // 2) Ejecutar el procedimiento almacenado ingresar_dinero
            ingresarDinero(conexion, id, cantidad);

            // 3) Volver a consultar el saldo
            System.out.println("\nSaldo después del ingreso:");
            mostrarSaldo(conexion, id);

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }

    // Muestra el saldo de una cuenta
    static void mostrarSaldo(Connection con, int id) throws SQLException {

        String sql = "SELECT saldo FROM cuenta WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Saldo de la cuenta " + id + ": " + rs.getDouble("saldo") + " €");
            } else {
                System.out.println("No existe ninguna cuenta con ese ID");
            }
            rs.close();
        }
    }

    // Llama al procedimiento almacenado ingresar_dinero con un CallableStatement
    static void ingresarDinero(Connection con, int id, double cantidad) throws SQLException {

        String sql = "{call ingresar_dinero(?, ?)}";

        try (CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, id);
            cs.setDouble(2, cantidad);

            cs.execute();

            System.out.println("Procedimiento ingresar_dinero ejecutado");
        }
    }
}