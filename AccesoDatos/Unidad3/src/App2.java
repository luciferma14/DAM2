import java.math.BigDecimal;
import jakarta.persistence.*;
import tienda_orm.FichaTecnica;
import tienda_orm.Producto;

public class App2 {
    public static void main(String[] args) throws Exception {

        // Factoria
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TIENDA_ORM_PU");
        
        EntityManager em = emf.createEntityManager();

        Producto p1 = em.find(Producto.class,1);

        em.close();

        try{
            tx.begin();

           
            
            

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