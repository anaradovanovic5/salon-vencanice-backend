package fon.bg.ac.rs.salonzavencanice.security;

import fon.bg.ac.rs.salonzavencanice.repository.impl.ZaposleniRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Ucitava Zaposlenog kao Spring Security korisnika na osnovu korisnickog imena.
 *
 * @author Ana
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final ZaposleniRepository repo;

    public CustomUserDetailsService(ZaposleniRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String korisnickoIme) throws UsernameNotFoundException {
        return repo.findByKorisnickoIme(korisnickoIme)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Korisnik sa korisnickim imenom '" + korisnickoIme + "' ne postoji."));
    }
}