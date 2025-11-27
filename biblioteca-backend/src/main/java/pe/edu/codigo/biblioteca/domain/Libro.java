package pe.edu.codigo.biblioteca.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
