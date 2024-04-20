package com.eclub.pruebaTecnica;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.eclub.controllers.ClienteController;
import com.eclub.models.Client;
import com.eclub.services.ClienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ClientTest {

    @Mock
    private ClienteService clientService;

    @Test
    public void testCrearCliente() {

        Client client = new Client();
        client.setId(1L);
        client.setNombre("John");
        client.setApellido("Doe");
        client.setEmail("john.doe@example.com");
        client.setDireccion("123 Main St");


        when(clientService.crearCliente(any(Client.class))).thenReturn(client);


        ClienteController controller = new ClienteController(clientService);
        Client createdClient = controller.crearCliente(client);


        assertEquals(client, createdClient);


        ArgumentCaptor<Client> clientCaptor = ArgumentCaptor.forClass(Client.class);
        verify(clientService, times(1)).crearCliente(clientCaptor.capture());
        assertEquals(client, clientCaptor.getValue());
    }
}
