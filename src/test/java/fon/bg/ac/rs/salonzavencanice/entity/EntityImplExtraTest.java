package fon.bg.ac.rs.salonzavencanice.entity;

import fon.bg.ac.rs.salonzavencanice.entity.impl.*;

import java.time.LocalDate;
import java.util.Collection;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

/**
 * Dodatni testovi za entity.impl - fokus na UserDetails logiku (Zaposleni)
 * i grane equals/hashCode/toString koje EntityTest ne pokriva.
 *
 * @author Ana
 */
public class EntityImplExtraTest {

    // ---------- Uloga enum ----------

    @Test
    void uloga_values() {
        assertThat(Uloga.values()).containsExactly(Uloga.ADMIN, Uloga.ZAPOSLENI);
    }

    @Test
    void uloga_valueOf() {
        assertThat(Uloga.valueOf("ADMIN")).isEqualTo(Uloga.ADMIN);
        assertThat(Uloga.valueOf("ZAPOSLENI")).isEqualTo(Uloga.ZAPOSLENI);
    }

    // ---------- Zaposleni implements UserDetails ----------

    @Test
    void zaposleni_getAuthorities_admin() {
        Zaposleni z = new Zaposleni(1, "Ana", "Anic", "aanic", "pass", Uloga.ADMIN);
        Collection<? extends GrantedAuthority> authorities = z.getAuthorities();
        assertThat(authorities).hasSize(1);
        assertThat(authorities.iterator().next().getAuthority()).isEqualTo("ROLE_ADMIN");
    }

    @Test
    void zaposleni_getAuthorities_zaposleni() {
        Zaposleni z = new Zaposleni(2, "Marko", "Markovic", "mmarkovic", "pass", Uloga.ZAPOSLENI);
        Collection<? extends GrantedAuthority> authorities = z.getAuthorities();
        assertThat(authorities).hasSize(1);
        assertThat(authorities.iterator().next().getAuthority()).isEqualTo("ROLE_ZAPOSLENI");
    }

    @Test
    void zaposleni_getPassword_returnsLozinka() {
        Zaposleni z = new Zaposleni(1, "Ana", "Anic", "aanic", "tajna123", Uloga.ADMIN);
        assertThat(z.getPassword()).isEqualTo("tajna123");
    }

    @Test
    void zaposleni_getUsername_returnsKorisnickoIme() {
        Zaposleni z = new Zaposleni(1, "Ana", "Anic", "aanic", "tajna123", Uloga.ADMIN);
        assertThat(z.getUsername()).isEqualTo("aanic");
    }

    @Test
    void zaposleni_accountFlags_alwaysTrue() {
        Zaposleni z = new Zaposleni(1, "Ana", "Anic", "aanic", "tajna123", Uloga.ADMIN);
        assertThat(z.isAccountNonExpired()).isTrue();
        assertThat(z.isAccountNonLocked()).isTrue();
        assertThat(z.isCredentialsNonExpired()).isTrue();
        assertThat(z.isEnabled()).isTrue();
    }

    @Test
    void zaposleni_toString_containsKorisnickoIme() {
        Zaposleni z = new Zaposleni(1, "Ana", "Anic", "aanic", "tajna123", Uloga.ADMIN);
        assertThat(z.toString()).contains("aanic");
    }

    @Test
    void zaposleni_equals_notEqual_differentUloga() {
        Zaposleni a = new Zaposleni(1, "Ana", "Anic", "aanic", "pass", Uloga.ADMIN);
        Zaposleni b = new Zaposleni(1, "Ana", "Anic", "aanic", "pass", Uloga.ZAPOSLENI);
        assertThat(a).isNotEqualTo(b);
    }

    // ---------- Zajedničke equals grane (null, drugi tip, isti objekat) ----------

    @Test
    void grad_equals_edgeCases() {
        Grad g = new Grad(1, "Beograd");
        assertThat(g).isEqualTo(g);
        assertThat(g).isNotEqualTo(null);
        assertThat(g).isNotEqualTo("nije grad");
        assertThat(g).isNotEqualTo(new Grad(2, "Beograd"));
        assertThat(g).isNotEqualTo(new Grad(1, "Novi Sad"));
    }

    @Test
    void klijent_equals_edgeCases() {
        Grad grad = new Grad(1, "Beograd");
        Klijent k = new Klijent(1, 28, "064", "Jelena", "Jovic", "j@mail.com", "Adresa 1", grad);
        assertThat(k).isEqualTo(k);
        assertThat(k).isNotEqualTo(null);
        assertThat(k).isNotEqualTo("string");
        assertThat(k).isNotEqualTo(new Klijent(2, 28, "064", "Jelena", "Jovic", "j@mail.com", "Adresa 1", grad));
        assertThat(k.hashCode()).isEqualTo(
                new Klijent(1, 28, "064", "Jelena", "Jovic", "j@mail.com", "Adresa 1", grad).hashCode());
    }

    @Test
    void klijent_toString_containsIme() {
        Klijent k = new Klijent(1, 28, "064", "Jelena", "Jovic", "j@mail.com", "Adresa 1", null);
        assertThat(k.toString()).contains("Jelena");
    }

    @Test
    void modelVencanice_equals_edgeCases() {
        ModelVencanice m = new ModelVencanice(1, "Bella", "VW", "bela", "svila");
        assertThat(m).isEqualTo(m);
        assertThat(m).isNotEqualTo(null);
        assertThat(m).isNotEqualTo("string");
        assertThat(m).isNotEqualTo(new ModelVencanice(2, "Bella", "VW", "bela", "svila"));
    }

    @Test
    void modelVencanice_toString_containsNaziv() {
        ModelVencanice m = new ModelVencanice(1, "Bella", "VW", "bela", "svila");
        assertThat(m.toString()).contains("Bella");
    }

    @Test
    void vencanica_equals_edgeCases() {
        ModelVencanice model = new ModelVencanice(1, "Bella", "VW", "bela", "svila");
        Vencanica v = new Vencanica(1, 1, 2021, "SB001", "", "38", model);
        assertThat(v).isEqualTo(v);
        assertThat(v).isNotEqualTo(null);
        assertThat(v).isNotEqualTo("string");
        assertThat(v).isNotEqualTo(new Vencanica(2, 1, 2021, "SB001", "", "38", model));
        assertThat(v).isNotEqualTo(new Vencanica(1, 0, 2021, "SB001", "", "38", model));
    }

    @Test
    void vencanica_toString_containsSerijskiBroj() {
        Vencanica v = new Vencanica(1, 1, 2021, "SB001", "", "38", null);
        assertThat(v.toString()).contains("SB001");
    }

    @Test
    void iznajmljivanje_equals_edgeCases() {
        LocalDate d1 = LocalDate.of(2024, 1, 1);
        LocalDate d2 = LocalDate.of(2024, 1, 10);
        Iznajmljivanje a = new Iznajmljivanje(1, d1, d2, null, null, null);
        assertThat(a).isEqualTo(a);
        assertThat(a).isNotEqualTo(null);
        assertThat(a).isNotEqualTo("string");
        assertThat(a).isNotEqualTo(new Iznajmljivanje(2, d1, d2, null, null, null));
    }

    @Test
    void iznajmljivanje_toString_notNull() {
        LocalDate d1 = LocalDate.of(2024, 1, 1);
        LocalDate d2 = LocalDate.of(2024, 1, 10);
        Iznajmljivanje i = new Iznajmljivanje(1, d1, d2, null, null, null);
        assertThat(i.toString()).isNotNull().contains("2024-01-01");
    }

    // ---------- Null-field equals grane (Lombok null-check branch po polju) ----------

    @Test
    void grad_equals_nullField_bothNull() {
        Grad a = new Grad(1, null);
        Grad b = new Grad(1, null);
        assertThat(a).isEqualTo(b);
    }

    @Test
    void grad_equals_nullField_oneNull() {
        Grad a = new Grad(1, null);
        Grad b = new Grad(1, "Beograd");
        assertThat(a).isNotEqualTo(b);
        assertThat(b).isNotEqualTo(a);
    }

    @Test
    void klijent_equals_nullFields_mix() {
        Klijent a = new Klijent(1, null, null, null, null, null, null, null);
        Klijent b = new Klijent(1, null, null, null, null, null, null, null);
        assertThat(a).isEqualTo(b);

        Klijent c = new Klijent(1, null, null, null, null, "j@mail.com", null, null);
        assertThat(a).isNotEqualTo(c);
        assertThat(c).isNotEqualTo(a);
    }

    @Test
    void klijent_equals_nullGodine() {
        Klijent a = new Klijent(1, null, "064", "Jelena", "Jovic", "j@mail.com", "Adresa 1", null);
        Klijent b = new Klijent(1, 28, "064", "Jelena", "Jovic", "j@mail.com", "Adresa 1", null);
        assertThat(a).isNotEqualTo(b);
    }

    @Test
    void modelVencanice_equals_nullFields() {
        ModelVencanice a = new ModelVencanice(1, null, null, null, null);
        ModelVencanice b = new ModelVencanice(1, null, null, null, null);
        assertThat(a).isEqualTo(b);

        ModelVencanice c = new ModelVencanice(1, "Bella", null, null, null);
        assertThat(a).isNotEqualTo(c);
        assertThat(c).isNotEqualTo(a);
    }

    @Test
    void vencanica_equals_nullFields() {
        Vencanica a = new Vencanica(1, 0, 0, null, null, null, null);
        Vencanica b = new Vencanica(1, 0, 0, null, null, null, null);
        assertThat(a).isEqualTo(b);

        Vencanica c = new Vencanica(1, 0, 0, "SB001", null, null, null);
        assertThat(a).isNotEqualTo(c);
        assertThat(c).isNotEqualTo(a);
    }

    @Test
    void iznajmljivanje_equals_nullFields() {
        Iznajmljivanje a = new Iznajmljivanje(1, null, null, null, null, null);
        Iznajmljivanje b = new Iznajmljivanje(1, null, null, null, null, null);
        assertThat(a).isEqualTo(b);

        Iznajmljivanje c = new Iznajmljivanje(1, LocalDate.of(2024, 1, 1), null, null, null, null);
        assertThat(a).isNotEqualTo(c);
        assertThat(c).isNotEqualTo(a);
    }

    @Test
    void zaposleni_equals_nullFields() {
        Zaposleni a = new Zaposleni(1, null, null, null, null, Uloga.ADMIN);
        Zaposleni b = new Zaposleni(1, null, null, null, null, Uloga.ADMIN);
        assertThat(a).isEqualTo(b);

        Zaposleni c = new Zaposleni(1, "Ana", null, null, null, Uloga.ADMIN);
        assertThat(a).isNotEqualTo(c);
        assertThat(c).isNotEqualTo(a);
    }

    // ---------- hashCode konzistentnost sa null poljima ----------

    @Test
    void grad_hashCode_consistentWithNullField() {
        Grad a = new Grad(1, null);
        Grad b = new Grad(1, null);
        assertThat(a.hashCode()).isEqualTo(b.hashCode());
    }

    @Test
    void klijent_hashCode_consistentWithNullFields() {
        Klijent a = new Klijent(1, null, null, null, null, null, null, null);
        Klijent b = new Klijent(1, null, null, null, null, null, null, null);
        assertThat(a.hashCode()).isEqualTo(b.hashCode());
    }
}