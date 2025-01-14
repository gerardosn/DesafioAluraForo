package gerardo.sn.desafioAluraForo.controller;

//package com.example.foro.controller;

import gerardo.sn.desafioAluraForo.dtos.CursoDTO;
import gerardo.sn.desafioAluraForo.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<CursoDTO>> getAllCursos() {
        return ResponseEntity.ok(cursoService.getAllCursos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> getCursoById(@PathVariable Long id) {
        return ResponseEntity.ok(cursoService.getCursoById(id));
    }

    @PostMapping
    public ResponseEntity<CursoDTO> createCurso(@RequestBody CursoDTO cursoDTO) {
        return new ResponseEntity<>(cursoService.createCurso(cursoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoDTO> updateCurso(@PathVariable Long id, @RequestBody CursoDTO cursoDTO) {
        return ResponseEntity.ok(cursoService.updateCurso(id, cursoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Long id) {
        cursoService.deleteCurso(id);
        return ResponseEntity.noContent().build();
    }
}
