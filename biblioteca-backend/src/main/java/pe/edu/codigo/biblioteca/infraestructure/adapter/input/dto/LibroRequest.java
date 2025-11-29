package pe.edu.codigo.biblioteca.infraestructure.adapter.input.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * DTO DE ENTRADA (Data Transfer Object)
 * Representa los datos que llegan desde el Frontend (JSON).
 * Aquí van las validaciones de formato (@NotBlank, @Size).
 */
@Data
public class LibroRequest {
    @NotBlank(message = "El título es obligatorio")
    @Size(max = 200)
    private String titulo;

    @NotBlank(message = "El autor es obligatorio")
    @Size(max = 100)
    private String autor;

    @NotBlank(message = "El ISBN es obligatorio")
    // CAMBIO: Regex más estricta.
    // Significa: Debe empezar con "ISBN-", seguido de cualquier caracter que NO sea espacio.
    @Pattern(regexp = "^ISBN-\\S+$", message = "El formato debe ser 'ISBN-XXXX...'")
    @Size(max = 30)
    private String isbn;

    @NotBlank(message = "El género es obligatorio")
    private String genero;

    @NotNull(message = "El año es obligatorio")
    @Min(value = 1000, message = "Año mínimo 1000")
    @Max(value = 2025, message = "Año máximo 2025")
    private Integer anioPublicacion;

    private Boolean disponible;
}