
package fon.bg.ac.rs.salonzavencanice.entity.impl;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

/**
 *
 * @author Ana
 */
@Entity
@Table(name = "iznajmljivanje")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Iznajmljivanje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idIznajmljivanje;

    private LocalDate datumUzimanja;
    private LocalDate datumVracanja;

    @ManyToOne
    @JoinColumn(name = "idVencanica")
    private Vencanica vencanica;

    @ManyToOne
    @JoinColumn(name = "idKlijent")
    private Klijent klijent;

    @ManyToOne
    @JoinColumn(name = "idZaposleni")
    private Zaposleni zaposleni;
}
