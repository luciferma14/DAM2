package EjerciciosFicheros;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Ejer9_comentado {
    public static void main(String[] args) {

    try {
        // Creamos la fabrica y el builder para poder parsear el XML
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Cargamos alumnos.xml en memoria como un arbol DOM (objeto Document)
        Document documento = builder.parse(new File("alumnos.xml"));
        // Buscamos todos los nodos <alumno> del documento
        NodeList alumnos = documento.getElementsByTagName("alumno");
        // Numero total de alumnos, para mostrarlo al final
        int cantidadAlumno = alumnos.getLength();
        

        // Recorremos la lista de alumnos con un for clasico
        for (int i = 0; i < alumnos.getLength(); i++){
            Node node = alumnos.item(i);
            // Nos aseguramos de que es un elemento real (no un salto de linea)
            if (node.getNodeType() == Node.ELEMENT_NODE){
                Element alumno = (Element) node;           
                // getAttribute() lee el valor del atributo "id" de <alumno id="...">
                String id = alumno.getAttribute("id");
                
                // Solo entramos a mostrar los datos cuando encontramos al
                // alumno cuyo id es "2" (el que pide el enunciado)
                if (id.equals("2")) {
                    // Obtenemos todos los hijos directos de este alumno
                    // (nombre, notaFinal..., y tambien los saltos de linea)
                    NodeList propiedadesAlumno = alumno.getChildNodes();

                    for (int j = 0; j < propiedadesAlumno.getLength(); j++){
                        Node n = propiedadesAlumno.item(j);
                        // Filtramos para quedarnos solo con los elementos
                        // reales, descartando los nodos de texto en blanco
                        if (n.getNodeType() == Node.ELEMENT_NODE){
                            Element e = (Element) n;
                            // Mostramos el nombre de la etiqueta y su valor
                            System.out.println(e.getNodeName()+": " + e.getTextContent());
                        }
                    }
                }
                // NOTA para el examen: este codigo, tal y como esta, solo
                // LEE y MUESTRA los datos del alumno con id=2. No modifica
                // notaFinal con setTextContent() ni elimina al alumno con
                // id=3 (con removeChild), ni guarda nada con Transformer,
                // que es lo que pedia el enunciado completo del ejercicio 9.
            }
        }
        System.out.println("El número de alumno es: " + cantidadAlumno);
    } catch (Exception e) {
        // Capturamos cualquier error al abrir o parsear el XML
        System.out.println("Error al leer el XML");
    }
    }
}
