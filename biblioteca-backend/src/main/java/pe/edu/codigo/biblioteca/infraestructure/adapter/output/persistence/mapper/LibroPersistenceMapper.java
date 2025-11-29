package pe.edu.codigo.biblioteca.infraestructure.adapter.output.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import pe.edu.codigo.biblioteca.domain.model.Libro;
import pe.edu.codigo.biblioteca.infraestructure.adapter.output.persistence.entity.LibroEntity;

/**
 * MAPPER DE PERSISTENCIA (Domain <-> Entity)
 * Totalmente separado del Mapper Web.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LibroPersistenceMapper {
    Libro toDomain(LibroEntity entity);

    @InheritInverseConfiguration
    LibroEntity toEntity(Libro domain);
}