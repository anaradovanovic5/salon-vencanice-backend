
package fon.bg.ac.rs.salonzavencanice.service;

import fon.bg.ac.rs.salonzavencanice.dto.impl.ModelVencaniceDto;
import fon.bg.ac.rs.salonzavencanice.entity.impl.ModelVencanice;
import fon.bg.ac.rs.salonzavencanice.exception.EntityNotFoundException;
import fon.bg.ac.rs.salonzavencanice.mapper.impl.ModelVencaniceMapper;
import fon.bg.ac.rs.salonzavencanice.repository.impl.ModelVencaniceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Ana
 */
@ExtendWith(MockitoExtension.class)
public class ModelVencaniceServisTest {

    @Mock
    private ModelVencaniceRepository repo;

    @Mock
    private ModelVencaniceMapper mapper;

    @InjectMocks
    private ModelVencaniceService service;

    private ModelVencanice model;
    private ModelVencaniceDto modelDto;

    @BeforeEach
    void setUp() {
        model = new ModelVencanice(1, "Princess", "Vera Wang", "Bela", "Svila");
        modelDto = new ModelVencaniceDto(1, "Princess", "Vera Wang", "Bela", "Svila");
    }

    @Test
    void findAll_vratiListuModela() {
        when(repo.findAll()).thenReturn(List.of(model));
        when(mapper.toDto(model)).thenReturn(modelDto);

        List<ModelVencaniceDto> result = service.findAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNaziv()).isEqualTo("Princess");
        verify(repo).findAll();
    }

    @Test
    void findAll_praznaLista_vratiPraznoPolje() {
        when(repo.findAll()).thenReturn(List.of());

        List<ModelVencaniceDto> result = service.findAll();

        assertThat(result).isEmpty();
    }

    @Test
    void findById_postojeciId_vratiModelDto() {
        when(repo.findById(1)).thenReturn(Optional.of(model));
        when(mapper.toDto(model)).thenReturn(modelDto);

        ModelVencaniceDto result = service.findById(1);

        assertThat(result.getIdModel()).isEqualTo(1);
        assertThat(result.getNaziv()).isEqualTo("Princess");
        assertThat(result.getDizajner()).isEqualTo("Vera Wang");
    }

    @Test
    void findById_nepostojeciId_baciEntityNotFoundException() {
        when(repo.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.findById(99))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("ModelVencanice")
                .hasMessageContaining("99");
    }

    @Test
    void create_validan_sacuvajIVratiDto() {
        ModelVencaniceDto ulaz = new ModelVencaniceDto(null, "Ballroom", "Dior", "Bela", "Čipka");
        ModelVencanice entitet = new ModelVencanice(null, "Ballroom", "Dior", "Bela", "Čipka");
        ModelVencanice sacuvan = new ModelVencanice(2, "Ballroom", "Dior", "Bela", "Čipka");
        ModelVencaniceDto ocekivani = new ModelVencaniceDto(2, "Ballroom", "Dior", "Bela", "Čipka");

        when(mapper.toEntity(ulaz)).thenReturn(entitet);
        when(repo.save(entitet)).thenReturn(sacuvan);
        when(mapper.toDto(sacuvan)).thenReturn(ocekivani);

        ModelVencaniceDto result = service.create(ulaz);

        assertThat(result.getIdModel()).isEqualTo(2);
        assertThat(result.getNaziv()).isEqualTo("Ballroom");
        assertThat(ulaz.getIdModel()).isNull();
        verify(repo).save(entitet);
    }

    @Test
    void create_idUlazaSeIgnorise_setujujeSeNaNull() {
        ModelVencaniceDto ulaz = new ModelVencaniceDto(99, "Ballroom", "Dior", "Bela", "Čipka");
        ModelVencanice entitet = new ModelVencanice(null, "Ballroom", "Dior", "Bela", "Čipka");
        ModelVencanice sacuvan = new ModelVencanice(2, "Ballroom", "Dior", "Bela", "Čipka");
        ModelVencaniceDto ocekivani = new ModelVencaniceDto(2, "Ballroom", "Dior", "Bela", "Čipka");

        when(mapper.toEntity(ulaz)).thenReturn(entitet);
        when(repo.save(entitet)).thenReturn(sacuvan);
        when(mapper.toDto(sacuvan)).thenReturn(ocekivani);

        service.create(ulaz);

        assertThat(ulaz.getIdModel()).isNull();
        verify(repo, never()).findById(any());
    }

    @Test
    void update_postojeciId_azurirajIVratiDto() {
        ModelVencaniceDto ulaz = new ModelVencaniceDto(null, "Mermaid", "Chanel", "Slonova Kost", "Saten");
        ModelVencanice entitet = new ModelVencanice(1, "Mermaid", "Chanel", "Slonova Kost", "Saten");
        ModelVencaniceDto ocekivani = new ModelVencaniceDto(1, "Mermaid", "Chanel", "Slonova Kost", "Saten");

        when(repo.findById(1)).thenReturn(Optional.of(model));
        when(mapper.toEntity(ulaz)).thenReturn(entitet);
        when(repo.save(entitet)).thenReturn(entitet);
        when(mapper.toDto(entitet)).thenReturn(ocekivani);

        ModelVencaniceDto result = service.update(1, ulaz);

        assertThat(result.getIdModel()).isEqualTo(1);
        assertThat(result.getNaziv()).isEqualTo("Mermaid");
        assertThat(ulaz.getIdModel()).isEqualTo(1);
    }

    @Test
    void update_nepostojeciId_baciEntityNotFoundException() {
        when(repo.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.update(99, modelDto))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void delete_postojeciId_obrisiModel() {
        when(repo.findById(1)).thenReturn(Optional.of(model));

        service.delete(1);

        verify(repo).delete(model);
    }

    @Test
    void delete_nepostojeciId_baciEntityNotFoundException() {
        when(repo.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.delete(99))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("ModelVencanice")
                .hasMessageContaining("99");

        verify(repo, never()).deleteById(any());
    }
}