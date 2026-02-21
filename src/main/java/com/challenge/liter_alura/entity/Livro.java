package com.challenge.liter_alura.entity;

import com.challenge.liter_alura.dto.DadosLivro;
import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    private String idioma;
    private Double numeroDownloads;

    @ManyToOne
    @JoinColumn(name = "autor_id") // Nome da coluna estrangeira no banco
    private Autor autor;

    public Livro() {}

    public Livro(DadosLivro dadosLivro) {
        this.titulo = dadosLivro.titulo();
        // Pegamos apenas o primeiro idioma da lista que vem do DTO
        this.idioma = dadosLivro.idiomas().isEmpty() ? "Desconhecido" : dadosLivro.idiomas().get(0);
        this.numeroDownloads = dadosLivro.numeroDownloads();
    }

    // Getters e Setters
    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }
    public Double getNumeroDownloads() { return numeroDownloads; }
    public void setNumeroDownloads(Double numeroDownloads) { this.numeroDownloads = numeroDownloads; }
    public Autor getAutor() { return autor; }
    public void setAutor(Autor autor) { this.autor = autor; }

    @Override
    public String toString() {
        return "----- LIVRO -----\n" +
                "Título: " + titulo + "\n" +
                "Autor: " + (autor != null ? autor.getNome() : "Desconhecido") + "\n" +
                "Idioma: " + idioma + "\n" +
                "Downloads: " + numeroDownloads + "\n" +
                "-----------------";
    }
}
