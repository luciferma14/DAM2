package catalogo_libros;

import jakarta.persistence.*;

@Entity 
@Table(name = "ficha_bibliografica")
public class FichaBibliografica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;
 
    @Column(name = "numero_paginas", nullable = false)
    private int numeroPaginas;
 
    @Column(name = "idioma", nullable = false)
    private String idioma;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_libro", nullable = false, unique = true)
    private Libro libro;

    public FichaBibliografica(){}

    public FichaBibliografica(String isbn, int numeroPaginas, String idioma){
        this.isbn = isbn;
        this.numeroPaginas = numeroPaginas;
        this.idioma = idioma;
    }

    //Getters
    public Long getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public String getIdioma() {
        return idioma;
    }

    public Libro getLibro() {
        return libro;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    
    
    
}
