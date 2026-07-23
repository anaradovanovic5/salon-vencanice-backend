
package fon.bg.ac.rs.salonzavencanice.service;

import fon.bg.ac.rs.salonzavencanice.dto.impl.VencanicaDto;
import fon.bg.ac.rs.salonzavencanice.entity.impl.ModelVencanice;
import fon.bg.ac.rs.salonzavencanice.entity.impl.Vencanica;
import fon.bg.ac.rs.salonzavencanice.exception.EntityNotFoundException;
import fon.bg.ac.rs.salonzavencanice.mapper.impl.VencanicaMapper;
import fon.bg.ac.rs.salonzavencanice.repository.impl.VencanicaRepository;
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
public class VencanicaServisTest {

    @Mock
    private VencanicaRepository repo;

    @Mock
    private VencanicaMapper mapper;

    @InjectMocks
    private VencanicaService service;

    private Vencanica vencanica;
    private VencanicaDto vencanicaDto;
    private ModelVencanice model;

    @BeforeEach
    void setUp() {
        model = new ModelVencanice(1, "Princess", "Vera Wang", "Bela", "Svila");
        vencanica = new Vencanica(1, 0, 2020, "SB-001", "Bez napomena", "38", model);
        vencanicaDto = new VencanicaDto(1, 0, 2020, "SB-001", "Bez napomena", "38", 1);
    }

    @Test
    void findAll_vratiListuVencanica() {
        when(repo.findAll()).thenReturn(List.of(vencanica));
        when(mapper.toDto(vencanica)).thenReturn(vencanicaDto);

        List<VencanicaDto> result = service.findAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getSerijskiBroj()).isEqualTo("SB-001");
        verify(repo).findAll();
    }

    @Test
    void findAll_praznaLista_vratiPraznoPolje() {
        when(repo.findAll()).thenReturn(List.of());

        List<VencanicaDto> result = service.findAll();

        assertThat(result).isEmpty();
    }

    @Test
    void findById_postojeciId_vratiVencanicaDto() {
        when(repo.findById(1)).thenReturn(Optional.of(vencanica));
        when(mapper.toDto(vencanica)).thenReturn(vencanicaDto);

        VencanicaDto result = service.findById(1);

        assertThat(result.getIdVencanica()).isEqualTo(1);
        assertThat(result.getSerijskiBroj()).isEqualTo("SB-001");
        assertThat(result.getStatus()).isEqualTo(0);
    }

    @Test
    void findById_nepostojeciId_baciEntityNotFoundException() {
        when(repo.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.findById(99))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Vencanica")
                .hasMessageContaining("99");
    }

    @Test
    void findByVelicina_vratiVencaniceKojeOdgovaraju() {
        when(repo.findByVelicina("38")).thenReturn(List.of(vencanica));
        when(mapper.toDto(vencanica)).thenReturn(vencanicaDto);

        List<VencanicaDto> result = service.findByVelicina("38");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getVelicina()).isEqualTo("38");
    }

    @Test
    void findByVelicina_nepostojecaVelicina_vratiPraznoPolje() {
        when(repo.findByVelicina("99")).thenReturn(List.of());

        List<VencanicaDto> result = service.findByVelicina("99");

        assertThat(result).isEmpty();
    }

    @Test
    void findSlobodne_vratiSamoSlobodneVencanice() {
        when(repo.findByStatus(0)).thenReturn(List.of(vencanica));
        when(mapper.toDto(vencanica)).thenReturn(vencanicaDto);

        List<VencanicaDto> result = service.findSlobodne();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getStatus()).isEqualTo(0);
        verify(repo).findByStatus(0);
    }

    @Test
    void findSlobodne_nemaSlobodnih_vratiPraznoPolje() {
        when(repo.findByStatus(0)).thenReturn(List.of());

        List<VencanicaDto> result = service.findSlobodne();

        assertThat(result).isEmpty();
    }

    @Test
    void findSlobodne_pozivaSeFindByStatusNula() {
        when(repo.findByStatus(0)).thenReturn(List.of());

        service.findSlobodne();

        verify(repo).findByStatus(0);
        verify(repo, never()).findByStatus(1);
    }

    @Test
    void create_validan_sacuvajIVratiDto() {
        VencanicaDto ulaz = new VencanicaDto(null, 0, 2022, "SB-002", null, "40", 1);
        Vencanica entitet = new Vencanica(null, 0, 2022, "SB-002", null, "40", model);
        Vencanica sacuvana = new Vencanica(2, 0, 2022, "SB-002", null, "40", model);
        VencanicaDto ocekivani = new VencanicaDto(2, 0, 2022, "SB-002", null, "40", 1);

        when(mapper.toEntity(ulaz)).thenReturn(entitet);
        when(repo.save(entitet)).thenReturn(sacuvana);
        when(mapper.toDto(sacuvana)).thenReturn(ocekivani);

        VencanicaDto result = service.create(ulaz);

        assertThat(result.getIdVencanica()).isEqualTo(2);
        assertThat(result.getSerijskiBroj()).isEqualTo("SB-002");
        assertThat(ulaz.getIdVencanica()).isNull();
        verify(repo).save(entitet);
    }

    @Test
    void create_idUlazaSeIgnorise_setujujeSeNaNull() {
        VencanicaDto ulaz = new VencanicaDto(99, 0, 2022, "SB-002", null, "40", 1);
        Vencanica entitet = new Vencanica(null, 0, 2022, "SB-002", null, "40", model);
        Vencanica sacuvana = new Vencanica(2, 0, 2022, "SB-002", null, "40", model);
        VencanicaDto ocekivani = new VencanicaDto(2, 0, 2022, "SB-002", null, "40", 1);

        when(mapper.toEntity(ulaz)).thenReturn(entitet);
        when(repo.save(entitet)).thenReturn(sacuvana);
        when(mapper.toDto(sacuvana)).thenReturn(ocekivani);

        service.create(ulaz);

        assertThat(ulaz.getIdVencanica()).isNull();
        verify(repo, never()).findById(any());
    }

    @Test
    void update_postojeciId_azurirajIVratiDto() {
        VencanicaDto ulaz = new VencanicaDto(null, 0, 2021, "SB-001-UPD", "Napomena", "42", 1);
        Vencanica entitet = new Vencanica(1, 0, 2021, "SB-001-UPD", "Napomena", "42", model);
        VencanicaDto ocekivani = new VencanicaDto(1, 0, 2021, "SB-001-UPD", "Napomena", "42", 1);

        when(repo.findById(1)).thenReturn(Optional.of(vencanica));
        when(mapper.toEntity(ulaz)).thenReturn(entitet);
        when(repo.save(entitet)).thenReturn(entitet);
        when(mapper.toDto(entitet)).thenReturn(ocekivani);

        VencanicaDto result = service.update(1, ulaz);

        assertThat(result.getIdVencanica()).isEqualTo(1);
        assertThat(result.getSerijskiBroj()).isEqualTo("SB-001-UPD");
        assertThat(ulaz.getIdVencanica()).isEqualTo(1);
    }

    @Test
    void update_nepostojeciId_baciEntityNotFoundException() {
        when(repo.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.update(99, vencanicaDto))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void delete_postojeciId_obrisiVencanicu() {
        when(repo.findById(1)).thenReturn(Optional.of(vencanica));

        service.delete(1);

        verify(repo).delete(vencanica);
    }

    @Test
    void delete_nepostojeciId_baciEntityNotFoundException() {
        when(repo.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.delete(99))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Vencanica")
                .hasMessageContaining("99");

        verify(repo, never()).deleteById(any());
    }
}