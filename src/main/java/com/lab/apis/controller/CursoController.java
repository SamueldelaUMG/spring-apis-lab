package com.lab.apis.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lab.apis.model.Curso;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CursoController {

    private List<Curso> cursos = new ArrayList<>();

    public CursoController() {
        cursos.add(new Curso(1L, "Programación II", "Desarrollo de aplicaciones", 4, "Presencial"));
        cursos.add(new Curso(2L, "Bases de Datos", "Diseño y administración de bases de datos", 4, "Presencial"));
        cursos.add(new Curso(3L, "Matemática IV", "Cálculo y aplicaciones matemáticas", 5, "Presencial"));
        cursos.add(new Curso(4L, "Ingeniería de Software", "Principios de desarrollo de software", 3, "Virtual"));
        cursos.add(new Curso(5L, "Redes de Computadoras", "Fundamentos de redes y comunicaciones", 4, "Híbrida"));
    }

    @GetMapping("/api/cursos")
    public List<Curso> obtenerCursos() {
        return cursos;
    }

    @GetMapping("/api/cursos/{id}")
    public Curso obtenerCursoPorId(@PathVariable Long id) {
        for (Curso curso : cursos) {
            if (curso.getId().equals(id)) {
                return curso;
            }
        }
        return null;
    }

    @PostMapping("/api/cursos")
    public Curso crearCurso(@RequestBody Curso curso) {
        cursos.add(curso);
        return curso;
    }

    @PutMapping("/api/cursos/{id}")
    public Curso actualizarCurso(
            @PathVariable Long id,
            @RequestBody Curso cursoActualizado) {

        for (Curso curso : cursos) {
            if (curso.getId().equals(id)) {
                curso.setNombre(cursoActualizado.getNombre());
                curso.setDescripcion(cursoActualizado.getDescripcion());
                curso.setCreditos(cursoActualizado.getCreditos());
                curso.setModalidad(cursoActualizado.getModalidad());

                return curso;
            }
        }

        return null;
    }

    @PatchMapping("/api/cursos/{id}")
    public Curso actualizarParcialmente(
            @PathVariable Long id,
            @RequestBody Curso cambios) {

        for (Curso curso : cursos) {
            if (curso.getId().equals(id)) {

                if (cambios.getNombre() != null) {
                    curso.setNombre(cambios.getNombre());
                }

                if (cambios.getDescripcion() != null) {
                    curso.setDescripcion(cambios.getDescripcion());
                }

                if (cambios.getCreditos() != null) {
                    curso.setCreditos(cambios.getCreditos());
                }

                if (cambios.getModalidad() != null) {
                    curso.setModalidad(cambios.getModalidad());
                }

                return curso;
            }
        }

        return null;
    }

    @DeleteMapping("/api/cursos/{id}")
    public String eliminarCurso(@PathVariable Long id) {

        boolean eliminado = cursos.removeIf(
                curso -> curso.getId().equals(id)
        );

        if (eliminado) {
            return "Curso eliminado correctamente";
        }

        return "Curso no encontrado";
    }
}