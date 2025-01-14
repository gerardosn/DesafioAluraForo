package gerardo.sn.desafioAluraForo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String mensaje;

    @ManyToOne
    @JoinColumn(name = "topico", referencedColumnName = "id")
    private Topico topico;

    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "autor", referencedColumnName = "id")
    private Usuario autor;

    private Boolean solucion = false;

    // Getters and Setters

    public Respuesta() {
    }

    public Respuesta(Long id, String mensaje, Topico topico, LocalDateTime fechaCreacion, Usuario autor, Boolean solucion) {
        this.id = id;
        this.mensaje = mensaje;
        this.topico = topico;
        this.fechaCreacion = fechaCreacion;
        this.autor = autor;
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

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Boolean getSolucion() {
        return solucion;
    }

    public void setSolucion(Boolean solucion) {
        this.solucion = solucion;
    }
}