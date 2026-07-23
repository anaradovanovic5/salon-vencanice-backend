
package fon.bg.ac.rs.salonzavencanice.repository.impl;

import fon.bg.ac.rs.salonzavencanice.entity.impl.Vencanica;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ana
 */
@Repository
public interface VencanicaRepository
        extends JpaRepository<Vencanica, Integer> {

    // Spring cita naziv metode i generiše SQL
    List<Vencanica> findByStatus(int status);

    List<Vencanica> findByVelicina(String velicina);

    List<Vencanica> findByModelVencanice_IdModel(Integer modelId);
}
