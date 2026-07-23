
package fon.bg.ac.rs.salonzavencanice.entity.impl;

import jakarta.persistence.*;
import lombok.*;

/**
 *
 * @author Ana
 */
@Entity
@Table(name = "vencanica")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vencanica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVencanica;

    private int status;
    private int godinaProizvodnje;
    private String serijskiBroj;
    private String napomene;
    private String velicina;

    @ManyToOne
    @JoinColumn(name = "idModel")
    private ModelVencanice modelVencanice;
}
