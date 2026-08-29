package com.lab.apis.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lab.apis.model.Cliente;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ClienteController {

    private List<Cliente> clientes = new ArrayList<>();

    public ClienteController() {
        clientes.add(new Cliente(1L, "Carlos", "López", "carlos@gmail.com", "5555-1001"));
        clientes.add(new Cliente(2L, "María", "Gómez", "maria@gmail.com", "5555-1002"));
        clientes.add(new Cliente(3L, "Juan", "Pérez", "juan@gmail.com", "5555-1003"));
        clientes.add(new Cliente(4L, "Ana", "Rodríguez", "ana@gmail.com", "5555-1004"));
        clientes.add(new Cliente(5L, "Luis", "Martínez", "luis@gmail.com", "5555-1005"));
    }

    @GetMapping("/api/clientes")
    public List<Cliente> obtenerClientes() {
        return clientes;
    }

    @GetMapping("/api/clientes/{id}")
    public Cliente obtenerClientePorId(@PathVariable Long id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(id)) {
                return cliente;
            }
        }
        return null;
    }

    @PostMapping("/api/clientes")
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        clientes.add(cliente);
        return cliente;
    }

    @PutMapping("/api/clientes/{id}")
    public Cliente actualizarCliente(
            @PathVariable Long id,
            @RequestBody Cliente clienteActualizado) {

        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(id)) {

                cliente.setNombre(clienteActualizado.getNombre());
                cliente.setApellido(clienteActualizado.getApellido());
                cliente.setCorreo(clienteActualizado.getCorreo());
                cliente.setTelefono(clienteActualizado.getTelefono());

                return cliente;
            }
        }

        return null;
    }

    @PatchMapping("/api/clientes/{id}")
    public Cliente actualizarParcialmente(
            @PathVariable Long id,
            @RequestBody Cliente cambios) {

        for (Cliente cliente : clientes) {

            if (cliente.getId().equals(id)) {

                if (cambios.getNombre() != null) {
                    cliente.setNombre(cambios.getNombre());
                }

                if (cambios.getApellido() != null) {
                    cliente.setApellido(cambios.getApellido());
                }

                if (cambios.getCorreo() != null) {
                    cliente.setCorreo(cambios.getCorreo());
                }

                if (cambios.getTelefono() != null) {
                    cliente.setTelefono(cambios.getTelefono());
                }

                return cliente;
            }
        }

        return null;
    }

    @DeleteMapping("/api/clientes/{id}")
    public String eliminarCliente(@PathVariable Long id) {

        boolean eliminado = clientes.removeIf(
                cliente -> cliente.getId().equals(id)
        );

        if (eliminado) {
            return "Cliente eliminado correctamente";
        }

        return "Cliente no encontrado";
    }
}