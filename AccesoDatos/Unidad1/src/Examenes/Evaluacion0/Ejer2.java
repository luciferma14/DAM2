package Examenes.Evaluacion0;

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

public class Ejer2{
    public static void main(String[] args) {
        
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
    
            Document documento = builder.parse(new File("videojuegos.xml"));
            NodeList videojuegos = documento.getElementsByTagName("videojuego");
            Element catalogo = documento.createElement("catalogo");
        
            for (int i = 0; i < videojuegos.getLength(); i++){
                Node videojuego = videojuegos.item(i);

                if (videojuego.getNodeType() == Node.ELEMENT_NODE){
                    Node titulo = videojuego.getFirstChild();

                    if (titulo.getTextContent().equals("Hades")) {
                        Element elem = (Element) videojuego;
                        elem.setAttribute("plataforma", "Multiplataforma");
                    }
                }
            }
            
            documento.appendChild(catalogo);

            crearVideojuego(documento, catalogo);
    
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
    
            DOMSource source = new DOMSource(documento);
            StreamResult result = new StreamResult("videojuegos_modificados.xml");
    
            transformer.transform(source, result);
    
            System.out.println("Fichero videojuegos_modificados.xml generado correctamente");
        } catch (Exception e) {
            System.out.println("Error al modificar el XML: " + e);
        }
    }

    private static void crearVideojuego(Document document, Element catalogo) {
        // Creamos el elemento <producto> y le añadimos el atributo codigo
        Element videoJuego = document.createElement("videojuego");
        videoJuego.setAttribute("id", "V004");
        videoJuego.setAttribute("plataforma", "Switch");

        // Creamos los sub-elementos con su valor de texto
        Element titulo = document.createElement("titulo");
        titulo.setTextContent("The Legend of Zelda: Tears of the Kingdom");

        Element genero = document.createElement("genero");
        genero.setTextContent("Aventura");

        Element desarrollador = document.createElement("desarrollador");
        
        Element nombreDes = document.createElement("nombre");
        nombreDes.setTextContent("Nintendo");

        Element paisDes = document.createElement("pais");
        paisDes.setTextContent("Japón");

        // Enlazamos los sub-elementos dentro de <producto>
        videoJuego.appendChild(titulo);
        videoJuego.appendChild(genero);
        videoJuego.appendChild(desarrollador);
        desarrollador.appendChild(nombreDes);
        desarrollador.appendChild(paisDes);

        // Enlazamos el <producto> ya completo dentro de <productos>
        catalogo.appendChild(videoJuego);
    }

}