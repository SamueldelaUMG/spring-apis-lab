package com.lab.apis.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lab.apis.model.Pedido;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PedidoController {

    private List<Pedido> pedidos = new ArrayList<>();

    public PedidoController() {
        pedidos.add(new Pedido(1L, "Carlos López", "Laptop", 1, 4500.0, "PENDIENTE"));
        pedidos.add(new Pedido(2L, "María Gómez", "Mouse", 2, 300.0, "ENVIADO"));
        pedidos.add(new Pedido(3L, "Juan Pérez", "Teclado", 1, 300.0, "ENTREGADO"));
        pedidos.add(new Pedido(4L, "Ana Rodríguez", "Monitor", 1, 1200.0, "PENDIENTE"));
        pedidos.add(new Pedido(5L, "Luis Martínez", "Audífonos", 2, 1000.0, "ENVIADO"));
    }

    @GetMapping("/api/pedidos")
    public List<Pedido> obtenerPedidos() {
        return pedidos;
    }

    @GetMapping("/api/pedidos/{id}")
    public Pedido obtenerPedidoPorId(@PathVariable Long id) {
        for (Pedido pedido : pedidos) {
            if (pedido.getId().equals(id)) {
                return pedido;
            }
        }
        return null;
    }

    @PostMapping("/api/pedidos")
    public Pedido crearPedido(@RequestBody Pedido pedido) {
        pedidos.add(pedido);
        return pedido;
    }

    @PutMapping("/api/pedidos/{id}")
    public Pedido actualizarPedido(
            @PathVariable Long id,
            @RequestBody Pedido pedidoActualizado) {

        for (Pedido pedido : pedidos) {
            if (pedido.getId().equals(id)) {
                pedido.setCliente(pedidoActualizado.getCliente());
                pedido.setProducto(pedidoActualizado.getProducto());
                pedido.setCantidad(pedidoActualizado.getCantidad());
                pedido.setTotal(pedidoActualizado.getTotal());
                pedido.setEstado(pedidoActualizado.getEstado());

                return pedido;
            }
        }

        return null;
    }

    @PatchMapping("/api/pedidos/{id}")
    public Pedido actualizarEstado(
            @PathVariable Long id,
            @RequestBody Pedido cambios) {

        for (Pedido pedido : pedidos) {
            if (pedido.getId().equals(id)) {

                String nuevoEstado = cambios.getEstado();

                if (nuevoEstado != null && !nuevoEstado.isBlank()) {
                    pedido.setEstado(nuevoEstado);
                }

                return pedido;
            }
        }

        return null;
    }

    @DeleteMapping("/api/pedidos/{id}")
    public String eliminarPedido(@PathVariable Long id) {

        boolean eliminado = pedidos.removeIf(
                pedido -> pedido.getId().equals(id));

        if (eliminado) {
            return "Pedido eliminado correctamente";
        }

        return "Pedido no encontrado";
    }
}