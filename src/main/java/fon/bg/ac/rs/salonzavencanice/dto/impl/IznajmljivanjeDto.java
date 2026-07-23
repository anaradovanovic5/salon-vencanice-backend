package fon.bg.ac.rs.salonzavencanice.dto.impl;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import lombok.*;

/**
 *
 * @author Ana
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IznajmljivanjeDto {

    private Integer idIznajmljivanje;

    @NotNull(message = "Datum uzimanja je obavezan")
    private LocalDate datumUzimanja;

    @NotNull(message = "Datum vracanja je obavezan")
    private LocalDate datumVracanja;

    private Integer vencanicaId;

    private Integer klijentId;

    private Integer zaposleniId;
}