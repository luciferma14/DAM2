package EjerAccesoFicheros;

import java.io.BufferedReader;
import java.io.FileReader;

public class Ejer1{
    public static void main(String[] args) {
        
        try(
            FileReader fr = new FileReader("alumnos.txt");
            BufferedReader bf = new BufferedReader(fr);
        ){
            String linea;
            int numLineas = 0;
            int numCaracteres = 0;
            while((linea = bf.readLine()) != null){
                numLineas++;
                for(int i = 0; i < linea.length(); i++){
                        
                    numCaracteres++;    
                }
                System.out.println(linea);
            }

            System.out.println("Número de líneas: " + numLineas);
            System.out.println("Número de caracteres " + numCaracteres);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}