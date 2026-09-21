package EjerAccesoFicheros;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public  class Ejer8{
    public static void main(String[] args) {
        
        try {
            // Crear los recursos necesarios para crear el parser
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Crear la estructura de arbol
            Document documento = builder.parse(new File("libros.xml"));
            NodeList libros = documento.getElementsByTagName("libro");
            int cont = 0;

            for(int i = 0; i < libros.getLength(); i++){
                Node libro = libros.item(i);
                if(libro.getNodeType() == Node.ELEMENT_NODE){
                    Element libroElem = (Element) libro;
                    NodeList propiedadesLibro = libroElem.getChildNodes();

                    Node titulo = propiedadesLibro.item(0);
                    Node autor = propiedadesLibro.item(1);
                    Node precio = propiedadesLibro.item(2);
                    
                    System.out.println(titulo.getNodeName() + ": " + titulo.getTextContent());
                    System.out.println(autor.getNodeName() + ": " + autor.getTextContent());
                    System.out.println(precio.getNodeName() + ": " + precio.getTextContent());
                    cont++;
                }
            }

            System.out.println("Número total de libros: " + cont);

        } catch (Exception e) {
            System.out.println("Error al leer el XML");
        }
    }
}