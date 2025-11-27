package pe.edu.codigo.biblioteca.persistence;

import org.springframework.stereotype.Repository;
import pe.edu.codigo.biblioteca.domain.Libro;
import pe.edu.codigo.biblioteca.domain.LibroRepository;
import pe.edu.codigo.biblioteca.persistence.entity.LibroEntity;
import pe.edu.codigo.biblioteca.persistence.jpa.LibroJpaRepository;
import pe.edu.codigo.biblioteca.persistence.mapper.LibroMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class LibroRepositoryImpl implements LibroRepository {

    private final LibroJpaRepository jpaRepository;
    private final LibroMapper mapper;

    public LibroRepositoryImpl(LibroJpaRepository jpaRepository, LibroMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Libro> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Libro> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Libro save(Libro libro) {
        LibroEntity entity = mapper.toEntity(libro);
        LibroEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public List<Libro> search(String term) {
        return jpaRepository.buscarSensible(term).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}