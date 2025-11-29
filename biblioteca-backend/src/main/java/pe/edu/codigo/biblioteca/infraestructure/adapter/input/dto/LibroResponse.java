package pe.edu.codigo.biblioteca.infraestructure.adapter.input.dto;

import lombok.Data;

/**
 * DTO DE SALIDA
 * Representa lo que respondemos al Frontend.
 * Incluye el ID (que el Request no tiene).
 */
@Data
public class LibroResponse {
    private Long id;
    private String titulo;
    private String autor;
    private String isbn;
    private String genero;
    private Integer anioPublicacion;
    private Boolean disponible;
}