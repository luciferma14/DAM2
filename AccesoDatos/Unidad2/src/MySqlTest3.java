import java.sql.Statement;
import java.util.Locale;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlTest3 {

    static Scanner teclado = new Scanner(System.in).useLocale(Locale.US);
    public static void main(String[] args) {
        
        String url = "jdbc:mysql://192.168.3.176/tienda_completa";
        String user = "lucia";
        String pass = "hola";

        try (
            Connection conexion = DriverManager.getConnection(url, user, pass);
            Statement stmt = conexion.createStatement();
            PreparedStatement pstmt = conexion.prepareStatement("SELECT * FROM cliente WHERE nombre = ?");
        ){
            System.out.println("Dime tu nombre para conocer tus datos: ");
            String nombre = teclado.nextLine();

            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String nombreC = rs.getString("nombre");
                String email = rs.getString("email");
                String ciudad = rs.getString("ciudad");

                System.out.println("ID: " + id + "\nNombre: " + nombreC + "\nEmail: " + email + "\nCiudad: " + ciudad);
            }

            System.out.println("Conexión realizada correctamente");
        } catch (SQLException e) {
            System.out.println("Error al conectar con MySQL");
        }
    }
}
