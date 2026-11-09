import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.Scanner;


public class Ejer1{
    
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        String url = "jdbc:mysql://192.168.3.176/festival_musica";
        String user = "lucia";
        String pass = "hola";

    
        sc.useLocale(Locale.US); // los decimales se escriben con punto: 25.50

        try (Connection conexion = DriverManager.getConnection(url, user, pass);){
        
            int opcion = -1;

            while(opcion != 0){
                System.out.println();
                System.out.println("¡Bienvenidos al ADfest 2026!");
                System.out.println("1. Consultar grupos por género musical");
                System.out.println("2. Comprar entradas para el festival");
                System.out.println("5. Solicitar reembolso y devolver entradas");
                System.out.println("0. Salir");
                System.out.print("Elige una opción: ");
                opcion = sc.nextInt();

                try {

                    switch (opcion) {
                        case 1:
                            
                            consultarGrupos(conexion, sc);
                            break;
                        case 2:
                            System.out.print("Dime tu DNI: ");
                            String dni = sc.next();
                            System.out.print("Cantidad de entradas ha comprar: ");
                            int cantidadEntradas = sc.nextInt();

                            comprarEntradas(conexion, dni, cantidadEntradas);
                            break;
                        case 3:
                            //reembolsoDevolver(conexion);
                            //break;
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

    static void consultarGrupos(Connection con, Scanner sc) throws SQLException {
        System.out.print("Dime un género musical: ");
        String genero = sc.next();

        String sql = "SELECT G.nombre AS nomGru, E.nombre AS nomEsc, A.orden FROM Grupo_Musical G, Actuacion A, Escenario E WHERE genero = ? AND G.id = A.id_grupo AND A.id_escenario = E.id";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, genero);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println(rs.getString("nomGru") + " | "
                        + genero + " | "
                        + rs.getString("nomEsc") + " | "
                        + rs.getInt("orden"));
            }
            rs.close();
        }
    }

    static void comprarEntradas(Connection con, String dni, int cantidadEntradas) throws SQLException {

       if (dni.length() < 9) {
            System.out.println("El DNI está incompleto");
            return;
        }
        if (cantidadEntradas <= 0) {
            System.out.println("La cantidad tiene que ser superior a 0");
            return;
        }

        con.setAutoCommit(false);
        
        for(int i = 0; i < cantidadEntradas; i++){

            String sql = "INSERT INTO Entrada (codigo, asistente, tipo, precio, dni_comprador) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement pstmt = con.prepareStatement(sql)){
                
                System.out.print("Dime el código de la entrada: ");
                String codigo = sc.next();
                
                if ((codigo.length() == 6) && (codigo.startsWith("ENT"))){
                    System.out.print("Dime el nombre del asistente: ");
                    String nombre = sc.nextLine();
                    sc.nextLine();
                    System.out.print("Tipo de entrada (General / VIP): ");
                    String tipoEntrada = sc.next();
                    double precio = 0.0;

                    if (tipoEntrada == "General" || tipoEntrada == "VIP") {
                        if (tipoEntrada == "General"){
                            precio = 75.00;
                        }else{
                            precio = 120.00;
                        }

                        pstmt.setString(1, codigo);
                        pstmt.setString(2, nombre);
                        pstmt.setString(3, tipoEntrada);
                        pstmt.setDouble(4, precio);
                        pstmt.setString(5, dni);

                        ResultSet rs = pstmt.executeQuery();

                        while(rs.next()){
                            System.out.println("Insertado correctamente");
                        }
                                                
                    }else{
                        System.out.println("Tipo de entrada no válido");
                    }
                }else{
                    System.out.println("Código mal introducido");
                }
        
                con.commit();
                System.out.println("\nEntradas compradas correctamente");

            } catch (SQLException e) {
                // Si algo falla, deshacemos lo que se haya hecho
                con.rollback();
                System.out.println("\nError: " + e.getMessage());
                System.out.println("Se han deshecho las operaciones (rollback)");
            }
        }
        con.setAutoCommit(true);

    }
}