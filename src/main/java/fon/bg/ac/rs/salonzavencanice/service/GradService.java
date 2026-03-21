/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.bg.ac.rs.salonzavencanice.service;
import fon.bg.ac.rs.salonzavencanice.dto.impl.GradDto;
import fon.bg.ac.rs.salonzavencanice.exception.EntityNotFoundException;
import fon.bg.ac.rs.salonzavencanice.mapper.impl.GradMapper;
import fon.bg.ac.rs.salonzavencanice.repository.impl.GradRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
/**
 *
 * @author Ana
 */
@Service
public class GradService {

    private final GradRepository gradRepository;
    private final GradMapper gradMapper;

    public GradService(GradRepository gradRepository, GradMapper gradMapper) {
        this.gradRepository = gradRepository;
        this.gradMapper = gradMapper;
    }

    public List<GradDto> findAll() {
        return gradRepository.findAll()
                .stream()
                .map(gradMapper::toDto)
                .collect(Collectors.toList());
    }

    public GradDto findById(int id) {
        return gradRepository.findById(id)
                .map(gradMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Grad", id));
    }

    public GradDto create(GradDto dto) {
        dto.setIdGrad(null); 
        return gradMapper.toDto(gradRepository.save(gradMapper.toEntity(dto)));
    }

    public GradDto update(int id, GradDto dto) {
        gradRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Grad", id));
        dto.setIdGrad(id);
        return gradMapper.toDto(gradRepository.save(gradMapper.toEntity(dto)));
    }

    public void delete(int id) {
        gradRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Grad", id));
        gradRepository.deleteById(id);
    }
}