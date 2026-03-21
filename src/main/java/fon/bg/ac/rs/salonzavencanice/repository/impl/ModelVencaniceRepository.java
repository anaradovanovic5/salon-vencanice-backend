/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.bg.ac.rs.salonzavencanice.repository.impl;
import fon.bg.ac.rs.salonzavencanice.entity.impl.ModelVencanice;
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
public class ModelVencaniceRepository {

    @PersistenceContext
    private EntityManager em;

    public List<ModelVencanice> findAll() {
        return em.createQuery("SELECT m FROM ModelVencanice m", ModelVencanice.class)
                 .getResultList();
    }

    public Optional<ModelVencanice> findById(int id) {
        return Optional.ofNullable(em.find(ModelVencanice.class, id));
    }

    @Transactional
    public ModelVencanice save(ModelVencanice model) {
        if (model.getIdModel() == null) {
            em.persist(model);
            return model;
        }
        return em.merge(model);
    }

    @Transactional
    public void deleteById(int id) {
        findById(id).ifPresent(em::remove);
    }
}