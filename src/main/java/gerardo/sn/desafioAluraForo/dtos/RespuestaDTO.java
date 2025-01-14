package gerardo.sn.desafioAluraForo.dtos;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class RespuestaDTO {

    private Long id;

    @NotBlank
    private String mensaje;

    private Long topicoId;

    private LocalDateTime fechaCreacion;

    private Long autorId;

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