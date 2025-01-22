package gerardo.sn.desafioAluraForo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import gerardo.sn.desafioAluraForo.entity.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    @Value("${api.security.issuer:Alura Foro API}")
    private String issuer;

    @Value("${api.security.expiration:7200000}")
    private long expiration;

    @Value("${api.security.refresh-expiration:7200000}")
    private long refreshExpiration;

    public String generarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer(issuer)
                    .withSubject(usuario.getCorreoElectronico())
                    .withClaim("id", usuario.getId())
                    .withClaim("nombre", usuario.getNombre())
                    .withClaim("perfil", usuario.getPerfil().getNombre())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new SecurityException("Error al generar token JWT");
        }
    }

    public String getSubject(String token) {
        if (token == null) {
            throw new SecurityException("Token no proporcionado");
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new SecurityException("Token JWT inválido o expirado");
        }
    }

    private Instant generarFechaExpiracion() {
        return LocalDateTime.now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-05:00"));
    }

    public String generarRefreshToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer(issuer)
                    .withSubject(usuario.getCorreoElectronico())
                    .withExpiresAt(generarFechaExpiracionRefresh())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new SecurityException("Error al generar refresh token");
        }
    }

    public String validarRefreshToken(String token) {
        if (token == null) {
            throw new SecurityException("Refresh token no proporcionado");
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new SecurityException("Refresh token inválido o expirado");
        }
    }

    private Instant generarFechaExpiracionRefresh() {
        return LocalDateTime.now()
                .plusDays(7)
                .toInstant(ZoneOffset.of("-05:00"));
    }
}