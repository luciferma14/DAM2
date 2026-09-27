import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Ejer1_comentado {
 public static void main(String[] args) {

    // try-with-resources: fr y lector se cerraran automaticamente al
    // salir del bloque try, tanto si todo va bien como si salta una excepcion
    try (
        // FileReader abre el fichero de texto para poder leer sus caracteres
        FileReader fr = new FileReader("alumnos.txt");
        // BufferedReader envuelve al FileReader para poder leer comodamente
        // linea a linea con la funcion readLine()
        BufferedReader lector = new BufferedReader(fr);
    ) {
        // Contador de lineas leidas
        int contLin = 0;
        // Contador de caracteres leidos (sin contar saltos de linea)
        int contCaract = 0;
        // Aqui se ira guardando cada linea que leamos
        String linea;

        // readLine() devuelve la siguiente linea del fichero, o null
        // cuando ya no quedan mas lineas (fin de fichero)
        while ((linea = lector.readLine()) != null) {
            // Mostramos la linea leida por consola
            System.out.println(linea);
            // Cada vuelta del bucle hemos leido una linea mas
            contLin++;
            // length() nos da el numero de caracteres de esa linea.
            // Como readLine() ya quita el salto de linea del final,
            // estos caracteres no se estan contando
            contCaract += linea.length();            
        }
        
        // Mostramos el resumen final con los dos contadores
        System.out.println("Tiene "+ contLin + " lineas y " + contCaract + " caracteres");

    } catch (IOException e) {
        // Si el fichero no existe o hay algun problema al leerlo,
        // capturamos la excepcion y avisamos por consola
        System.out.println("Error al leer el fichero.");
    }
    }
}
