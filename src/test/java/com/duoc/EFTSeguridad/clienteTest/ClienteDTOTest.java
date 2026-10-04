package com.duoc.EFTSeguridad.clienteTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.duoc.EFTSeguridad.cliente.ClienteDTO;

class ClienteDTOTest {

    @Test
    void dto_debeGuardarTodosLosDatos() {

        ClienteDTO dto = new ClienteDTO();

        dto.setRut("11111111-1");
        dto.setNombre("Juan");
        dto.setApellido("Perez");
        dto.setTelefono("999999999");
        dto.setEmail("juan@gmail.com");

        assertEquals("11111111-1", dto.getRut());
        assertEquals("Juan", dto.getNombre());
        assertEquals("Perez", dto.getApellido());
        assertEquals("999999999", dto.getTelefono());
        assertEquals("juan@gmail.com", dto.getEmail());
    }
}