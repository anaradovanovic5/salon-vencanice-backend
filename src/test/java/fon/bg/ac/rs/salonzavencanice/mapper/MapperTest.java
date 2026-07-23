
package fon.bg.ac.rs.salonzavencanice.mapper;

import fon.bg.ac.rs.salonzavencanice.dto.impl.GradDto;
import fon.bg.ac.rs.salonzavencanice.dto.impl.IznajmljivanjeDto;
import fon.bg.ac.rs.salonzavencanice.dto.impl.KlijentDto;
import fon.bg.ac.rs.salonzavencanice.dto.impl.ModelVencaniceDto;
import fon.bg.ac.rs.salonzavencanice.dto.impl.VencanicaDto;
import fon.bg.ac.rs.salonzavencanice.dto.impl.ZaposleniDto;
import fon.bg.ac.rs.salonzavencanice.entity.impl.*;
import fon.bg.ac.rs.salonzavencanice.mapper.impl.GradMapper;
import fon.bg.ac.rs.salonzavencanice.mapper.impl.IznajmljivanjeMapper;
import fon.bg.ac.rs.salonzavencanice.mapper.impl.KlijentMapper;
import fon.bg.ac.rs.salonzavencanice.mapper.impl.ModelVencaniceMapper;
import fon.bg.ac.rs.salonzavencanice.mapper.impl.VencanicaMapper;
import fon.bg.ac.rs.salonzavencanice.mapper.impl.ZaposleniMapper;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Ana
 */
@SpringBootTest
public class MapperTest {

    @Autowired
    private GradMapper gradMapper;

    @Autowired
    private ZaposleniMapper zaposleniMapper;

    @Autowired
    private KlijentMapper klijentMapper;

    @Autowired
    private ModelVencaniceMapper modelVencaniceMapper;

    @Autowired
    private VencanicaMapper vencanicaMapper;

    @Autowired
    private IznajmljivanjeMapper iznajmljivanjeMapper;

    // GradMapper 
    @Test
    void gradMapper_toDto_preslikavataNaziv() {
        Grad grad = new Grad(1, "Beograd");
        GradDto dto = gradMapper.toDto(grad);
        assertThat(dto.getIdGrad()).isEqualTo(1);
        assertThat(dto.getNaziv()).isEqualTo("Beograd");
    }

    @Test
    void gradMapper_toEntity_preslikavaNaziv() {
        GradDto dto = new GradDto(1, "Novi Sad");
        Grad grad = gradMapper.toEntity(dto);
        assertThat(grad.getIdGrad()).isEqualTo(1);
        assertThat(grad.getNaziv()).isEqualTo("Novi Sad");
    }

    @Test
    void gradMapper_toDto_null_vratiNull() {
        assertThat(gradMapper.toDto(null)).isNull();
    }

    @Test
    void gradMapper_toEntity_null_vratiNull() {
        assertThat(gradMapper.toEntity(null)).isNull();
    }

    // ZaposleniMapper
    @Test
    void zaposleniMapper_toDto_lozinkaIgnorisana() {
        Zaposleni z = new Zaposleni(1, "Ana", "Anic", "aanic", "tajna", Uloga.ZAPOSLENI);
        ZaposleniDto dto = zaposleniMapper.toDto(z);
        assertThat(dto.getIdZaposleni()).isEqualTo(1);
        assertThat(dto.getIme()).isEqualTo("Ana");
        assertThat(dto.getPrezime()).isEqualTo("Anic");
        assertThat(dto.getKorisnickoIme()).isEqualTo("aanic");
        assertThat(dto.getLozinka()).isNull(); // @Mapping(target = "lozinka", ignore = true)
    }

    @Test
    void zaposleniMapper_toEntity_preslikavaSvaPolja() {
        ZaposleniDto dto = new ZaposleniDto(2, "Marko", "Markovic", "mmarkovic", "pass", Uloga.ZAPOSLENI);
        Zaposleni z = zaposleniMapper.toEntity(dto);
        assertThat(z.getIdZaposleni()).isEqualTo(2);
        assertThat(z.getIme()).isEqualTo("Marko");
        assertThat(z.getLozinka()).isEqualTo("pass");
    }

    @Test
    void zaposleniMapper_toDto_null_vratiNull() {
        assertThat(zaposleniMapper.toDto(null)).isNull();
    }

    // KlijentMapper
    @Test
    void klijentMapper_toDto_preslikavaGradId() {
        Grad grad = new Grad(3, "Nis");
        Klijent k = new Klijent(1, 25, "064", "Jelena", "Jovic", "j@j.com", "ul 1", grad);
        KlijentDto dto = klijentMapper.toDto(k);
        assertThat(dto.getIdKlijent()).isEqualTo(1);
        assertThat(dto.getGradId()).isEqualTo(3);
        assertThat(dto.getIme()).isEqualTo("Jelena");
    }

    @Test
    void klijentMapper_toEntity_preslikavaGradId() {
        KlijentDto dto = new KlijentDto(1, 25, "064", "Jelena", "Jovic", "j@j.com", "ul 1", 3);
        Klijent k = klijentMapper.toEntity(dto);
        assertThat(k.getIdKlijent()).isEqualTo(1);
        assertThat(k.getGrad().getIdGrad()).isEqualTo(3);
    }

    @Test
    void klijentMapper_toDto_null_vratiNull() {
        assertThat(klijentMapper.toDto(null)).isNull();
    }

    @Test
    void klijentMapper_toEntity_null_vratiNull() {
        assertThat(klijentMapper.toEntity(null)).isNull();
    }

    // ModelVencaniceMapper
    @Test
    void modelVencaniceMapper_toDto_preslikavaSvaPolja() {
        ModelVencanice m = new ModelVencanice(1, "Bella", "Vera Wang", "bela", "svila");
        ModelVencaniceDto dto = modelVencaniceMapper.toDto(m);
        assertThat(dto.getIdModel()).isEqualTo(1);
        assertThat(dto.getNaziv()).isEqualTo("Bella");
        assertThat(dto.getDizajner()).isEqualTo("Vera Wang");
        assertThat(dto.getBoja()).isEqualTo("bela");
        assertThat(dto.getMaterijal()).isEqualTo("svila");
    }

    @Test
    void modelVencaniceMapper_toEntity_preslikavaSvaPolja() {
        ModelVencaniceDto dto = new ModelVencaniceDto(2, "Grace", "Chanel", "kremasta", "taft");
        ModelVencanice m = modelVencaniceMapper.toEntity(dto);
        assertThat(m.getIdModel()).isEqualTo(2);
        assertThat(m.getNaziv()).isEqualTo("Grace");
    }

    @Test
    void modelVencaniceMapper_null_vratiNull() {
        assertThat(modelVencaniceMapper.toDto(null)).isNull();
        assertThat(modelVencaniceMapper.toEntity(null)).isNull();
    }

    // VencanicaMapper
    @Test
    void vencanicaMapper_toDto_preslikavaModelId() {
        ModelVencanice model = new ModelVencanice(1, "Bella", "VW", "bela", "svila");
        Vencanica v = new Vencanica(1, 1, 2021, "SB001", "ok", "38", model);
        VencanicaDto dto = vencanicaMapper.toDto(v);
        assertThat(dto.getIdVencanica()).isEqualTo(1);
        assertThat(dto.getModelId()).isEqualTo(1);
        assertThat(dto.getSerijskiBroj()).isEqualTo("SB001");
    }

    @Test
    void vencanicaMapper_toEntity_preslikavaModelId() {
        VencanicaDto dto = new VencanicaDto(1, 1, 2021, "SB001", "ok", "38", 2);
        Vencanica v = vencanicaMapper.toEntity(dto);
        assertThat(v.getModelVencanice().getIdModel()).isEqualTo(2);
    }

    @Test
    void vencanicaMapper_null_vratiNull() {
        assertThat(vencanicaMapper.toDto(null)).isNull();
        assertThat(vencanicaMapper.toEntity(null)).isNull();
    }

    // IznajmljivanjeMapper
    @Test
    void iznajmljivanjeMapper_toDto_preslikavaIdReference() {
        LocalDate d1 = LocalDate.of(2024, 5, 1);
        LocalDate d2 = LocalDate.of(2024, 5, 10);
        Vencanica v = new Vencanica(1, 1, 2020, "SB001", "", "38", null);
        Klijent k = new Klijent(2, 25, "064", "Ana", "Anic", "a@a.com", "ul 1", null);
        Zaposleni z = new Zaposleni(3, "Marko", "M", "mm", "pass", Uloga.ZAPOSLENI);
        Iznajmljivanje iz = new Iznajmljivanje(10, d1, d2, v, k, z);

        IznajmljivanjeDto dto = iznajmljivanjeMapper.toDto(iz);
        assertThat(dto.getIdIznajmljivanje()).isEqualTo(10);
        assertThat(dto.getVencanicaId()).isEqualTo(1);
        assertThat(dto.getKlijentId()).isEqualTo(2);
        assertThat(dto.getZaposleniId()).isEqualTo(3);
        assertThat(dto.getDatumUzimanja()).isEqualTo(d1);
    }

    @Test
    void iznajmljivanjeMapper_toEntity_preslikavaIdReference() {
        LocalDate d1 = LocalDate.of(2024, 5, 1);
        LocalDate d2 = LocalDate.of(2024, 5, 10);
        IznajmljivanjeDto dto = new IznajmljivanjeDto(1, d1, d2, 5, 6, 7);

        Iznajmljivanje iz = iznajmljivanjeMapper.toEntity(dto);
        assertThat(iz.getVencanica().getIdVencanica()).isEqualTo(5);
        assertThat(iz.getKlijent().getIdKlijent()).isEqualTo(6);
        assertThat(iz.getZaposleni().getIdZaposleni()).isEqualTo(7);
    }

    @Test
    void iznajmljivanjeMapper_null_vratiNull() {
        assertThat(iznajmljivanjeMapper.toDto(null)).isNull();
        assertThat(iznajmljivanjeMapper.toEntity(null)).isNull();
    }
}

