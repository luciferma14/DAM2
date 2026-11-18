import java.util.*;
import jakarta.persistence.*;
import tienda_orm.Producto;

public class App3{

    static Scanner sc = new Scanner(System.in).useLocale(Locale.US);
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TIENDA_ORM_PU");
        EntityManager em = emf.createEntityManager();
        
        Producto p = em.createQuery("SQL", Producto.class)
        .getSingleResult();

        String sql = "SELECT p FROM producto p WHERE p.precio = :precio";
        List<Producto> listaP = em.createNativeQuery(sql, Producto.class)
        .setParameter("precio", 25.49)
        .getResultList();

        String sql2 = "SELECT * FROM producto WHERE precio = :precio";
        List<Producto> lista = em.createNativeQuery(sql2, Producto.class)
        .setParameter("precio", 25.49)
        .getResultList();
    }
}