
package fon.bg.ac.rs.salonzavencanice.service;

import fon.bg.ac.rs.salonzavencanice.dto.impl.GradDto;
import fon.bg.ac.rs.salonzavencanice.entity.impl.Grad;
import fon.bg.ac.rs.salonzavencanice.exception.EntityNotFoundException;
import fon.bg.ac.rs.salonzavencanice.mapper.impl.GradMapper;
import fon.bg.ac.rs.salonzavencanice.repository.impl.GradRepository;
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
public class GradServisTest {

    @Mock
    private GradRepository gradRepository;

    @Mock
    private GradMapper gradMapper;

    @InjectMocks
    private GradService gradService;

    private Grad grad;
    private GradDto gradDto;

    @BeforeEach
    void setUp() {
        grad = new Grad(1, "Beograd");
        gradDto = new GradDto(1, "Beograd");
    }

    @Test
    void findAll_vratiListuGradova() {
        when(gradRepository.findAll()).thenReturn(List.of(grad));
        when(gradMapper.toDto(grad)).thenReturn(gradDto);

        List<GradDto> result = gradService.findAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNaziv()).isEqualTo("Beograd");
        verify(gradRepository).findAll();
    }

    @Test
    void findAll_praznaLista_vratiPraznoPolje() {
        when(gradRepository.findAll()).thenReturn(List.of());

        List<GradDto> result = gradService.findAll();

        assertThat(result).isEmpty();
    }

    @Test
    void findById_postojeciId_vratiGradDto() {
        when(gradRepository.findById(1)).thenReturn(Optional.of(grad));
        when(gradMapper.toDto(grad)).thenReturn(gradDto);

        GradDto result = gradService.findById(1);

        assertThat(result.getIdGrad()).isEqualTo(1);
        assertThat(result.getNaziv()).isEqualTo("Beograd");
    }

    @Test
    void findById_nepostojeciId_baciEntityNotFoundException() {
        when(gradRepository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> gradService.findById(99))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Grad")
                .hasMessageContaining("99");
    }

    @Test
    void create_validan_sacuvajIVratiDto() {
        GradDto ulaz = new GradDto(null, "Novi Sad");
        Grad entitet = new Grad(null, "Novi Sad");
        Grad sacuvan = new Grad(2, "Novi Sad");
        GradDto ocekivani = new GradDto(2, "Novi Sad");

        when(gradMapper.toEntity(ulaz)).thenReturn(entitet);
        when(gradRepository.save(entitet)).thenReturn(sacuvan);
        when(gradMapper.toDto(sacuvan)).thenReturn(ocekivani);

        GradDto result = gradService.create(ulaz);

        assertThat(result.getIdGrad()).isEqualTo(2);
        assertThat(result.getNaziv()).isEqualTo("Novi Sad");
        assertThat(ulaz.getIdGrad()).isNull();
        verify(gradRepository).save(entitet);
    }

    @Test
    void create_idUlazaSeIgnorise_setujujeSeNaNull() {
        GradDto ulaz = new GradDto(99, "Kragujevac");
        Grad entitet = new Grad(null, "Kragujevac");
        Grad sacuvan = new Grad(3, "Kragujevac");
        GradDto ocekivani = new GradDto(3, "Kragujevac");

        when(gradMapper.toEntity(ulaz)).thenReturn(entitet);
        when(gradRepository.save(entitet)).thenReturn(sacuvan);
        when(gradMapper.toDto(sacuvan)).thenReturn(ocekivani);

        GradDto result = gradService.create(ulaz);

        assertThat(ulaz.getIdGrad()).isNull();
        assertThat(result.getIdGrad()).isEqualTo(3);
        verify(gradRepository, never()).findById(any());
    }

    @Test
    void update_postojeciId_azurirajIVratiDto() {
        GradDto ulaz = new GradDto(null, "Niš");
        Grad entitet = new Grad(1, "Niš");
        GradDto ocekivani = new GradDto(1, "Niš");

        when(gradRepository.findById(1)).thenReturn(Optional.of(grad));
        when(gradMapper.toEntity(ulaz)).thenReturn(entitet);
        when(gradRepository.save(entitet)).thenReturn(entitet);
        when(gradMapper.toDto(entitet)).thenReturn(ocekivani);

        GradDto result = gradService.update(1, ulaz);

        assertThat(result.getIdGrad()).isEqualTo(1);
        assertThat(result.getNaziv()).isEqualTo("Niš");
        assertThat(ulaz.getIdGrad()).isEqualTo(1);
    }

    @Test
    void update_nepostojeciId_baciEntityNotFoundException() {
        when(gradRepository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> gradService.update(99, gradDto))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void delete_postojeciId_obrisiGrad() {
        when(gradRepository.findById(1)).thenReturn(Optional.of(grad));

        gradService.delete(1);

        verify(gradRepository).delete(grad);
    }

    @Test
    void delete_nepostojeciId_baciEntityNotFoundException() {
        when(gradRepository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> gradService.delete(99))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("99");

        verify(gradRepository, never()).deleteById(any());
    }
}