
package fon.bg.ac.rs.salonzavencanice.entity;

import fon.bg.ac.rs.salonzavencanice.entity.impl.*;

import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Ana
 */
public class EntityTest {

    // Grad
    @Test
    void grad_noArgsConstructor() {
        Grad g = new Grad();
        assertThat(g).isNotNull();
        assertThat(g.getIdGrad()).isNull();
        assertThat(g.getNaziv()).isNull();
    }

    @Test
    void grad_allArgsConstructor() {
        Grad g = new Grad(1, "Beograd");
        assertThat(g.getIdGrad()).isEqualTo(1);
        assertThat(g.getNaziv()).isEqualTo("Beograd");
    }

    @Test
    void grad_setters() {
        Grad g = new Grad();
        g.setIdGrad(2);
        g.setNaziv("Novi Sad");
        assertThat(g.getIdGrad()).isEqualTo(2);
        assertThat(g.getNaziv()).isEqualTo("Novi Sad");
    }

    @Test
    void grad_equalsAndHashCode() {
        Grad a = new Grad(1, "Beograd");
        Grad b = new Grad(1, "Beograd");
        assertThat(a).isEqualTo(b);
        assertThat(a.hashCode()).isEqualTo(b.hashCode());
    }

    @Test
    void grad_toString() {
        Grad g = new Grad(1, "Beograd");
        assertThat(g.toString()).contains("Beograd");
    }

    // Zaposleni
    @Test
    void zaposleni_noArgsConstructor() {
        Zaposleni z = new Zaposleni();
        assertThat(z).isNotNull();
        assertThat(z.getIdZaposleni()).isNull();
    }

    @Test
    void zaposleni_allArgsConstructor() {
        Zaposleni z = new Zaposleni(1, "Marko", "Markovic", "mmarkovic", "pass123", Uloga.ZAPOSLENI);
        assertThat(z.getIdZaposleni()).isEqualTo(1);
        assertThat(z.getIme()).isEqualTo("Marko");
        assertThat(z.getPrezime()).isEqualTo("Markovic");
        assertThat(z.getKorisnickoIme()).isEqualTo("mmarkovic");
        assertThat(z.getLozinka()).isEqualTo("pass123");
    }

    @Test
    void zaposleni_setters() {
        Zaposleni z = new Zaposleni();
        z.setIdZaposleni(3);
        z.setIme("Petar");
        z.setPrezime("Petrovic");
        z.setKorisnickoIme("ppetrovic");
        z.setLozinka("lozinka");
        assertThat(z.getIdZaposleni()).isEqualTo(3);
        assertThat(z.getIme()).isEqualTo("Petar");
        assertThat(z.getPrezime()).isEqualTo("Petrovic");
        assertThat(z.getKorisnickoIme()).isEqualTo("ppetrovic");
        assertThat(z.getLozinka()).isEqualTo("lozinka");
    }

    @Test
    void zaposleni_equalsAndHashCode() {
        Zaposleni a = new Zaposleni(1, "Ana", "Anic", "aanic", "pass", Uloga.ZAPOSLENI);
        Zaposleni b = new Zaposleni(1, "Ana", "Anic", "aanic", "pass", Uloga.ZAPOSLENI);
        assertThat(a).isEqualTo(b);
        assertThat(a.hashCode()).isEqualTo(b.hashCode());
    }

    // ModelVencanice
    @Test
    void modelVencanice_noArgsConstructor() {
        ModelVencanice m = new ModelVencanice();
        assertThat(m).isNotNull();
        assertThat(m.getIdModel()).isNull();
    }

    @Test
    void modelVencanice_allArgsConstructor() {
        ModelVencanice m = new ModelVencanice(1, "Bella", "Vera Wang", "bela", "svila");
        assertThat(m.getIdModel()).isEqualTo(1);
        assertThat(m.getNaziv()).isEqualTo("Bella");
        assertThat(m.getDizajner()).isEqualTo("Vera Wang");
        assertThat(m.getBoja()).isEqualTo("bela");
        assertThat(m.getMaterijal()).isEqualTo("svila");
    }

    @Test
    void modelVencanice_setters() {
        ModelVencanice m = new ModelVencanice();
        m.setIdModel(2);
        m.setNaziv("Grace");
        m.setDizajner("Chanel");
        m.setBoja("kremasta");
        m.setMaterijal("taft");
        assertThat(m.getIdModel()).isEqualTo(2);
        assertThat(m.getNaziv()).isEqualTo("Grace");
        assertThat(m.getDizajner()).isEqualTo("Chanel");
        assertThat(m.getBoja()).isEqualTo("kremasta");
        assertThat(m.getMaterijal()).isEqualTo("taft");
    }

    @Test
    void modelVencanice_equalsAndHashCode() {
        ModelVencanice a = new ModelVencanice(1, "Bella", "VW", "bela", "svila");
        ModelVencanice b = new ModelVencanice(1, "Bella", "VW", "bela", "svila");
        assertThat(a).isEqualTo(b);
        assertThat(a.hashCode()).isEqualTo(b.hashCode());
    }

    // Klijent
    @Test
    void klijent_noArgsConstructor() {
        Klijent k = new Klijent();
        assertThat(k).isNotNull();
        assertThat(k.getIdKlijent()).isNull();
    }

    @Test
    void klijent_allArgsConstructor() {
        Grad grad = new Grad(1, "Beograd");
        Klijent k = new Klijent(1, 28, "0641234567", "Jelena", "Jovic", "jelena@mail.com", "Cara Dusana 1", grad);
        assertThat(k.getIdKlijent()).isEqualTo(1);
        assertThat(k.getGodine()).isEqualTo(28);
        assertThat(k.getTelefon()).isEqualTo("0641234567");
        assertThat(k.getIme()).isEqualTo("Jelena");
        assertThat(k.getPrezime()).isEqualTo("Jovic");
        assertThat(k.getEmail()).isEqualTo("jelena@mail.com");
        assertThat(k.getAdresa()).isEqualTo("Cara Dusana 1");
        assertThat(k.getGrad()).isEqualTo(grad);
    }

    @Test
    void klijent_setters() {
        Klijent k = new Klijent();
        Grad grad = new Grad(2, "Novi Sad");
        k.setIdKlijent(5);
        k.setGodine(22);
        k.setTelefon("0601111111");
        k.setIme("Milica");
        k.setPrezime("Milicic");
        k.setEmail("milica@gmail.com");
        k.setAdresa("Bulevar 5");
        k.setGrad(grad);
        assertThat(k.getIdKlijent()).isEqualTo(5);
        assertThat(k.getGodine()).isEqualTo(22);
        assertThat(k.getTelefon()).isEqualTo("0601111111");
        assertThat(k.getIme()).isEqualTo("Milica");
        assertThat(k.getPrezime()).isEqualTo("Milicic");
        assertThat(k.getEmail()).isEqualTo("milica@gmail.com");
        assertThat(k.getAdresa()).isEqualTo("Bulevar 5");
        assertThat(k.getGrad()).isEqualTo(grad);
    }

    // Vencanica
    @Test
    void vencanica_noArgsConstructor() {
        Vencanica v = new Vencanica();
        assertThat(v).isNotNull();
        assertThat(v.getIdVencanica()).isNull();
    }

    @Test
    void vencanica_allArgsConstructor() {
        ModelVencanice model = new ModelVencanice(1, "Bella", "VW", "bela", "svila");
        Vencanica v = new Vencanica(1, 1, 2021, "SB001", "bez napomene", "38", model);
        assertThat(v.getIdVencanica()).isEqualTo(1);
        assertThat(v.getStatus()).isEqualTo(1);
        assertThat(v.getGodinaProizvodnje()).isEqualTo(2021);
        assertThat(v.getSerijskiBroj()).isEqualTo("SB001");
        assertThat(v.getNapomene()).isEqualTo("bez napomene");
        assertThat(v.getVelicina()).isEqualTo("38");
        assertThat(v.getModelVencanice()).isEqualTo(model);
    }

    @Test
    void vencanica_setters() {
        Vencanica v = new Vencanica();
        ModelVencanice model = new ModelVencanice(2, "Grace", "Chanel", "kremasta", "taft");
        v.setIdVencanica(7);
        v.setStatus(0);
        v.setGodinaProizvodnje(2023);
        v.setSerijskiBroj("SB999");
        v.setNapomene("nova");
        v.setVelicina("40");
        v.setModelVencanice(model);
        assertThat(v.getIdVencanica()).isEqualTo(7);
        assertThat(v.getStatus()).isEqualTo(0);
        assertThat(v.getGodinaProizvodnje()).isEqualTo(2023);
        assertThat(v.getSerijskiBroj()).isEqualTo("SB999");
        assertThat(v.getNapomene()).isEqualTo("nova");
        assertThat(v.getVelicina()).isEqualTo("40");
        assertThat(v.getModelVencanice()).isEqualTo(model);
    }

    // Iznajmljivanje
    @Test
    void iznajmljivanje_noArgsConstructor() {
        Iznajmljivanje i = new Iznajmljivanje();
        assertThat(i).isNotNull();
        assertThat(i.getIdIznajmljivanje()).isNull();
    }

    @Test
    void iznajmljivanje_allArgsConstructor() {
        LocalDate d1 = LocalDate.of(2024, 3, 1);
        LocalDate d2 = LocalDate.of(2024, 3, 10);
        Vencanica vencanica = new Vencanica(1, 1, 2020, "SB001", "", "38", null);
        Klijent klijent = new Klijent(1, 25, "064", "Ana", "Anic", "a@a.com", "ul 1", null);
        Zaposleni zaposleni = new Zaposleni(1, "Marko", "M", "mm", "pass", Uloga.ZAPOSLENI);

        Iznajmljivanje iz = new Iznajmljivanje(1, d1, d2, vencanica, klijent, zaposleni);
        assertThat(iz.getIdIznajmljivanje()).isEqualTo(1);
        assertThat(iz.getDatumUzimanja()).isEqualTo(d1);
        assertThat(iz.getDatumVracanja()).isEqualTo(d2);
        assertThat(iz.getVencanica()).isEqualTo(vencanica);
        assertThat(iz.getKlijent()).isEqualTo(klijent);
        assertThat(iz.getZaposleni()).isEqualTo(zaposleni);
    }

    @Test
    void iznajmljivanje_setters() {
        Iznajmljivanje iz = new Iznajmljivanje();
        LocalDate d1 = LocalDate.of(2024, 6, 1);
        LocalDate d2 = LocalDate.of(2024, 6, 20);
        Vencanica vencanica = new Vencanica();
        Klijent klijent = new Klijent();
        Zaposleni zaposleni = new Zaposleni();

        iz.setIdIznajmljivanje(5);
        iz.setDatumUzimanja(d1);
        iz.setDatumVracanja(d2);
        iz.setVencanica(vencanica);
        iz.setKlijent(klijent);
        iz.setZaposleni(zaposleni);

        assertThat(iz.getIdIznajmljivanje()).isEqualTo(5);
        assertThat(iz.getDatumUzimanja()).isEqualTo(d1);
        assertThat(iz.getDatumVracanja()).isEqualTo(d2);
        assertThat(iz.getVencanica()).isEqualTo(vencanica);
        assertThat(iz.getKlijent()).isEqualTo(klijent);
        assertThat(iz.getZaposleni()).isEqualTo(zaposleni);
    }

    @Test
    void iznajmljivanje_equalsAndHashCode() {
        LocalDate d1 = LocalDate.of(2024, 1, 1);
        LocalDate d2 = LocalDate.of(2024, 1, 10);
        Iznajmljivanje a = new Iznajmljivanje(1, d1, d2, null, null, null);
        Iznajmljivanje b = new Iznajmljivanje(1, d1, d2, null, null, null);
        assertThat(a).isEqualTo(b);
        assertThat(a.hashCode()).isEqualTo(b.hashCode());
    }
}
