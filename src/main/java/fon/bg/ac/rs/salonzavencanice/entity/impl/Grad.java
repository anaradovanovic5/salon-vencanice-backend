
package fon.bg.ac.rs.salonzavencanice.entity.impl;

import jakarta.persistence.*;
import lombok.*;

/**
 *
 * @author Ana
 */
@Entity
@Table(name = "grad")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGrad;

    private String naziv;
}
