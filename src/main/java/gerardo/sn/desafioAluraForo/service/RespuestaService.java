package gerardo.sn.desafioAluraForo.service;

import gerardo.sn.desafioAluraForo.dtos.RespuestaDTO;
import gerardo.sn.desafioAluraForo.entity.Respuesta;
import gerardo.sn.desafioAluraForo.entity.Topico;
import gerardo.sn.desafioAluraForo.entity.Usuario;
import gerardo.sn.desafioAluraForo.exception.NotFoundException;
import gerardo.sn.desafioAluraForo.repository.RespuestaRepository;
import gerardo.sn.desafioAluraForo.repository.TopicoRepository;
import gerardo.sn.desafioAluraForo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RespuestaService {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<RespuestaDTO> getAllRespuestas() {
        return respuestaRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public RespuestaDTO getRespuestaById(Long id) {
        Respuesta respuesta = respuestaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Respuesta not found"));
        return mapToDTO(respuesta);
    }

    public RespuestaDTO createRespuesta(RespuestaDTO respuestaDTO) {
        Respuesta respuesta = new Respuesta();
        respuesta.setMensaje(respuestaDTO.getMensaje());

        // Buscar el tópico por ID
        Topico topico = topicoRepository.findById(respuestaDTO.getTopicoId())
                .orElseThrow(() -> new NotFoundException("Topico not found"));
        respuesta.setTopico(topico);

        // Buscar el usuario por ID
        Usuario autor = usuarioRepository.findById(respuestaDTO.getAutorId())
                .orElseThrow(() -> new NotFoundException("Usuario not found"));
        respuesta.setAutor(autor);

        respuesta.setSolucion(respuestaDTO.getSolucion());
        Respuesta savedRespuesta = respuestaRepository.save(respuesta);
        return mapToDTO(savedRespuesta);
    }

    public RespuestaDTO updateRespuesta(Long id, RespuestaDTO respuestaDTO) {
        Respuesta respuesta = respuestaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Respuesta not found"));
        respuesta.setMensaje(respuestaDTO.getMensaje());

        // Buscar el tópico por ID
        Topico topico = topicoRepository.findById(respuestaDTO.getTopicoId())
                .orElseThrow(() -> new NotFoundException("Topico not found"));
        respuesta.setTopico(topico);

        // Buscar el usuario por ID
        Usuario autor = usuarioRepository.findById(respuestaDTO.getAutorId())
                .orElseThrow(() -> new NotFoundException("Usuario not found"));
        respuesta.setAutor(autor);

        respuesta.setSolucion(respuestaDTO.getSolucion());
        Respuesta updatedRespuesta = respuestaRepository.save(respuesta);
        return mapToDTO(updatedRespuesta);
    }

    public void deleteRespuesta(Long id) {
        Respuesta respuesta = respuestaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Respuesta not found"));
        respuestaRepository.delete(respuesta);
    }

    private RespuestaDTO mapToDTO(Respuesta respuesta) {
        RespuestaDTO respuestaDTO = new RespuestaDTO();
        respuestaDTO.setId(respuesta.getId());
        respuestaDTO.setMensaje(respuesta.getMensaje());
        respuestaDTO.setTopicoId(respuesta.getTopico().getId());
        respuestaDTO.setFechaCreacion(respuesta.getFechaCreacion());
        respuestaDTO.setAutorId(respuesta.getAutor().getId());
        respuestaDTO.setSolucion(respuesta.getSolucion());
        return respuestaDTO;
    }
}