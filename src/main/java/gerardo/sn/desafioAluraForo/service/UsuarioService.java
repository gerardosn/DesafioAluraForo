package gerardo.sn.desafioAluraForo.service;

import gerardo.sn.desafioAluraForo.dtos.PerfilDto;
import gerardo.sn.desafioAluraForo.dtos.UsuarioDto;
import gerardo.sn.desafioAluraForo.entity.Perfil;
import gerardo.sn.desafioAluraForo.entity.Usuario;
import gerardo.sn.desafioAluraForo.repository.UsuarioRepository;
import gerardo.sn.desafioAluraForo.repository.PerfilRepository;

import gerardo.sn.desafioAluraForo.exception.NotFoundException;
import gerardo.sn.desafioAluraForo.exception.SecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PerfilRepository perfilRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, PerfilRepository perfilRepository) {
        this.usuarioRepository = usuarioRepository;
        this.perfilRepository = perfilRepository;
    }

    public List<UsuarioDto> getAllUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(usuario -> new UsuarioDto(
                        usuario.getId(),
                        usuario.getNombre(),
                        usuario.getCorreoElectronico(),
                        usuario.getContrasena(),
                        new PerfilDto(usuario.getPerfil().getId(), usuario.getPerfil().getNombre())
                ))
                .collect(Collectors.toList());
    }

    public UsuarioDto getUsuarioById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        return new UsuarioDto(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreoElectronico(),
                usuario.getContrasena(),
                new PerfilDto(usuario.getPerfil().getId(), usuario.getPerfil().getNombre())
        );
    }

    @Transactional
    public UsuarioDto createUsuario(UsuarioDto usuarioDto) {
        // Validar si el correo ya existe
        if (usuarioRepository.existsByCorreoElectronico(usuarioDto.getCorreoElectronico())) {
            throw new SecurityException("El correo electrónico ya está registrado");
        }

        // Obtener el perfil
        Perfil perfil = perfilRepository.findById(usuarioDto.getPerfil().getId())
                .orElseThrow(() -> new NotFoundException("Perfil no encontrado"));

        // Crear y configurar el usuario
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setCorreoElectronico(usuarioDto.getCorreoElectronico());
        usuario.setContrasena(passwordEncoder.encode(usuarioDto.getContrasena()));
        usuario.setPerfil(perfil);
        usuario.setActivo(true);

        // Guardar el usuario
        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        // Convertir a DTO para la respuesta (sin incluir la contraseña)
        return new UsuarioDto(
                usuarioGuardado.getId(),
                usuarioGuardado.getNombre(),
                usuarioGuardado.getCorreoElectronico(),
                null, // No devolvemos la contraseña en la respuesta
                new PerfilDto(usuarioGuardado.getPerfil().getId(), usuarioGuardado.getPerfil().getNombre())
        );
    }

    public UsuarioDto updateUsuario(Long id, UsuarioDto usuarioDto) {
        Perfil perfil = perfilRepository.findById(usuarioDto.getPerfil().getId())
                .orElseThrow(() -> new NotFoundException("Perfil no encontrado"));

        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setCorreoElectronico(usuarioDto.getCorreoElectronico());
        usuario.setContrasena(usuarioDto.getContrasena());
        usuario.setPerfil(perfil);
        Usuario usuarioActualizado = usuarioRepository.save(usuario);
        return new UsuarioDto(
                usuarioActualizado.getId(),
                usuarioActualizado.getNombre(),
                usuarioActualizado.getCorreoElectronico(),
                usuarioActualizado.getContrasena(),
                new PerfilDto(usuarioActualizado.getPerfil().getId(), usuarioActualizado.getPerfil().getNombre())
        );
    }

    public void deleteUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        usuarioRepository.delete(usuario);
    }
}