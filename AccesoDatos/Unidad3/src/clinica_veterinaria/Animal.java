package clinica_veterinaria;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "especie", nullable = false, length = 30)
    private String especie;

    @Column(name = "raza", length = 50)
    private String raza;

    @Column(name = "fecha_nacimiento", nullable = true)
    private LocalDate fechaNacimiento;

    @Column(name = "peso", precision = 6, scale = 2)
    private BigDecimal peso;

    public Animal() {}

    public Animal(String nombre, String especie, String raza, LocalDate fechaNacimiento, BigDecimal peso) {
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.peso = peso;
    }

    // Getters
    public int getId() { 
        return id; 
    }
    public String getNombre() { 
        return nombre; 
    }
    public String getEspecie() { 
        return especie; 
    }
    public String getRaza() {
        return raza; 
    }
    public LocalDate getFechaNacimiento() { 
        return fechaNacimiento; 
    }
    public BigDecimal getPeso() { 
        return peso; 
    }

    // Setters
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }
    public void setEspecie(String especie) { 
        this.especie = especie; 
    }
    public void setRaza(String raza) { 
        this.raza = raza; 
    }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { 
        this.fechaNacimiento = fechaNacimiento; 
    }
    public void setPeso(BigDecimal peso) { 
        this.peso = peso; 
    }
}