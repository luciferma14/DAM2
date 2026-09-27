package EjerciciosFicheros;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class Ejer6_comentado {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try (
            // FileWriter abre (o crea) alumnos.csv para escribir en el
            FileWriter fw = new FileWriter("alumnos.csv");
            // CSVWriter, creado con su constructor simple, escribe por
            // defecto los campos separados por ',' y entre comillas
            CSVWriter escritor = new CSVWriter(fw)
        ) {
            // Escribimos la linea de cabecera con el nombre de cada columna.
            // writeNext() recibe un array de Strings y escribe una linea completa
            escritor.writeNext(new String[]{"nombre", "apellido", "edad", "nota"});

            System.out.println("Escribe los datos del CSV");

            // Repetimos 3 veces para pedir los datos de 3 alumnos
            for (int i = 0; i < 3; i++) {
                System.out.println("Introduce el nombre: ");
                String nombre = scanner.nextLine();

                System.out.println("Introduce el apellido: ");
                String apellido = scanner.nextLine();

                System.out.println("Introduce la edad: ");
                String edad = scanner.nextLine();

                System.out.println("Introduce la nota: ");
                String nota = scanner.nextLine();

                // Escribimos el registro completo del alumno en el CSV
                escritor.writeNext(new String[]{nombre, apellido, edad, nota});
                
                // OJO para el examen: esta llamada extra consume una linea
                // de entrada de mas en cada vuelta del bucle (aparte de las
                // 4 que ya se han leido arriba), asi que en la practica
                // "roba" la primera respuesta del siguiente alumno.
                scanner.nextLine();
            }
        } catch (Exception e) {
            // Capturamos cualquier error al escribir el fichero
            System.out.println("Error al escribir el fichero.");
        }
    }
}
