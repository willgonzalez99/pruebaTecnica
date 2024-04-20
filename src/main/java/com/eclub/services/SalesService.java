package com.eclub.services;

import com.eclub.models.Sales;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface SalesService {
    Sales crearVentas(Sales sales);


    List<Sales> getVentas();

    Optional<Sales> getVentasById(Long id);

    Sales updateVentas(Long id, Sales ventasDetails);

    void deleteVentas(Long id);

    Sales venderProducto(Sales venta);
}
