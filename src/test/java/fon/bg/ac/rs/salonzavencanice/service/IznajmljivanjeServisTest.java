
package fon.bg.ac.rs.salonzavencanice.service;

import fon.bg.ac.rs.salonzavencanice.dto.impl.IznajmljivanjeDto;
import fon.bg.ac.rs.salonzavencanice.entity.impl.*;
import fon.bg.ac.rs.salonzavencanice.exception.EntityNotFoundException;
import fon.bg.ac.rs.salonzavencanice.mapper.impl.IznajmljivanjeMapper;
import fon.bg.ac.rs.salonzavencanice.repository.impl.IznajmljivanjeRepository;
import fon.bg.ac.rs.salonzavencanice.repository.impl.VencanicaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Ana
 */
@ExtendWith(MockitoExtension.class)
public class IznajmljivanjeServisTest {

    @Mock
    private IznajmljivanjeRepository repo;

    @Mock
    private IznajmljivanjeMapper mapper;

    @Mock
    private VencanicaRepository vencanicaRepo;

    @InjectMocks
    private IznajmljivanjeService service;

    private Vencanica slobodnaVencanica;
    private Vencanica zauzetaVencanica;
    private Klijent klijent;
    private Zaposleni zaposleni;
    private Iznajmljivanje iznajmljivanje;
    private IznajmljivanjeDto iznajmljivanjeDto;

    @BeforeEach
    void setUp() {
        ModelVencanice model = new ModelVencanice(1, "Princess", "Vera Wang", "Bela", "Svila");
        slobodnaVencanica = new Vencanica(1, 0, 2020, "SB-001", null, "38", model);
        zauzetaVencanica = new Vencanica(2, 1, 2021, "SB-002", null, "40", model);

        klijent = new Klijent(1, 25, "0641111111", "Ana", "Marković", "ana@mail.com", "Ulica 1", null);
        zaposleni = new Zaposleni(1, "Stefan", "Jovanović", "sjovanovic", "lozinka", Uloga.ZAPOSLENI);

        iznajmljivanje = new Iznajmljivanje(
                1,
                LocalDate.of(2025, 5, 1),
                LocalDate.of(2025, 5, 10),
                slobodnaVencanica,
                klijent,
                zaposleni
        );

        iznajmljivanjeDto = new IznajmljivanjeDto(
                1,
                LocalDate.of(2025, 5, 1),
                LocalDate.of(2025, 5, 10),
                1, 1, 1
        );
    }

    @Test
    void findAll_vratiListuIznajmljivanja() {
        when(repo.findAll()).thenReturn(List.of(iznajmljivanje));
        when(mapper.toDto(iznajmljivanje)).thenReturn(iznajmljivanjeDto);

        List<IznajmljivanjeDto> result = service.findAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getVencanicaId()).isEqualTo(1);
        verify(repo).findAll();
    }

    @Test
    void findAll_praznaLista_vratiPraznoPolje() {
        when(repo.findAll()).thenReturn(List.of());

        List<IznajmljivanjeDto> result = service.findAll();

        assertThat(result).isEmpty();
    }

    @Test
    void findById_postojeciId_vratiIznajmljivanjeDto() {
        when(repo.findById(1)).thenReturn(Optional.of(iznajmljivanje));
        when(mapper.toDto(iznajmljivanje)).thenReturn(iznajmljivanjeDto);

        IznajmljivanjeDto result = service.findById(1);

        assertThat(result.getIdIznajmljivanje()).isEqualTo(1);
        assertThat(result.getKlijentId()).isEqualTo(1);
        assertThat(result.getDatumUzimanja()).isEqualTo(LocalDate.of(2025, 5, 1));
    }

    @Test
    void findById_nepostojeciId_baciEntityNotFoundException() {
        when(repo.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.findById(99))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Iznajmljivanje")
                .hasMessageContaining("99");
    }

    @Test
    void findByKlijent_vratiSvaIznajmljivanjaZaKlijenta() {
        when(repo.findByKlijent_IdKlijent(1)).thenReturn(List.of(iznajmljivanje));
        when(mapper.toDto(iznajmljivanje)).thenReturn(iznajmljivanjeDto);

        List<IznajmljivanjeDto> result = service.findByKlijent(1);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getKlijentId()).isEqualTo(1);
        verify(repo).findByKlijent_IdKlijent(1);
    }

    @Test
    void findByKlijent_klijentBezIznajmljivanja_vratiPraznoPolje() {
        when(repo.findByKlijent_IdKlijent(99)).thenReturn(List.of());

        List<IznajmljivanjeDto> result = service.findByKlijent(99);

        assertThat(result).isEmpty();
    }

    @Test
    void findByKlijent_viseIznajmljivanja_svaSeMape() {
        ModelVencanice model = new ModelVencanice(1, "Princess", "Vera Wang", "Bela", "Svila");
        Vencanica vencanica2 = new Vencanica(3, 1, 2022, "SB-003", null, "42", model);
        Iznajmljivanje drugo = new Iznajmljivanje(2,
                LocalDate.of(2025, 7, 1), LocalDate.of(2025, 7, 5),
                vencanica2, klijent, zaposleni);
        IznajmljivanjeDto drugiDto = new IznajmljivanjeDto(2,
                LocalDate.of(2025, 7, 1), LocalDate.of(2025, 7, 5),
                3, 1, 1);

        when(repo.findByKlijent_IdKlijent(1)).thenReturn(List.of(iznajmljivanje, drugo));
        when(mapper.toDto(iznajmljivanje)).thenReturn(iznajmljivanjeDto);
        when(mapper.toDto(drugo)).thenReturn(drugiDto);

        List<IznajmljivanjeDto> result = service.findByKlijent(1);

        assertThat(result).hasSize(2);
        verify(mapper, times(2)).toDto(any());
    }

    @Test
    void findAktivna_vratiAktivnaIznajmljivanja() {
        when(repo.findAktivna()).thenReturn(List.of(iznajmljivanje));
        when(mapper.toDto(iznajmljivanje)).thenReturn(iznajmljivanjeDto);

        List<IznajmljivanjeDto> result = service.findAktivna();

        assertThat(result).hasSize(1);
        verify(repo).findAktivna();
    }

    @Test
    void findAktivna_nemaAktivnih_vratiPraznoPolje() {
        when(repo.findAktivna()).thenReturn(List.of());

        List<IznajmljivanjeDto> result = service.findAktivna();

        assertThat(result).isEmpty();
    }

    @Test
    void create_slobodnaVencanica_kreirajIznajmljivanjeIPostaviStatusNaZauzeto() {
        IznajmljivanjeDto ulaz = new IznajmljivanjeDto(
                null,
                LocalDate.of(2025, 6, 1),
                LocalDate.of(2025, 6, 10),
                1, 1, 1
        );
        Iznajmljivanje entitet = new Iznajmljivanje(null,
                LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 10),
                slobodnaVencanica, klijent, zaposleni);
        Iznajmljivanje sacuvan = new Iznajmljivanje(2,
                LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 10),
                slobodnaVencanica, klijent, zaposleni);
        IznajmljivanjeDto ocekivani = new IznajmljivanjeDto(2,
                LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 10),
                1, 1, 1);

        when(vencanicaRepo.findById(1)).thenReturn(Optional.of(slobodnaVencanica));
        when(mapper.toEntity(ulaz)).thenReturn(entitet);
        when(repo.save(entitet)).thenReturn(sacuvan);
        when(mapper.toDto(sacuvan)).thenReturn(ocekivani);

        IznajmljivanjeDto result = service.create(ulaz);

        ArgumentCaptor<Vencanica> vencanicaCaptor = ArgumentCaptor.forClass(Vencanica.class);
        verify(vencanicaRepo).save(vencanicaCaptor.capture());
        assertThat(vencanicaCaptor.getValue().getStatus()).isEqualTo(1);

        assertThat(result.getIdIznajmljivanje()).isEqualTo(2);
        assertThat(ulaz.getIdIznajmljivanje()).isNull();
        verify(repo).save(entitet);
    }

    @Test
    void create_zauzetaVencanica_baciIllegalStateException() {
        IznajmljivanjeDto ulaz = new IznajmljivanjeDto(
                null,
                LocalDate.of(2025, 6, 1),
                LocalDate.of(2025, 6, 10),
                2, 1, 1
        );

        when(vencanicaRepo.findById(2)).thenReturn(Optional.of(zauzetaVencanica));

        assertThatThrownBy(() -> service.create(ulaz))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("nije slobodna");

        verify(repo, never()).save(any());
        verify(vencanicaRepo, never()).save(any());
    }

    @Test
    void create_nepostojecaVencanica_baciEntityNotFoundException() {
        IznajmljivanjeDto ulaz = new IznajmljivanjeDto(
                null,
                LocalDate.of(2025, 6, 1),
                LocalDate.of(2025, 6, 10),
                99, 1, 1
        );

        when(vencanicaRepo.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.create(ulaz))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Vencanica")
                .hasMessageContaining("99");

        verify(repo, never()).save(any());
    }

    @Test
    void create_idUlazaSeIgnorise_setujujeSeNaNull() {
        IznajmljivanjeDto ulaz = new IznajmljivanjeDto(
                99,
                LocalDate.of(2025, 6, 1),
                LocalDate.of(2025, 6, 10),
                1, 1, 1
        );
        Iznajmljivanje entitet = new Iznajmljivanje(null,
                LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 10),
                slobodnaVencanica, klijent, zaposleni);
        Iznajmljivanje sacuvan = new Iznajmljivanje(5,
                LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 10),
                slobodnaVencanica, klijent, zaposleni);
        IznajmljivanjeDto ocekivani = new IznajmljivanjeDto(5,
                LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 10),
                1, 1, 1);

        when(vencanicaRepo.findById(1)).thenReturn(Optional.of(slobodnaVencanica));
        when(mapper.toEntity(ulaz)).thenReturn(entitet);
        when(repo.save(entitet)).thenReturn(sacuvan);
        when(mapper.toDto(sacuvan)).thenReturn(ocekivani);

        service.create(ulaz);

        assertThat(ulaz.getIdIznajmljivanje()).isNull();
    }

    @Test
    void delete_postojeceIznajmljivanje_obrisiIOslobodiVencanicu() {
        when(repo.findById(1)).thenReturn(Optional.of(iznajmljivanje));

        iznajmljivanje.getVencanica().setStatus(1);

        service.delete(1);

        ArgumentCaptor<Vencanica> vencanicaCaptor = ArgumentCaptor.forClass(Vencanica.class);
        verify(vencanicaRepo).save(vencanicaCaptor.capture());
        assertThat(vencanicaCaptor.getValue().getStatus()).isEqualTo(0);

        verify(repo).deleteById(1);
    }

    @Test
    void delete_nepostojeciId_baciEntityNotFoundException() {
        when(repo.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.delete(99))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Iznajmljivanje")
                .hasMessageContaining("99");

        verify(repo, never()).deleteById(any());
        verify(vencanicaRepo, never()).save(any());
    }

    @Test
    void delete_iznajmljivanjeBezVencanice_obrisiSamoBezMenjanjaStatusa() {
        Iznajmljivanje bezVencanice = new Iznajmljivanje(
                3,
                LocalDate.of(2025, 5, 1),
                LocalDate.of(2025, 5, 10),
                null, klijent, zaposleni
        );
        when(repo.findById(3)).thenReturn(Optional.of(bezVencanice));

        service.delete(3);

        verify(vencanicaRepo, never()).save(any());
        verify(repo).deleteById(3);
    }
}