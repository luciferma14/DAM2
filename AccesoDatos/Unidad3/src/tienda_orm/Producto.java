package tienda_orm;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity 
@Table(name = "producto")
public class Producto{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int id;

    @Column (name = "nomnre", nullable = false, length = 50)
    private String nombre;

    @Column (name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column (name = "stock", nullable = false) 
    private int stock;

    public Producto(){}

    public Producto(String n, BigDecimal p, int s){
        this.nombre = n;
        this.precio = p;
        this.stock = s;
    }
}