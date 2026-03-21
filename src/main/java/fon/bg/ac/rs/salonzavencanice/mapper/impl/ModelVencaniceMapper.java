/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.bg.ac.rs.salonzavencanice.mapper.impl;

import fon.bg.ac.rs.salonzavencanice.dto.impl.ModelVencaniceDto;
import fon.bg.ac.rs.salonzavencanice.entity.impl.ModelVencanice;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ana
 */
@Component
public class ModelVencaniceMapper {

    public ModelVencaniceDto toDto(ModelVencanice m) {
        return new ModelVencaniceDto(
                m.getIdModel(),
                m.getNaziv(),
                m.getDizajner(),
                m.getBoja(),
                m.getMaterijal()
        );
    }

    public ModelVencanice toEntity(ModelVencaniceDto dto) {
        ModelVencanice m = new ModelVencanice();
        m.setIdModel(dto.getIdModel());
        m.setNaziv(dto.getNaziv());
        m.setDizajner(dto.getDizajner());
        m.setBoja(dto.getBoja());
        m.setMaterijal(dto.getMaterijal());
        return m;
    }
}
