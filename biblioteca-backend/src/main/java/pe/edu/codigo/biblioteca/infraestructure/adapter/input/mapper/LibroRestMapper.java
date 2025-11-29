package pe.edu.codigo.biblioteca.infraestructure.adapter.input.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import pe.edu.codigo.biblioteca.domain.model.Libro;
import pe.edu.codigo.biblioteca.infraestructure.adapter.input.dto.LibroRequest;
import pe.edu.codigo.biblioteca.infraestructure.adapter.input.dto.LibroResponse;

import java.util.List;

/**
 * MAPPER DE ENTRADA (Web <-> Domain)
 * Usa MapStruct para convertir automáticamente.
 */
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface LibroRestMapper {

    // De JSON a Dominio (El request no tiene ID, se ignora)
    @Mapping(target = "id", ignore = true)
    Libro toDomain(LibroRequest request);

    // De Dominio a JSON de respuesta
    LibroResponse toResponse(Libro domain);

    // Lista de Dominio a Lista de JSONs
    List<LibroResponse> toResponseList(List<Libro> domainList);
}