package com.lab.apis.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lab.apis.model.Pelicula;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PeliculaController {

    private List<Pelicula> peliculas = new ArrayList<>();

    public PeliculaController() {
        peliculas.add(new Pelicula(1L, "Interestelar", "Christopher Nolan", "Ciencia ficción", 2014));
        peliculas.add(new Pelicula(2L, "El Padrino", "Francis Ford Coppola", "Drama", 1972));
        peliculas.add(new Pelicula(3L, "Matrix", "Lana y Lilly Wachowski", "Ciencia ficción", 1999));
        peliculas.add(new Pelicula(4L, "Titanic", "James Cameron", "Romance", 1997));
        peliculas.add(new Pelicula(5L, "Jurassic Park", "Steven Spielberg", "Aventura", 1993));
    }

    @GetMapping("/api/peliculas")
    public List<Pelicula> obtenerPeliculas() {
        return peliculas;
    }

    @GetMapping("/api/peliculas/{id}")
    public Pelicula obtenerPeliculaPorId(@PathVariable Long id) {
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getId().equals(id)) {
                return pelicula;
            }
        }
        return null;
    }

    @PostMapping("/api/peliculas")
    public Pelicula crearPelicula(@RequestBody Pelicula pelicula) {
        peliculas.add(pelicula);
        return pelicula;
    }

    @PutMapping("/api/peliculas/{id}")
    public Pelicula actualizarPelicula(
            @PathVariable Long id,
            @RequestBody Pelicula peliculaActualizada) {

        for (Pelicula pelicula : peliculas) {
            if (pelicula.getId().equals(id)) {
                pelicula.setTitulo(peliculaActualizada.getTitulo());
                pelicula.setDirector(peliculaActualizada.getDirector());
                pelicula.setGenero(peliculaActualizada.getGenero());
                pelicula.setAnio(peliculaActualizada.getAnio());

                return pelicula;
            }
        }

        return null;
    }

    @PatchMapping("/api/peliculas/{id}")
    public Pelicula actualizarParcialmente(
            @PathVariable Long id,
            @RequestBody Pelicula cambios) {

        for (Pelicula pelicula : peliculas) {
            if (pelicula.getId().equals(id)) {

                if (cambios.getTitulo() != null) {
                    pelicula.setTitulo(cambios.getTitulo());
                }

                if (cambios.getDirector() != null) {
                    pelicula.setDirector(cambios.getDirector());
                }

                if (cambios.getGenero() != null) {
                    pelicula.setGenero(cambios.getGenero());
                }

                if (cambios.getAnio() != null) {
                    pelicula.setAnio(cambios.getAnio());
                }

                return pelicula;
            }
        }

        return null;
    }

    @DeleteMapping("/api/peliculas/{id}")
    public String eliminarPelicula(@PathVariable Long id) {

        boolean eliminado = peliculas.removeIf(
                pelicula -> pelicula.getId().equals(id)
        );

        if (eliminado) {
            return "Película eliminada correctamente";
        }

        return "Película no encontrada";
    }
}