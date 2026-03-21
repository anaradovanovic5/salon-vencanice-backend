/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.bg.ac.rs.salonzavencanice.mapper.impl;

import fon.bg.ac.rs.salonzavencanice.dto.impl.GradDto;
import org.springframework.stereotype.Component;
import fon.bg.ac.rs.salonzavencanice.entity.impl.Grad;

/**
 *
 * @author Ana
 */
@Component
public class GradMapper {

    public GradDto toDto(Grad grad) {
        return new GradDto(
                grad.getIdGrad(),
                grad.getNaziv()
        );
    }

    public Grad toEntity(GradDto dto) {
        Grad grad = new Grad();
        grad.setIdGrad(dto.getIdGrad());
        grad.setNaziv(dto.getNaziv());
        return grad;
    }
}
