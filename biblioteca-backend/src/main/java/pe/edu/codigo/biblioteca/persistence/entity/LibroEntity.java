package pe.edu.codigo.biblioteca.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "libros")
public class LibroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;
    private String isbn;
    private String genero;

    @Column(name = "anio_publicacion")
    private Integer anioPublicacion;

    private Boolean disponible;
}