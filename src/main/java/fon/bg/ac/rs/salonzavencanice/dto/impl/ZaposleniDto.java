package fon.bg.ac.rs.salonzavencanice.dto.impl;

import fon.bg.ac.rs.salonzavencanice.entity.impl.Uloga;
import jakarta.validation.constraints.*;
import lombok.*;

/**
 *
 * @author Ana
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZaposleniDto {

    private Integer idZaposleni;

    @NotBlank(message = "Ime je obavezno")
    private String ime;

    @NotBlank(message = "Prezime je obavezno")
    private String prezime;

    @NotBlank(message = "Korisnicko ime je obavezno")
    private String korisnickoIme;

    @NotBlank(message = "Lozinka je obavezna")
    @Size(min = 6, message = "Lozinka mora imati najmanje 6 karaktera")
    private String lozinka;

    private Uloga uloga;
}