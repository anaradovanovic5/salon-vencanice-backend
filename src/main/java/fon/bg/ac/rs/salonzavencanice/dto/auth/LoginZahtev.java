package fon.bg.ac.rs.salonzavencanice.dto.auth;

import jakarta.validation.constraints.NotBlank;

/**
 * @author Ana
 */
public record LoginZahtev(
        @NotBlank(message = "Korisnicko ime je obavezno") String korisnickoIme,
        @NotBlank(message = "Lozinka je obavezna") String lozinka) {
}