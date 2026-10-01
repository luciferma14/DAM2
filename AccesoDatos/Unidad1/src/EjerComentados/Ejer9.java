package EjerComentados;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Ejer9 {
    public static void main(String[] args) {

    try {
        // Creamos la fabrica y el builder para poder parsear el XML
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Cargamos alumnos.xml en memoria como un arbol DOM (objeto Document)
        Document documento = builder.parse(new File("alumnos.xml"));
        // Buscamos todos los nodos <alumno> del documento
        NodeList alumnos = documento.getElementsByTagName("alumno");

        // Aqui guardaremos el alumno con id=3. No lo eliminamos dentro del
        // bucle porque alumnos es una lista "viva": si quitamos un nodo
        // mientras la recorremos, la lista cambia de tamaño a mitad del for
        Element alumnoAEliminar = null;

        // Recorremos la lista de alumnos con un for clasico
        for (int i = 0; i < alumnos.getLength(); i++){
            Node node = alumnos.item(i);
            // Nos aseguramos de que es un elemento real (no un salto de linea)
            if (node.getNodeType() == Node.ELEMENT_NODE){
                Element alumno = (Element) node;
                // getAttribute() lee el valor del atributo "id" de <alumno id="...">
                String id = alumno.getAttribute("id");

                // ---- Alumno con id=2: modificar su nota ----
                if (id.equals("2")) {
                    // Recorremos los hijos de este alumno (nombre, curso, nota)
                    NodeList propiedadesAlumno = alumno.getChildNodes();

                    for (int j = 0; j < propiedadesAlumno.getLength(); j++){
                        Node n = propiedadesAlumno.item(j);
                        // Filtramos los nodos de texto en blanco entre etiquetas
                        if (n.getNodeType() == Node.ELEMENT_NODE){
                            Element e = (Element) n;
                            // En alumnos.xml la etiqueta se llama <nota>
                            // (no <notaFinal>). Cuando la encontramos,
                            // cambiamos su texto a 9.0 (solo en memoria)
                            if (e.getNodeName().equals("nota")) {
                                e.setTextContent("9.0");
                            }
                        }
                    }
                }

                // ---- Alumno con id=3: guardarlo para eliminarlo despues ----
                if (id.equals("3")) {
                    alumnoAEliminar = alumno;
                }
            }
        }

        // Eliminamos al alumno con id=3. removeChild() se llama sobre el
        // nodo PADRE (<alumnos>), que obtenemos con getParentNode()
        if (alumnoAEliminar != null) {
            alumnoAEliminar.getParentNode().removeChild(alumnoAEliminar);
        }

        // Hasta aqui todos los cambios estan solo en memoria. Para que
        // lleguen a un fichero hay que escribir el Document con un Transformer
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        // DOMSource envuelve el Document modificado...
        DOMSource source = new DOMSource(documento);
        // ...y StreamResult indica el fichero NUEVO donde se guarda
        // (asi alumnos.xml original no se toca)
        StreamResult result = new StreamResult("alumnos_modificados.xml");

        // transform() vuelca el contenido del DOM al fichero indicado
        transformer.transform(source, result);

        System.out.println("Fichero alumnos_modificados.xml generado correctamente");
    } catch (Exception e) {
        System.out.println("Error al modificar el XML: " + e);
    }
    }
}