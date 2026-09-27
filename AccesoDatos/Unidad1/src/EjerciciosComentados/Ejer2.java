package EjerciciosFicheros;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejer2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // try-with-resources: fw y escritor se cerraran solos al salir del try
        try (
            // El segundo parametro "false" hace que se SOBRESCRIBA el
            // fichero (se borra el contenido anterior). Si no existe, se crea
            FileWriter fw = new FileWriter("notas.txt", false);
            // BufferedWriter añade la funcion newLine() y hace la
            // escritura mas eficiente
            BufferedWriter escritor = new BufferedWriter(fw);
        ) {
            // Repetimos 5 veces para pedir los datos de 5 alumnos
            for (int i = 0; i < 5; i++) {
                System.out.println("Introduce el nombre del alumno: ");
                String nombre = scanner.nextLine();

                System.out.println("Introduce la nota: ");
                String nota = scanner.nextLine();

                // Escribimos la linea con el formato pedido: "Nombre: X - Nota: Y"
                escritor.write("Nombre: " + nombre + " - Nota: " + nota);
                // Saltamos de linea para que el siguiente alumno vaya aparte
                escritor.newLine();
            }

            System.out.println("Fichero notas.txt creado correctamente");

        } catch (IOException e) {
            // Capturamos cualquier error al escribir el fichero
            System.out.println("Error al escribir el fichero.");
        }
    }
}
