
package fon.bg.ac.rs.salonzavencanice.mapper.impl;

import fon.bg.ac.rs.salonzavencanice.dto.impl.ModelVencaniceDto;
import fon.bg.ac.rs.salonzavencanice.entity.impl.ModelVencanice;
import org.mapstruct.*;

/**
 *
 * @author Ana
 */
@Mapper(componentModel = "spring")
public interface ModelVencaniceMapper {

    ModelVencaniceDto toDto(ModelVencanice model);

    ModelVencanice toEntity(ModelVencaniceDto dto);
}
