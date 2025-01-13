package gerardo.sn.desafioAluraForo.entity;

import jakarta.persistence.*;
//import javax.persistence.Entity;
//import javax.persistence.Table;
//import javax.persistence.Column;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
import jakarta.validation.constraints.*;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Size;

@Entity
@Table(name = "perfil")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es requerido")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    @Column(name = "nombre")
    private String nombre;

    // Constructor, getters y setters
    public Perfil() {}

    public Perfil(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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
}
