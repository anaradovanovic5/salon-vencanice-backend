package fon.bg.ac.rs.salonzavencanice.dto.auth;

/**
 * @author Ana
 */
public record LoginOdgovor(
        String token,
        String korisnickoIme,
        String uloga) {
}