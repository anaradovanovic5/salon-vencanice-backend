/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.bg.ac.rs.salonzavencanice.service;

import fon.bg.ac.rs.salonzavencanice.dto.impl.ZaposleniDto;
import fon.bg.ac.rs.salonzavencanice.exception.EntityNotFoundException;
import fon.bg.ac.rs.salonzavencanice.mapper.impl.ZaposleniMapper;
import fon.bg.ac.rs.salonzavencanice.repository.impl.ZaposleniRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ana
 */
@Service
public class ZaposleniService {

    private final ZaposleniRepository repo;
    private final ZaposleniMapper mapper;

    public ZaposleniService(ZaposleniRepository repo, ZaposleniMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public List<ZaposleniDto> findAll() {
        return repo.findAll().stream()
                .map(mapper::toDto).collect(Collectors.toList());
    }

    public ZaposleniDto findById(int id) {
        return repo.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Zaposleni", id));
    }

    public ZaposleniDto create(ZaposleniDto dto) {
        dto.setIdZaposleni(null);
        return mapper.toDto(repo.save(mapper.toEntity(dto)));
    }

    public ZaposleniDto update(int id, ZaposleniDto dto) {
        repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Zaposleni", id));
        dto.setIdZaposleni(id);
        return mapper.toDto(repo.save(mapper.toEntity(dto)));
    }

    public void delete(int id) {
        repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Zaposleni", id));
        repo.deleteById(id);
    }
}
