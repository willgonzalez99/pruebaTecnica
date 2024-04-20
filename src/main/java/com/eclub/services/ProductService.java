package com.eclub.services;

import com.eclub.models.CompraProducto;
import com.eclub.models.Product;
import com.eclub.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface ProductService {


    Product obtenerProductoPorId(Long id);

    Product crearProducto(Product product);

    Product actualizarProducto(Long id, Product productDetails);

    void actualizarStock(Long id, int newStock);

    void eliminarProducto(Long id);

    Product comprarProducto(CompraProducto compraProducto);
}
