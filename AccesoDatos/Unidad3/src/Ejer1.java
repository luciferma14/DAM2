import java.math.BigDecimal;
import java.time.LocalDate;
import clinica_veterinaria.*;
import jakarta.persistence.*;

public class Ejer1 {

    public static void main(String[] args) {

        // Fábrica de gestores de entidades con la unidad de persistencia del persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_CLINICA_VETERINARIA");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // Un objeto de cada entidad (aún están en estado Transient)
            Propietario propietario = new Propietario("Ana", "López García", "12345678A", "600123456", "ana.lopez@correo.com");

            Animal animal = new Animal("Toby", "Perro", "Labrador", LocalDate.of(2020, 5, 14), new BigDecimal("28.50"));

            Veterinario veterinario = new Veterinario("Pedro", "Ruiz Martín", "COL-12345", "Cirugía", new BigDecimal("2100.00"));

            Consulta consulta = new Consulta(LocalDate.of(2026, 11, 9), "Revisión anual", "Animal sano", new BigDecimal("35.00"));

            // persist() los pasa a estado Managed
            em.persist(propietario);
            em.persist(animal);
            em.persist(veterinario);
            em.persist(consulta);

            tx.commit();

            System.out.println("Los datos se han guardado correctamente.");

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
