package gerardo.sn.desafioAluraForo.controller;

import gerardo.sn.desafioAluraForo.dtos.PerfilDto;
import gerardo.sn.desafioAluraForo.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perfiles")
public class PerfilController {
    private final PerfilService perfilService;

    @Autowired
    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @GetMapping
    public ResponseEntity<List<PerfilDto>> getAllPerfiles() {
        return ResponseEntity.ok(perfilService.getAllPerfiles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerfilDto> getPerfilById(@PathVariable Long id) {
        return ResponseEntity.ok(perfilService.getPerfilById(id));
    }

    @PostMapping
    public ResponseEntity<PerfilDto> createPerfil(@RequestBody PerfilDto perfilDto) {
        return ResponseEntity.ok(perfilService.createPerfil(perfilDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PerfilDto> updatePerfil(@PathVariable Long id, @RequestBody PerfilDto perfilDto) {
        return ResponseEntity.ok(perfilService.updatePerfil(id, perfilDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerfil(@PathVariable Long id) {
        perfilService.deletePerfil(id);
        return ResponseEntity.noContent().build();
    }
}
