package EjerciciosFicheros;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Ejer7 {
    public static void main(String[] args) {
        try (
            // Lector del CSV original: usa ',' por defecto (delimitador de por defecto de OpenCSV)
            FileReader fr = new FileReader("productos.csv");
            CSVReader lector = new CSVReader(fr);

            // Escritor del nuevo fichero
            FileWriter fw = new FileWriter("productos_puntoycoma.csv");
            // Usamos el constructor completo de CSVWriter para cambiar
            // solo el separador a ';', dejando el resto de parametros
            // con los valores por defecto de la propia clase
            CSVWriter escritor = new CSVWriter(
                fw,
                ';',
                CSVWriter.DEFAULT_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END
            );
        ) {
            String[] datos;

            // Leemos cada registro del CSV original (array de Strings)...
            while ((datos = lector.readNext()) != null) {
                // ...y lo escribimos tal cual en el nuevo fichero. Es
                // el propio CSVWriter el que coloca el ';' entre campos
                escritor.writeNext(datos);
            }

            System.out.println("Fichero productos_puntoycoma.csv generado correctamente.");

        } catch (Exception e) {
            System.out.println("Error al convertir el fichero.");
        }
    }
}
