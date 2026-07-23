
package fon.bg.ac.rs.salonzavencanice.service;

import fon.bg.ac.rs.salonzavencanice.dto.impl.ModelVencaniceDto;
import fon.bg.ac.rs.salonzavencanice.exception.EntityNotFoundException;
import fon.bg.ac.rs.salonzavencanice.mapper.impl.ModelVencaniceMapper;
import fon.bg.ac.rs.salonzavencanice.repository.impl.ModelVencaniceRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ana
 */
@Service
public class ModelVencaniceService {

    private final ModelVencaniceRepository repo;
    private final ModelVencaniceMapper mapper;

    public ModelVencaniceService(ModelVencaniceRepository repo,
            ModelVencaniceMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public List<ModelVencaniceDto> findAll() {
        return repo.findAll().stream()
                .map(mapper::toDto).collect(Collectors.toList());
    }

    public ModelVencaniceDto findById(int id) {
        return repo.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("ModelVencanice", id));
    }

    public ModelVencaniceDto create(ModelVencaniceDto dto) {
        dto.setIdModel(null);
        return mapper.toDto(repo.save(mapper.toEntity(dto)));
    }

    public ModelVencaniceDto update(int id, ModelVencaniceDto dto) {
        var postojeci = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ModelVencanice", id));
        dto.setIdModel(postojeci.getIdModel());
        return mapper.toDto(repo.save(mapper.toEntity(dto)));
    }

    public void delete(int id) {
        var model = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ModelVencanice", id));
        repo.delete(model);
    }
}
