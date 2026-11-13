package tienda_orm;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;

@Entity
    public class Label {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @ManyToMany (mappedBy = "etiquetas")
    private Set<Producto> productos = new HashSet<>();

    public Label() {}

    public Label(String nombre) { this.nombre = nombre; }
    
    public int getId() { return id;}
    public String getNombre() { return nombre; }
}