package com.duoc.EFTSeguridad.clienteTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.duoc.EFTSeguridad.cliente.Cliente;

class ClienteTest {

    @Test
    void crearCliente_debeGuardarTodosLosDatos() {

        Cliente cliente = new Cliente();

        cliente.setId(1L);
        cliente.setRut("11111111-1");
        cliente.setNombre("Juan");
        cliente.setApellido("Perez");
        cliente.setTelefono("999999999");
        cliente.setEmail("juan@gmail.com");

        assertEquals(1L, cliente.getId());
        assertEquals("11111111-1", cliente.getRut());
        assertEquals("Juan", cliente.getNombre());
        assertEquals("Perez", cliente.getApellido());
        assertEquals("999999999", cliente.getTelefono());
        assertEquals("juan@gmail.com", cliente.getEmail());
    }

    @Test
    void constructor_debeCrearClienteCorrectamente() {

        Cliente cliente = new Cliente(
                1L,
                "11111111-1",
                "Juan",
                "Perez",
                "999999999",
                "juan@gmail.com"
        );

        assertEquals(1L, cliente.getId());
        assertEquals("Juan", cliente.getNombre());
        assertEquals("Perez", cliente.getApellido());
    }
}