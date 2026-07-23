
package fon.bg.ac.rs.salonzavencanice.service;

import fon.bg.ac.rs.salonzavencanice.dto.impl.KlijentDto;
import fon.bg.ac.rs.salonzavencanice.entity.impl.Grad;
import fon.bg.ac.rs.salonzavencanice.entity.impl.Klijent;
import fon.bg.ac.rs.salonzavencanice.exception.EntityNotFoundException;
import fon.bg.ac.rs.salonzavencanice.mapper.impl.KlijentMapper;
import fon.bg.ac.rs.salonzavencanice.repository.impl.KlijentRepository;
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
public class KlijentServisTest {

    @Mock
    private KlijentRepository repo;

    @Mock
    private KlijentMapper mapper;

    @InjectMocks
    private KlijentService service;

    private Klijent klijent;
    private KlijentDto klijentDto;

    @BeforeEach
    void setUp() {
        Grad grad = new Grad(1, "Beograd");
        klijent = new Klijent(1, 28, "0641234567", "Ana", "Marković", "ana@mail.com", "Ulica 1", grad);
        klijentDto = new KlijentDto(1, 28, "0641234567", "Ana", "Marković", "ana@mail.com", "Ulica 1", 1);
    }

    @Test
    void findAll_vratiListuKlijenata() {
        when(repo.findAll()).thenReturn(List.of(klijent));
        when(mapper.toDto(klijent)).thenReturn(klijentDto);

        List<KlijentDto> result = service.findAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getIme()).isEqualTo("Ana");
        verify(repo).findAll();
    }

    @Test
    void findAll_praznaLista_vratiPraznoPolje() {
        when(repo.findAll()).thenReturn(List.of());

        List<KlijentDto> result = service.findAll();

        assertThat(result).isEmpty();
    }

    @Test
    void findById_postojeciId_vratiKlijentDto() {
        when(repo.findById(1)).thenReturn(Optional.of(klijent));
        when(mapper.toDto(klijent)).thenReturn(klijentDto);

        KlijentDto result = service.findById(1);

        assertThat(result.getIdKlijent()).isEqualTo(1);
        assertThat(result.getIme()).isEqualTo("Ana");
        assertThat(result.getEmail()).isEqualTo("ana@mail.com");
    }

    @Test
    void findById_nepostojeciId_baciEntityNotFoundException() {
        when(repo.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.findById(99))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Klijent")
                .hasMessageContaining("99");
    }

    @Test
    void create_validan_sacuvajIVratiDto() {
        KlijentDto ulaz = new KlijentDto(null, 25, "0651234567", "Milica", "Stojanović",
                "milica@mail.com", "Ulica 2", 1);
        Klijent entitet = new Klijent(null, 25, "0651234567", "Milica", "Stojanović",
                "milica@mail.com", "Ulica 2", new Grad(1, "Beograd"));
        Klijent sacuvan = new Klijent(2, 25, "0651234567", "Milica", "Stojanović",
                "milica@mail.com", "Ulica 2", new Grad(1, "Beograd"));
        KlijentDto ocekivani = new KlijentDto(2, 25, "0651234567", "Milica", "Stojanović",
                "milica@mail.com", "Ulica 2", 1);

        when(mapper.toEntity(ulaz)).thenReturn(entitet);
        when(repo.save(entitet)).thenReturn(sacuvan);
        when(mapper.toDto(sacuvan)).thenReturn(ocekivani);

        KlijentDto result = service.create(ulaz);

        assertThat(result.getIdKlijent()).isEqualTo(2);
        assertThat(result.getIme()).isEqualTo("Milica");
        assertThat(ulaz.getIdKlijent()).isNull();
        verify(repo).save(entitet);
    }

    @Test
    void create_idUlazaSeIgnorise_setujujeSeNaNull() {
        KlijentDto ulaz = new KlijentDto(99, 25, "0651234567", "Milica", "Stojanović",
                "milica@mail.com", "Ulica 2", 1);
        Klijent entitet = new Klijent(null, 25, "0651234567", "Milica", "Stojanović",
                "milica@mail.com", "Ulica 2", new Grad(1, "Beograd"));
        Klijent sacuvan = new Klijent(2, 25, "0651234567", "Milica", "Stojanović",
                "milica@mail.com", "Ulica 2", new Grad(1, "Beograd"));
        KlijentDto ocekivani = new KlijentDto(2, 25, "0651234567", "Milica", "Stojanović",
                "milica@mail.com", "Ulica 2", 1);

        when(mapper.toEntity(ulaz)).thenReturn(entitet);
        when(repo.save(entitet)).thenReturn(sacuvan);
        when(mapper.toDto(sacuvan)).thenReturn(ocekivani);

        service.create(ulaz);

        assertThat(ulaz.getIdKlijent()).isNull();
        verify(repo, never()).findById(any());
    }

    @Test
    void update_postojeciId_azurirajIVratiDto() {
        KlijentDto ulaz = new KlijentDto(null, 30, "0671234567", "Ana", "Lazović",
                "ana.lazovic@mail.com", "Nova Ulica 5", 1);
        Klijent entitet = new Klijent(1, 30, "0671234567", "Ana", "Lazović",
                "ana.lazovic@mail.com", "Nova Ulica 5", new Grad(1, "Beograd"));
        KlijentDto ocekivani = new KlijentDto(1, 30, "0671234567", "Ana", "Lazović",
                "ana.lazovic@mail.com", "Nova Ulica 5", 1);

        when(repo.findById(1)).thenReturn(Optional.of(klijent));
        when(mapper.toEntity(ulaz)).thenReturn(entitet);
        when(repo.save(entitet)).thenReturn(entitet);
        when(mapper.toDto(entitet)).thenReturn(ocekivani);

        KlijentDto result = service.update(1, ulaz);

        assertThat(result.getIdKlijent()).isEqualTo(1);
        assertThat(result.getPrezime()).isEqualTo("Lazović");
        assertThat(ulaz.getIdKlijent()).isEqualTo(1);
    }

    @Test
    void update_nepostojeciId_baciEntityNotFoundException() {
        when(repo.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.update(99, klijentDto))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void delete_postojeciId_obrisiKlijenta() {
        when(repo.findById(1)).thenReturn(Optional.of(klijent));

        service.delete(1);

        verify(repo).delete(klijent);
    }

    @Test
    void delete_nepostojeciId_baciEntityNotFoundException() {
        when(repo.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.delete(99))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Klijent")
                .hasMessageContaining("99");

        verify(repo, never()).deleteById(any());
    }
}