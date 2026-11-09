import java.math.BigDecimal;
import jakarta.persistence.*;
import tienda_orm.FichaTecnica;
import tienda_orm.Producto;

public class App {
    public static void main(String[] args) throws Exception {

        // Factoria
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TIENDA_ORM_PU");
        
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        try{
            tx.begin();

            //Producto p1 = new Producto("Teclado", new BigDecimal(29.95), 15); // Crear uno nuevo
            Producto p1 = em.find(Producto.class,1); // Coger uno que ya existe
            FichaTecnica f1 = new FichaTecnica(new BigDecimal(0.47), "Chapa", 2, p1);
            p1.setNombre("Teclado");
            em.persist(p1); //Añade al contexto de persistencia
            //em.persist(f1);
            

            tx.commit();
            System.out.println("Se ha insertado correctamente");

        } catch (Exception e) {

            if(tx.isActive()){
                tx.rollback();
            }

            e.printStackTrace();
        }
    }
}