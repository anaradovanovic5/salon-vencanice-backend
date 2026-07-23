package fon.bg.ac.rs.salonzavencanice.dto.auth;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author Ana
 */
public class AuthDtoTest {

    private final Validator validator;

    public AuthDtoTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void loginZahtev_validniPodaci_vratiIspravneVrednosti() {
        LoginZahtev zahtev = new LoginZahtev("mpetrovic", "tajna123");

        assertThat(zahtev.korisnickoIme()).isEqualTo("mpetrovic");
        assertThat(zahtev.lozinka()).isEqualTo("tajna123");
    }

    @Test
    void loginZahtev_praznoKorisnickoIme_baciValidacionuGresku() {
        LoginZahtev zahtev = new LoginZahtev("", "tajna123");

        Set<ConstraintViolation<LoginZahtev>> violations = validator.validate(zahtev);

        assertThat(violations).isNotEmpty();
    }

    @Test
    void loginZahtev_praznaLozinka_baciValidacionuGresku() {
        LoginZahtev zahtev = new LoginZahtev("mpetrovic", "");

        Set<ConstraintViolation<LoginZahtev>> violations = validator.validate(zahtev);

        assertThat(violations).isNotEmpty();
    }

    @Test
    void loginOdgovor_validniPodaci_vratiIspravneVrednosti() {
        LoginOdgovor odgovor = new LoginOdgovor("token123", "mpetrovic", "ZAPOSLENI");

        assertThat(odgovor.token()).isEqualTo("token123");
        assertThat(odgovor.korisnickoIme()).isEqualTo("mpetrovic");
        assertThat(odgovor.uloga()).isEqualTo("ZAPOSLENI");
    }
}