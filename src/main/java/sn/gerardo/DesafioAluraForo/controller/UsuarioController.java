package sn.gerardo.DesafioAluraForo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.gerardo.DesafioAluraForo.dto.UsuarioDTO;
import sn.gerardo.DesafioAluraForo.service.UsuarioService;

import java.util.List;

//public class UsuarioController {
//}
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    @Autowired
    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<UsuarioDTO> obtenerTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}")
    public UsuarioDTO obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    public UsuarioDTO crear(@RequestBody UsuarioDTO usuarioDTO) {
        return service.crear(usuarioDTO);
    }

    @PutMapping("/{id}")
    public UsuarioDTO actualizar(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        return service.actualizar(id, usuarioDTO);
    }

//    @PatchMapping("/{id}")
//    public void eliminarLogico(@PathVariable Long id) {
//        service.eliminarLogico(id);
//    }
    @GetMapping("/ocultar/{id}")//sin usar el patch
      public ResponseEntity<String> eliminarLogico(@PathVariable Long id) {
      String mensaje = service.eliminarLogico(id);
      return ResponseEntity.ok(mensaje);//muestro el estado
    }
}