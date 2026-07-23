package fon.bg.ac.rs.salonzavencanice.repository.impl;

import fon.bg.ac.rs.salonzavencanice.entity.impl.Zaposleni;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ana
 */
@Repository
public interface ZaposleniRepository
        extends JpaRepository<Zaposleni, Integer> {

    // Spring generise SQL iz naziva metode
    boolean existsByKorisnickoIme(String korisnickoIme);

    Optional<Zaposleni> findByKorisnickoIme(String korisnickoIme);
}