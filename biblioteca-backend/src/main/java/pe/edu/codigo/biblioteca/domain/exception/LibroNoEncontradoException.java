package pe.edu.codigo.biblioteca.domain.exception;

/**
 * EXCEPCIÓN DE DOMINIO
 * Se lanza cuando se intenta acceder a un libro que no existe.
 */
public class LibroNoEncontradoException extends RuntimeException {
    public LibroNoEncontradoException(Long id) {
        super("No se encontró el libro con el ID: " + id);
    }
}