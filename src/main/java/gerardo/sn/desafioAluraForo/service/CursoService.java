package gerardo.sn.desafioAluraForo.service;

import gerardo.sn.desafioAluraForo.dtos.CursoDTO;
import gerardo.sn.desafioAluraForo.entity.Curso;
import gerardo.sn.desafioAluraForo.exception.NotFoundException;
import gerardo.sn.desafioAluraForo.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<CursoDTO> getAllCursos() {
        return cursoRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public CursoDTO getCursoById(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Curso not found"));
        return mapToDTO(curso);
    }

    public CursoDTO createCurso(CursoDTO cursoDTO) {
        Curso curso = new Curso();
        curso.setNombre(cursoDTO.getNombre());
        curso.setCategoria(cursoDTO.getCategoria());
        Curso savedCurso = cursoRepository.save(curso);
        return mapToDTO(savedCurso);
    }

    public CursoDTO updateCurso(Long id, CursoDTO cursoDTO) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Curso not found"));
        curso.setNombre(cursoDTO.getNombre());
        curso.setCategoria(cursoDTO.getCategoria());
        Curso updatedCurso = cursoRepository.save(curso);
        return mapToDTO(updatedCurso);
    }

    public void deleteCurso(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Curso not found"));
        cursoRepository.delete(curso);
    }

    private CursoDTO mapToDTO(Curso curso) {
        CursoDTO cursoDTO = new CursoDTO();
        cursoDTO.setId(curso.getId());
        cursoDTO.setNombre(curso.getNombre());
        cursoDTO.setCategoria(curso.getCategoria());
        return cursoDTO;
    }
}

