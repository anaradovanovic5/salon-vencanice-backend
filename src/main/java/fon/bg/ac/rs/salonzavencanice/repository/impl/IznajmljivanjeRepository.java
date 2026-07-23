
package fon.bg.ac.rs.salonzavencanice.repository.impl;

import fon.bg.ac.rs.salonzavencanice.entity.impl.Iznajmljivanje;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ana
 */
@Repository
public interface IznajmljivanjeRepository
        extends JpaRepository<Iznajmljivanje, Integer> {

    List<Iznajmljivanje> findByKlijent_IdKlijent(Integer klijentId);

    @Query("SELECT i FROM Iznajmljivanje i WHERE i.datumVracanja >= CURRENT_DATE")
    List<Iznajmljivanje> findAktivna();
}
