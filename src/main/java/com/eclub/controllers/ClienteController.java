package com.eclub.controllers;

import com.eclub.models.Client;
import com.eclub.services.ClienteService;
import com.eclub.services.ClienteServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
@Slf4j
public class ClienteController {




    private ClienteService clienteService;

    @PostMapping("/crear")
    public Client crearCliente(@RequestBody Client cliente)  {

        return clienteService.crearCliente(cliente);
    }


}
