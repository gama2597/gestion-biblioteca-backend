package pe.edu.codigo.biblioteca.web.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Error de Validación (@NotBlank, @Size, etc.)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> manejarValidaciones(MethodArgumentNotValidException ex) {
        Map<String, Object> errores = new HashMap<>();
        errores.put("timestamp", LocalDateTime.now());
        errores.put("mensaje", "Error en los datos enviados");

        // Extraer campo y error específico
        Map<String, String> detalles = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                detalles.put(error.getField(), error.getDefaultMessage())
        );
        errores.put("errores", detalles);

        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }

    // 2. Error de Base de Datos (ISBN Duplicado)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> manejarDuplicados(DataIntegrityViolationException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("mensaje", "Error de integridad de datos");

        // Verificamos si es por el ISBN
        if (ex.getMessage() != null && ex.getMessage().contains("ISBN")) {
            error.put("detalle", "El ISBN ingresado ya existe en el sistema.");
        } else {
            error.put("detalle", "Conflicto de datos únicos.");
        }

        return new ResponseEntity<>(error, HttpStatus.CONFLICT); // 409 Conflict
    }

    // 3. Otros errores (Runtime)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> manejarRuntime(RuntimeException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("mensaje", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}