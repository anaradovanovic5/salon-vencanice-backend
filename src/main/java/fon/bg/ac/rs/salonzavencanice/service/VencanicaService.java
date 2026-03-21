/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.bg.ac.rs.salonzavencanice.service;

import fon.bg.ac.rs.salonzavencanice.dto.impl.VencanicaDto;
import fon.bg.ac.rs.salonzavencanice.exception.EntityNotFoundException;
import fon.bg.ac.rs.salonzavencanice.mapper.impl.VencanicaMapper;
import fon.bg.ac.rs.salonzavencanice.repository.impl.VencanicaRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ana
 */
@Service
public class VencanicaService {

    private final VencanicaRepository repo;
    private final VencanicaMapper mapper;

    public VencanicaService(VencanicaRepository repo, VencanicaMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public List<VencanicaDto> findAll() {
        return repo.findAll().stream()
                .map(mapper::toDto).collect(Collectors.toList());
    }

    public VencanicaDto findById(int id) {
        return repo.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Vencanica", id));
    }

    public List<VencanicaDto> findByVelicina(String velicina) {
        return repo.findByVelicina(velicina).stream()
                .map(mapper::toDto).collect(Collectors.toList());
    }

    public List<VencanicaDto> findSlobodne() {
        return repo.findSlobodne().stream()
                .map(mapper::toDto).collect(Collectors.toList());
    }

    public VencanicaDto create(VencanicaDto dto) {
        dto.setIdVencanica(null);
        return mapper.toDto(repo.save(mapper.toEntity(dto)));
    }

    public VencanicaDto update(int id, VencanicaDto dto) {
        repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vencanica", id));
        dto.setIdVencanica(id);
        return mapper.toDto(repo.save(mapper.toEntity(dto)));
    }

    public void delete(int id) {
        repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vencanica", id));
        repo.deleteById(id);
    }
}