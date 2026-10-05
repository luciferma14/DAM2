import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlTest {
    public static void main(String[] args) {
        
        String url = "jdbc:mysql://192.168.3.176/prueba";
        String user = "lucia";
        String pass = "hola";

        try (
            Connection conexion = DriverManager.getConnection(url, user, pass);
            Statement stmt = conexion.createStatement();
        ){
            String sql = "SELECT * FROM tabla";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                int id = rs.getInt(1);
                String nombre = rs.getString("nombre");

                System.out.println("Hola, tu id es: " + id + " y tu nombre es: " + nombre);
            }

            System.out.println("Conexión realizada correctamente");
        } catch (SQLException e) {
            System.out.println("Error al conectar con MySQL");
        }
    }
}
