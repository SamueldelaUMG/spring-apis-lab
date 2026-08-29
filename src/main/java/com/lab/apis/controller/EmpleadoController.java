package com.lab.apis.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lab.apis.model.Empleado;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmpleadoController {

    private List<Empleado> empleados = new ArrayList<>();

    public EmpleadoController() {
        empleados.add(new Empleado(1L, "Carlos López", "Gerente", 8500.0, "Administración"));
        empleados.add(new Empleado(2L, "María Gómez", "Contadora", 6000.0, "Finanzas"));
        empleados.add(new Empleado(3L, "Juan Pérez", "Programador", 7000.0, "Tecnología"));
        empleados.add(new Empleado(4L, "Ana Rodríguez", "Diseñadora", 5500.0, "Marketing"));
        empleados.add(new Empleado(5L, "Luis Martínez", "Supervisor", 6500.0, "Operaciones"));
    }

    @GetMapping("/api/empleados")
    public List<Empleado> obtenerEmpleados() {
        return empleados;
    }

    @GetMapping("/api/empleados/{id}")
    public Empleado obtenerEmpleadoPorId(@PathVariable Long id) {
        for (Empleado empleado : empleados) {
            if (empleado.getId().equals(id)) {
                return empleado;
            }
        }
        return null;
    }

    @PostMapping("/api/empleados")
    public Empleado crearEmpleado(@RequestBody Empleado empleado) {
        empleados.add(empleado);
        return empleado;
    }

    @PutMapping("/api/empleados/{id}")
    public Empleado actualizarEmpleado(
            @PathVariable Long id,
            @RequestBody Empleado empleadoActualizado) {

        for (Empleado empleado : empleados) {
            if (empleado.getId().equals(id)) {

                empleado.setNombre(empleadoActualizado.getNombre());
                empleado.setPuesto(empleadoActualizado.getPuesto());
                empleado.setSalario(empleadoActualizado.getSalario());
                empleado.setDepartamento(empleadoActualizado.getDepartamento());

                return empleado;
            }
        }

        return null;
    }

    @PatchMapping("/api/empleados/{id}")
    public Empleado actualizarParcialmente(
            @PathVariable Long id,
            @RequestBody Empleado cambios) {

        for (Empleado empleado : empleados) {
            if (empleado.getId().equals(id)) {

                if (cambios.getNombre() != null) {
                    empleado.setNombre(cambios.getNombre());
                }

                if (cambios.getPuesto() != null) {
                    empleado.setPuesto(cambios.getPuesto());
                }

                if (cambios.getSalario() != null) {
                    empleado.setSalario(cambios.getSalario());
                }

                if (cambios.getDepartamento() != null) {
                    empleado.setDepartamento(cambios.getDepartamento());
                }

                return empleado;
            }
        }

        return null;
    }

    @DeleteMapping("/api/empleados/{id}")
    public String eliminarEmpleado(@PathVariable Long id) {

        boolean eliminado = empleados.removeIf(
                empleado -> empleado.getId().equals(id)
        );

        if (eliminado) {
            return "Empleado eliminado correctamente";
        }

        return "Empleado no encontrado";
    }
}