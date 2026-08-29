package com.lab.apis.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lab.apis.model.Libro;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LibroController {

    private List<Libro> libros = new ArrayList<>();

    public LibroController() {
        libros.add(new Libro(1L, "Cien años de soledad", "Gabriel García Márquez", "Novela", 150.0));
        libros.add(new Libro(2L, "1984", "George Orwell", "Distopía", 120.0));
        libros.add(new Libro(3L, "El principito", "Antoine de Saint-Exupéry", "Fantasía", 80.0));
        libros.add(new Libro(4L, "Don Quijote de la Mancha", "Miguel de Cervantes", "Novela", 200.0));
        libros.add(new Libro(5L, "Orgullo y prejuicio", "Jane Austen", "Romance", 100.0));
    }

    @GetMapping("/api/libros")
    public List<Libro> obtenerLibros() {
        return libros;
    }

    @GetMapping("/api/libros/{id}")
    public Libro obtenerLibroPorId(@PathVariable Long id) {
        for (Libro libro : libros) {
            if (libro.getId().equals(id)) {
                return libro;
            }
        }
        return null;
    }

    @PostMapping("/api/libros")
    public Libro crearLibro(@RequestBody Libro libro) {
        libros.add(libro);
        return libro;
    }

    @PutMapping("/api/libros/{id}")
    public Libro actualizarLibro(
            @PathVariable Long id,
            @RequestBody Libro libroActualizado) {

        for (Libro libro : libros) {
            if (libro.getId().equals(id)) {
                libro.setTitulo(libroActualizado.getTitulo());
                libro.setAutor(libroActualizado.getAutor());
                libro.setGenero(libroActualizado.getGenero());
                libro.setPrecio(libroActualizado.getPrecio());

                return libro;
            }
        }

        return null;
    }

    @PatchMapping("/api/libros/{id}")
    public Libro actualizarParcialmente(
            @PathVariable Long id,
            @RequestBody Libro cambios) {

        for (Libro libro : libros) {
            if (libro.getId().equals(id)) {

                if (cambios.getTitulo() != null) {
                    libro.setTitulo(cambios.getTitulo());
                }

                if (cambios.getAutor() != null) {
                    libro.setAutor(cambios.getAutor());
                }

                if (cambios.getGenero() != null) {
                    libro.setGenero(cambios.getGenero());
                }

                if (cambios.getPrecio() != null) {
                    libro.setPrecio(cambios.getPrecio());
                }

                return libro;
            }
        }

        return null;
    }

    @DeleteMapping("/api/libros/{id}")
    public String eliminarLibro(@PathVariable Long id) {

        boolean eliminado = libros.removeIf(
                libro -> libro.getId().equals(id)
        );

        if (eliminado) {
            return "Libro eliminado correctamente";
        }

        return "Libro no encontrado";
    }
}
