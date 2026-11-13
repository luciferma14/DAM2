package tienda_orm;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

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

    // Atributo producto dentro de Ficha Tecnica
    @OneToOne (mappedBy = "producto", cascade = CascadeType.PERSIST) 
    private FichaTecnica fichaTecnica;

    @ManyToMany
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    @ManyToMany 
    @JoinTable (
        name = "producto_label",
        joinColumns = @JoinColumn(name ="producto_id"),
        inverseJoinColumns = @JoinColumn(name = "id_label")
    )
    private Set<Label> etiquetas = new HashSet<>();

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