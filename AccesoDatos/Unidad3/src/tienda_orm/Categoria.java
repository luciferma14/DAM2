package tienda_orm;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @OneToMany (mappedBy = "categoria")
    private List<Producto> productos = new ArrayList();

    public Categoria() {}

    public Categoria(String nombre) {
        this.nombre = nombre;
    }
    
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}