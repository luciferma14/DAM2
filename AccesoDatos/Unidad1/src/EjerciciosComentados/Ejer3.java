package EjerciciosFicheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejer3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Contador de incidencias que ya existen en el fichero
        int contInc = 0;

        // Primero LEEMOS el fichero (si existe) para saber cuantas
        // incidencias hay ya y poder calcular el numero de la siguiente
        try (
            FileReader fr = new FileReader("incidencias.txt");
            BufferedReader lector = new BufferedReader(fr);
        ) {
            // Cada linea leida es una incidencia ya registrada
            while (lector.readLine() != null) {
                contInc++;
            }
        } catch (IOException e) {
            // Si el fichero todavia no existe (primera ejecucion), no
            // hay incidencias previas: seguimos con contInc a 0
            System.out.println("No existe el fichero todavía, se creará uno nuevo.");
        }

        System.out.println("Introduce la descripción de la incidencia: ");
        String descripcion = scanner.nextLine();

        // Ahora ESCRIBIMOS la nueva incidencia al final del fichero
        try (
            // El segundo parametro "true" indica modo AÑADIR (append):
            // no borra las incidencias anteriores
            FileWriter fw = new FileWriter("incidencias.txt", true);
            BufferedWriter escritor = new BufferedWriter(fw);
        ) {
            // El numero de la nueva incidencia es el siguiente al contador
            escritor.write("Incidencia " + (contInc + 1) + ": " + descripcion);
            escritor.newLine();

            System.out.println("Incidencia registrada correctamente.");

        } catch (IOException e) {
            System.out.println("Error al escribir el fichero.");
        }
    }
}
