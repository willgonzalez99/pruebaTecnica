package com.eclub.services;

import com.eclub.exceptions.ResourceNotFoundException;
import com.eclub.models.CompraProducto;
import com.eclub.models.Product;
import com.eclub.repositories.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private AmqpTemplate rabbitTemplate;
    @Override
    public Product obtenerProductoPorId(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
    }

    @Override
    public Product crearProducto(Product product){
        return productRepository.save(product);
    }

    @Override
    public Product actualizarProducto(Long id, Product productDetails){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));

        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        product.setStock(productDetails.getStock());

        return productRepository.saveAndFlush(product);
    }


    @Override
    public void actualizarStock(Long id, int newStock){
         productRepository.updateProductStock(id, newStock);
    }

    @Override
    public void eliminarProducto(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));

        productRepository.delete(product);
    }

    @Override
    public Product comprarProducto(CompraProducto compraProducto){
        Product product = productRepository.findById(compraProducto.getIdProducto())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + compraProducto));
        product.setStock(product.getStock()+compraProducto.getCantidad());

        rabbitTemplate.convertAndSend("cola_compras", compraProducto);

        return product;

    }

}
