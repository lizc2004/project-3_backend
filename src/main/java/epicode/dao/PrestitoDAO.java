package epicode.dao;

import epicode.entities.Prestito;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class PrestitoDAO {

    private EntityManager em;

    public PrestitoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Prestito prestito) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(prestito);
            t.commit();
            System.out.println("Prestito registrato per l'elemento '" + prestito.getElemento().getTitolo() + "'");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Prestito findById(long id) {
        return em.find(Prestito.class, id);
    }

    public List<Prestito> findPrestitiAttiviByTessera(String numeroTessera) {
        TypedQuery<Prestito> query = em.createQuery(
                "SELECT p FROM Prestito p WHERE p.utente.numeroTessera = :tessera AND p.dataRestituzioneEffettiva IS NULL",
                Prestito.class);
        query.setParameter("tessera", numeroTessera);
        return query.getResultList();
    }

    public List<Prestito> findPrestitiScaduti() {
        TypedQuery<Prestito> query = em.createQuery(
                "SELECT p FROM Prestito p WHERE p.dataRestituzionePrevista < :oggi AND p.dataRestituzioneEffettiva IS NULL",
                Prestito.class);
        query.setParameter("oggi", LocalDate.now());
        return query.getResultList();
    }
}
