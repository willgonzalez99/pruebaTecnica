package com.eclub.services;


import com.eclub.exceptions.ResourceNotFoundException;
import com.eclub.models.Product;
import com.eclub.models.Sales;
import com.eclub.models.VentaProducto;
import com.eclub.repositories.ClientRepository;
import com.eclub.repositories.VentasRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalesServiceImpl implements SalesService{
    @Autowired
    private VentasRepository ventasRepository;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    ProductService productService;

    @Autowired
    ClientRepository clientRepository;


    @Override
    public Sales crearVentas(Sales sales) {

        return ventasRepository.save(sales);
    }


    @Override
    public List<Sales> getVentas() {

        return ventasRepository.findAll();
    }

    @Override
    public Optional<Sales> getVentasById(Long id) {

        return ventasRepository.findById(id);
    }


    @Override
    public Sales updateVentas(Long id, Sales ventasDetails) {
        Sales sales = ventasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sales not found with id " + id));

        sales.setProduct(ventasDetails.getProducto());
        sales.setQuantity(ventasDetails.getQuantity());

        return ventasRepository.save(sales);
    }

    @Override
    public void deleteVentas(Long id) {
        Sales sales = ventasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sales not found with id " + id));

        ventasRepository.delete(sales);
    }


    @Override
    public Sales venderProducto(Sales venta) {

        Product producto = venta.getProducto();

        if (producto.getStock() < venta.getQuantity()) {
            throw new RuntimeException("No hay suficiente stock disponible para la venta");
        }
        Sales nuevaVenta= new Sales();
        try {
             nuevaVenta = ventasRepository.save(venta);
        } catch (Exception e) {
            Throwable causa = e.getCause();
            causa.printStackTrace();
        }
        producto.setStock(producto.getStock() - venta.getQuantity());
        nuevaVenta.setClient(clientRepository.getReferenceById(venta.getClient().getId()));
        nuevaVenta.setProduct(producto);
        VentaProducto ventaProducto = new VentaProducto();
        ventaProducto.setIdProducto(producto.getId());
        ventaProducto.setCantidad(venta.getQuantity());

        rabbitTemplate.convertAndSend("cola_ventas",ventaProducto);

        return nuevaVenta;
    }

}
