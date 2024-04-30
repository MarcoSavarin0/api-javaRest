package com.apirest.apirest.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.apirest.apirest.Repositories.ProductoRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.apirest.apirest.Entities.Producto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    @PostMapping
    public Producto createProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    @GetMapping("/{id}")
    public Producto obteneProductoPorId(@PathVariable Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el id " + id));
    }

    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto detallesProducto) {
      Producto productoEncontrado = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el id " + id));
    
        productoEncontrado.setNombre(detallesProducto.getNombre());
        productoEncontrado.setPrecio(detallesProducto.getPrecio());

        return productoRepository.save(productoEncontrado);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        Producto productoEncontrado = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el id " + id));
        productoRepository.delete(productoEncontrado);
        return "EL PRODUCTO SE ELIMINO ID: " + id;
    }

}
