package pe.edu.codigo.biblioteca.application.port.input;

import pe.edu.codigo.biblioteca.domain.model.Libro;

import java.util.List;

/**
 * PUERTO DE ENTRADA (Input Port)
 * Define los Casos de Uso disponibles para los adaptadores de entrada (Controladores).
 */
public interface LibroService {
    List<Libro> obtenerTodos();
    List<Libro> buscar(String termino);
    List<Libro> obtenerPorEstado(String estado); // Lógica para filtros del frontend
    Libro obtenerPorId(Long id);
    Libro guardar(Libro libro);
    Libro actualizar(Long id, Libro libro);
    void eliminar(Long id);
    Libro prestar(Long id);
    Libro devolver(Long id);
}