package academia;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "expediente")
public class Expediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "numero_expediente", nullable = false, unique = true, length = 20)
    private String numeroExpediente;

    @Column(name = "fecha_alta", nullable = false)
    private LocalDate fechaAlta;

    // Valor por defecto 0 en la base de datos y también en Java
    @Column(name = "nota_media", columnDefinition = "DECIMAL(4,2) DEFAULT 0")
    private BigDecimal notaMedia = BigDecimal.ZERO;

    // Lado propietario: aquí se guarda la clave ajena alumno_id.
    // No puede ser nula y es única (un alumno solo tiene un expediente).
    @OneToOne(optional = false)
    @JoinColumn(name = "alumno_id", nullable = false, unique = true)
    private Alumno alumno;

    // Constructor sin parámetros (obligatorio para JPA)
    public Expediente() {
    }

    public Expediente(String numeroExpediente, LocalDate fechaAlta, BigDecimal notaMedia) {
        this.numeroExpediente = numeroExpediente;
        this.fechaAlta = fechaAlta;
        this.notaMedia = notaMedia;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public BigDecimal getNotaMedia() {
        return notaMedia;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public void setNotaMedia(BigDecimal notaMedia) {
        this.notaMedia = notaMedia;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
 
}