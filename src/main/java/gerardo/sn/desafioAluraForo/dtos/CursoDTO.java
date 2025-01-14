package gerardo.sn.desafioAluraForo.dtos;

import jakarta.validation.constraints.NotBlank;

public class CursoDTO {

    private Long id;

    @NotBlank
    private String nombre;

    private String categoria;

    // Getters and Setters

    public CursoDTO() {
    }

    public CursoDTO(Long id, String nombre, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
