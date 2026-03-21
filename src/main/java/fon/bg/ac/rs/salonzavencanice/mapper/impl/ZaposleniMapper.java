/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.bg.ac.rs.salonzavencanice.mapper.impl;

import fon.bg.ac.rs.salonzavencanice.dto.impl.ZaposleniDto;
import fon.bg.ac.rs.salonzavencanice.entity.impl.Zaposleni;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ana
 */
@Component
public class ZaposleniMapper {

    public ZaposleniDto toDto(Zaposleni z) {
        return new ZaposleniDto(
                z.getIdZaposleni(),
                z.getIme(),
                z.getPrezime(),
                z.getKorisnickoIme()
        );
    }

    public Zaposleni toEntity(ZaposleniDto dto) {
        Zaposleni z = new Zaposleni();
        z.setIdZaposleni(dto.getIdZaposleni());
        z.setIme(dto.getIme());
        z.setPrezime(dto.getPrezime());
        z.setKorisnickoIme(dto.getKorisnickoIme());
        z.setLozinka(dto.getLozinka());
        return z;
    }
}
