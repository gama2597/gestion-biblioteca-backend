package pe.edu.codigo.biblioteca.application.service;

import lombok.RequiredArgsConstructor;
import pe.edu.codigo.biblioteca.application.port.input.LibroService;
import pe.edu.codigo.biblioteca.application.port.output.LibroRepositoryPort;
import pe.edu.codigo.biblioteca.domain.exception.LibroNoEncontradoException;
import pe.edu.codigo.biblioteca.domain.exception.OperacionNoPermitidaException;
import pe.edu.codigo.biblioteca.domain.model.Libro;

import java.util.List;

/**
 * IMPLEMENTACIÓN DEL SERVICIO (Lógica de Negocio Pura)
 * NO tiene anotaciones de Spring (@Service). Se inyecta vía Configuración.
 * Aquí se aplican las reglas del negocio y se lanzan excepciones de dominio.
 */
@RequiredArgsConstructor
public class LibroServiceImpl implements LibroService {

    private final LibroRepositoryPort repositoryPort;

    @Override
    public List<Libro> obtenerTodos() {
        return repositoryPort.findAll();
    }

    @Override
    public List<Libro> buscar(String termino) {
        return repositoryPort.search(termino);
    }

    @Override
    public List<Libro> obtenerPorEstado(String estado) {
        if ("disponibles".equalsIgnoreCase(estado)) return repositoryPort.findByDisponible(true);
        if ("prestados".equalsIgnoreCase(estado)) return repositoryPort.findByDisponible(false);
        return repositoryPort.findAll();
    }

    @Override
    public Libro obtenerPorId(Long id) {
        return repositoryPort.findById(id)
                .orElseThrow(() -> new LibroNoEncontradoException(id));
    }

    @Override
    public Libro guardar(Libro libro) {
        // Regla: Si es nuevo y no se especifica, está disponible por defecto
        if (libro.getId() == null && libro.getDisponible() == null) {
            libro.setDisponible(true);
        }
        return repositoryPort.save(libro);
    }

    @Override
    public Libro actualizar(Long id, Libro nuevosDatos) {
        Libro existente = obtenerPorId(id);

        // Actualización parcial de campos
        if (nuevosDatos.getTitulo() != null) existente.setTitulo(nuevosDatos.getTitulo());
        if (nuevosDatos.getAutor() != null) existente.setAutor(nuevosDatos.getAutor());
        if (nuevosDatos.getIsbn() != null) existente.setIsbn(nuevosDatos.getIsbn());
        if (nuevosDatos.getGenero() != null) existente.setGenero(nuevosDatos.getGenero());
        if (nuevosDatos.getAnioPublicacion() != null) existente.setAnioPublicacion(nuevosDatos.getAnioPublicacion());
        if (nuevosDatos.getDisponible() != null) existente.setDisponible(nuevosDatos.getDisponible());

        return repositoryPort.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        obtenerPorId(id); // Validamos que exista antes de borrar
        repositoryPort.deleteById(id);
    }

    @Override
    public Libro prestar(Long id) {
        Libro libro = obtenerPorId(id);
        if (Boolean.FALSE.equals(libro.getDisponible())) {
            throw new OperacionNoPermitidaException("El libro ya se encuentra prestado.");
        }
        libro.setDisponible(false);
        return repositoryPort.save(libro);
    }

    @Override
    public Libro devolver(Long id) {
        Libro libro = obtenerPorId(id);
        if (Boolean.TRUE.equals(libro.getDisponible())) {
            throw new OperacionNoPermitidaException("El libro ya se encuentra en la biblioteca.");
        }
        libro.setDisponible(true);
        return repositoryPort.save(libro);
    }
}