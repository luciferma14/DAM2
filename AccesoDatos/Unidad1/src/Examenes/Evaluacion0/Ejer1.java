package Examenes.Evaluacion0;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.Pipe.SourceChannel;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class Ejer1{
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();

        try(
            FileReader fr = new FileReader("expresiones.csv");
            CSVReader lector = new CSVReaderBuilder(fr).withCSVParser(parser).build();
            FileWriter fw = new FileWriter("diccionario.txt");
        ){
            String[] datos;
            while ((datos = lector.readNext()) != null) {
            
                String expre = datos[0];
                String sign = datos[1];
                String ejem = datos[2];
                String cate = datos[3];

                fw.append("=== " + expre.toUpperCase() + " ===\n");
                fw.append("Significado: " + sign + "\n");
                fw.append("Categoria: " + cate + "\n");
                fw.append("Ejemplo: " + ejem + "\n");
                fw.append("\n");
                
            }

            System.out.println("Archivo creado correctamente");

        } catch (Exception e) {
            System.out.println("Error al leer el fichero.");
        }
    }
}