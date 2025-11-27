package pe.edu.codigo.biblioteca.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.codigo.biblioteca.domain.Libro;
import pe.edu.codigo.biblioteca.service.LibroService;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/libros")
@CrossOrigin(origins = "http://localhost:4200")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public List<Libro> listar(@RequestParam(required = false) String busqueda) {
        if (busqueda != null && !busqueda.isEmpty()) {
            return libroService.buscar(busqueda);
        }
        return libroService.obtenerTodos();
    }

    @GetMapping("/disponibles")
    public List<Libro> listarDisponibles() {
        return libroService.listarDisponibles();
    }

    @GetMapping("/prestados")
    public List<Libro> listarPrestados() {
        return libroService.listarPrestados();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(libroService.obtenerPorId(id));
    }

    @PostMapping
    public Libro crear(@Valid @RequestBody Libro libro) {
        return libroService.guardar(libro);
    }

    @PutMapping("/{id}")
    public Libro actualizar(@PathVariable Long id, @Valid @RequestBody Libro libro) {
        return libroService.actualizar(id, libro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        libroService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/prestar")
    public ResponseEntity<Libro> prestar(@PathVariable Long id) {
        return ResponseEntity.ok(libroService.prestar(id));
    }

    @PatchMapping("/{id}/devolver")
    public ResponseEntity<Libro> devolver(@PathVariable Long id) {
        return ResponseEntity.ok(libroService.devolver(id));
    }
}