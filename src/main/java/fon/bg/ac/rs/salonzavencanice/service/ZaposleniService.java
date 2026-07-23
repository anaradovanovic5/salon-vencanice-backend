package fon.bg.ac.rs.salonzavencanice.service;

import fon.bg.ac.rs.salonzavencanice.dto.impl.ZaposleniDto;
import fon.bg.ac.rs.salonzavencanice.entity.impl.Uloga;
import fon.bg.ac.rs.salonzavencanice.exception.EntityNotFoundException;
import fon.bg.ac.rs.salonzavencanice.mapper.impl.ZaposleniMapper;
import fon.bg.ac.rs.salonzavencanice.repository.impl.ZaposleniRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ana
 */
@Service
public class ZaposleniService {

    private final ZaposleniRepository repo;
    private final ZaposleniMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public ZaposleniService(ZaposleniRepository repo, ZaposleniMapper mapper, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
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
        if (dto.getUloga() == null) {
            dto.setUloga(Uloga.ZAPOSLENI);
        }
        dto.setLozinka(passwordEncoder.encode(dto.getLozinka()));
        return mapper.toDto(repo.save(mapper.toEntity(dto)));
    }

    public ZaposleniDto update(int id, ZaposleniDto dto) {
        var postojeci = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Zaposleni", id));
        dto.setIdZaposleni(id);
        // ako lozinka nije poslata (menja se samo ime/prezime), zadrzi staru hesiranu
        if (dto.getLozinka() == null || dto.getLozinka().isBlank()) {
            dto.setLozinka(postojeci.getLozinka());
        } else {
            dto.setLozinka(passwordEncoder.encode(dto.getLozinka()));
        }
        if (dto.getUloga() == null) {
            dto.setUloga(postojeci.getUloga());
        }
        return mapper.toDto(repo.save(mapper.toEntity(dto)));
    }

    public void delete(int id) {
        var zaposleni = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Zaposleni", id));
        repo.delete(zaposleni);
    }
}