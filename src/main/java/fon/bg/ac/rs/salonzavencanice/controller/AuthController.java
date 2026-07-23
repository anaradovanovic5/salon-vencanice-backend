package fon.bg.ac.rs.salonzavencanice.controller;

import fon.bg.ac.rs.salonzavencanice.dto.auth.LoginOdgovor;
import fon.bg.ac.rs.salonzavencanice.dto.auth.LoginZahtev;
import fon.bg.ac.rs.salonzavencanice.dto.impl.ZaposleniDto;
import fon.bg.ac.rs.salonzavencanice.entity.impl.Uloga;
import fon.bg.ac.rs.salonzavencanice.entity.impl.Zaposleni;
import fon.bg.ac.rs.salonzavencanice.mapper.impl.ZaposleniMapper;
import fon.bg.ac.rs.salonzavencanice.repository.impl.ZaposleniRepository;
import fon.bg.ac.rs.salonzavencanice.security.JwtUtil;
import fon.bg.ac.rs.salonzavencanice.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Autentikacija i registracija zaposlenih. Javno dostupno (bez tokena).
 *
 * @author Ana
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final ZaposleniRepository repo;
    private final ZaposleniMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthController(
            ZaposleniRepository repo,
            ZaposleniMapper mapper,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil,
            AuthenticationManager authenticationManager) {
        this.repo = repo;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginOdgovor>> login(@Valid @RequestBody LoginZahtev zahtev) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(zahtev.korisnickoIme(), zahtev.lozinka()));
        } catch (BadCredentialsException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponse.error("Pogresno korisnicko ime ili lozinka."));
        }

        Zaposleni zaposleni = repo.findByKorisnickoIme(zahtev.korisnickoIme())
                .orElseThrow(() -> new BadCredentialsException("Korisnik ne postoji."));

        String token = jwtUtil.generisiToken(zaposleni.getKorisnickoIme(), zaposleni.getUloga().name());
        LoginOdgovor odgovor = new LoginOdgovor(token, zaposleni.getKorisnickoIme(), zaposleni.getUloga().name());

        return ResponseEntity.ok(ApiResponse.ok(odgovor));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<ZaposleniDto>> register(@Valid @RequestBody ZaposleniDto dto) {
        if (repo.existsByKorisnickoIme(dto.getKorisnickoIme())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(ApiResponse.error("Korisnicko ime vec postoji."));
        }

        dto.setIdZaposleni(null);
        if (dto.getUloga() == null) {
            dto.setUloga(Uloga.ZAPOSLENI);
        }
        dto.setLozinka(passwordEncoder.encode(dto.getLozinka()));

        Zaposleni sacuvan = repo.save(mapper.toEntity(dto));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok(mapper.toDto(sacuvan)));
    }
}