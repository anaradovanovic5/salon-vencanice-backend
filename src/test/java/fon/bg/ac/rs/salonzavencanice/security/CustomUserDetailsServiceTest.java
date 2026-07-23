package fon.bg.ac.rs.salonzavencanice.security;

import fon.bg.ac.rs.salonzavencanice.entity.impl.Uloga;
import fon.bg.ac.rs.salonzavencanice.entity.impl.Zaposleni;
import fon.bg.ac.rs.salonzavencanice.repository.impl.ZaposleniRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Ana
 */
@ExtendWith(MockitoExtension.class)
public class CustomUserDetailsServiceTest {

    @Mock
    private ZaposleniRepository repo;

    @InjectMocks
    private CustomUserDetailsService service;

    private Zaposleni zaposleni;

    @BeforeEach
    void setUp() {
        zaposleni = new Zaposleni(1, "Marija", "Petrović", "mpetrovic", "hesirano", Uloga.ZAPOSLENI);
    }

    @Test
    void loadUserByUsername_postojeciKorisnik_vratiUserDetails() {
        when(repo.findByKorisnickoIme("mpetrovic")).thenReturn(Optional.of(zaposleni));

        UserDetails result = service.loadUserByUsername("mpetrovic");

        assertThat(result.getUsername()).isEqualTo("mpetrovic");
        assertThat(result.getPassword()).isEqualTo("hesirano");
        assertThat(result.getAuthorities()).extracting("authority").containsExactly("ROLE_ZAPOSLENI");
    }

    @Test
    void loadUserByUsername_nepostojeciKorisnik_baciUsernameNotFoundException() {
        when(repo.findByKorisnickoIme("nepostojeci")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.loadUserByUsername("nepostojeci"))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessageContaining("nepostojeci");
    }
}