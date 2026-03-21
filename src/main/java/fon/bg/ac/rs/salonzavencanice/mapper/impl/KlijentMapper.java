/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.bg.ac.rs.salonzavencanice.mapper.impl;

import fon.bg.ac.rs.salonzavencanice.dto.impl.KlijentDto;
import fon.bg.ac.rs.salonzavencanice.entity.impl.Grad;
import fon.bg.ac.rs.salonzavencanice.entity.impl.Klijent;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ana
 */
@Component
public class KlijentMapper {

    public KlijentDto toDto(Klijent k) {
        Integer gradId = k.getGrad() != null ? k.getGrad().getIdGrad() : null;
        return new KlijentDto(
                k.getIdKlijent(),
                k.getGodine(),
                k.getTelefon(),
                k.getIme(),
                k.getPrezime(),
                k.getEmail(),
                k.getAdresa(),
                gradId
        );
    }

    public Klijent toEntity(KlijentDto dto) {
        Klijent k = new Klijent();
        k.setIdKlijent(dto.getIdKlijent());
        k.setGodine(dto.getGodine());
        k.setTelefon(dto.getTelefon());
        k.setIme(dto.getIme());
        k.setPrezime(dto.getPrezime());
        k.setEmail(dto.getEmail());
        k.setAdresa(dto.getAdresa());

        if (dto.getGradId() != null) {
            Grad grad = new Grad();
            grad.setIdGrad(dto.getGradId());
            k.setGrad(grad);
        }
        return k;
    }
}
