/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.bg.ac.rs.salonzavencanice.repository.impl;

import fon.bg.ac.rs.salonzavencanice.entity.impl.Grad;
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
public class GradRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Grad> findAll() {
        return em.createQuery("SELECT g FROM Grad g", Grad.class)
                .getResultList();
    }

    public Optional<Grad> findById(int id) {
        return Optional.ofNullable(em.find(Grad.class, id));
    }

    @Transactional
    public Grad save(Grad grad) {
        if (grad.getIdGrad() == null) {
            em.persist(grad);
            return grad;
        }
        return em.merge(grad);
    }

    @Transactional
    public void deleteById(int id) {
        findById(id).ifPresent(em::remove);
    }
}
