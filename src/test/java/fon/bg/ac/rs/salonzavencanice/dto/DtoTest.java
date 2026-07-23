
package fon.bg.ac.rs.salonzavencanice.dto;

import fon.bg.ac.rs.salonzavencanice.dto.impl.GradDto;
import fon.bg.ac.rs.salonzavencanice.dto.impl.IznajmljivanjeDto;
import fon.bg.ac.rs.salonzavencanice.dto.impl.KlijentDto;
import fon.bg.ac.rs.salonzavencanice.dto.impl.ModelVencaniceDto;
import fon.bg.ac.rs.salonzavencanice.dto.impl.VencanicaDto;
import fon.bg.ac.rs.salonzavencanice.dto.impl.ZaposleniDto;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

import fon.bg.ac.rs.salonzavencanice.entity.impl.Uloga;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Ana
 */
public class DtoTest {

    // GradDto 
    @Test
    void gradDto_noArgsConstructor() {
        GradDto dto = new GradDto();
        assertThat(dto).isNotNull();
        assertThat(dto.getIdGrad()).isNull();
        assertThat(dto.getNaziv()).isNull();
    }

    @Test
    void gradDto_allArgsConstructor() {
        GradDto dto = new GradDto(1, "Beograd");
        assertThat(dto.getIdGrad()).isEqualTo(1);
        assertThat(dto.getNaziv()).isEqualTo("Beograd");
    }

    @Test
    void gradDto_setters() {
        GradDto dto = new GradDto();
        dto.setIdGrad(5);
        dto.setNaziv("Novi Sad");
        assertThat(dto.getIdGrad()).isEqualTo(5);
        assertThat(dto.getNaziv()).isEqualTo("Novi Sad");
    }

    @Test
    void gradDto_equalsAndHashCode() {
        GradDto a = new GradDto(1, "Beograd");
        GradDto b = new GradDto(1, "Beograd");
        assertThat(a).isEqualTo(b);
        assertThat(a.hashCode()).isEqualTo(b.hashCode());
    }

    @Test
    void gradDto_toString() {
        GradDto dto = new GradDto(1, "Beograd");
        assertThat(dto.toString()).contains("Beograd");
    }

    // ZaposleniDto
    @Test
    void zaposleniDto_noArgsConstructor() {
        ZaposleniDto dto = new ZaposleniDto();
        assertThat(dto).isNotNull();
        assertThat(dto.getIdZaposleni()).isNull();
    }

    @Test
    void zaposleniDto_allArgsConstructor() {
        ZaposleniDto dto = new ZaposleniDto(1, "Marko", "Markovic", "mmarkovic", "pass123", Uloga.ZAPOSLENI);
        assertThat(dto.getIdZaposleni()).isEqualTo(1);
        assertThat(dto.getIme()).isEqualTo("Marko");
        assertThat(dto.getPrezime()).isEqualTo("Markovic");
        assertThat(dto.getKorisnickoIme()).isEqualTo("mmarkovic");
        assertThat(dto.getLozinka()).isEqualTo("pass123");
    }

    @Test
    void zaposleniDto_setters() {
        ZaposleniDto dto = new ZaposleniDto();
        dto.setIdZaposleni(2);
        dto.setIme("Ana");
        dto.setPrezime("Anic");
        dto.setKorisnickoIme("aanic");
        dto.setLozinka("tajnaLozinka");
        assertThat(dto.getIdZaposleni()).isEqualTo(2);
        assertThat(dto.getIme()).isEqualTo("Ana");
        assertThat(dto.getPrezime()).isEqualTo("Anic");
        assertThat(dto.getKorisnickoIme()).isEqualTo("aanic");
        assertThat(dto.getLozinka()).isEqualTo("tajnaLozinka");
    }

    @Test
    void zaposleniDto_equalsAndHashCode() {
        ZaposleniDto a = new ZaposleniDto(1, "Ana", "Anic", "aanic", "pass", Uloga.ZAPOSLENI);
        ZaposleniDto b = new ZaposleniDto(1, "Ana", "Anic", "aanic", "pass", Uloga.ZAPOSLENI);
        assertThat(a).isEqualTo(b);
        assertThat(a.hashCode()).isEqualTo(b.hashCode());
    }

    //  KlijentDto
    @Test
    void klijentDto_noArgsConstructor() {
        KlijentDto dto = new KlijentDto();
        assertThat(dto).isNotNull();
        assertThat(dto.getIdKlijent()).isNull();
    }

    @Test
    void klijentDto_allArgsConstructor() {
        KlijentDto dto = new KlijentDto(1, 25, "0641234567", "Jelena", "Jovic", "jelena@mail.com", "Kralja Petra 1", 1);
        assertThat(dto.getIdKlijent()).isEqualTo(1);
        assertThat(dto.getGodine()).isEqualTo(25);
        assertThat(dto.getTelefon()).isEqualTo("0641234567");
        assertThat(dto.getIme()).isEqualTo("Jelena");
        assertThat(dto.getPrezime()).isEqualTo("Jovic");
        assertThat(dto.getEmail()).isEqualTo("jelena@mail.com");
        assertThat(dto.getAdresa()).isEqualTo("Kralja Petra 1");
        assertThat(dto.getGradId()).isEqualTo(1);
    }

    @Test
    void klijentDto_setters() {
        KlijentDto dto = new KlijentDto();
        dto.setIdKlijent(3);
        dto.setGodine(30);
        dto.setTelefon("0601111111");
        dto.setIme("Milica");
        dto.setPrezime("Milicic");
        dto.setEmail("milica@mail.com");
        dto.setAdresa("Nemanjina 5");
        dto.setGradId(2);
        assertThat(dto.getIdKlijent()).isEqualTo(3);
        assertThat(dto.getGodine()).isEqualTo(30);
        assertThat(dto.getTelefon()).isEqualTo("0601111111");
        assertThat(dto.getIme()).isEqualTo("Milica");
        assertThat(dto.getPrezime()).isEqualTo("Milicic");
        assertThat(dto.getEmail()).isEqualTo("milica@mail.com");
        assertThat(dto.getAdresa()).isEqualTo("Nemanjina 5");
        assertThat(dto.getGradId()).isEqualTo(2);
    }

    @Test
    void klijentDto_equalsAndHashCode() {
        KlijentDto a = new KlijentDto(1, 25, "064", "Ana", "Anic", "a@a.com", "ul 1", 1);
        KlijentDto b = new KlijentDto(1, 25, "064", "Ana", "Anic", "a@a.com", "ul 1", 1);
        assertThat(a).isEqualTo(b);
        assertThat(a.hashCode()).isEqualTo(b.hashCode());
    }

    // ModelVencaniceDto
    @Test
    void modelVencaniceDto_noArgsConstructor() {
        ModelVencaniceDto dto = new ModelVencaniceDto();
        assertThat(dto).isNotNull();
        assertThat(dto.getIdModel()).isNull();
    }

    @Test
    void modelVencaniceDto_allArgsConstructor() {
        ModelVencaniceDto dto = new ModelVencaniceDto(1, "Bella", "Vera Wang", "bela", "svila");
        assertThat(dto.getIdModel()).isEqualTo(1);
        assertThat(dto.getNaziv()).isEqualTo("Bella");
        assertThat(dto.getDizajner()).isEqualTo("Vera Wang");
        assertThat(dto.getBoja()).isEqualTo("bela");
        assertThat(dto.getMaterijal()).isEqualTo("svila");
    }

    @Test
    void modelVencaniceDto_setters() {
        ModelVencaniceDto dto = new ModelVencaniceDto();
        dto.setIdModel(2);
        dto.setNaziv("Elegance");
        dto.setDizajner("Oscar de la Renta");
        dto.setBoja("slonovaca");
        dto.setMaterijal("cipka");
        assertThat(dto.getIdModel()).isEqualTo(2);
        assertThat(dto.getNaziv()).isEqualTo("Elegance");
        assertThat(dto.getDizajner()).isEqualTo("Oscar de la Renta");
        assertThat(dto.getBoja()).isEqualTo("slonovaca");
        assertThat(dto.getMaterijal()).isEqualTo("cipka");
    }

    @Test
    void modelVencaniceDto_equalsAndHashCode() {
        ModelVencaniceDto a = new ModelVencaniceDto(1, "Bella", "Vera Wang", "bela", "svila");
        ModelVencaniceDto b = new ModelVencaniceDto(1, "Bella", "Vera Wang", "bela", "svila");
        assertThat(a).isEqualTo(b);
        assertThat(a.hashCode()).isEqualTo(b.hashCode());
    }

    // VencanicaDto
    @Test
    void vencanicaDto_noArgsConstructor() {
        VencanicaDto dto = new VencanicaDto();
        assertThat(dto).isNotNull();
        assertThat(dto.getIdVencanica()).isNull();
    }

    @Test
    void vencanicaDto_allArgsConstructor() {
        VencanicaDto dto = new VencanicaDto(1, 1, 2020, "SB001", "nema napomene", "38", 1);
        assertThat(dto.getIdVencanica()).isEqualTo(1);
        assertThat(dto.getStatus()).isEqualTo(1);
        assertThat(dto.getGodinaProizvodnje()).isEqualTo(2020);
        assertThat(dto.getSerijskiBroj()).isEqualTo("SB001");
        assertThat(dto.getNapomene()).isEqualTo("nema napomene");
        assertThat(dto.getVelicina()).isEqualTo("38");
        assertThat(dto.getModelId()).isEqualTo(1);
    }

    @Test
    void vencanicaDto_setters() {
        VencanicaDto dto = new VencanicaDto();
        dto.setIdVencanica(5);
        dto.setStatus(0);
        dto.setGodinaProizvodnje(2022);
        dto.setSerijskiBroj("SB999");
        dto.setNapomene("dobro stanje");
        dto.setVelicina("40");
        dto.setModelId(3);
        assertThat(dto.getIdVencanica()).isEqualTo(5);
        assertThat(dto.getStatus()).isEqualTo(0);
        assertThat(dto.getGodinaProizvodnje()).isEqualTo(2022);
        assertThat(dto.getSerijskiBroj()).isEqualTo("SB999");
        assertThat(dto.getNapomene()).isEqualTo("dobro stanje");
        assertThat(dto.getVelicina()).isEqualTo("40");
        assertThat(dto.getModelId()).isEqualTo(3);
    }

    @Test
    void vencanicaDto_equalsAndHashCode() {
        VencanicaDto a = new VencanicaDto(1, 1, 2020, "SB001", "n", "38", 1);
        VencanicaDto b = new VencanicaDto(1, 1, 2020, "SB001", "n", "38", 1);
        assertThat(a).isEqualTo(b);
        assertThat(a.hashCode()).isEqualTo(b.hashCode());
    }

    // IznajmljivanjeDto
    @Test
    void iznajmljivanjeDto_noArgsConstructor() {
        IznajmljivanjeDto dto = new IznajmljivanjeDto();
        assertThat(dto).isNotNull();
        assertThat(dto.getIdIznajmljivanje()).isNull();
    }

    @Test
    void iznajmljivanjeDto_allArgsConstructor() {
        LocalDate uzimanje = LocalDate.of(2024, 5, 1);
        LocalDate vracanje = LocalDate.of(2024, 5, 10);
        IznajmljivanjeDto dto = new IznajmljivanjeDto(1, uzimanje, vracanje, 1, 2, 3);
        assertThat(dto.getIdIznajmljivanje()).isEqualTo(1);
        assertThat(dto.getDatumUzimanja()).isEqualTo(uzimanje);
        assertThat(dto.getDatumVracanja()).isEqualTo(vracanje);
        assertThat(dto.getVencanicaId()).isEqualTo(1);
        assertThat(dto.getKlijentId()).isEqualTo(2);
        assertThat(dto.getZaposleniId()).isEqualTo(3);
    }

    @Test
    void iznajmljivanjeDto_setters() {
        IznajmljivanjeDto dto = new IznajmljivanjeDto();
        LocalDate uzimanje = LocalDate.of(2024, 6, 1);
        LocalDate vracanje = LocalDate.of(2024, 6, 15);
        dto.setIdIznajmljivanje(10);
        dto.setDatumUzimanja(uzimanje);
        dto.setDatumVracanja(vracanje);
        dto.setVencanicaId(5);
        dto.setKlijentId(6);
        dto.setZaposleniId(7);
        assertThat(dto.getIdIznajmljivanje()).isEqualTo(10);
        assertThat(dto.getDatumUzimanja()).isEqualTo(uzimanje);
        assertThat(dto.getDatumVracanja()).isEqualTo(vracanje);
        assertThat(dto.getVencanicaId()).isEqualTo(5);
        assertThat(dto.getKlijentId()).isEqualTo(6);
        assertThat(dto.getZaposleniId()).isEqualTo(7);
    }

    @Test
    void iznajmljivanjeDto_equalsAndHashCode() {
        LocalDate d1 = LocalDate.of(2024, 1, 1);
        LocalDate d2 = LocalDate.of(2024, 1, 10);
        IznajmljivanjeDto a = new IznajmljivanjeDto(1, d1, d2, 1, 2, 3);
        IznajmljivanjeDto b = new IznajmljivanjeDto(1, d1, d2, 1, 2, 3);
        assertThat(a).isEqualTo(b);
        assertThat(a.hashCode()).isEqualTo(b.hashCode());
    }
}

