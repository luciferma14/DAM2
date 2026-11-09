package UD2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Locale;
import java.util.Scanner;

// Usa el procedimiento retirar_dinero (script en retirar_dinero.sql)
public class Ejer10 {

    public static void main(String[] args) {

        String url = "jdbc:mysql://192.168.3.176/banco";
        String user = "lucia";
        String pass = "hola";

        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.US); // los decimales se escriben con punto: 25.50

        try (Connection conexion = DriverManager.getConnection(url, user, pass);) {

            retirarEfectivo(conexion, sc);

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }

    // Pide los datos, hace las comprobaciones y, si todo es correcto, retira el dinero
    static void retirarEfectivo(Connection con, Scanner sc) throws SQLException {

        System.out.print("Número de cuenta: ");
        String numero = sc.nextLine();
        System.out.print("Cantidad a retirar: ");
        double cantidad = sc.nextDouble();

        // a) Comprobar que la cuenta existe
        if (!existeCuenta(con, numero)) {
            System.out.println("La cuenta " + numero + " no existe");
            return;
        }

        // b) Comprobar que la cuenta está activa
        if (!estaActiva(con, numero)) {
            System.out.println("La cuenta " + numero + " está inactiva");
            System.out.println("No se pueden realizar operaciones de retirada sobre cuentas inactivas");
            return;
        }

        // c) Comprobar que la cantidad es positiva (si no, se pide otra)
        while (cantidad <= 0) {
            System.out.println("La cantidad debe ser positiva");
            System.out.print("Introduce una nueva cantidad a retirar: ");
            cantidad = sc.nextDouble();
        }

        // d) Comprobar que el saldo es suficiente
        double saldo = obtenerSaldo(con, numero);
        if (saldo < cantidad) {
            System.out.println("Saldo insuficiente: la cuenta tiene " + saldo + " € y se quieren retirar " + cantidad + " €");
            return;
        }

        // Todo correcto: retiramos el dinero con el procedimiento almacenado
        double nuevoSaldo = retirarDinero(con, numero, cantidad);

        System.out.println("Retirada realizada correctamente");
        System.out.println("Nuevo saldo de la cuenta " + numero + ": " + nuevoSaldo + " €");
    }

    // a) Devuelve true si existe una cuenta con ese número
    static boolean existeCuenta(Connection con, String numero) throws SQLException {

        String sql = "SELECT numero FROM cuenta WHERE numero = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, numero);

            ResultSet rs = ps.executeQuery();
            boolean existe = rs.next();
            rs.close();
            return existe;
        }
    }

    // b) Devuelve true si la cuenta está activa
    static boolean estaActiva(Connection con, String numero) throws SQLException {

        String sql = "SELECT activa FROM cuenta WHERE numero = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, numero);

            ResultSet rs = ps.executeQuery();
            boolean activa = false;
            if (rs.next()) {
                activa = rs.getBoolean("activa");
            }
            rs.close();
            return activa;
        }
    }

    // d) Devuelve el saldo de la cuenta
    static double obtenerSaldo(Connection con, String numero) throws SQLException {

        String sql = "SELECT saldo FROM cuenta WHERE numero = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, numero);

            ResultSet rs = ps.executeQuery();
            double saldo = 0;
            if (rs.next()) {
                saldo = rs.getDouble("saldo");
            }
            rs.close();
            return saldo;
        }
    }

    // Llama al procedimiento retirar_dinero.
    // El segundo parámetro es INOUT: entra con la cantidad a retirar
    // y sale con el nuevo saldo de la cuenta.
    static double retirarDinero(Connection con, String numero, double cantidad) throws SQLException {

        String sql = "{call retirar_dinero(?, ?)}";

        try (CallableStatement cs = con.prepareCall(sql)) {
            cs.setString(1, numero);                    // parámetro IN
            cs.setDouble(2, cantidad);                  // parte IN del parámetro INOUT
            cs.registerOutParameter(2, Types.DECIMAL);  // parte OUT del parámetro INOUT

            cs.execute();

            return cs.getDouble(2);                     // nuevo saldo
        }
    }
}