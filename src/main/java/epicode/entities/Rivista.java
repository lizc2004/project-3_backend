package epicode.entities;

import javax.persistence.*;

@Entity
@Table(name = "riviste")
public class Rivista extends ElementoCatalogo {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Periodicita periodicita;

    public Rivista() {
    }

    public Rivista(String isbn, String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return "Rivista{" +
                "id=" + getId() +
                ", isbn='" + getIsbn() + '\'' +
                ", titolo='" + getTitolo() + '\'' +
                ", periodicita=" + periodicita +
                ", annoPubblicazione=" + getAnnoPubblicazione() +
                '}';
    }
}
