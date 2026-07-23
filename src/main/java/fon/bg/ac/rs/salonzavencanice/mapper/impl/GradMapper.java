
package fon.bg.ac.rs.salonzavencanice.mapper.impl;

import fon.bg.ac.rs.salonzavencanice.dto.impl.GradDto;
import fon.bg.ac.rs.salonzavencanice.entity.impl.Grad;
import org.mapstruct.*;

/**
 *
 * @author Ana
 */
@Mapper(componentModel = "spring")
public interface GradMapper {

    GradDto toDto(Grad grad);

    Grad toEntity(GradDto dto);
}
