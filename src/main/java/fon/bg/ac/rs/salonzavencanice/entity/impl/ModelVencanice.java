
package fon.bg.ac.rs.salonzavencanice.entity.impl;

import jakarta.persistence.*;
import lombok.*;

/**
 *
 * @author Ana
 */
@Entity
@Table(name = "model_vencanice")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelVencanice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idModel;

    private String naziv;
    private String dizajner;
    private String boja;
    private String materijal;
}