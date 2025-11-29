package pe.edu.codigo.biblioteca.infraestructure.adapter.output.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pe.edu.codigo.biblioteca.application.port.output.LibroRepositoryPort;
import pe.edu.codigo.biblioteca.domain.model.Libro;
import pe.edu.codigo.biblioteca.infraestructure.adapter.output.persistence.entity.LibroEntity;
import pe.edu.codigo.biblioteca.infraestructure.adapter.output.persistence.mapper.LibroPersistenceMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * ADAPTADOR DE PERSISTENCIA
 * Implementa el Puerto de Salida definido en el dominio.
 * Usa JPA y Mapper para traducir las peticiones del dominio a SQL.
 */
@Repository
@RequiredArgsConstructor
public class LibroRepositoryAdapter implements LibroRepositoryPort {

    private final LibroJpaRepository jpaRepository;
    private final LibroPersistenceMapper mapper;

    @Override
    public List<Libro> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Libro> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Libro save(Libro libro) {
        LibroEntity entity = mapper.toEntity(libro);
        return mapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public List<Libro> search(String term) {
        return jpaRepository.buscarFlexible(term).stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Libro> findByDisponible(Boolean disponible) {
        return jpaRepository.findByDisponible(disponible).stream().map(mapper::toDomain).collect(Collectors.toList());
    }
}