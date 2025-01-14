package gerardo.sn.desafioAluraForo.service;

import gerardo.sn.desafioAluraForo.dtos.TopicoDTO;
import gerardo.sn.desafioAluraForo.entity.Curso;
import gerardo.sn.desafioAluraForo.entity.Topico;
import gerardo.sn.desafioAluraForo.entity.Usuario;
import gerardo.sn.desafioAluraForo.exception.NotFoundException;
import gerardo.sn.desafioAluraForo.repository.CursoRepository;
import gerardo.sn.desafioAluraForo.repository.TopicoRepository;
import gerardo.sn.desafioAluraForo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public List<TopicoDTO> getAllTopicos() {
        return topicoRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public TopicoDTO getTopicoById(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Topico not found"));
        return mapToDTO(topico);
    }

    public TopicoDTO createTopico(TopicoDTO topicoDTO) {
        Topico topico = new Topico();
        topico.setTitulo(topicoDTO.getTitulo());
        topico.setMensaje(topicoDTO.getMensaje());
        topico.setStatus(topicoDTO.getStatus());

        // Buscar el usuario por ID
        Usuario autor = usuarioRepository.findById(topicoDTO.getAutorId())
                .orElseThrow(() -> new NotFoundException("Usuario not found"));
        topico.setAutor(autor);

        // Buscar el curso por ID
        Curso curso = cursoRepository.findById(topicoDTO.getCursoId())
                .orElseThrow(() -> new NotFoundException("Curso not found"));
        topico.setCurso(curso);

        Topico savedTopico = topicoRepository.save(topico);
        return mapToDTO(savedTopico);
    }

    public TopicoDTO updateTopico(Long id, TopicoDTO topicoDTO) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Topico not found"));
        topico.setTitulo(topicoDTO.getTitulo());
        topico.setMensaje(topicoDTO.getMensaje());
        topico.setStatus(topicoDTO.getStatus());

        // Buscar el usuario por ID
        Usuario autor = usuarioRepository.findById(topicoDTO.getAutorId())
                .orElseThrow(() -> new NotFoundException("Usuario not found"));
        topico.setAutor(autor);

        // Buscar el curso por ID
        Curso curso = cursoRepository.findById(topicoDTO.getCursoId())
                .orElseThrow(() -> new NotFoundException("Curso not found"));
        topico.setCurso(curso);

        Topico updatedTopico = topicoRepository.save(topico);
        return mapToDTO(updatedTopico);
    }

    public void deleteTopico(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Topico not found"));
        topicoRepository.delete(topico);
    }

    private TopicoDTO mapToDTO(Topico topico) {
        TopicoDTO topicoDTO = new TopicoDTO();
        topicoDTO.setId(topico.getId());
        topicoDTO.setTitulo(topico.getTitulo());
        topicoDTO.setMensaje(topico.getMensaje());
        topicoDTO.setFechaCreacion(topico.getFechaCreacion());
        topicoDTO.setStatus(topico.getStatus());
        topicoDTO.setAutorId(topico.getAutor().getId());
        topicoDTO.setCursoId(topico.getCurso().getId());
        return topicoDTO;
    }
}