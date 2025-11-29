package pe.edu.codigo.biblioteca.infraestructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.edu.codigo.biblioteca.application.port.input.LibroService;
import pe.edu.codigo.biblioteca.application.port.output.LibroRepositoryPort;
import pe.edu.codigo.biblioteca.application.service.LibroServiceImpl;

/**
 * CONFIGURACIÓN DE BEANS (Inyección Manual)
 * Como LibroServiceImpl no tiene @Service (para mantenerlo puro),
 * aquí le decimos a Spring explícitamente cómo crearlo e inyectar sus dependencias.
 */
@Configuration
public class ApplicationConfig {

    @Bean
    public LibroService libroService(LibroRepositoryPort repositoryPort) {
        return new LibroServiceImpl(repositoryPort);
    }
}