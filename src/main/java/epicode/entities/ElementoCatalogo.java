package epicode.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "elementi_catalogo")
public abstract class ElementoCatalogo {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true, nullable = false)
    private String isbn;

    @Column(nullable = false)
    private String titolo;

    @Column(name = "anno_pubblicazione")
    private int annoPubblicazione;

    @Column(name = "numero_pagine")
    private int numeroPagine;

    @OneToMany(mappedBy = "elemento", cascade = CascadeType.REMOVE)
    private List<Prestito> listaPrestiti;

    public ElementoCatalogo() {
    }

    public ElementoCatalogo(String isbn, String titolo, int annoPubblicazione, int numeroPagine) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
    }

    public long getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(int numeroPagine) {
        this.numeroPagine = numeroPagine;
    }

    public List<Prestito> getListaPrestiti() {
        return listaPrestiti;
    }

    @Override
    public String toString() {
        return "ElementoCatalogo{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", titolo='" + titolo + '\'' +
                ", annoPubblicazione=" + annoPubblicazione +
                ", numeroPagine=" + numeroPagine +
                '}';
    }
}
