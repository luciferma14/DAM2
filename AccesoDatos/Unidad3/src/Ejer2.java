import jakarta.persistence.*;
import catalogo_libros.*;

public class Ejer2 {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_EDITORIAL");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Libro libro = new Libro("El nombre del viento", "Patrick Rothfuss", "Plaza & Janés");
            FichaBibliografica ficha = new FichaBibliografica("1234567890", 880, "Español");

            // Relacionamos los dos objetos (se actualizan ambos lados)
            libro.setFichaBibliografica(ficha);

            // Solo hacemos persist del libro: la cascada guarda también la ficha
            em.persist(libro);

            tx.commit();

            System.out.println("El libro y su ficha bibliográfica se han guardado correctamente.");

        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.out.println("Error al guardar los datos: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }
}