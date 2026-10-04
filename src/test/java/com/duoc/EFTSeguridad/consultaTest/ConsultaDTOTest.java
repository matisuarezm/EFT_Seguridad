package com.duoc.EFTSeguridad.consultaTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.duoc.EFTSeguridad.consulta.ConsultaDTO;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ConsultaDTOTest {

    private ConsultaDTO consultaDTO;

    @BeforeEach
    void setUp() {
        consultaDTO = new ConsultaDTO();
    }

    @Test
    void testGettersAndSetters() {
        LocalDateTime fecha = LocalDateTime.now();
        String diagnostico = "Control de rutina";
        String tratamiento = "Vacunacion anual";

        consultaDTO.setFecha(fecha);
        consultaDTO.setDiagnostico(diagnostico);
        consultaDTO.setTratamiento(tratamiento);

        assertEquals(fecha, consultaDTO.getFecha());
        assertEquals(diagnostico, consultaDTO.getDiagnostico());
        assertEquals(tratamiento, consultaDTO.getTratamiento());
    }
}