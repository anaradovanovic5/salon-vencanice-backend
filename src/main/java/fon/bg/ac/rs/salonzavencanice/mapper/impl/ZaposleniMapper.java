
package fon.bg.ac.rs.salonzavencanice.mapper.impl;

import fon.bg.ac.rs.salonzavencanice.dto.impl.ZaposleniDto;
import fon.bg.ac.rs.salonzavencanice.entity.impl.Zaposleni;
import org.mapstruct.*;

/**
 *
 * @author Ana
 */
@Mapper(componentModel = "spring")
public interface ZaposleniMapper {

    @Mapping(target = "lozinka", ignore = true)
    ZaposleniDto toDto(Zaposleni zaposleni);

    @Mapping(target = "authorities", ignore = true)
    Zaposleni toEntity(ZaposleniDto dto);
}
