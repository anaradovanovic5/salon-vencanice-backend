
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
public class GradDto {

    private Integer idGrad;

    @NotBlank(message = "Naziv grada je obavezan")
    private String naziv;
}
