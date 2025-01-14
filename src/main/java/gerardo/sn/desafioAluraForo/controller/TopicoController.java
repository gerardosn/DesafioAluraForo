package gerardo.sn.desafioAluraForo.controller;

//package com.example.foro.controller;

import gerardo.sn.desafioAluraForo.dtos.TopicoDTO;
import gerardo.sn.desafioAluraForo.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(topicoService.getTopicoById(id));
    }

    @PostMapping
    public ResponseEntity<TopicoDTO> createTopico(@RequestBody TopicoDTO topicoDTO) {
        return new ResponseEntity<>(topicoService.createTopico(topicoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoDTO> updateTopico(@PathVariable Long id, @RequestBody TopicoDTO topicoDTO) {
        return ResponseEntity.ok(topicoService.updateTopico(id, topicoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopico(@PathVariable Long id) {
        topicoService.deleteTopico(id);
        return ResponseEntity.noContent().build();
    }
}
