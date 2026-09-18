package EjerAccesoFicheros;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.FileReader;

public class Ejer5 {
    public static void main(String[] args) {
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
        try (
            FileReader fr = new FileReader("alumnos_puntoycoma.csv");
            CSVReader lector = new CSVReaderBuilder(fr).withCSVParser(parser).build();
        ) {
            String[] datos;
            while ((datos = lector.readNext()) != null) {
                for (String dato : datos) {
                    System.out.print(dato + " | ");
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Error al leer el fichero.");
        }
    }
}