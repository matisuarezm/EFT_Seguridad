package com.duoc.EFTSeguridad.facturaTest;

import org.junit.jupiter.api.Test;

import com.duoc.EFTSeguridad.factura.Factura;
import com.duoc.EFTSeguridad.factura.FacturaDTO;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class FacturaTest {

    @Test
    void testFacturaEntityAndDTO() {
        LocalDateTime ahora = LocalDateTime.now();

        // Test Entidad
        Factura f = new Factura();
        f.setFechaEmision(ahora);
        f.setMontoTotal(25000.0);
        f.setEstadoPago("PAGADO");
        f.setMetodoPago("EFECTIVO");

        assertEquals(ahora, f.getFechaEmision());
        assertEquals(25000.0, f.getMontoTotal());
        assertEquals("PAGADO", f.getEstadoPago());
        assertEquals("EFECTIVO", f.getMetodoPago());

        // Test DTO
        FacturaDTO dto = new FacturaDTO();
        dto.setFechaEmision(ahora);
        dto.setMontoTotal(25000.0);
        dto.setEstadoPago("PAGADO");
        dto.setMetodoPago("EFECTIVO");

        assertEquals(ahora, dto.getFechaEmision());
        assertEquals(25000.0, dto.getMontoTotal());
        assertEquals("PAGADO", dto.getEstadoPago());
        assertEquals("EFECTIVO", dto.getMetodoPago());
    }
}