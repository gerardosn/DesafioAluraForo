package gerardo.sn.desafioAluraForo.controller;

import gerardo.sn.desafioAluraForo.dtos.TopicoDTO;
import gerardo.sn.desafioAluraForo.exception.NotFoundException;
import gerardo.sn.desafioAluraForo.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @GetMapping
    public ResponseEntity<List<TopicoDTO>> getAllTopicos() {
        return ResponseEntity.ok(topicoService.getAllTopicos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDTO> getTopicoById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(topicoService.getTopicoById(id));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<TopicoDTO> createTopico(@Valid @RequestBody TopicoDTO topicoDTO) {
        return new ResponseEntity<>(topicoService.createTopico(topicoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoDTO> updateTopico(@PathVariable Long id, @Valid @RequestBody TopicoDTO topicoDTO) {
        try {
            return ResponseEntity.ok(topicoService.updateTopico(id, topicoDTO));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopico(@PathVariable Long id) {
        try {
            topicoService.deleteTopico(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
