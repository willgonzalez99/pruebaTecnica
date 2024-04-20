package com.eclub.controllers;

import com.eclub.exceptions.ResourceNotFoundException;
import com.eclub.models.Sales;
import com.eclub.repositories.VentasRepository;
import com.eclub.services.SalesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/sales")
@AllArgsConstructor
@Slf4j
public class VentasControler {



    private SalesService salesService;

    @PostMapping
    public Sales createVentas(@RequestBody Sales sales) {
        return salesService.crearVentas(sales);
    }
    @GetMapping
    public List<Sales> getAllVentas() {

        return salesService.getVentas();
    }

    @GetMapping("/{id}")
    public Optional<Sales> getVentasById(@PathVariable Long id) {

        return salesService.getVentasById(id);
    }


    @PutMapping("/{id}")
    public Sales updateVentas(@PathVariable Long id, @RequestBody Sales ventasDetails) {
        return salesService.updateVentas(id, ventasDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteVentas(@PathVariable Long id) {
        salesService.deleteVentas(id);
    }


    @PostMapping("/vender")
    public Sales venderProducto(@RequestBody Sales venta) {
        return salesService.venderProducto(venta);
    }
}