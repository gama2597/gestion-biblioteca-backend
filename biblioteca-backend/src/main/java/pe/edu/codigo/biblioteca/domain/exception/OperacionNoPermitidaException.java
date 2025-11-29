package pe.edu.codigo.biblioteca.domain.exception;

/**
 * EXCEPCIÓN DE DOMINIO
 * Se lanza cuando se intenta realizar una acción ilegal en el negocio
 * (ej: prestar un libro que ya está prestado).
 */
public class OperacionNoPermitidaException extends RuntimeException {
    public OperacionNoPermitidaException(String mensaje) {
        super(mensaje);
    }
}