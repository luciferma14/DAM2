package UD2;

import java.sql.*;
import java.util.Scanner;

public class Ejer4 {

    public static void main(String[] args) {

        String url = "jdbc:mysql://192.168.3.176/tienda";
        String user = "lucia";
        String pass = "hola";

        Scanner sc = new Scanner(System.in);

        try (Connection conexion = DriverManager.getConnection(url, user, pass);) {

            // Listado de los nombres de los clientes, para que el usuario elija
            System.out.println("\n--- CLIENTES ---");
            listarClientes(conexion);

            System.out.print("\nDime un nombre: ");
            String nombre = sc.nextLine();

            // Se prueba el mismo texto en las dos versiones
            System.out.println("\n=== Versión Statement ===");
            buscarStatement(conexion, nombre);

            System.out.println("\n=== Versión PreparedStatement ===");
            buscarPrepared(conexion, nombre);

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }

    // Muestra solo los nombres de los clientes, para que el usuario vea los que hay
    static void listarClientes(Connection con) throws SQLException {

        String sql = "SELECT nombre FROM cliente";

        try (Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getString("nombre"));
            }
            rs.close();
        }
    }

    // Versión 1: Statement, el nombre se concatena directamente en la consulta
    static void buscarStatement(Connection con, String nombre) throws SQLException {

        String sql = "SELECT * FROM cliente WHERE nombre = '" + nombre + "'";
        System.out.println("Consulta ejecutada: " + sql);

        try (Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            mostrarResultado(rs);
            rs.close();
        }
    }

    // Versión 2: PreparedStatement, el nombre se pasa como parámetro (?)
    static void buscarPrepared(Connection con, String nombre) throws SQLException {

        String sql = "SELECT * FROM cliente WHERE nombre = ?";
        System.out.println("Consulta ejecutada: " + sql + "  (? = '" + nombre + "')");

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            mostrarResultado(rs);
            rs.close();
        }
    }

    // Muestra todas las columnas de cada fila (así vemos qué información se extrae)
    static void mostrarResultado(ResultSet rs) throws SQLException {

        ResultSetMetaData meta = rs.getMetaData();
        int columnas = meta.getColumnCount();
        int filas = 0;

        while (rs.next()) {
            filas++;
            String linea = "";
            for (int i = 1; i <= columnas; i++) {
                linea = linea + rs.getString(i);
                if (i < columnas) {
                    linea = linea + " | ";
                }
            }
            System.out.println(linea);
        }

        if (filas == 0) {
            System.out.println("Sin resultados");
        }
    }
}