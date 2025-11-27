package pe.edu.codigo.biblioteca.service;

import pe.edu.codigo.biblioteca.domain.Libro;

import java.util.List;

public interface LibroService {
    List<Libro> obtenerTodos();
    List<Libro> buscar(String termino);
    Libro obtenerPorId(Long id);
    Libro guardar(Libro libro);
    Libro actualizar(Long id, Libro libro);
    void eliminar(Long id);

    List<Libro> listarDisponibles();
    List<Libro> listarPrestados();
    Libro prestar(Long id);
    Libro devolver(Long id);
}