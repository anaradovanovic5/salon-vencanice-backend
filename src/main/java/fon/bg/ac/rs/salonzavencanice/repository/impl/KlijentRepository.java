/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.bg.ac.rs.salonzavencanice.repository.impl;
import fon.bg.ac.rs.salonzavencanice.entity.impl.Klijent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Ana
 */
@Repository
public class KlijentRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Klijent> findAll() {
        return em.createQuery("SELECT k FROM Klijent k", Klijent.class)
                 .getResultList();
    }

    public Optional<Klijent> findById(int id) {
        return Optional.ofNullable(em.find(Klijent.class, id));
    }

    @Transactional
    public Klijent save(Klijent k) {
        if (k.getIdKlijent() == null) {
            em.persist(k);
            return k;
        }
        return em.merge(k);
    }

    @Transactional
    public void deleteById(int id) {
        findById(id).ifPresent(em::remove);
    }
}
