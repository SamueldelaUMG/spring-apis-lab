package com.lab.apis.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lab.apis.model.Tarea;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TareaController {

    private List<Tarea> tareas = new ArrayList<>();

    public TareaController() {
        tareas.add(new Tarea(1L, "Estudiar Java", "Repasar Spring Boot y APIs REST", "ALTA", false));
        tareas.add(new Tarea(2L, "Hacer ejercicio", "Realizar 30 minutos de ejercicio", "MEDIA", true));
        tareas.add(new Tarea(3L, "Leer libro", "Leer un capítulo del libro actual", "BAJA", false));
        tareas.add(new Tarea(4L, "Entregar laboratorio", "Finalizar y entregar el Laboratorio V", "ALTA", false));
        tareas.add(new Tarea(5L, "Organizar archivos", "Ordenar documentos del proyecto", "MEDIA", false));
    }

    @GetMapping("/api/tareas")
    public List<Tarea> obtenerTareas() {
        return tareas;
    }

    @GetMapping("/api/tareas/{id}")
    public Tarea obtenerTareaPorId(@PathVariable Long id) {
        for (Tarea tarea : tareas) {
            if (tarea.getId().equals(id)) {
                return tarea;
            }
        }
        return null;
    }

    @PostMapping("/api/tareas")
    public Tarea crearTarea(@RequestBody Tarea tarea) {
        tareas.add(tarea);
        return tarea;
    }

    @PutMapping("/api/tareas/{id}")
    public Tarea actualizarTarea(
            @PathVariable Long id,
            @RequestBody Tarea tareaActualizada) {

        for (Tarea tarea : tareas) {
            if (tarea.getId().equals(id)) {

                tarea.setTitulo(tareaActualizada.getTitulo());
                tarea.setDescripcion(tareaActualizada.getDescripcion());
                tarea.setPrioridad(tareaActualizada.getPrioridad());
                tarea.setCompletada(tareaActualizada.getCompletada());

                return tarea;
            }
        }

        return null;
    }

    @PatchMapping("/api/tareas/{id}")
    public Tarea actualizarEstado(
            @PathVariable Long id,
            @RequestBody Tarea cambios) {

        for (Tarea tarea : tareas) {

            if (tarea.getId().equals(id)) {

                Boolean nuevoEstado = cambios.getCompletada();

                if (nuevoEstado != null) {
                    tarea.setCompletada(nuevoEstado);
                }

                return tarea;
            }
        }

        return null;
    }

    @DeleteMapping("/api/tareas/{id}")
    public String eliminarTarea(@PathVariable Long id) {

        boolean eliminado = tareas.removeIf(
                tarea -> tarea.getId().equals(id)
        );

        if (eliminado) {
            return "Tarea eliminada correctamente";
        }

        return "Tarea no encontrada";
    }
}