
package fon.bg.ac.rs.salonzavencanice.mapper.impl;

import fon.bg.ac.rs.salonzavencanice.dto.impl.KlijentDto;
import fon.bg.ac.rs.salonzavencanice.entity.impl.Klijent;
import org.mapstruct.*;

/**
 *
 * @author Ana
 */
@Mapper(componentModel = "spring")
public interface KlijentMapper {

    @Mapping(source = "grad.idGrad", target = "gradId")
    KlijentDto toDto(Klijent klijent);

    @Mapping(source = "gradId", target = "grad.idGrad")
    Klijent toEntity(KlijentDto dto);
}
