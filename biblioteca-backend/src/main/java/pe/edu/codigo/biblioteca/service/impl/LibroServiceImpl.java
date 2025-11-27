package pe.edu.codigo.biblioteca.service.impl;

import org.springframework.stereotype.Service;
import pe.edu.codigo.biblioteca.domain.Libro;
import pe.edu.codigo.biblioteca.domain.LibroRepository;
import pe.edu.codigo.biblioteca.service.LibroService;

import java.util.List;

@Service
public class LibroServiceImpl implements LibroService {

    private final LibroRepository libroRepository;

    public LibroServiceImpl(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }

    @Override
    public List<Libro> buscar(String termino) {
        return libroRepository.search(termino);
    }

    @Override
    public Libro obtenerPorId(Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con ID: " + id));
    }

    @Override
    public Libro guardar(Libro libro) {
        if(libro.getId() == null) {
            libro.setDisponible(true);
        }
        return libroRepository.save(libro);
    }

    @Override
    public Libro actualizar(Long id, Libro libro) {
        // 1. Buscamos el libro que ya existe en BD
        Libro libroExistente = obtenerPorId(id);

        // 2. Solo actualizamos los campos que NO vengan nulos

        if (libro.getTitulo() != null) {
            libroExistente.setTitulo(libro.getTitulo());
        }

        if (libro.getAutor() != null) {
            libroExistente.setAutor(libro.getAutor());
        }

        if (libro.getIsbn() != null) {
            libroExistente.setIsbn(libro.getIsbn());
        }

        if (libro.getGenero() != null) {
            libroExistente.setGenero(libro.getGenero());
        }

        if (libro.getAnioPublicacion() != null) {
            libroExistente.setAnioPublicacion(libro.getAnioPublicacion());
        }

        if (libro.getDisponible() != null) {
            libroExistente.setDisponible(libro.getDisponible());
        }

        // 3. Guardamos los cambios
        return libroRepository.save(libroExistente);
    }

    @Override
    public void eliminar(Long id) {
        obtenerPorId(id);
        libroRepository.deleteById(id);
    }
}