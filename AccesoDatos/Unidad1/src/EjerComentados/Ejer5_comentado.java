package EjerComentados;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.FileReader;

public class Ejer5_comentado {
    public static void main(String[] args) {
        // CSVParserBuilder construye un parser indicando que caracter
        // se debe usar como separador de campos. Aqui se configura ';'
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
        try (
            // FileReader abre el fichero CSV como si fuera un fichero de texto normal
            FileReader fr = new FileReader("alumnos_comas.csv");
            // CSVReaderBuilder crea el CSVReader indicandole que use
            // el parser que acabamos de configurar (el de ';') en lugar
            // del parser por defecto de OpenCSV (que usa ',')
            CSVReader lector = new CSVReaderBuilder(fr).withCSVParser(parser).build();
        ) {
            // Cada posicion del array sera un campo del registro leido
            String[] datos;

            // readNext() devuelve un array de Strings con los campos de
            // cada linea, o null cuando se llega al final del fichero
            while ((datos = lector.readNext()) != null) {
                // Recorremos todos los campos del registro y los mostramos
                for (String dato : datos) {
                    System.out.print(dato + " | ");
                }
                System.out.println();
            }
        } catch (Exception e) {
            // Capturamos cualquier error al abrir o leer el fichero
            System.out.println("Error al leer el fichero.");
        }
    }
}
