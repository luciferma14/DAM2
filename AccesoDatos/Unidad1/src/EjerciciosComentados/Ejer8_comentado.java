package EjerciciosComentados;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Ejer8_comentado {
    public static void main(String[] args) {

    try {
        // Creamos la fabrica y el builder necesarios para poder
        // parsear (leer) un documento XML
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // parse() carga libros.xml en memoria como un arbol DOM y nos
        // devuelve el objeto Document que representa ese arbol
        Document documento = builder.parse(new File("libros.xml"));
        // Buscamos en todo el documento todos los nodos <libro>
        NodeList libros = documento.getElementsByTagName("libro");
        // getLength() nos da directamente cuantos libros hay en total
        int cantidadLibros = libros.getLength();
        

        // Recorremos la lista de libros con un for clasico, porque
        // NodeList no implementa Iterable (no vale un for-each con iterador)
        for (int i = 0; i < libros.getLength(); i++){
            Node node = libros.item(i);
            // Comprobamos que es un ELEMENT_NODE y no un salto de linea/
            // espacio en blanco, que tambien cuenta como nodo hijo
            if (node.getNodeType() == Node.ELEMENT_NODE){
                // Convertimos el Node a Element para poder trabajar mejor con el
                Element libroElem = (Element) node;
                // getChildNodes() nos da TODOS los hijos directos de <libro>
                // (titulo, autor, precio... pero tambien los saltos de linea)
                NodeList propiedadesLibro = libroElem.getChildNodes();
                
                // Recorremos esos hijos uno a uno
                for (int j = 0; j < propiedadesLibro.getLength(); j++){
                    Node n = propiedadesLibro.item(j);
                    // Filtramos otra vez para quedarnos solo con los
                    // elementos reales (titulo, autor, precio), descartando
                    // los nodos de texto/espacios en blanco entre etiquetas
                    if (n.getNodeType() == Node.ELEMENT_NODE){
                        Element e = (Element) n;
                        // getNodeName() da el nombre de la etiqueta (p.ej. "titulo")
                        // getTextContent() da el valor que contiene (p.ej. "1984")
                        System.out.println(e.getNodeName()+": " + e.getTextContent());
                    }
                }

                //Opción 2: Sacar cada nodo del libro con la función GetElementByTagsName

                // NodeList tituloLista = libroElem.getElementsByTagName("titulo");
                // Element titulo = (Element) tituloLista.item(0);
                // System.out.println(titulo.getNodeName() + ": " + titulo.getTextContent());
                // etc
            }
        }
        // Mostramos el total de libros calculado al principio con getLength()
        System.out.println("El número de libros es: " + cantidadLibros);
    } catch (Exception e) {
        // Capturamos cualquier error al abrir o parsear el XML
        System.out.println("Error al leer el XML");
    }
    }
}
