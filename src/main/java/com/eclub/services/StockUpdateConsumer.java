package com.eclub.services;

import com.eclub.models.CompraProducto;
import com.eclub.models.Product;
import com.eclub.models.Sales;
import com.eclub.models.VentaProducto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class StockUpdateConsumer {

    @Autowired
    private ProductService productService;

    @Async
    @RabbitListener(queues = "cola_compras")
    public CompletableFuture<Void> handleCompra(CompraProducto compraProducto) {

        Product product = productService.obtenerProductoPorId(compraProducto.getIdProducto());
        if (product != null) {

            product.setStock(product.getStock() + compraProducto.getCantidad());
            productService.actualizarProducto(product.getId(), product);
        }
        return CompletableFuture.completedFuture(null);
    }

    @Async
    @RabbitListener(queues = "cola_ventas")
    public CompletableFuture<Void> handleVenta(VentaProducto ventaProducto) {

        Product product = productService.obtenerProductoPorId(ventaProducto.getIdProducto());
        if (product != null && product.getStock() >= ventaProducto.getCantidad()) {

            product.setStock(product.getStock() - ventaProducto.getCantidad());
            productService.actualizarProducto(product.getId(), product);
        }
        return CompletableFuture.completedFuture(null);
    }
}
