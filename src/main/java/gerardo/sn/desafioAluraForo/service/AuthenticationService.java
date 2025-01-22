package gerardo.sn.desafioAluraForo.service;

import gerardo.sn.desafioAluraForo.dtos.SecurityDtos.LoginRequestDTO;
import gerardo.sn.desafioAluraForo.dtos.SecurityDtos.LoginResponseDTO;
import gerardo.sn.desafioAluraForo.dtos.SecurityDtos.RegistroUsuarioDTO;
import gerardo.sn.desafioAluraForo.entity.Perfil;
import gerardo.sn.desafioAluraForo.entity.Usuario;
import gerardo.sn.desafioAluraForo.exception.SecurityException;
import gerardo.sn.desafioAluraForo.repository.PerfilRepository;
import gerardo.sn.desafioAluraForo.repository.UsuarioRepository;
import gerardo.sn.desafioAluraForo.security.SecurityTokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final SecurityTokenService securityTokenService;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final PerfilRepository perfilRepository;

    /**
     * Autentica un usuario y genera tokens de acceso
     */
    public LoginResponseDTO login(@Valid LoginRequestDTO request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        Usuario usuario = (Usuario) authentication.getPrincipal();
        String token = securityTokenService.generarToken(usuario);
        String refreshToken = securityTokenService.generarRefreshToken(usuario);

        return new LoginResponseDTO(token, refreshToken, "Bearer");
    }

    /**
     * Registra un nuevo usuario en el sistema
     */
    @Transactional
    public LoginResponseDTO registrar(@Valid RegistroUsuarioDTO request) {
        if (usuarioRepository.existsByCorreoElectronico(request.getEmail())) {
            throw new SecurityException("El email ya está registrado");
        }

        // Buscar el perfil  en la base de datos
        Perfil perfil = perfilRepository.findByNombre("ADMIN, PROFESOR, ALUMNO")
                .orElseThrow(() -> new SecurityException("Error al asignar el perfil de ALUMNO"));


        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setCorreoElectronico(request.getEmail());
        usuario.setContrasena(passwordEncoder.encode(request.getPassword()));
        usuario.setPerfil(perfil);
        usuario.setActivo(true);

        usuarioRepository.save(usuario);

        String token = securityTokenService.generarToken(usuario);
        String refreshToken = securityTokenService.generarRefreshToken(usuario);

        return new LoginResponseDTO(token, refreshToken, "Bearer");
    }

    /**
     * Refresca el token de acceso usando un refresh token
     */
    public LoginResponseDTO refreshToken(String refreshToken) {
        String email = securityTokenService.validarRefreshToken(refreshToken);
        Usuario usuario = usuarioRepository.findByCorreoElectronico(email)
                .orElseThrow(() -> new SecurityException("Usuario no encontrado"));

        String newToken = securityTokenService.generarToken(usuario);
        String newRefreshToken = securityTokenService.generarRefreshToken(usuario);

        return new LoginResponseDTO(newToken, newRefreshToken, "Bearer");
    }
}