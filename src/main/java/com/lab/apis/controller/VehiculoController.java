package com.lab.apis.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lab.apis.model.Vehiculo;

import java.util.ArrayList;
import java.util.List;

@RestController
public class VehiculoController {

    private List<Vehiculo> vehiculos = new ArrayList<>();

    public VehiculoController() {
        vehiculos.add(new Vehiculo(1L, "Toyota", "Corolla", 2024, 22000.0));
        vehiculos.add(new Vehiculo(2L, "Honda", "Civic", 2024, 25000.0));
        vehiculos.add(new Vehiculo(3L, "Ford", "Mustang", 2024, 31000.0));
        vehiculos.add(new Vehiculo(4L, "Mazda", "CX-5", 2024, 29000.0));
        vehiculos.add(new Vehiculo(5L, "Volkswagen", "Jetta", 2024, 23000.0));
    }

    @GetMapping("/api/vehiculos")
    public List<Vehiculo> obtenerVehiculos() {
        return vehiculos;
    }

    @GetMapping("/api/vehiculos/{id}")
    public Vehiculo obtenerVehiculoPorId(@PathVariable Long id) {
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getId().equals(id)) {
                return vehiculo;
            }
        }
        return null;
    }

    @PostMapping("/api/vehiculos")
    public Vehiculo crearVehiculo(@RequestBody Vehiculo vehiculo) {
        vehiculos.add(vehiculo);
        return vehiculo;
    }

    @PutMapping("/api/vehiculos/{id}")
    public Vehiculo actualizarVehiculo(
            @PathVariable Long id,
            @RequestBody Vehiculo vehiculoActualizado) {

        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getId().equals(id)) {
                vehiculo.setMarca(vehiculoActualizado.getMarca());
                vehiculo.setModelo(vehiculoActualizado.getModelo());
                vehiculo.setAnio(vehiculoActualizado.getAnio());
                vehiculo.setPrecio(vehiculoActualizado.getPrecio());

                return vehiculo;
            }
        }

        return null;
    }

    @PatchMapping("/api/vehiculos/{id}")
    public Vehiculo actualizarParcialmente(
            @PathVariable Long id,
            @RequestBody Vehiculo cambios) {

        for (Vehiculo vehiculo : vehiculos) {

            if (vehiculo.getId().equals(id)) {

                if (cambios.getMarca() != null) {
                    vehiculo.setMarca(cambios.getMarca());
                }

                if (cambios.getModelo() != null) {
                    vehiculo.setModelo(cambios.getModelo());
                }

                if (cambios.getAnio() != null) {
                    vehiculo.setAnio(cambios.getAnio());
                }

                if (cambios.getPrecio() != null) {
                    vehiculo.setPrecio(cambios.getPrecio());
                }

                return vehiculo;
            }
        }

        return null;
    }

    @DeleteMapping("/api/vehiculos/{id}")
    public String eliminarVehiculo(@PathVariable Long id) {

        boolean eliminado = vehiculos.removeIf(
                vehiculo -> vehiculo.getId().equals(id)
        );

        if (eliminado) {
            return "Vehículo eliminado correctamente";
        }

        return "Vehículo no encontrado";
    }
}