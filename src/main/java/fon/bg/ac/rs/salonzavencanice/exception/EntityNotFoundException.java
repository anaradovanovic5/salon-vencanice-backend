
package fon.bg.ac.rs.salonzavencanice.exception;

/**
 *
 * @author Ana
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String entitet, Object id) {
        super(entitet + " sa ID " + id + " nije pronađen.");
    }
}
