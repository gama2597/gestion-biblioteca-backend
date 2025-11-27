-- ═══════════════════════════════════════════════════════════════
-- SCRIPT DE CARGA DE DATOS INICIALES - SISTEMA DE BIBLIOTECA
-- Base de datos: H2
-- Descripción: 30 libros de prueba (20 disponibles, 10 prestados)
-- ═══════════════════════════════════════════════════════════════

-- Limpiar datos existentes (opcional, útil para re-ejecuciones)
DELETE FROM libros;

-- Resetear secuencia de IDs para empezar desde 1
ALTER TABLE libros ALTER COLUMN id RESTART WITH 1;

-- ═══════════════════════════════════════════════════════════════
-- CATEGORÍA: NOVELAS CLÁSICAS (Disponibles)
-- ═══════════════════════════════════════════════════════════════

INSERT INTO libros (titulo, autor, isbn, genero, anio_publicacion, disponible) VALUES
('Cien años de soledad', 'Gabriel García Márquez', 'ISBN-978-0-3078-4740-2', 'Novela', 1967, TRUE);

INSERT INTO libros (titulo, autor, isbn, genero, anio_publicacion, disponible) VALUES
('Don Quijote de la Mancha', 'Miguel de Cervantes', 'ISBN-978-8-4206-7366-1', 'Novela', 1605, TRUE);

INSERT INTO libros (titulo, autor, isbn, genero, anio_publicacion, disponible) VALUES
('1984', 'George Orwell', 'ISBN-978-0-4526-2420-7', 'Novela', 1949, TRUE);

INSERT INTO libros (titulo, autor, isbn, genero, anio_publicacion, disponible) VALUES
('El principito', 'Antoine de Saint-Exupéry', 'ISBN-978-0-1562-0217-3', 'Novela', 1943, TRUE);

INSERT INTO libros (titulo, autor, isbn, genero, anio_publicacion, disponible) VALUES
('Orgullo y prejuicio', 'Jane Austen', 'ISBN-978-0-1411-9926-4', 'Novela', 1813, TRUE);

-- ═══════════════════════════════════════════════════════════════
-- CATEGORÍA: CIENCIA FICCIÓN (Algunos prestados)
-- ═══════════════════════════════════════════════════════════════

INSERT INTO libros (titulo, autor, isbn, genero, anio_publicacion, disponible) VALUES
('Fundación', 'Isaac Asimov', 'ISBN-978-0-5532-9335-0', 'Ciencia Ficción', 1951, FALSE);

INSERT INTO libros (titulo, autor, isbn, genero, anio_publicacion, disponible) VALUES
('Dune', 'Frank Herbert', 'ISBN-978-0-4410-1590-0', 'Ciencia Ficción', 1965, TRUE);

INSERT INTO libros (titulo, autor, isbn, genero, anio_publicacion, disponible) VALUES
('Neuromante', 'William Gibson', 'ISBN-978-0-4410-5692-7', 'Ciencia Ficción', 1984, FALSE);

INSERT INTO libros (titulo, autor, isbn, genero, anio_publicacion, disponible) VALUES
('El juego de Ender', 'Orson Scott Card', 'ISBN-978-0-8125-5070-2', 'Ciencia Ficción', 1985, TRUE);

-- ═══════════════════════════════════════════════════════════════
-- CATEGORÍA: FANTASÍA
-- ═══════════════════════════════════════════════════════════════

INSERT INTO libros (titulo, autor, isbn, genero, anio_publicacion, disponible) VALUES
('El Señor de los Anillos', 'J.R.R. Tolkien', 'ISBN-978-0-5453-9681-1', 'Fantasía', 1954, FALSE);

INSERT INTO libros (titulo, autor, isbn, genero, anio_publicacion, disponible) VALUES
('Harry Potter y la piedra filosofal', 'J.K. Rowling', 'ISBN-978-0-4393-3554-5', 'Fantasía', 1997, TRUE);

INSERT INTO libros (titulo, autor, isbn, genero, anio_publicacion, disponible) VALUES
('Crónica del asesino de reyes', 'Patrick Rothfuss', 'ISBN-978-0-7564-0474-1', 'Fantasía', 2011, TRUE);

INSERT INTO libros (titulo, autor, isbn, genero, anio_publicacion, disponible) VALUES
('Las Crónicas de Narnia', 'C.S. Lewis', 'ISBN-978-0-0604-6715-5', 'Fantasía', 1950, TRUE);

-- ═══════════════════════════════════════════════════════════════
-- FIN DEL SCRIPT DE CARGA DE DATOS
-- ═══════════════════════════════════════════════════════════════