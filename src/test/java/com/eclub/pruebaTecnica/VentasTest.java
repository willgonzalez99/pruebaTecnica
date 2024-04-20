package com.eclub.pruebaTecnica;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.eclub.controllers.VentasControler;
import com.eclub.models.Client;
import com.eclub.models.Product;
import com.eclub.models.Sales;
import com.eclub.services.SalesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class VentasTest {

    @Mock
    private SalesService salesService;

    @Test
    public void testVenderProducto() {

        Sales venta = new Sales();
        venta.setId(1L);
        venta.setQuantity(5);

        Client client = new Client();
        client.setId(1L);
        client.setNombre("John");
        client.setApellido("Doe");
        client.setEmail("john.doe@example.com");
        client.setDireccion("123 Main St");

        venta.setClient(client);

        Product product = new Product();
        product.setId(1L);
        product.setName("Producto de ejemplo");
        product.setPrice(10.0);
        product.setStock(100);

        venta.setProduct(product);

        when(salesService.venderProducto(any(Sales.class))).thenReturn(venta);


        VentasControler controller = new VentasControler(salesService);
        Sales soldProduct = controller.venderProducto(venta);


        assertEquals(venta, soldProduct);


        ArgumentCaptor<Sales> ventaCaptor = ArgumentCaptor.forClass(Sales.class);
        verify(salesService, times(1)).venderProducto(ventaCaptor.capture());
        assertEquals(venta, ventaCaptor.getValue());
    }
}
