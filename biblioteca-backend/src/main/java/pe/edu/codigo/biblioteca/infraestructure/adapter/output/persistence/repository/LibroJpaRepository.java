package pe.edu.codigo.biblioteca.infraestructure.adapter.output.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.codigo.biblioteca.infraestructure.adapter.output.persistence.entity.LibroEntity;

import java.util.List;

public interface LibroJpaRepository extends JpaRepository<LibroEntity, Long> {

    @Query("SELECT l FROM LibroEntity l WHERE " +
            "LOWER(l.titulo) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
            "LOWER(l.autor) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
            "LOWER(l.genero) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
            "LOWER(l.isbn) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
            "CAST(l.anioPublicacion AS string) LIKE CONCAT('%', :term, '%')")
    List<LibroEntity> buscarFlexible(@Param("term") String term);

    List<LibroEntity> findByDisponible(Boolean disponible);
}