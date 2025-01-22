package gerardo.sn.desafioAluraForo.security;

import gerardo.sn.desafioAluraForo.entity.Usuario;
import gerardo.sn.desafioAluraForo.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final SecurityTokenService securityTokenService;
    private final UsuarioRepository usuarioRepository;

    public SecurityFilter(SecurityTokenService securityTokenService, UsuarioRepository usuarioRepository) {
        this.securityTokenService = securityTokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = recuperarToken(request);

        if (token != null) {
            String correoElectronico = securityTokenService.getSubject(token);
            Usuario usuario = usuarioRepository.findActiveByCorreoElectronico(correoElectronico)
                    .orElseThrow(() -> new ServletException("Usuario no encontrado o inactivo"));

            var authentication = new UsernamePasswordAuthenticationToken(
                    usuario,
                    null,
                    usuario.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        return authHeader.substring(7);
    }
}