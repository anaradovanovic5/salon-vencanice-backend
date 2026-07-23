
package fon.bg.ac.rs.salonzavencanice.repository.impl;

import fon.bg.ac.rs.salonzavencanice.entity.impl.ModelVencanice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ana
 */
@Repository
public interface ModelVencaniceRepository
        extends JpaRepository<ModelVencanice, Integer> {
}
