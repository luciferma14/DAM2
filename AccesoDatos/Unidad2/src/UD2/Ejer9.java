package UD2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Locale;
import java.util.Scanner;

/*
 * FUNCIÓN SQL (ejecútala antes en phpMyAdmin)
 *
 * Versión 1 (devuelve la comisión):
 *   RETURN ROUND(cantidad * porcentaje / 100, 2);
 *
 * Versión 2 (devuelve el total, es la que usa este programa):
 *
 *   DROP FUNCTION IF EXISTS calcular_comision;
 *   DELIMITER $$
 *   CREATE FUNCTION calcular_comision(cantidad DECIMAL(10,2), porcentaje DECIMAL(5,2))
 *   RETURNS DECIMAL(10,2)
 *   DETERMINISTIC
 *   BEGIN
 *       RETURN ROUND(cantidad + cantidad * porcentaje / 100, 2);
 *   END$$
 *   DELIMITER ;
 */
public class Ejer9 {

    public static void main(String[] args) {

        String url = "jdbc:mysql://192.168.3.176/banco";
        String user = "lucia";
        String pass = "hola";

        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.US); // los decimales se escriben con punto: 1250.00

        try (Connection conexion = DriverManager.getConnection(url, user, pass);) {

            calcularComision(conexion, sc);

        } catch (SQLException e) {
            System.out.println("No se pudo conectar: " + e.getMessage());
        }

        sc.close();
    }

    static void calcularComision(Connection con, Scanner sc) throws SQLException {

        System.out.print("Cantidad: ");
        double importe = sc.nextDouble();
        System.out.print("% de comisión: ");
        double porcentaje = sc.nextDouble();

        // El primer ? es el valor que devuelve la función
        String sql = "{? = call calcular_comision(?, ?)}";

        try (CallableStatement cs = con.prepareCall(sql)) {

            cs.registerOutParameter(1, Types.DECIMAL);
            cs.setDouble(2, importe);
            cs.setDouble(3, porcentaje);

            cs.execute();

            double total = cs.getDouble(1);
            double comision = total - importe;

            System.out.printf(Locale.US, "Importe: %.2f €%n", importe);
            System.out.printf(Locale.US, "Comisión: %.2f €%n", comision);
            System.out.printf(Locale.US, "Total: %.2f €%n", total);
        }
    }
}