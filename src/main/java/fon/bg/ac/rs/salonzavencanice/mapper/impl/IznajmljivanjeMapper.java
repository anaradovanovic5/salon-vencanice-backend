/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.bg.ac.rs.salonzavencanice.mapper.impl;

import fon.bg.ac.rs.salonzavencanice.dto.impl.IznajmljivanjeDto;
import fon.bg.ac.rs.salonzavencanice.entity.impl.Iznajmljivanje;
import fon.bg.ac.rs.salonzavencanice.entity.impl.Klijent;
import fon.bg.ac.rs.salonzavencanice.entity.impl.Vencanica;
import fon.bg.ac.rs.salonzavencanice.entity.impl.Zaposleni;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ana
 */
@Component
public class IznajmljivanjeMapper {

    public IznajmljivanjeDto toDto(Iznajmljivanje i) {
        Integer vencanicaId = i.getVencanica() != null
                ? i.getVencanica().getIdVencanica() : null;
        Integer klijentId = i.getKlijent() != null
                ? i.getKlijent().getIdKlijent() : null;
        Integer zaposleniId = i.getZaposleni() != null
                ? i.getZaposleni().getIdZaposleni() : null;

        return new IznajmljivanjeDto(
                i.getIdIznajmljivanje(),
                i.getDatumUzimanja(),
                i.getDatumVracanja(),
                vencanicaId,
                klijentId,
                zaposleniId
        );
    }

    public Iznajmljivanje toEntity(IznajmljivanjeDto dto) {
        Iznajmljivanje i = new Iznajmljivanje();
        i.setIdIznajmljivanje(dto.getIdIznajmljivanje());
        i.setDatumUzimanja(dto.getDatumUzimanja());
        i.setDatumVracanja(dto.getDatumVracanja());

        if (dto.getVencanicaId() != null) {
            Vencanica v = new Vencanica();
            v.setIdVencanica(dto.getVencanicaId());
            i.setVencanica(v);
        }
        if (dto.getKlijentId() != null) {
            Klijent k = new Klijent();
            k.setIdKlijent(dto.getKlijentId());
            i.setKlijent(k);
        }
        if (dto.getZaposleniId() != null) {
            Zaposleni z = new Zaposleni();
            z.setIdZaposleni(dto.getZaposleniId());
            i.setZaposleni(z);
        }
        return i;
    }
}
