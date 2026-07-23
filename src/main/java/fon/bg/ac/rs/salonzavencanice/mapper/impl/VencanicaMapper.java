
package fon.bg.ac.rs.salonzavencanice.mapper.impl;

import fon.bg.ac.rs.salonzavencanice.dto.impl.VencanicaDto;
import fon.bg.ac.rs.salonzavencanice.entity.impl.Vencanica;
import org.mapstruct.*;

/**
 *
 * @author Ana
 */
@Mapper(componentModel = "spring")
public interface VencanicaMapper {

    @Mapping(source = "modelVencanice.idModel", target = "modelId")
    VencanicaDto toDto(Vencanica vencanica);

    @Mapping(source = "modelId", target = "modelVencanice.idModel")
    Vencanica toEntity(VencanicaDto dto);
}
