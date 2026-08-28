package com.lab.apis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;

import org.springframework.web.bind.annotation.RestController;
import com.lab.apis.model.Producto;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductoController {

    private List<Producto> productos = new ArrayList<>();

    public ProductoController() {
        productos.add(new Producto(1L, "Laptop", 4500.0, "Tecnología"));
        productos.add(new Producto(2L, "Mouse", 150.0, "Accesorios"));
        productos.add(new Producto(3L, "Teclado", 300.0, "Accesorios"));
        productos.add(new Producto(4L, "Monitor", 1200.0, "Tecnología"));
        productos.add(new Producto(5L, "Audífonos", 500.0, "Audio"));
    }

    @GetMapping("/api/productos")
    public List<Producto> obtenerProductos() {
        return productos;
    }

    @PostMapping("/api/productos")
    public Producto crearProducto(@RequestBody Producto producto) {
        productos.add(producto);
        return producto;

    }

    @PutMapping("/api/productos/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto productoActualizado) {
        for (Producto producto : productos) {
            if (producto.getId().equals(id)) {
                producto.setNombre(productoActualizado.getNombre());
                producto.setPrecio(productoActualizado.getPrecio());
                producto.setCategoria(productoActualizado.getCategoria());
                return producto;
            }
        }
        return null;
    }

    // aqui
    @PatchMapping("/api/productos/{id}")
    public Producto actualizarParcialmente(@PathVariable Long id, @RequestBody Producto cambios) {
        for (Producto producto : productos) {
            if (producto.getId().equals(id)) {

                if (cambios.getNombre() != null) {
                    producto.setNombre(cambios.getNombre());
                }

                if (cambios.getPrecio() != null) {
                    producto.setPrecio(cambios.getPrecio());
                }

                if (cambios.getCategoria() != null) {
                    producto.setCategoria(cambios.getCategoria());
                }

                return producto;
            }
        }

        return null;
    }

    @DeleteMapping("/api/productos/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        boolean eliminado = productos.removeIf(producto -> producto.getId().equals(id));

        if (eliminado) {
            return "Producto eliminado correctamente";
        }

        return "Producto no encontrado";
    }

    @GetMapping("/api/productos/{id}")
    public Producto obtenerProductoPorId(@PathVariable Long id) {
        for (Producto producto : productos) {
            if (producto.getId().equals(id)) {
                return producto;
            }
        }
        return null;
    }

}
