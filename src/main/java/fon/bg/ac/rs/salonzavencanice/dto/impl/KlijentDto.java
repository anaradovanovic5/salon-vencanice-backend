
package fon.bg.ac.rs.salonzavencanice.dto.impl;

import jakarta.validation.constraints.*;
import lombok.*;

/**
 *
 * @author Ana
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KlijentDto {

    private Integer idKlijent;

    @Min(value = 18, message = "Klijent mora imati najmanje 18 godina")
    @Max(value = 120, message = "Nerealna vrednost godina")
    private Integer godine;

    @NotBlank(message = "Telefon je obavezan")
    private String telefon;

    @NotBlank(message = "Ime je obavezno")
    private String ime;

    @NotBlank(message = "Prezime je obavezno")
    private String prezime;

    @Email(message = "Email nije validan")
    private String email;

    private String adresa;
    private Integer gradId;
}
