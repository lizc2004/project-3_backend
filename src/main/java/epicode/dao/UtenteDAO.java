package epicode.dao;

import epicode.entities.Utente;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class UtenteDAO {

    private EntityManager em;

    public UtenteDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Utente utente) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(utente);
            t.commit();
            System.out.println("Utente " + utente.getNome() + " " + utente.getCognome() + " registrato!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Utente findById(long id) {
        return em.find(Utente.class, id);
    }

    public Utente findByNumeroTessera(String numeroTessera) {
        return em.createQuery("SELECT u FROM Utente u WHERE u.numeroTessera = :tessera", Utente.class)
                .setParameter("tessera", numeroTessera)
                .getSingleResult();
    }
}
