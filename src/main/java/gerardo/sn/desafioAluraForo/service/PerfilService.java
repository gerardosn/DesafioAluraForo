package gerardo.sn.desafioAluraForo.service;

import gerardo.sn.desafioAluraForo.dtos.PerfilDto;
import gerardo.sn.desafioAluraForo.entity.Perfil;
import gerardo.sn.desafioAluraForo.exception.NotFoundException;
import gerardo.sn.desafioAluraForo.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PerfilService {
    private final PerfilRepository perfilRepository;

    @Autowired
    public PerfilService(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    public List<PerfilDto> getAllPerfiles() {
        return perfilRepository.findAll().stream()
                .map(perfil -> new PerfilDto(perfil.getId(), perfil.getNombre()))
                .collect(Collectors.toList());
    }

    public PerfilDto getPerfilById(Long id) {
        Perfil perfil = perfilRepository.findById(id).orElseThrow(() -> new NotFoundException("Perfil no encontrado"));
        return new PerfilDto(perfil.getId(), perfil.getNombre());
    }

    public PerfilDto createPerfil(PerfilDto perfilDto) {
        Perfil perfil = new Perfil(null, perfilDto.getNombre());
        perfil.setNombre(perfilDto.getNombre());
        Perfil perfilGuardado = perfilRepository.save(perfil);
        return new PerfilDto(perfilGuardado.getId(), perfilGuardado.getNombre());
    }

    public PerfilDto updatePerfil(Long id, PerfilDto perfilDto) {
        Perfil perfil = perfilRepository.findById(id).orElseThrow(() -> new NotFoundException("Perfil no encontrado"));
        perfil.setNombre(perfilDto.getNombre());
        Perfil perfilActualizado = perfilRepository.save(perfil);
        return new PerfilDto(perfilActualizado.getId(), perfilActualizado.getNombre());
    }

    public void deletePerfil(Long id) {
        Perfil perfil = perfilRepository.findById(id).orElseThrow(() -> new NotFoundException("Perfil no encontrado"));
        perfilRepository.delete(perfil);
    }
}