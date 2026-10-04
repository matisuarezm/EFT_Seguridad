package com.duoc.EFTSeguridad.mascotasTest;

import org.junit.jupiter.api.Test;

import com.duoc.EFTSeguridad.mascota.Mascota;
import com.duoc.EFTSeguridad.mascota.MascotaDTO;

import static org.junit.jupiter.api.Assertions.*;

class MascotaTest {

    @Test
    void testMascotaEntityAndDTO() {
        // Test Entidad
        Mascota m = new Mascota();
        m.setNombre("Bobi");
        m.setEspecie("Perro");
        m.setRaza("Kulter");
        m.setEdad(3);
        m.setNombreDuenio("Juan Perez");

        assertEquals("Bobi", m.getNombre());
        assertEquals("Perro", m.getEspecie());
        assertEquals("Kulter", m.getRaza());
        assertEquals(3, m.getEdad());
        assertEquals("Juan Perez", m.getNombreDuenio());

        // Test DTO
        MascotaDTO dto = new MascotaDTO();
        dto.setNombre("Bobi");
        dto.setEspecie("Perro");
        dto.setRaza("Kulter");
        dto.setEdad(3);
        dto.setNombreDuenio("Juan Perez");

        assertEquals("Bobi", dto.getNombre());
        assertEquals("Perro", dto.getEspecie());
        assertEquals("Kulter", dto.getRaza());
        assertEquals(3, dto.getEdad());
        assertEquals("Juan Perez", dto.getNombreDuenio());
    }
}