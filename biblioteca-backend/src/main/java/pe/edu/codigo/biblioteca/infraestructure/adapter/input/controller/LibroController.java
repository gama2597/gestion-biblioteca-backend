package pe.edu.codigo.biblioteca.infraestructure.adapter.input.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.codigo.biblioteca.application.port.input.LibroService;
import pe.edu.codigo.biblioteca.domain.model.Libro;
import pe.edu.codigo.biblioteca.infraestructure.adapter.input.dto.LibroRequest;
import pe.edu.codigo.biblioteca.infraestructure.adapter.input.dto.LibroResponse;
import pe.edu.codigo.biblioteca.infraestructure.adapter.input.mapper.LibroRestMapper;

import java.util.List;

@RestController
@RequestMapping("/libros")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class LibroController {

    private final LibroService service;
    private final LibroRestMapper mapper;

    @GetMapping
    public List<LibroResponse> listar(@RequestParam(required = false) String busqueda) {
        List<Libro> libros = (busqueda != null && !busqueda.isEmpty())
                ? service.buscar(busqueda)
                : service.obtenerTodos();
        return mapper.toResponseList(libros);
    }

    // Ruta específica para estados
    @GetMapping("/{estado}")
    public List<LibroResponse> listarPorEstado(@PathVariable String estado) {
        return mapper.toResponseList(service.obtenerPorEstado(estado));
    }

    // Ruta específica para detalle por ID (evita conflicto con /{estado})
    @GetMapping("/detalle/{id}")
    public ResponseEntity<LibroResponse> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toResponse(service.obtenerPorId(id)));
    }

    @PostMapping
    public ResponseEntity<LibroResponse> crear(@Valid @RequestBody LibroRequest request) {
        Libro libro = mapper.toDomain(request);
        return ResponseEntity.ok(mapper.toResponse(service.guardar(libro)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibroResponse> actualizar(@PathVariable Long id, @Valid @RequestBody LibroRequest request) {
        Libro libro = mapper.toDomain(request);
        return ResponseEntity.ok(mapper.toResponse(service.actualizar(id, libro)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/prestar")
    public ResponseEntity<LibroResponse> prestar(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toResponse(service.prestar(id)));
    }

    @PatchMapping("/{id}/devolver")
    public ResponseEntity<LibroResponse> devolver(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toResponse(service.devolver(id)));
    }
}