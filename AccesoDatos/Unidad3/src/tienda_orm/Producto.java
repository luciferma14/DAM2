package tienda_orm;

import java.math.BigDecimal;
import jakarta.persistence.*;

@Entity 
@Table(name = "producto")
public class Producto{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int id;

    @Column (name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column (name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column (name = "stock", nullable = false) 
    private int stock;

    @OneToOne (
        mappedBy = "producto",
        cascade = CascadeType.PERSIST
    ) // Atributo producto dentro de Ficha Tecnica
    private FichaTecnica fichaTecnica;

    public Producto(){}

    public Producto(String n, BigDecimal p, int s){
        this.nombre = n;
        this.precio = p;
        this.stock = s;
    }

    public void setFichatecnica(FichaTecnica f){
        this.fichaTecnica = f;
    }

    public void setNombre(String n){
        this.nombre = n;
    }
}