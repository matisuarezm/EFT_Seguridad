package com.duoc.EFTSeguridad.clienteTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.duoc.EFTSeguridad.cliente.Cliente;
import com.duoc.EFTSeguridad.cliente.ClienteRepository;
import com.duoc.EFTSeguridad.cliente.ClienteService;

class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void obtenerTodos_debeRetornarListaDeClientes() {

        Cliente cliente1 = new Cliente(
                1L,
                "11111111-1",
                "Juan",
                "Perez",
                "999999999",
                "juan@gmail.com"
        );

        Cliente cliente2 = new Cliente(
                2L,
                "22222222-2",
                "Maria",
                "Gonzalez",
                "888888888",
                "maria@gmail.com"
        );

        when(clienteRepository.findAll())
                .thenReturn(Arrays.asList(cliente1, cliente2));

        List<Cliente> resultado = clienteService.obtenerTodos();

        assertEquals(2, resultado.size());
        assertEquals("Juan", resultado.get(0).getNombre());

        verify(clienteRepository).findAll();
    }

    @Test
    void obtenerPorId_debeRetornarClienteCuandoExiste() {

        Cliente cliente = new Cliente(
                1L,
                "11111111-1",
                "Juan",
                "Perez",
                "999999999",
                "juan@gmail.com"
        );

        when(clienteRepository.findById(1L))
                .thenReturn(Optional.of(cliente));

        Optional<Cliente> resultado =
                clienteService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Juan", resultado.get().getNombre());

        verify(clienteRepository).findById(1L);
    }

    @Test
    void obtenerPorId_debeRetornarVacioCuandoNoExiste() {

        when(clienteRepository.findById(99L))
                .thenReturn(Optional.empty());

        Optional<Cliente> resultado =
                clienteService.obtenerPorId(99L);

        assertTrue(resultado.isEmpty());

        verify(clienteRepository).findById(99L);
    }

    @Test
    void guardar_debeGuardarCliente() {

        Cliente cliente = new Cliente(
                1L,
                "11111111-1",
                "Juan",
                "Perez",
                "999999999",
                "juan@gmail.com"
        );

        when(clienteRepository.save(cliente))
                .thenReturn(cliente);

        Cliente resultado = clienteService.guardar(cliente);

        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombre());

        verify(clienteRepository).save(cliente);
    }

    @Test
    void eliminarPorId_debeEliminarCliente() {

        clienteService.eliminarPorId(1L);

        verify(clienteRepository).deleteById(1L);
    }
}