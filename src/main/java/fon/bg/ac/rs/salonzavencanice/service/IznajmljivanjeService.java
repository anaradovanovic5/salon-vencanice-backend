/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.bg.ac.rs.salonzavencanice.service;

import fon.bg.ac.rs.salonzavencanice.dto.impl.IznajmljivanjeDto;
import fon.bg.ac.rs.salonzavencanice.entity.impl.Iznajmljivanje;
import fon.bg.ac.rs.salonzavencanice.entity.impl.Vencanica;
import fon.bg.ac.rs.salonzavencanice.exception.EntityNotFoundException;
import fon.bg.ac.rs.salonzavencanice.mapper.impl.IznajmljivanjeMapper;
import fon.bg.ac.rs.salonzavencanice.repository.impl.IznajmljivanjeRepository;
import fon.bg.ac.rs.salonzavencanice.repository.impl.VencanicaRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ana
 */
@Service
public class IznajmljivanjeService {

    private final IznajmljivanjeRepository repo;
    private final IznajmljivanjeMapper mapper;
    private final VencanicaRepository vencanicaRepo;

    public IznajmljivanjeService(IznajmljivanjeRepository repo,
            IznajmljivanjeMapper mapper,
            VencanicaRepository vencanicaRepo) {
        this.repo = repo;
        this.mapper = mapper;
        this.vencanicaRepo = vencanicaRepo;
    }

    public List<IznajmljivanjeDto> findAll() {
        return repo.findAll().stream()
                .map(mapper::toDto).collect(Collectors.toList());
    }

    public IznajmljivanjeDto findById(int id) {
        return repo.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Iznajmljivanje", id));
    }

    public List<IznajmljivanjeDto> findByKlijent(int klijentId) {
        return repo.findByKlijent(klijentId).stream()
                .map(mapper::toDto).collect(Collectors.toList());
    }

    public List<IznajmljivanjeDto> findAktivna() {
        return repo.findAktivna().stream()
                .map(mapper::toDto).collect(Collectors.toList());
    }

    public IznajmljivanjeDto create(IznajmljivanjeDto dto) {
        Vencanica vencanica = vencanicaRepo.findById(dto.getVencanicaId())
                .orElseThrow(() -> new EntityNotFoundException(
                "Vencanica", dto.getVencanicaId()));

        if (vencanica.getStatus() != 0) {
            throw new IllegalStateException(
                    "Vencanica sa ID " + dto.getVencanicaId() + " nije slobodna!");
        }

        vencanica.setStatus(1);
        vencanicaRepo.save(vencanica);

        dto.setIdIznajmljivanje(null);
        Iznajmljivanje saved = repo.save(mapper.toEntity(dto));
        return mapper.toDto(saved);
    }

    public void delete(int id) {
        Iznajmljivanje iznajmljivanje = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Iznajmljivanje", id));

        Vencanica vencanica = iznajmljivanje.getVencanica();
        if (vencanica != null) {
            vencanica.setStatus(0);
            vencanicaRepo.save(vencanica);
        }

        repo.deleteById(id);
    }
}
