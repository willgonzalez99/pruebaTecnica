package com.eclub.services;

import com.eclub.models.Client;
import com.eclub.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService{
    @Autowired
    ClientRepository clientRepository;
    @Override
    public Client crearCliente(Client client) {
        return clientRepository.save(client);
    }
}
