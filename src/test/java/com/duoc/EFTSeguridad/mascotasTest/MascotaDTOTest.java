package com.duoc.EFTSeguridad.mascotasTest;

import org.junit.jupiter.api.Test;

import com.duoc.EFTSeguridad.mascota.MascotaDTO;

import static org.junit.jupiter.api.Assertions.*;

class MascotaDTOTest {

    @Test
    void testGettersAndSetters() {
        MascotaDTO dto = new MascotaDTO();
        dto.setNombre("Kaiser");
        dto.setEspecie("Perro");
        dto.setRaza("Pastor Aleman");
        dto.setEdad(5);
        dto.setNombreDuenio("Roberto Gomez");

        assertEquals("Kaiser", dto.getNombre());
        assertEquals("Perro", dto.getEspecie());
        assertEquals("Pastor Aleman", dto.getRaza());
        assertEquals(5, dto.getEdad());
        assertEquals("Roberto Gomez", dto.getNombreDuenio());
    }
}