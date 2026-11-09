package academia;

import java.math.BigDecimal;
import jakarta.persistence.*;

// Relación unidireccional: Curso no tiene ningún atributo Alumno
@Entity
@Table(name = "curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "codigo", nullable = false, unique = true, length = 15)
    private String codigo;

    @Column(name = "horas", nullable = false)
    private int horas;

    @Column(name = "precio", nullable = false, precision = 8, scale = 2)
    private BigDecimal precio;

    // Constructor sin parámetros (obligatorio para JPA)
    public Curso() {
    }

    public Curso(String nombre, String codigo, int horas, BigDecimal precio) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.horas = horas;
        this.precio = precio;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public int getHoras() {
        return horas;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
}