package gerardo.sn.desafioAluraForo.dtos;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class TopicoDTO {

    private Long id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String mensaje;

    private LocalDateTime fechaCreacion;

    private String status;

    private Long autorId;

    private Long cursoId;

    // Getters and Setters

    public TopicoDTO() {
    }

    public TopicoDTO(Long id, String titulo, String mensaje, LocalDateTime fechaCreacion, String status, Long autorId, Long cursoId) {
        this.id = id;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaCreacion = fechaCreacion;
        this.status = status;
        this.autorId = autorId;
        this.cursoId = cursoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }
}
