package com.duoc.EFTSeguridad.facturaTest;

import org.junit.jupiter.api.Test;

import com.duoc.EFTSeguridad.factura.FacturaDTO;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class FacturaDTOTest {

    @Test
    void testGettersAndSetters() {
        FacturaDTO dto = new FacturaDTO();
        LocalDateTime fecha = LocalDateTime.now();

        dto.setFechaEmision(fecha);
        dto.setMontoTotal(35000.0);
        dto.setEstadoPago("PAGADO");
        dto.setMetodoPago("EFECTIVO");

        assertEquals(fecha, dto.getFechaEmision());
        assertEquals(35000.0, dto.getMontoTotal());
        assertEquals("PAGADO", dto.getEstadoPago());
        assertEquals("EFECTIVO", dto.getMetodoPago());
    }
}