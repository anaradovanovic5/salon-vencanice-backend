
package fon.bg.ac.rs.salonzavencanice.entity.impl;

import jakarta.persistence.*;
import lombok.*;

/**
 *
 * @author Ana
 */
@Entity
@Table(name = "klijent")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Klijent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idKlijent;

    private Integer godine;
    private String telefon;
    private String ime;
    private String prezime;
    private String email;
    private String adresa;

    @ManyToOne
    @JoinColumn(name = "idGrad")
    private Grad grad;
}
