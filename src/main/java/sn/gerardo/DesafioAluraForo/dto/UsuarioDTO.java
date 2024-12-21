package sn.gerardo.DesafioAluraForo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(
        Long id,
        @NotBlank(message = "El nombre es requerido")
        String nombre,
        @NotBlank(message = "El correo electrónico es requerido")
        @Email(message = "El correo electrónico no es válido")
        String correoElectronico,
        @NotBlank(message = "La contraseña es requerida")
        String contrasena,
        Long perfilId,
        Boolean activo
        ) {}