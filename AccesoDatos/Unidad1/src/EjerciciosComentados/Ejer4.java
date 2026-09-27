package EjerciciosFicheros;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Ejer4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] nombres = new String[3];
        int[] edades = new int[3];

        // Pedimos por teclado el nombre y la edad de 3 personas
        for (int i = 0; i < 3; i++) {
            System.out.println("Introduce el nombre de la persona " + (i + 1) + ": ");
            nombres[i] = scanner.nextLine();

            System.out.println("Introduce la edad: ");
            edades[i] = Integer.parseInt(scanner.nextLine());
        }

        // --- ESCRITURA en el fichero binario ---
        try (
            // FileOutputStream abre el fichero binario para escritura
            FileOutputStream fos = new FileOutputStream("edades.dat");
            // DataOutputStream nos deja escribir tipos primitivos
            // (String con writeUTF, int con writeInt) en binario
            DataOutputStream salida = new DataOutputStream(fos);
        ) {
            // Escribimos primero los 3 nombres...
            for (int i = 0; i < 3; i++) {
                salida.writeUTF(nombres[i]);
            }
            // ...y despues las 3 edades. Hay que leerlos luego en este mismo orden
            for (int i = 0; i < 3; i++) {
                salida.writeInt(edades[i]);
            }
        } catch (IOException e) {
            System.out.println("Error al escribir el fichero.");
        }

        String[] nombresLeidos = new String[3];
        int[] edadesLeidas = new int[3];
        int suma = 0;

        // --- LECTURA del fichero binario ---
        try (
            // Volvemos a abrir el mismo fichero, ahora para lectura
            FileInputStream fis = new FileInputStream("edades.dat");
            DataInputStream entrada = new DataInputStream(fis);
        ) {
            // Leemos primero los 3 nombres (mismo orden que al escribir)
            for (int i = 0; i < 3; i++) {
                nombresLeidos[i] = entrada.readUTF();
            }
            // Luego las 3 edades, y aprovechamos para sumar para la media
            for (int i = 0; i < 3; i++) {
                edadesLeidas[i] = entrada.readInt();
                suma += edadesLeidas[i];
            }

            // Mostramos los datos recuperados
            for (int i = 0; i < 3; i++) {
                System.out.println(nombresLeidos[i] + " - " + edadesLeidas[i]);
            }

            // Dividimos entre 3.0 (double) para que la media no se trunque como entero
            double media = suma / 3.0;
            System.out.println("La media de edad es: " + media);

        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
        }
    }
}
