
package fon.bg.ac.rs.salonzavencanice.repository.impl;

import fon.bg.ac.rs.salonzavencanice.entity.impl.Klijent;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ana
 */
@Repository
public interface KlijentRepository
        extends JpaRepository<Klijent, Integer> {

    List<Klijent> findByGrad_IdGrad(Integer gradId);
}
