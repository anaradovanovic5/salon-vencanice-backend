/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.bg.ac.rs.salonzavencanice.repository.impl;
import fon.bg.ac.rs.salonzavencanice.entity.impl.Iznajmljivanje;
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
public class IznajmljivanjeRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Iznajmljivanje> findAll() {
        return em.createQuery(
            "SELECT i FROM Iznajmljivanje i", Iznajmljivanje.class)
            .getResultList();
    }

    public Optional<Iznajmljivanje> findById(int id) {
        return Optional.ofNullable(em.find(Iznajmljivanje.class, id));
    }

    public List<Iznajmljivanje> findByKlijent(int klijentId) {
        return em.createQuery(
            "SELECT i FROM Iznajmljivanje i WHERE i.klijent.idKlijent = :id",
            Iznajmljivanje.class)
            .setParameter("id", klijentId)
            .getResultList();
    }

    public List<Iznajmljivanje> findAktivna() {
        return em.createQuery(
            "SELECT i FROM Iznajmljivanje i WHERE i.datumVracanja >= CURRENT_DATE",
            Iznajmljivanje.class)
            .getResultList();
    }

    @Transactional
    public Iznajmljivanje save(Iznajmljivanje i) {
        if (i.getIdIznajmljivanje() == null) {
            em.persist(i);
            return i;
        }
        return em.merge(i);
    }

    @Transactional
    public void deleteById(int id) {
        findById(id).ifPresent(em::remove);
    }
}