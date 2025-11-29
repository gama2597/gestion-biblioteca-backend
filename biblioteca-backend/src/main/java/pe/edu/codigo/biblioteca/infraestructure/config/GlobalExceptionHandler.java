package pe.edu.codigo.biblioteca.infraestructure.config;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pe.edu.codigo.biblioteca.domain.exception.LibroNoEncontradoException;
import pe.edu.codigo.biblioteca.domain.exception.OperacionNoPermitidaException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * MANEJADOR DE ERRORES GLOBAL
 * Traduce las excepciones de Dominio a Respuestas HTTP amigables (JSON).
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Error 404: No encontrado
    @ExceptionHandler(LibroNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> manejarNoEncontrado(LibroNoEncontradoException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Error 409: Conflicto (Regla de negocio violada)
    @ExceptionHandler(OperacionNoPermitidaException.class)
    public ResponseEntity<Map<String, Object>> manejarOperacionIlegal(OperacionNoPermitidaException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.CONFLICT);
    }

    // Error 409: Duplicados en BD (ISBN único)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> manejarDuplicados(DataIntegrityViolationException ex) {
        String mensaje = ex.getMessage().contains("ISBN") ? "El ISBN ya existe." : "Error de integridad de datos.";
        return buildResponse(mensaje, HttpStatus.CONFLICT);
    }

    // Error 400: Validaciones (@NotNull, @Size)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> manejarValidaciones(MethodArgumentNotValidException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("mensaje", "Datos inválidos");
        Map<String, String> detalles = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(e -> detalles.put(e.getField(), e.getDefaultMessage()));
        error.put("errores", detalles);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Map<String, Object>> buildResponse(String mensaje, HttpStatus estado) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("mensaje", mensaje);
        error.put("estado", estado.value());
        return new ResponseEntity<>(error, estado);
    }
}