package UD2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Scanner;

public class Ejer5{

    public static void main(String[] args) {

        String url = "jdbc:mysql://192.168.3.176/banco";
        String user = "lucia";
        String pass = "hola";

        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.US); // los decimales se escriben con punto: 25.50

        try (Connection conexion = DriverManager.getConnection(url, user, pass);){
        
            int opcion = -1;

            while(opcion != 0){
                System.out.println();
                System.out.println("=======MENÚ=======");
                System.out.println("1. Comprobar autocommit");
                System.out.println("2. Activar autocommit");
                System.out.println("3. Desactivar autocommit");
                System.out.println("4. Realizar un ingreso");
                System.out.println("5. Realizar una retirada");
                System.out.println("6. Consultar el saldo");
                System.out.println("7. Hacer el commit de las operaciones");
                System.out.println("0. Salir");
                System.out.print("Elige una opción: ");
                opcion = sc.nextInt();

                try {

                    switch (opcion) {
                        case 1:
                            comprobarAutocommit(conexion);
                            break;
                        case 2:
                            activarAutocommit(conexion);
                            break;
                        case 3:
                            desactivarAutocommit(conexion);
                            break;
                        case 4:
                            ingresar(conexion, sc);
                            break;
                        case 5:
                            retirar(conexion, sc);
                            break;
                        case 6:
                            consultarSaldo(conexion, sc);
                            break;
                        case 7:
                            hacerCommit(conexion);
                            break;

                        default:
                            break;
                    }
                    

                } catch (SQLException e) {
                    System.out.println("Error de base de datos: " + e.getMessage());
                }
            }

        }catch(SQLException e){
            System.out.println("No se pudo conectar: " + e.getMessage());
        }

        sc.close();
    }

    static void comprobarAutocommit(Connection con) throws SQLException{
        if (con.getAutoCommit()){
            System.out.println("El autocommit está activado");
        }else{
            System.out.println("El autocommit está desactivado");
        }
    }

    static void activarAutocommit(Connection con) throws SQLException{
        con.setAutoCommit(true);
        System.out.println("El autocommit ha sido activado");
    }

    static void desactivarAutocommit(Connection con) throws SQLException{
        con.setAutoCommit(false);
        System.out.println("El autocommit ha sido desactivado");
    }

    static void ingresar(Connection con, Scanner sc) throws SQLException{
        
        System.out.print("ID de la cuenta: ");
        int id = sc.nextInt();
        System.out.print("Cantidad a ingresar: ");
        double cantidad = sc.nextDouble();

        String sql = "UPDATE cuenta SET saldo = saldo + ? WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)){
            
            ps.setDouble(1, cantidad);
            ps.setInt(2, id);

            int filas = ps.executeUpdate();

            if(filas > 0){
                System.out.println("Se ha ingresado correctamente");
            }else{
                System.out.println("No existe ninguna cuenta con ese ID");
            }
        }
    }

    static void retirar(Connection con, Scanner sc) throws SQLException{

        System.out.print("ID de la cuenta: ");
        int id = sc.nextInt();
        System.out.print("Cantidad a retirar: ");
        double cantidad = sc.nextDouble();

        String sql = "UPDATE cuenta SET saldo = saldo - ? WHERE id = ? AND saldo >= ?";

        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setDouble(1, cantidad);
            ps.setInt(2, id);
            ps.setDouble(3, cantidad);

            int filas = ps.executeUpdate();

            if(filas > 0){
                System.out.println("Se ha retirado correctamente");
            }else{
                System.out.println("No existe ninguna cuenta con ese ID o no hay saldo suficiente");
            }
        }
    }

    static void consultarSaldo(Connection con, Scanner sc) throws SQLException{
        
        System.out.print("ID de la cuenta: ");
        int id = sc.nextInt();

        String sql = "SELECT saldo FROM cuenta WHERE id = ?";

        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                System.out.println("Saldo de la cuenta " + id + ": " + rs.getDouble("saldo") + " €");
            } else {
                System.out.println("No existe ninguna cuenta con ese ID");
            }
            rs.close();
        }
    }

    static void hacerCommit(Connection con) throws SQLException{
        if (con.getAutoCommit()){
            System.out.println("El autocommit está activado, no hace falta hacer commit");
        }else{
            con.commit();
            System.out.println("Commit realizado correctamente");
        }
    }
}