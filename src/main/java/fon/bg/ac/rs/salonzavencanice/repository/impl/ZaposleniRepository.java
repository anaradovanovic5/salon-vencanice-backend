/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.bg.ac.rs.salonzavencanice.repository.impl;
import fon.bg.ac.rs.salonzavencanice.entity.impl.Zaposleni;
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
public class ZaposleniRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Zaposleni> findAll() {
        return em.createQuery("SELECT z FROM Zaposleni z", Zaposleni.class)
                 .getResultList();
    }

    public Optional<Zaposleni> findById(int id) {
        return Optional.ofNullable(em.find(Zaposleni.class, id));
    }

    @Transactional
    public Zaposleni save(Zaposleni z) {
        if (z.getIdZaposleni() == null) {
            em.persist(z);
            return z;
        }
        return em.merge(z);
    }

    @Transactional
    public void deleteById(int id) {
        findById(id).ifPresent(em::remove);
    }
}
