-- ═══════════════════════════════════════════════════════════════
-- SCRIPT DE CREACIÓN DE TABLAS - SISTEMA DE BIBLIOTECA
-- Base de datos: H2
-- Descripción: Catálogo de libros con gestión de préstamos
-- ═══════════════════════════════════════════════════════════════

-- Eliminar tabla si existe (para recrear limpiamente)
DROP TABLE IF EXISTS libros;

-- ═══════════════════════════════════════════════════════════════
-- TABLA: libros
-- Descripción: Almacena el catálogo de libros de la biblioteca
-- ═══════════════════════════════════════════════════════════════

CREATE TABLE libros (
    -- Identificador único (clave primaria)
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    -- Información básica del libro
    titulo VARCHAR(200) NOT NULL,
    autor VARCHAR(100) NOT NULL,
    isbn VARCHAR(30) NOT NULL UNIQUE,
    genero VARCHAR(50) NOT NULL,
    anio_publicacion INTEGER NOT NULL,

    -- Estado de disponibilidad
    disponible BOOLEAN NOT NULL DEFAULT TRUE,

    -- Constraint: año de publicación válido
    CONSTRAINT chk_anio_publicacion CHECK (anio_publicacion >= 1000 AND anio_publicacion <= 2025),

    -- Constraint: ISBN no vacío
    CONSTRAINT chk_isbn_formato CHECK (isbn LIKE 'ISBN-%')
);

-- ═══════════════════════════════════════════════════════════════
-- ÍNDICES PARA MEJORAR RENDIMIENTO
-- ═══════════════════════════════════════════════════════════════

-- Índice para búsquedas por disponibilidad (préstamos/devoluciones)
CREATE INDEX idx_libros_disponible ON libros(disponible);

-- Índice para búsquedas por género
CREATE INDEX idx_libros_genero ON libros(genero);

-- Índice para búsquedas por autor
CREATE INDEX idx_libros_autor ON libros(autor);

-- Índice para búsquedas por año
CREATE INDEX idx_libros_anio ON libros(anio_publicacion);

-- ═══════════════════════════════════════════════════════════════
-- COMENTARIOS DE DOCUMENTACIÓN
-- ═══════════════════════════════════════════════════════════════

COMMENT ON TABLE libros IS 'Catálogo de libros de la biblioteca';

COMMENT ON COLUMN libros.id IS 'Identificador único del libro';
COMMENT ON COLUMN libros.titulo IS 'Título completo del libro';
COMMENT ON COLUMN libros.autor IS 'Nombre del autor o autores';
COMMENT ON COLUMN libros.isbn IS 'Código ISBN único internacional (formato: ISBN-XXX-X-XXXX-XXXX-X)';
COMMENT ON COLUMN libros.genero IS 'Género literario (novela, ensayo, poesía, etc.)';
COMMENT ON COLUMN libros.anio_publicacion IS 'Año de publicación del libro';
COMMENT ON COLUMN libros.disponible IS 'Indica si el libro está disponible para préstamo (true) o prestado (false)';

-- ═══════════════════════════════════════════════════════════════
-- FIN DEL SCRIPT DE CREACIÓN
-- ═══════════════════════════════════════════════════════════════