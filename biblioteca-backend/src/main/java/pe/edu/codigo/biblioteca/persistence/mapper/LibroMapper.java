package pe.edu.codigo.biblioteca.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import pe.edu.codigo.biblioteca.domain.Libro;
import pe.edu.codigo.biblioteca.persistence.entity.LibroEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LibroMapper {
    Libro toDomain(LibroEntity entity);

    @InheritInverseConfiguration
    LibroEntity toEntity(Libro domain);
}
