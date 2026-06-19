package epicode;

import epicode.dao.CatalogoDAO;
import epicode.dao.PrestitoDAO;
import epicode.dao.UtenteDAO;
import epicode.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("project3_backend");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();

        CatalogoDAO catalogoDAO = new CatalogoDAO(em);
        UtenteDAO utenteDAO = new UtenteDAO(em);
        PrestitoDAO prestitoDAO = new PrestitoDAO(em);

        // ===================== SALVATAGGIO ELEMENTI =====================

        Libro libro1 = new Libro("978-88-111-1111-1", "Il Nome della Rosa", 1980, 502, "Umberto Eco", "Romanzo storico");
        // catalogoDAO.save(libro1);

        Rivista rivista1 = new Rivista("978-88-222-2222-2", "Focus", 2023, 120, Periodicita.MENSILE);
        // catalogoDAO.save(rivista1);

        // ===================== SALVATAGGIO UTENTI =====================

        Utente utente1 = new Utente("Mario", "Rossi", LocalDate.of(1990, 5, 12), "TESSERA-001");
        // utenteDAO.save(utente1);

        // ===================== PRESTITI =====================

        // ElementoCatalogo el = catalogoDAO.findByIsbn("978-88-111-1111-1");
        // Utente u = utenteDAO.findByNumeroTessera("TESSERA-001");
        // Prestito p = new Prestito(u, el, LocalDate.now());
        // prestitoDAO.save(p);

        // ===================== RICERCHE =====================

        System.out.println("--- Ricerca per anno ---");
        catalogoDAO.findByAnnoPubblicazione(1980).forEach(System.out::println);

        System.out.println("--- Ricerca per titolo ---");
        catalogoDAO.findByTitolo("Rosa").forEach(System.out::println);

        System.out.println("--- Prestiti attivi per tessera ---");
        prestitoDAO.findPrestitiAttiviByTessera("TESSERA-001").forEach(System.out::println);

        System.out.println("--- Prestiti scaduti ---");
        prestitoDAO.findPrestitiScaduti().forEach(System.out::println);

        em.close();
        emf.close();
    }
}
