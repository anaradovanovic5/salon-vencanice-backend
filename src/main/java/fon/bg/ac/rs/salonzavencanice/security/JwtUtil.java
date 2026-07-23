package fon.bg.ac.rs.salonzavencanice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Generisanje i validacija JWT tokena.
 *
 * @author Ana
 */
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration-ms}")
    private long expirationMs;

    private SecretKey kljuc() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generisiToken(String korisnickoIme, String uloga) {
        Date sada = new Date();
        Date istice = new Date(sada.getTime() + expirationMs);

        return Jwts.builder()
                .setSubject(korisnickoIme)
                .claim("uloga", uloga)
                .setIssuedAt(sada)
                .setExpiration(istice)
                .signWith(kljuc(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String izvuciKorisnickoIme(String token) {
        return parsirajClaims(token).getSubject();
    }

    public String izvuciUlogu(String token) {
        return parsirajClaims(token).get("uloga", String.class);
    }

    public boolean jeTokenValidan(String token) {
        try {
            parsirajClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Claims parsirajClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(kljuc())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}