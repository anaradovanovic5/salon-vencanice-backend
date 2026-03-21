/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.bg.ac.rs.salonzavencanice.service;

import fon.bg.ac.rs.salonzavencanice.dto.impl.KlijentDto;
import fon.bg.ac.rs.salonzavencanice.exception.EntityNotFoundException;
import fon.bg.ac.rs.salonzavencanice.mapper.impl.KlijentMapper;
import fon.bg.ac.rs.salonzavencanice.repository.impl.KlijentRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ana
 */
@Service
public class KlijentService {

    private final KlijentRepository repo;
    private final KlijentMapper mapper;

    public KlijentService(KlijentRepository repo, KlijentMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public List<KlijentDto> findAll() {
        return repo.findAll().stream()
                .map(mapper::toDto).collect(Collectors.toList());
    }

    public KlijentDto findById(int id) {
        return repo.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Klijent", id));
    }

    public KlijentDto create(KlijentDto dto) {
        dto.setIdKlijent(null);
        return mapper.toDto(repo.save(mapper.toEntity(dto)));
    }

    public KlijentDto update(int id, KlijentDto dto) {
        repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Klijent", id));
        dto.setIdKlijent(id);
        return mapper.toDto(repo.save(mapper.toEntity(dto)));
    }

    public void delete(int id) {
        repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Klijent", id));
        repo.deleteById(id);
    }
}
