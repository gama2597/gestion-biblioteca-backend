package pe.edu.codigo.biblioteca.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * MODELO DE DOMINIO
 * Representa la entidad central del negocio.
 * Es agnóstica a la infraestructura (no tiene @Entity de JPA ni @JsonProperty de Jackson).
 * Puede ser reutilizada en cualquier entorno Java.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Libro {
    private Long id;
    private String titulo;
    private String autor;
    private String isbn;
    private String genero;
    private Integer anioPublicacion;
    private Boolean disponible;
}