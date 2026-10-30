import java.math.BigDecimal;

import jakarta.persistence.*;
import tienda_orm.Producto;

public class App {
    public static void main(String[] args) throws Exception {

        // Factoria
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TIENDA_ORM_PU");
        
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        try{
            tx.begin();

            Producto p1 = new Producto("Teclado", new BigDecimal(29.95), 15);

            em.persist(p1); //Añade al contexto de persistencia

            tx.commit();
            System.out.println("Se ha insertado correctamente");

        } catch (Exception e) {

            if(tx.isActive()){
                tx.rollback();
            }

            e.printStackTrace();
        }
    }

    private static BigDecimal BigDecimal(double d) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'BigDecimal'");
    }
}
