package com.eclub.controllers;

import com.eclub.exceptions.ResourceNotFoundException;
import com.eclub.models.CompraProducto;
import com.eclub.models.Product;
import com.eclub.repositories.ProductRepository;
import com.eclub.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
@Slf4j
public class ProductController {



    private ProductService productService;



    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.obtenerProductoPorId(id);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {

        return productService.crearProducto(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        return productService.actualizarProducto(id,productDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.eliminarProducto(id);
    }

    @PutMapping("/compra")
    public Product comprarProducto(@RequestBody CompraProducto compraProducto){
        return productService.comprarProducto(compraProducto);

    }

}
