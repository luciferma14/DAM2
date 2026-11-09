package clinica_veterinaria;

import jakarta.persistence.*;


@Entity
@Table(name = "propietario")
public class Propietario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false, length = 60)
    private String nombre;

    @Column(name = "apellidos", nullable = false, length = 100)
    private String apellidos;

    @Column(name = "dni", nullable = false, unique = true, length = 9)
    private String dni;

    @Column(name = "telefono", length = 15)
    private String telefono;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    public Propietario() {}

    public Propietario(String nombre, String apellidos, String dni, String telefono, String email) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
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
    public String getDni() { 
        return dni; 
    }
    public String getTelefono() { 
        return telefono; 
    }
    public String getEmail() { 
        return email; 
    }

    // Setters
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }
    public void setApellidos(String apellidos) { 
        this.apellidos = apellidos; 
    }
    public void setDni(String dni) { 
        this.dni = dni; 
    }
    public void setTelefono(String telefono) { 
        this.telefono = telefono; 
    }
    public void setEmail(String email) { 
        this.email = email; 
    }
}