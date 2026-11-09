package clinica_veterinaria;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "motivo", nullable = false, length = 150)
    private String motivo;

    @Column(name = "diagnostico", length = 250)
    private String diagnostico;

    @Column(name = "coste", nullable = false, precision = 8, scale = 2)
    private BigDecimal coste;

    // Constructor sin parámetros (obligatorio para JPA)
    public Consulta() {}

    public Consulta(LocalDate fecha, String motivo, String diagnostico, BigDecimal coste) {
        this.fecha = fecha;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.coste = coste;
    }

    // Getters
    public int getId() { 
        return id; 
    }
    public LocalDate getFecha() { 
        return fecha; 
    }
    public String getMotivo() { 
        return motivo; 
    }
    public String getDiagnostico() { 
        return diagnostico; 
    }
    public BigDecimal getCoste() { 
        return coste; 
    }

    // Setters
    public void setFecha(LocalDate fecha) { 
        this.fecha = fecha; 
    }
    public void setMotivo(String motivo) { 
        this.motivo = motivo; 
    }
    public void setDiagnostico(String diagnostico) { 
        this.diagnostico = diagnostico; 
    }
    public void setCoste(BigDecimal coste) { 
        this.coste = coste; 
    }
}