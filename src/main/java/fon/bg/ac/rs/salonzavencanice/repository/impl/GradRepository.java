
package fon.bg.ac.rs.salonzavencanice.repository.impl;

import fon.bg.ac.rs.salonzavencanice.entity.impl.Grad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ana
 */
@Repository
public interface GradRepository extends JpaRepository<Grad, Integer> {
    // findAll(), findById(), save(), deleteById()
}
