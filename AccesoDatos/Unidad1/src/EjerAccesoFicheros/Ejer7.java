package EjerAccesoFicheros;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Ejer7 {

    public static void main(String[] args) {
        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();

        try (
            FileReader fr = new FileReader("productos.csv");
            CSVReader lector = new CSVReaderBuilder(fr).withCSVParser(parser).build();
            
            FileWriter fw = new FileWriter("productos_puntoycoma.csv");
            CSVWriter escritor = new CSVWriter(fw, ';', CSVWriter.DEFAULT_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
        ) {
            String[] datos;
            while ((datos = lector.readNext()) != null) {
                escritor.writeNext(datos);
            }
            
        } catch (Exception e) {
            System.out.println("Error al procesar el fichero.");
        }
    }
    
}