package EjerAccesoFicheros;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Ejer9 {

    public static void main(String[] args) {
        
        try {
            // Crear los recursos necesarios para crear el parser
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Crear la estructura de arbol
            Document documento = builder.parse(new File("alumnos.xml"));
            NodeList alumnos = documento.getElementsByTagName("alumnos");

            for(int i = 0; i < alumnos.getLength(); i++){
                Node alumno = alumnos.item(i);

                if(alumno.getNodeType() == Node.ELEMENT_NODE){
                    Element alumElem = (Element) alumno;
                    NodeList propiedadesAlum = alumElem.getChildNodes();
                    
                    Element alum = (Element) alumno;
                    String id = alumElem.getAttribute("id");

                    if (id.equals("2")){
                        alumElem.setAttribute("nota","9");   
                    }

                    for(int j = 0; j < propiedadesAlum.getLength(); j++){
                        Node n = propiedadesAlum.item(j);
                        if(n.getNodeType() == Node.ELEMENT_NODE){
                            Element e = (Element) n;
                            System.out.println(e.getNodeName() + ": " + e.getTextContent());
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error al leer el XML");
        }

    }
}