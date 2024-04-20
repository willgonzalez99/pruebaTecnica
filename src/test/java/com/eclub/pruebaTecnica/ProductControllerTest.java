package com.eclub.pruebaTecnica;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.eclub.controllers.ProductController;
import com.eclub.models.CompraProducto;
import com.eclub.models.Product;
import com.eclub.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Test
    public void testCreateProduct() {

        Product product = new Product();
        product.setId(1L);
        product.setName("Producto eclub");
        product.setPrice(10.0);
        product.setStock(100);

        when(productService.crearProducto(any(Product.class))).thenReturn(product);

        ProductController controller = new ProductController(productService);
        Product createdProduct = controller.createProduct(product);

        assertEquals(product, createdProduct);

        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productService, times(1)).crearProducto(productCaptor.capture());
        assertEquals(product, productCaptor.getValue());
    }


    @Test
    public void testComprarProducto() {

        CompraProducto compraProducto = new CompraProducto();
        compraProducto.setIdProducto(1L);
        compraProducto.setCantidad(10);

        Product product = new Product();
        product.setId(1L);
        product.setName("Producto de ejemplo");
        product.setPrice(10.0);
        product.setStock(100);

        when(productService.comprarProducto(any(CompraProducto.class))).thenAnswer(invocation -> {
            CompraProducto cp = invocation.getArgument(0);
            product.setStock(product.getStock() + cp.getCantidad());
            return product;
        });

        ProductController controller = new ProductController(productService);
        Product purchasedProduct = controller.comprarProducto(compraProducto);

        assertEquals(110, purchasedProduct.getStock());

        ArgumentCaptor<CompraProducto> compraProductoCaptor = ArgumentCaptor.forClass(CompraProducto.class);
        verify(productService, times(1)).comprarProducto(compraProductoCaptor.capture());
        assertEquals(compraProducto, compraProductoCaptor.getValue());
    }

}
