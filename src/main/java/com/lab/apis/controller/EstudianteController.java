package com.lab.apis.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lab.apis.model.Estudiante;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EstudianteController {

    private List<Estudiante> estudiantes = new ArrayList<>();

    public EstudianteController() {
        estudiantes.add(new Estudiante(1L, "Carlos", "López", "Ingeniería", 21));
        estudiantes.add(new Estudiante(2L, "María", "Gómez", "Administración", 22));
        estudiantes.add(new Estudiante(3L, "Juan", "Pérez", "Derecho", 20));
        estudiantes.add(new Estudiante(4L, "Ana", "Rodríguez", "Psicología", 23));
        estudiantes.add(new Estudiante(5L, "Luis", "Martínez", "Arquitectura", 24));
    }

    @GetMapping("/api/estudiantes")
    public List<Estudiante> obtenerEstudiantes() {
        return estudiantes;
    }

    @GetMapping("/api/estudiantes/{id}")
    public Estudiante obtenerEstudiantePorId(@PathVariable Long id) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getId().equals(id)) {
                return estudiante;
            }
        }
        return null;
    }

    @PostMapping("/api/estudiantes")
    public Estudiante crearEstudiante(@RequestBody Estudiante estudiante) {
        estudiantes.add(estudiante);
        return estudiante;
    }

    @PutMapping("/api/estudiantes/{id}")
    public Estudiante actualizarEstudiante(
            @PathVariable Long id,
            @RequestBody Estudiante estudianteActualizado) {

        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getId().equals(id)) {
                estudiante.setNombre(estudianteActualizado.getNombre());
                estudiante.setApellido(estudianteActualizado.getApellido());
                estudiante.setCarrera(estudianteActualizado.getCarrera());
                estudiante.setEdad(estudianteActualizado.getEdad());

                return estudiante;
            }
        }

        return null;
    }

    @PatchMapping("/api/estudiantes/{id}")
    public Estudiante actualizarParcialmente(
            @PathVariable Long id,
            @RequestBody Estudiante cambios) {

        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getId().equals(id)) {

                if (cambios.getNombre() != null) {
                    estudiante.setNombre(cambios.getNombre());
                }

                if (cambios.getApellido() != null) {
                    estudiante.setApellido(cambios.getApellido());
                }

                if (cambios.getCarrera() != null) {
                    estudiante.setCarrera(cambios.getCarrera());
                }

                if (cambios.getEdad() != null) {
                    estudiante.setEdad(cambios.getEdad());
                }

                return estudiante;
            }
        }

        return null;
    }

    @DeleteMapping("/api/estudiantes/{id}")
    public String eliminarEstudiante(@PathVariable Long id) {

        boolean eliminado = estudiantes.removeIf(
                estudiante -> estudiante.getId().equals(id)
        );

        if (eliminado) {
            return "Estudiante eliminado correctamente";
        }

        return "Estudiante no encontrado";
    }
}