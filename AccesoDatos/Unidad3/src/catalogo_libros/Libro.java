package catalogo_libros;

import jakarta.persistence.*;

@Entity 
@Table(name = "libro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "autor", nullable = false)
    private String autor;

    @Column(name = "editorial", nullable = false)
    private String editorial;

    @OneToOne(mappedBy = "libro", cascade = CascadeType.PERSIST)
    private FichaBibliografica fichaBibliografica;


    public Libro() {}
    
    public Libro(String titulo, String autor, String editorial){
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
    }


    // Getters
    public Long getId() { 
        return id; 
    }

    public String getTitulo() {
        return titulo;
    }
 
    public String getAutor() {
        return autor;
    }
 
    public String getEditorial() {
        return editorial;
    }
 
    public FichaBibliografica getFichaBibliografica() {
        return fichaBibliografica;
    }

    
    // Setters
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
 
    // Actualiza los dos lados de la relación en memoria
    public void setFichaBibliografica(FichaBibliografica fichaBibliografica) {
        this.fichaBibliografica = fichaBibliografica;
        if (fichaBibliografica != null) {
            fichaBibliografica.setLibro(this);
        }
    }
}
