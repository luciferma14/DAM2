package UD2;

import java.sql.*;
import java.util.Scanner;

/*
 * PROCEDIMIENTO NUEVO para el apartado b (ejecútalo antes en phpMyAdmin):
 *
 *   DROP PROCEDURE IF EXISTS obtener_datos_cuenta;
 *   DELIMITER $$
 *   CREATE PROCEDURE obtener_datos_cuenta(
 *       IN  p_id      INT,
 *       OUT p_saldo   DECIMAL(10,2),
 *       OUT p_titular VARCHAR(100),
 *       OUT p_activa  BOOLEAN
 *   )
 *   BEGIN
 *       SELECT saldo, titular, activa
 *       INTO p_saldo, p_titular, p_activa
 *       FROM cuenta
 *       WHERE id = p_id;
 *   END$$
 *   DELIMITER ;
 */
public class Ejer8 {

    public static void main(String[] args) {

        String url = "jdbc:mysql://192.168.3.176/banco";
        String user = "lucia";
        String pass = "hola";

        Scanner sc = new Scanner(System.in);

        try (Connection conexion = DriverManager.getConnection(url, user, pass);) {

            System.out.print("Número de cuenta: ");
            int id = sc.nextInt();

            System.out.println("\n--- Apartado a) obtener_saldo ---");
            consultarSaldo(conexion, id);

            System.out.println("\n--- Apartado b) obtener_datos_cuenta ---");
            consultarCuenta(conexion, id);

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }

    // a) Llama al procedimiento obtener_saldo y muestra si la cuenta existe y su saldo
    static void consultarSaldo(Connection con, int id) throws SQLException {

        String sql = "{call obtener_saldo(?, ?)}";

        try (CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, id);                          // parámetro IN: id de la cuenta
            cs.registerOutParameter(2, Types.DECIMAL); // parámetro OUT: saldo

            cs.execute();

            double saldo = cs.getDouble(2);

            // Si el OUT viene a NULL, el procedimiento no ha encontrado la cuenta
            if (cs.wasNull()) {
                System.out.println("La cuenta " + id + " no existe");
            } else {
                System.out.println("La cuenta " + id + " existe");
                System.out.println("Saldo: " + saldo + " €");
            }
        }
    }

    // b) Llama al procedimiento obtener_datos_cuenta y muestra saldo, titular y estado
    static void consultarCuenta(Connection con, int id) throws SQLException {

        String sql = "{call obtener_datos_cuenta(?, ?, ?, ?)}";

        try (CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, id);                           // parámetro IN: id de la cuenta
            cs.registerOutParameter(2, Types.DECIMAL);  // parámetro OUT: saldo
            cs.registerOutParameter(3, Types.VARCHAR);  // parámetro OUT: titular
            cs.registerOutParameter(4, Types.BOOLEAN);  // parámetro OUT: activa

            cs.execute();

            double saldo = cs.getDouble(2);

            // Si el saldo viene a NULL, el procedimiento no ha encontrado la cuenta
            if (cs.wasNull()) {
                System.out.println("La cuenta " + id + " no existe");
            } else {
                String titular = cs.getString(3);
                boolean activa = cs.getBoolean(4);

                System.out.println("La cuenta " + id + " existe");
                System.out.println("Titular: " + titular);
                System.out.println("Saldo: " + saldo + " €");

                if (activa) {
                    System.out.println("Estado: activa");
                } else {
                    System.out.println("Estado: inactiva");
                }
            }
        }
    }
}