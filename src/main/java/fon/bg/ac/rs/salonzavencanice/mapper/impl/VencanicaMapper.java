/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.bg.ac.rs.salonzavencanice.mapper.impl;

import fon.bg.ac.rs.salonzavencanice.dto.impl.VencanicaDto;
import fon.bg.ac.rs.salonzavencanice.entity.impl.ModelVencanice;
import fon.bg.ac.rs.salonzavencanice.entity.impl.Vencanica;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ana
 */
@Component
public class VencanicaMapper {

    public VencanicaDto toDto(Vencanica v) {
        Integer modelId = v.getModelVencanice() != null
                ? v.getModelVencanice().getIdModel() : null;
        return new VencanicaDto(
                v.getIdVencanica(),
                v.getStatus(),
                v.getGodinaProizvodnje(),
                v.getSerijskiBroj(),
                v.getNapomene(),
                v.getVelicina(),
                modelId
        );
    }

    public Vencanica toEntity(VencanicaDto dto) {
        Vencanica v = new Vencanica();
        v.setIdVencanica(dto.getIdVencanica());
        v.setStatus(dto.getStatus());
        v.setGodinaProizvodnje(dto.getGodinaProizvodnje());
        v.setSerijskiBroj(dto.getSerijskiBroj());
        v.setNapomene(dto.getNapomene());
        v.setVelicina(dto.getVelicina());

        if (dto.getModelId() != null) {
            ModelVencanice model = new ModelVencanice();
            model.setIdModel(dto.getModelId());
            v.setModelVencanice(model);
        }
        return v;
    }
}
