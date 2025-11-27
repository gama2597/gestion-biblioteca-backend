package pe.edu.codigo.biblioteca.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Libro {
    private Long id;

    @NotBlank(message = "El título es obligatorio")
    @Size(max = 200, message = "El título no debe superar los 200 caracteres")
    private String titulo;

    @NotBlank(message = "El autor es obligatorio")
    @Size(max = 100, message = "El autor no debe superar los 100 caracteres")
    private String autor;

    @NotBlank(message = "El ISBN es obligatorio")
    // Validamos que empiece con ISBN- y tenga longitud razonable
    @Pattern(regexp = "^ISBN-.*", message = "El ISBN debe comenzar con 'ISBN-'")
    @Size(max = 30, message = "El ISBN es demasiado largo")
    private String isbn;

    @NotBlank(message = "El género es obligatorio")
    @Size(max = 50, message = "El género no debe superar los 50 caracteres")
    private String genero;

    @NotNull(message = "El año es obligatorio")
    @Min(value = 1000, message = "El año debe ser mayor a 1000")
    @Max(value = 2025, message = "El año no puede ser futuro (max 2025)")
    private Integer anioPublicacion;

    private Boolean disponible;
}
