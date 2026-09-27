package EjerciciosFicheros;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Ejer10 {
    public static void main(String[] args) {
        try {
            // Fabrica y builder necesarios para trabajar con DOM
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // A diferencia de leer un XML, aqui creamos un Document VACIO,
            // que iremos rellenando nosotros mismos desde cero
            Document document = builder.newDocument();

            // Creamos el elemento raiz <productos> y lo enlazamos al documento
            Element productos = document.createElement("productos");
            document.appendChild(productos);

            // Creamos y añadimos los dos productos que pide el enunciado
            crearProducto(document, productos, "P001", "Teclado mecánico", "59.90", "25");
            crearProducto(document, productos, "P002", "Ratón inalámbrico", "24.50", "40");

            // Guardamos todo el Document en el fichero productos.xml
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult("productos.xml");
            transformer.transform(source, result);

            System.out.println("Fichero productos.xml generado correctamente.");

        } catch (Exception e) {
            System.out.println("Error al crear el XML");
        }
    }

    // Metodo de apoyo para no repetir el mismo codigo por cada producto:
    // crea el bloque <producto> completo y lo cuelga de <productos>
    private static void crearProducto(Document document, Element productos, String codigo, String nombre, String precio, String stock) {
        // Creamos el elemento <producto> y le añadimos el atributo codigo
        Element producto = document.createElement("producto");
        producto.setAttribute("codigo", codigo);

        // Creamos los sub-elementos con su valor de texto
        Element nombreElem = document.createElement("nombre");
        nombreElem.setTextContent(nombre);

        Element precioElem = document.createElement("precio");
        precioElem.setTextContent(precio);

        Element stockElem = document.createElement("stock");
        stockElem.setTextContent(stock);

        // Enlazamos los sub-elementos dentro de <producto>
        producto.appendChild(nombreElem);
        producto.appendChild(precioElem);
        producto.appendChild(stockElem);

        // Enlazamos el <producto> ya completo dentro de <productos>
        productos.appendChild(producto);
    }
}
