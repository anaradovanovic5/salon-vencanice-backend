
package fon.bg.ac.rs.salonzavencanice.mapper.impl;

import fon.bg.ac.rs.salonzavencanice.dto.impl.IznajmljivanjeDto;
import fon.bg.ac.rs.salonzavencanice.entity.impl.Iznajmljivanje;
import org.mapstruct.*;

/**
 *
 * @author Ana
 */
@Mapper(componentModel = "spring")
public interface IznajmljivanjeMapper {

    @Mapping(source = "vencanica.idVencanica", target = "vencanicaId")
    @Mapping(source = "klijent.idKlijent", target = "klijentId")
    @Mapping(source = "zaposleni.idZaposleni", target = "zaposleniId")
    IznajmljivanjeDto toDto(Iznajmljivanje iznajmljivanje);

    @Mapping(source = "vencanicaId", target = "vencanica.idVencanica")
    @Mapping(source = "klijentId", target = "klijent.idKlijent")
    @Mapping(source = "zaposleniId", target = "zaposleni.idZaposleni")
    Iznajmljivanje toEntity(IznajmljivanjeDto dto);
}
