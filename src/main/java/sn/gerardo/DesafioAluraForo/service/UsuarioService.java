package sn.gerardo.DesafioAluraForo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.server.ResponseStatusException;
import sn.gerardo.DesafioAluraForo.dto.UsuarioDTO;
import sn.gerardo.DesafioAluraForo.entity.Perfil;
import sn.gerardo.DesafioAluraForo.entity.Usuario;
import sn.gerardo.DesafioAluraForo.repository.UsuarioRepository;

//public class UsuarioService {
//}
@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    @Autowired
    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<UsuarioDTO> obtenerTodos() {
        return repository.findAll().stream()
                .filter(Usuario::isActivo) // Filtro para incluir solo usuarios activos
                .map(this::mappearADTO)
                .collect(Collectors.toList());
    }

    public UsuarioDTO obtenerPorId(Long id) {
        return repository.findById(id)
                .map(this::mappearADTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no existente"));

    }

    public UsuarioDTO crear(UsuarioDTO usuarioDTO) {
        Usuario usuario = mappearAEntidad(usuarioDTO);
        usuario.setActivo(true);
        return mappearADTO(repository.save(usuario));
    }

    public UsuarioDTO actualizar(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuario = repository.findById(id)
                .orElseThrow();
        usuario.setNombre(usuarioDTO.nombre());
        usuario.setCorreoElectronico(usuarioDTO.correoElectronico());
        usuario.setContrasena(usuarioDTO.contrasena());
        usuario.setPerfil(new Perfil(usuarioDTO.perfilId()));
        usuario.setActivo(usuarioDTO.activo()); // Establecer el valor de activo
        return mappearADTO(repository.save(usuario));
    }


public String eliminarLogico(Long id) {
    try {
        Usuario usuario = repository.findById(id)
                .orElseThrow();
        usuario.setActivo(false);
        repository.save(usuario);
        return "Usuario ocultado con éxito";
    } catch (Exception e) {
        return "Ocurrió un error al ocultar el usuario: " + e.getMessage();
    }
}

    private Usuario mappearAEntidad(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.nombre());
        usuario.setCorreoElectronico(usuarioDTO.correoElectronico());
        usuario.setContrasena(usuarioDTO.contrasena());
        usuario.setPerfil(new Perfil(usuarioDTO.perfilId()));
        return usuario;
    }

    private UsuarioDTO mappearADTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreoElectronico(),
                usuario.getContrasena(),
                usuario.getPerfil().getId(),
                usuario.isActivo()
        );
    }
}