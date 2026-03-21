/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.bg.ac.rs.salonzavencanice.repository.impl;

import fon.bg.ac.rs.salonzavencanice.entity.impl.Vencanica;
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
public class VencanicaRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Vencanica> findAll() {
        return em.createQuery("SELECT v FROM Vencanica v", Vencanica.class)
                 .getResultList();
    }

    public Optional<Vencanica> findById(int id) {
        return Optional.ofNullable(em.find(Vencanica.class, id));
    }

    public List<Vencanica> findByVelicina(String velicina) {
        return em.createQuery(
            "SELECT v FROM Vencanica v WHERE v.velicina = :velicina",
            Vencanica.class)
            .setParameter("velicina", velicina)
            .getResultList();
    }

    public List<Vencanica> findSlobodne() {
        return em.createQuery(
            "SELECT v FROM Vencanica v WHERE v.status = 0",
            Vencanica.class)
            .getResultList();
    }

    @Transactional
    public Vencanica save(Vencanica v) {
        if (v.getIdVencanica() == null) {
            em.persist(v);
            return v;
        }
        return em.merge(v);
    }

    @Transactional
    public void deleteById(int id) {
        findById(id).ifPresent(em::remove);
    }
}