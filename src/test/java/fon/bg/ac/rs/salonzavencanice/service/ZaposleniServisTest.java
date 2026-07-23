
package fon.bg.ac.rs.salonzavencanice.service;

import fon.bg.ac.rs.salonzavencanice.dto.impl.ZaposleniDto;
import fon.bg.ac.rs.salonzavencanice.entity.impl.Uloga;
import fon.bg.ac.rs.salonzavencanice.entity.impl.Zaposleni;
import fon.bg.ac.rs.salonzavencanice.exception.EntityNotFoundException;
import fon.bg.ac.rs.salonzavencanice.mapper.impl.ZaposleniMapper;
import fon.bg.ac.rs.salonzavencanice.repository.impl.ZaposleniRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Ana
 */
@ExtendWith(MockitoExtension.class)
public class ZaposleniServisTest {

    @Mock
    private ZaposleniRepository repo;

    @Mock
    private ZaposleniMapper mapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private ZaposleniService service;

    private Zaposleni zaposleni;
    private ZaposleniDto zaposleniDto;

    @BeforeEach
    void setUp() {
        zaposleni = new Zaposleni(1, "Marija", "Petrović", "mpetrovic", "tajna123", Uloga.ZAPOSLENI);
        zaposleniDto = new ZaposleniDto(1, "Marija", "Petrović", "mpetrovic", null, Uloga.ZAPOSLENI);
    }

    @Test
    void findAll_vratiListuZaposlenih() {
        when(repo.findAll()).thenReturn(List.of(zaposleni));
        when(mapper.toDto(zaposleni)).thenReturn(zaposleniDto);

        List<ZaposleniDto> result = service.findAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getIme()).isEqualTo("Marija");
        verify(repo).findAll();
    }

    @Test
    void findAll_praznaLista_vratiPraznoPolje() {
        when(repo.findAll()).thenReturn(List.of());

        List<ZaposleniDto> result = service.findAll();

        assertThat(result).isEmpty();
    }

    @Test
    void findById_postojeciId_vratiZaposleniDto() {
        when(repo.findById(1)).thenReturn(Optional.of(zaposleni));
        when(mapper.toDto(zaposleni)).thenReturn(zaposleniDto);

        ZaposleniDto result = service.findById(1);

        assertThat(result.getIdZaposleni()).isEqualTo(1);
        assertThat(result.getIme()).isEqualTo("Marija");
        assertThat(result.getKorisnickoIme()).isEqualTo("mpetrovic");
    }

    @Test
    void findById_nepostojeciId_baciEntityNotFoundException() {
        when(repo.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.findById(99))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Zaposleni")
                .hasMessageContaining("99");
    }

    @Test
    void create_validan_sacuvajIVratiDto() {
        ZaposleniDto ulaz = new ZaposleniDto(null, "Stefan", "Jovanović", "sjovanovic", "lozinka", Uloga.ZAPOSLENI);
        Zaposleni entitet = new Zaposleni(null, "Stefan", "Jovanović", "sjovanovic", "hesirano", Uloga.ZAPOSLENI);
        Zaposleni sacuvan = new Zaposleni(2, "Stefan", "Jovanović", "sjovanovic", "hesirano", Uloga.ZAPOSLENI);
        ZaposleniDto ocekivani = new ZaposleniDto(2, "Stefan", "Jovanović", "sjovanovic", null, Uloga.ZAPOSLENI);

        when(passwordEncoder.encode(anyString())).thenReturn("hesirano");
        when(mapper.toEntity(ulaz)).thenReturn(entitet);
        when(repo.save(entitet)).thenReturn(sacuvan);
        when(mapper.toDto(sacuvan)).thenReturn(ocekivani);

        ZaposleniDto result = service.create(ulaz);

        assertThat(result.getIdZaposleni()).isEqualTo(2);
        assertThat(result.getIme()).isEqualTo("Stefan");
        assertThat(ulaz.getIdZaposleni()).isNull();
        verify(repo).save(entitet);
    }

    @Test
    void create_idUlazaSeIgnorise_setujujeSeNaNull() {
        ZaposleniDto ulaz = new ZaposleniDto(99, "Stefan", "Jovanović", "sjovanovic", "lozinka", Uloga.ZAPOSLENI);
        Zaposleni entitet = new Zaposleni(null, "Stefan", "Jovanović", "sjovanovic", "hesirano", Uloga.ZAPOSLENI);
        Zaposleni sacuvan = new Zaposleni(2, "Stefan", "Jovanović", "sjovanovic", "hesirano", Uloga.ZAPOSLENI);
        ZaposleniDto ocekivani = new ZaposleniDto(2, "Stefan", "Jovanović", "sjovanovic", null, Uloga.ZAPOSLENI);

        when(passwordEncoder.encode(anyString())).thenReturn("hesirano");
        when(mapper.toEntity(ulaz)).thenReturn(entitet);
        when(repo.save(entitet)).thenReturn(sacuvan);
        when(mapper.toDto(sacuvan)).thenReturn(ocekivani);

        service.create(ulaz);

        assertThat(ulaz.getIdZaposleni()).isNull();
        verify(repo, never()).findById(any());
    }

    @Test
    void update_postojeciId_azurirajIVratiDto() {
        ZaposleniDto ulaz = new ZaposleniDto(null, "Marija", "Nikolić", "mnikolic", null, Uloga.ZAPOSLENI);
        Zaposleni entitet = new Zaposleni(1, "Marija", "Nikolić", "mnikolic", null, Uloga.ZAPOSLENI);
        ZaposleniDto ocekivani = new ZaposleniDto(1, "Marija", "Nikolić", "mnikolic", null, Uloga.ZAPOSLENI);

        when(repo.findById(1)).thenReturn(Optional.of(zaposleni));
        when(mapper.toEntity(ulaz)).thenReturn(entitet);
        when(repo.save(entitet)).thenReturn(entitet);
        when(mapper.toDto(entitet)).thenReturn(ocekivani);

        ZaposleniDto result = service.update(1, ulaz);

        assertThat(result.getIdZaposleni()).isEqualTo(1);
        assertThat(result.getPrezime()).isEqualTo("Nikolić");
        assertThat(ulaz.getIdZaposleni()).isEqualTo(1);
    }

    @Test
    void update_nepostojeciId_baciEntityNotFoundException() {
        when(repo.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.update(99, zaposleniDto))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void delete_postojeciId_obrisiZaposlenog() {
        when(repo.findById(1)).thenReturn(Optional.of(zaposleni));

        service.delete(1);

        verify(repo).delete(zaposleni);
    }

    @Test
    void delete_nepostojeciId_baciEntityNotFoundException() {
        when(repo.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.delete(99))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Zaposleni")
                .hasMessageContaining("99");

        verify(repo, never()).deleteById(any());
    }
}