package pe.edu.codigo.biblioteca.application.port.output;

import pe.edu.codigo.biblioteca.domain.model.Libro;

import java.util.List;
import java.util.Optional;

/**
 * PUERTO DE SALIDA (Output Port)
 * Contrato que debe cumplir cualquier adaptador de persistencia (Base de Datos).
 * Permite que el dominio guarde datos sin saber CÓMO se guardan.
 */
public interface LibroRepositoryPort {
    List<Libro> findAll();
    Optional<Libro> findById(Long id);
    Libro save(Libro libro);
    void deleteById(Long id);
    List<Libro> search(String term);
    List<Libro> findByDisponible(Boolean disponible);
}
