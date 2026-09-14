import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;

public class LeerCSV{
    public static void main(String[] args) {
        try(
            FileReader fr = new FileReader("prueba.csv");
            CSVReader lector = new CSVReader(fr);
        ){
            String datos[];

            while ((datos = lector.readNext()) != null){
                String nombre = datos[0];
                String edad = datos[1];
                String nota = datos[2];

                int edadEntera = Integer.parseInt(edad);
                double notaDouble = Double.parseDouble(nota);

                System.out.println("Nombre: " + nombre + " | Edad: " + edadEntera + " | Nota: " + notaDouble);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}