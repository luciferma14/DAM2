package clinica_veterinaria;

import java.math.BigDecimal;
import jakarta.persistence.*;

@Entity
@Table(name = "veterinario")
public class Veterinario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false, length = 60)
    private String nombre;

    @Column(name = "apellidos", nullable = false, length = 100)
    private String apellidos;

    @Column(name = "numero_colegiado", nullable = false, unique = true, length = 20)
    private String numeroColegiado;

    @Column(name = "especialidad", length = 60)
    private String especialidad;

    @Column(name = "salario", precision = 10, scale = 2)
    private BigDecimal salario;

    public Veterinario() {}

    public Veterinario(String nombre, String apellidos, String numeroColegiado, String especialidad, BigDecimal salario) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.numeroColegiado = numeroColegiado;
        this.especialidad = especialidad;
        this.salario = salario;
    }

    // Getters
    public int getId() { 
        return id; 
    }
    public String getNombre() { 
        return nombre; 
    }
    public String getApellidos() { 
        return apellidos; 
    }
    public String getNumeroColegiado() { 
        return numeroColegiado; 
    }
    public String getEspecialidad() { 
        return especialidad; 
    }
    public BigDecimal getSalario() { 
        return salario; 
    }

    // Setters
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }
    public void setApellidos(String apellidos) { 
        this.apellidos = apellidos; 
    }
    public void setNumeroColegiado(String numeroColegiado) { 
        this.numeroColegiado = numeroColegiado; 
    }
    public void setEspecialidad(String especialidad) { 
        this.especialidad = especialidad; 
    }
    public void setSalario(BigDecimal salario) { 
        this.salario = salario; 
    }
}