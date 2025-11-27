package pe.edu.codigo.biblioteca.domain;

import java.util.List;
import java.util.Optional;

public interface LibroRepository {
    List<Libro> findAll();
    Optional<Libro> findById(Long id);
    Libro save(Libro libro);
    void deleteById(Long id);
    List<Libro> search(String term);
    List<Libro> findByStatus(Boolean disponible);
}
