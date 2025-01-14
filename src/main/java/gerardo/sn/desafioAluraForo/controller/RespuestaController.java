package gerardo.sn.desafioAluraForo.controller;

//package com.example.foro.controller;

import gerardo.sn.desafioAluraForo.dtos.RespuestaDTO;
import gerardo.sn.desafioAluraForo.service.RespuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/respuestas")
public class RespuestaController {

    @Autowired
    private RespuestaService respuestaService;

    @GetMapping
    public ResponseEntity<List<RespuestaDTO>> getAllRespuestas() {
        return ResponseEntity.ok(respuestaService.getAllRespuestas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaDTO> getRespuestaById(@PathVariable Long id) {
        return ResponseEntity.ok(respuestaService.getRespuestaById(id));
    }

    @PostMapping
    public ResponseEntity<RespuestaDTO> createRespuesta(@RequestBody RespuestaDTO respuestaDTO) {
        return new ResponseEntity<>(respuestaService.createRespuesta(respuestaDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespuestaDTO> updateRespuesta(@PathVariable Long id, @RequestBody RespuestaDTO respuestaDTO) {
        return ResponseEntity.ok(respuestaService.updateRespuesta(id, respuestaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRespuesta(@PathVariable Long id) {
        respuestaService.deleteRespuesta(id);
        return ResponseEntity.noContent().build();
    }
}