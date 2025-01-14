package gerardo.sn.desafioAluraForo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class RespuestaDTO {

    private Long id;


    @NotBlank(message = "El mensaje no puede estar vacío")
    @Size(min = 10, max = 1000, message = "El mensaje debe tener entre 10 y 1000 caracteres")
    private String mensaje;

    @NotNull(message = "El ID del tópico no puede ser nulo")
    private Long topicoId;

    @PastOrPresent(message = "La fecha de creación debe ser en el pasado o presente")
    private LocalDateTime fechaCreacion;

    @NotNull(message = "El ID del autor no puede ser nulo")
    private Long autorId;

    @NotNull(message = "El campo de solución no puede ser nulo")
    private Boolean solucion;

    // Getters and Setters

    public RespuestaDTO() {
    }

    public RespuestaDTO(Long id, String mensaje, Long topicoId, LocalDateTime fechaCreacion, Long autorId, Boolean solucion) {
        this.id = id;
        this.mensaje = mensaje;
        this.topicoId = topicoId;
        this.fechaCreacion = fechaCreacion;
        this.autorId = autorId;
        this.solucion = solucion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Long getTopicoId() {
        return topicoId;
    }

    public void setTopicoId(Long topicoId) {
        this.topicoId = topicoId;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }

    public Boolean getSolucion() {
        return solucion;
    }

    public void setSolucion(Boolean solucion) {
        this.solucion = solucion;
    }
}