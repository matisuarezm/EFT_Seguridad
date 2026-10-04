package com.duoc.EFTSeguridad.consultaTest;

import org.junit.jupiter.api.Test;

import com.duoc.EFTSeguridad.consulta.Consulta;
import com.duoc.EFTSeguridad.consulta.ConsultaDTO;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class ConsultaTest {

    @Test
    void testConsultaEntityAndDTO() {
        LocalDateTime ahora = LocalDateTime.now();

        // Test Entidad
        Consulta c = new Consulta();
        c.setFecha(ahora);
        c.setDiagnostico("Infeccion oido");
        c.setTratamiento("Gotas 7 dias");

        assertEquals(ahora, c.getFecha());
        assertEquals("Infeccion oido", c.getDiagnostico());
        assertEquals("Gotas 7 dias", c.getTratamiento());

        // Test DTO
        ConsultaDTO dto = new ConsultaDTO();
        dto.setFecha(ahora);
        dto.setDiagnostico("Infeccion oido");
        dto.setTratamiento("Gotas 7 dias");

        assertEquals(ahora, dto.getFecha());
        assertEquals("Infeccion oido", dto.getDiagnostico());
        assertEquals("Gotas 7 dias", dto.getTratamiento());
    }
}