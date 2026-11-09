package UD2;

import java.sql.*;
import java.util.Locale;
import java.util.Scanner;

public class Ejer6 {

    public static void main(String[] args) {

        String url = "jdbc:mysql://192.168.3.176/banco";
        String user = "lucia";
        String pass = "hola";

        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.US); // los decimales se escriben con punto: 25.50

        try (Connection conexion = DriverManager.getConnection(url, user, pass);) {

            System.out.print("Número de cuenta origen: ");
            int origen = sc.nextInt();
            System.out.print("Número de cuenta destino: ");
            int destino = sc.nextInt();
            System.out.print("Cantidad a transferir: ");
            double cantidad = sc.nextDouble();

            transferir(conexion, origen, destino, cantidad);

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }

    // Realiza la transferencia como una única transacción
    static void transferir(Connection con, int origen, int destino, double cantidad) throws SQLException {

        if (cantidad <= 0) {
            System.out.println("La cantidad debe ser mayor que 0");
            return;
        }
        if (origen == destino) {
            System.out.println("La cuenta origen y la cuenta destino no pueden ser la misma");
            return;
        }

        // Desactivamos el autocommit para poder deshacer las operaciones si algo falla
        con.setAutoCommit(false);

        try {
            // 1) Comprobar que la cuenta origen tiene saldo suficiente
            double saldo = obtenerSaldo(con, origen);

            if (saldo < 0) {
                throw new SQLException("La cuenta origen no existe");
            }
            if (saldo < cantidad) {
                throw new SQLException("La cuenta origen no tiene saldo suficiente");
            }

            // 2) Mostrar el saldo de la cuenta origen
            System.out.println("\nSaldo de la cuenta origen antes de la transferencia:");
            mostrarSaldo(con, origen);

            // 3) Eliminar el dinero de la cuenta origen
            retirar(con, origen, cantidad);

            // 4) Ingresar el dinero en la cuenta destino
            ingresar(con, destino, cantidad);

            // Si todo ha ido bien, confirmamos
            con.commit();
            System.out.println("\nTransferencia realizada correctamente");

        } catch (SQLException e) {
            // Si algo falla, deshacemos lo que se haya hecho
            con.rollback();
            System.out.println("\nError: " + e.getMessage());
            System.out.println("Se han deshecho las operaciones (rollback)");
        }

        // 5) Mostrar el saldo de la cuenta origen, salga bien o mal la operación
        System.out.println("\nSaldo de la cuenta origen tras la operación:");
        mostrarSaldo(con, origen);

        con.setAutoCommit(true);
    }

    // Devuelve el saldo de una cuenta, o -1 si la cuenta no existe
    static double obtenerSaldo(Connection con, int id) throws SQLException {

        String sql = "SELECT saldo FROM cuenta WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            double saldo = -1;
            if (rs.next()) {
                saldo = rs.getDouble("saldo");
            }
            rs.close();
            return saldo;
        }
    }

    // Muestra el saldo de una cuenta
    static void mostrarSaldo(Connection con, int id) throws SQLException {

        double saldo = obtenerSaldo(con, id);

        if (saldo < 0) {
            System.out.println("La cuenta " + id + " no existe");
        } else {
            System.out.println("Saldo de la cuenta " + id + ": " + saldo + " €");
        }
    }

    // Resta dinero de una cuenta
    static void retirar(Connection con, int id, double cantidad) throws SQLException {

        String sql = "UPDATE cuenta SET saldo = saldo - ? WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, cantidad);
            ps.setInt(2, id);

            int filas = ps.executeUpdate();

            if (filas == 0) {
                throw new SQLException("No se ha podido retirar el dinero de la cuenta origen");
            }
        }
    }

    // Suma dinero a una cuenta
    static void ingresar(Connection con, int id, double cantidad) throws SQLException {

        String sql = "UPDATE cuenta SET saldo = saldo + ? WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, cantidad);
            ps.setInt(2, id);

            int filas = ps.executeUpdate();

            if (filas == 0) {
                throw new SQLException("La cuenta destino no existe");
            }
        }
    }
}