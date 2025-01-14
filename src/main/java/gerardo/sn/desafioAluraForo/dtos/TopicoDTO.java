package gerardo.sn.desafioAluraForo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class TopicoDTO {

    private Long id;

    @NotBlank(message = "El título no puede estar vacío")
    @Size(min = 3, max = 100, message = "El título debe tener entre 3 y 100 caracteres")
    private String titulo;

    @NotBlank(message = "El mensaje no puede estar vacío")
    @Size(min = 10, max = 1000, message = "El mensaje debe tener entre 10 y 1000 caracteres")
    private String mensaje;

    @PastOrPresent(message = "La fecha de creación debe ser en el pasado o presente")
    private LocalDateTime fechaCreacion;

    @Pattern(regexp = "abierto|cerrado|pendiente", message = "El estado debe ser 'abierto', 'cerrado' o 'pendiente'")
    private String status;

    @NotNull(message = "El ID del autor no puede ser nulo")
    private Long autorId;

    @NotNull(message = "El ID del curso no puede ser nulo")
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
