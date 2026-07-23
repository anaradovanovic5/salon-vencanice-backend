package fon.bg.ac.rs.salonzavencanice.security;

import fon.bg.ac.rs.salonzavencanice.entity.impl.Uloga;
import fon.bg.ac.rs.salonzavencanice.entity.impl.Zaposleni;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Ana
 */
@ExtendWith(MockitoExtension.class)
public class JwtAuthFilterTest {

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private CustomUserDetailsService userDetailsService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @InjectMocks
    private JwtAuthFilter jwtAuthFilter;

    private Zaposleni zaposleni;

    @BeforeEach
    void setUp() {
        zaposleni = new Zaposleni(1, "Marija", "Petrović", "mpetrovic", "hesirano", Uloga.ZAPOSLENI);
    }

    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void doFilterInternal_bezAuthorizationHeadera_neSetujeAutentifikaciju() throws Exception {
        when(request.getHeader("Authorization")).thenReturn(null);

        jwtAuthFilter.doFilterInternal(request, response, filterChain);

        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void doFilterInternal_headerBezBearerPrefiksa_neSetujeAutentifikaciju() throws Exception {
        when(request.getHeader("Authorization")).thenReturn("Basic nekiTokenOvde");

        jwtAuthFilter.doFilterInternal(request, response, filterChain);

        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void doFilterInternal_validanToken_setujeAutentifikaciju() throws Exception {
        when(request.getHeader("Authorization")).thenReturn("Bearer validanToken");
        when(jwtUtil.jeTokenValidan("validanToken")).thenReturn(true);
        when(jwtUtil.izvuciKorisnickoIme("validanToken")).thenReturn("mpetrovic");
        when(userDetailsService.loadUserByUsername("mpetrovic")).thenReturn(zaposleni);

        jwtAuthFilter.doFilterInternal(request, response, filterChain);

        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNotNull();
        assertThat(SecurityContextHolder.getContext().getAuthentication().getName()).isEqualTo("mpetrovic");
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void doFilterInternal_nevalidanToken_neSetujeAutentifikaciju() throws Exception {
        when(request.getHeader("Authorization")).thenReturn("Bearer nevalidanToken");
        when(jwtUtil.jeTokenValidan("nevalidanToken")).thenReturn(false);

        jwtAuthFilter.doFilterInternal(request, response, filterChain);

        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
        verify(userDetailsService, never()).loadUserByUsername(any());
        verify(filterChain).doFilter(request, response);
    }
}