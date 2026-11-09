package academia;

import jakarta.persistence.*;

@Entity
@Table(name = "alumno")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false, length = 60)
    private String nombre;

    @Column(name = "apellidos", nullable = false, length = 100)
    private String apellidos;

    @Column(name = "dni", nullable = false, unique = true, length = 9)
    private String dni;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    // Lado propietario de la relación con Curso (unidireccional):
    // la clave ajena curso_id está en la tabla alumno, no puede ser nula y es única.
    // Con cascade PERSIST, al guardar el alumno se guarda también su curso.
    @OneToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "curso_id", nullable = false, unique = true)
    private Curso curso;

    // Lado inverso de la relación con Expediente (bidireccional):
    // la clave ajena alumno_id está en la tabla expediente.
    // Con cascade PERSIST, al guardar el alumno se guarda también su expediente.
    @OneToOne(mappedBy = "alumno", cascade = CascadeType.PERSIST)
    private Expediente expediente;

    // Constructor sin parámetros (obligatorio para JPA)
    public Alumno() {
    }

    public Alumno(String nombre, String apellidos, String dni, String email) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.email = email;
    }

    // Se ejecuta justo antes de insertar el alumno.
    // Si falta el curso o el expediente lanza una excepción y se hace rollback.
    @PrePersist
    public void comprobarRelaciones() {
        if (curso == null) {
            throw new IllegalStateException("El alumno con DNI " + dni + " no tiene curso asociado");
        }
        if (expediente == null) {
            throw new IllegalStateException("El alumno con DNI " + dni + " no tiene expediente asociado");
        }
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

    public String getEmail() {
        return email;
    }

    public Curso getCurso() {
        return curso;
    }

    public Expediente getExpediente() {
        return expediente;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    // Actualiza los dos lados de la relación en memoria
    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
        if (expediente != null) {
            expediente.setAlumno(this);
        }
    }
}