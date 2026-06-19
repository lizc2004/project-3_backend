package epicode.dao;

import epicode.entities.ElementoCatalogo;
import epicode.entities.Libro;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class CatalogoDAO {

    private EntityManager em;

    public CatalogoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(ElementoCatalogo elemento) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(elemento);
            t.commit();
            System.out.println("Elemento '" + elemento.getTitolo() + "' aggiunto al catalogo!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ElementoCatalogo findById(long id) {
        return em.find(ElementoCatalogo.class, id);
    }

    public ElementoCatalogo findByIsbn(String isbn) {
        TypedQuery<ElementoCatalogo> query = em.createQuery(
                "SELECT e FROM ElementoCatalogo e WHERE e.isbn = :isbn", ElementoCatalogo.class);
        query.setParameter("isbn", isbn);
        return query.getSingleResult();
    }

    public void deleteByIsbn(String isbn) {
        try {
            ElementoCatalogo found = findByIsbn(isbn);
            if (found != null) {
                EntityTransaction t = em.getTransaction();
                t.begin();
                em.remove(em.contains(found) ? found : em.merge(found));
                t.commit();
                System.out.println("Elemento con ISBN " + isbn + " eliminato!");
            } else {
                System.out.println("Elemento non trovato");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<ElementoCatalogo> findByAnnoPubblicazione(int anno) {
        TypedQuery<ElementoCatalogo> query = em.createQuery(
                "SELECT e FROM ElementoCatalogo e WHERE e.annoPubblicazione = :anno", ElementoCatalogo.class);
        query.setParameter("anno", anno);
        return query.getResultList();
    }

    public List<Libro> findByAutore(String autore) {
        TypedQuery<Libro> query = em.createQuery(
                "SELECT l FROM Libro l WHERE l.autore = :autore", Libro.class);
        query.setParameter("autore", autore);
        return query.getResultList();
    }

    public List<ElementoCatalogo> findByTitolo(String parola) {
        TypedQuery<ElementoCatalogo> query = em.createQuery(
                "SELECT e FROM ElementoCatalogo e WHERE LOWER(e.titolo) LIKE LOWER(:parola)", ElementoCatalogo.class);
        query.setParameter("parola", "%" + parola + "%");
        return query.getResultList();
    }
}
