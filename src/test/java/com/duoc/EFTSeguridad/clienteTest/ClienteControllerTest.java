package com.duoc.EFTSeguridad.clienteTest;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.duoc.EFTSeguridad.cliente.Cliente;
import com.duoc.EFTSeguridad.cliente.ClienteController;
import com.duoc.EFTSeguridad.cliente.ClienteDTO;
import com.duoc.EFTSeguridad.cliente.ClienteService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    public ClienteControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListarClientes() {

        // Arrange
        Cliente cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setRut("11111111-1");
        cliente1.setNombre("Juan");
        cliente1.setApellido("Perez");
        cliente1.setTelefono("999999999");
        cliente1.setEmail("juan@gmail.com");

        Cliente cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setRut("22222222-2");
        cliente2.setNombre("Maria");
        cliente2.setApellido("Gonzalez");
        cliente2.setTelefono("888888888");
        cliente2.setEmail("maria@gmail.com");

        List<Cliente> clientes =
                Arrays.asList(cliente1, cliente2);

        when(clienteService.obtenerTodos())
                .thenReturn(clientes);

        // Act
        ResponseEntity<List<Cliente>> response =
                clienteController.listar();

        // Assert
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("Juan", response.getBody().get(0).getNombre());
        assertEquals("Maria", response.getBody().get(1).getNombre());

        verify(clienteService, times(1)).obtenerTodos();
    }

    @Test
    void testObtenerClientePorId() {

        // Arrange
        Cliente cliente = new Cliente();

        cliente.setId(1L);
        cliente.setRut("11111111-1");
        cliente.setNombre("Juan");
        cliente.setApellido("Perez");
        cliente.setTelefono("999999999");
        cliente.setEmail("juan@gmail.com");

        when(clienteService.obtenerPorId(1L))
                .thenReturn(Optional.of(cliente));

        // Act
        ResponseEntity<Cliente> response =
                clienteController.obtenerPorId(1L);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("Juan", response.getBody().getNombre());
        assertEquals("Perez", response.getBody().getApellido());

        verify(clienteService, times(1))
                .obtenerPorId(1L);
    }

    @Test
    void testObtenerClientePorIdNoExiste() {

        // Arrange
        when(clienteService.obtenerPorId(99L))
                .thenReturn(Optional.empty());

        // Act
        ResponseEntity<Cliente> response =
                clienteController.obtenerPorId(99L);

        // Assert
        assertEquals(404, response.getStatusCode().value());

        verify(clienteService, times(1))
                .obtenerPorId(99L);
    }

    @Test
    void testCrearCliente() {

        // Arrange
        ClienteDTO dto = new ClienteDTO();

        dto.setRut("11111111-1");
        dto.setNombre("Juan");
        dto.setApellido("Perez");
        dto.setTelefono("999999999");
        dto.setEmail("juan@gmail.com");

        Cliente clienteGuardado = new Cliente();

        clienteGuardado.setId(1L);
        clienteGuardado.setRut("11111111-1");
        clienteGuardado.setNombre("Juan");
        clienteGuardado.setApellido("Perez");
        clienteGuardado.setTelefono("999999999");
        clienteGuardado.setEmail("juan@gmail.com");

        when(clienteService.guardar(any(Cliente.class)))
                .thenReturn(clienteGuardado);

        // Act
        ResponseEntity<Cliente> response =
                clienteController.crear(dto);

        // Assert
        assertEquals(201, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("Juan", response.getBody().getNombre());

        verify(clienteService, times(1))
                .guardar(any(Cliente.class));
    }

    @Test
    void testEliminarCliente() {

        // Arrange
        Long clienteId = 1L;

        Cliente cliente = new Cliente();
        cliente.setId(clienteId);
        cliente.setNombre("Juan");

        when(clienteService.obtenerPorId(clienteId))
                .thenReturn(Optional.of(cliente));

        // Act
        ResponseEntity<Void> response =
                clienteController.eliminar(clienteId);

        // Assert
        assertEquals(204, response.getStatusCode().value());

        verify(clienteService, times(1))
                .obtenerPorId(clienteId);

        verify(clienteService, times(1))
                .eliminarPorId(clienteId);
    }
}