
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
public class VencanicaDto {

    private Integer idVencanica;
    
    @Min(value = 0, message = "Status mora biti 0 ili 1")
    @Max(value = 1, message = "Status mora biti 0 ili 1")
    private int status;

    @Min(value = 1950, message = "Nerealna godina proizvodnje")
    @Max(value = 2026, message = "Godina proizvodnje ne moze biti u buducnosti")
    private int godinaProizvodnje;

    @NotBlank(message = "Serijski broj je obavezan")
    private String serijskiBroj;

    private String napomene;
    private String velicina;

    @NotNull(message = "Model je obavezan")
    private Integer modelId;
}
