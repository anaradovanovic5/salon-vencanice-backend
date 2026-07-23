package fon.bg.ac.rs.salonzavencanice.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author Ana
 */
public class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
        ReflectionTestUtils.setField(jwtUtil, "secret", "test-tajni-kljuc-za-testiranje-jwt-tokena-2026");
        ReflectionTestUtils.setField(jwtUtil, "expirationMs", 86400000L);
    }

    @Test
    void generisiToken_vratiNepraznaToken() {
        String token = jwtUtil.generisiToken("mpetrovic", "ZAPOSLENI");

        assertThat(token).isNotNull();
        assertThat(token).isNotBlank();
    }

    @Test
    void izvuciKorisnickoIme_vratiIstoImeKojeJeUnetoPriGenerisanju() {
        String token = jwtUtil.generisiToken("mpetrovic", "ZAPOSLENI");

        String korisnickoIme = jwtUtil.izvuciKorisnickoIme(token);

        assertThat(korisnickoIme).isEqualTo("mpetrovic");
    }

    @Test
    void izvuciUlogu_vratiIspravnuUlogu() {
        String token = jwtUtil.generisiToken("mpetrovic", "ADMIN");

        String uloga = jwtUtil.izvuciUlogu(token);

        assertThat(uloga).isEqualTo("ADMIN");
    }

    @Test
    void jeTokenValidan_validanToken_vratiTrue() {
        String token = jwtUtil.generisiToken("mpetrovic", "ZAPOSLENI");

        boolean rezultat = jwtUtil.jeTokenValidan(token);

        assertThat(rezultat).isTrue();
    }

    @Test
    void jeTokenValidan_izmenjenToken_vratiFalse() {
        String token = jwtUtil.generisiToken("mpetrovic", "ZAPOSLENI");
        String izmenjenToken = token + "izmena";

        boolean rezultat = jwtUtil.jeTokenValidan(izmenjenToken);

        assertThat(rezultat).isFalse();
    }

    @Test
    void jeTokenValidan_praznString_vratiFalse() {
        boolean rezultat = jwtUtil.jeTokenValidan("");

        assertThat(rezultat).isFalse();
    }

    @Test
    void jeTokenValidan_istekaoToken_vratiFalse() {
        ReflectionTestUtils.setField(jwtUtil, "expirationMs", -1000L);
        String istekaoToken = jwtUtil.generisiToken("mpetrovic", "ZAPOSLENI");

        boolean rezultat = jwtUtil.jeTokenValidan(istekaoToken);

        assertThat(rezultat).isFalse();
    }
}