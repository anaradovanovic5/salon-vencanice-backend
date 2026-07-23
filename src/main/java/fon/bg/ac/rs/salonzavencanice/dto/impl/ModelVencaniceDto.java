
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
public class ModelVencaniceDto {

    private Integer idModel;

    @NotBlank(message = "Naziv modela je obavezan")
    private String naziv;
    private String dizajner;
    private String boja;
    private String materijal;
}
