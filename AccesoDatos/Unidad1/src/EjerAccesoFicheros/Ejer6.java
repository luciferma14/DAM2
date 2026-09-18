package EjerAccesoFicheros;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.util.Scanner;
public class Ejer6 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        try (
            FileWriter fw = new FileWriter("alumnos.csv");
            CSVWriter escritor = new CSVWriter(fw);
        ) {
            escritor.writeNext(new String[]{"nombre", "apellido", "edad", "nota"});

            for (int i = 0; i < 3; i++){

                System.out.print("Escribe el nombre: ");
                String nombre = scanner.nextLine();

                System.out.print("Escribe el apellido: ");
                String apellido = scanner.nextLine();

                System.out.print("Escribe la edad: ");
                int edad = scanner.nextInt();

                System.out.print("Escribe la nota: ");
                double nota = scanner.nextDouble();

                String edadString = String.valueOf(edad);
                String notaString = String.valueOf(nota);

                escritor.writeNext(new String[]{nombre, apellido, edadString, notaString});

                scanner.nextLine();
            }
            
        } catch (Exception e) {
            System.out.println("Error al escribir el fichero.");
        }
    }
}