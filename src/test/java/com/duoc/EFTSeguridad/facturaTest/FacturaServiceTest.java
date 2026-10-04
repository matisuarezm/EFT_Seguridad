package com.duoc.EFTSeguridad.facturaTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.duoc.EFTSeguridad.factura.Factura;
import com.duoc.EFTSeguridad.factura.FacturaRepository;
import com.duoc.EFTSeguridad.factura.FacturaService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FacturaServiceTest {

    @Mock
    private FacturaRepository facturaRepository;

    @InjectMocks
    private FacturaService facturaService;

    private Factura factura;

    @BeforeEach
    void setUp() {
        factura = new Factura();
        factura.setId(1L);
        factura.setFechaEmision(LocalDateTime.now());
        factura.setMontoTotal(50000.0);
        factura.setEstadoPago("PAGADO");
        factura.setMetodoPago("TRANSFERENCIA");
    }

    @Test
    void testObtenerTodas() {
        when(facturaRepository.findAll()).thenReturn(Arrays.asList(factura));

        List<Factura> lista = facturaService.obtenerTodas();

        assertNotNull(lista);
        assertEquals(1, lista.size());
        assertEquals(50000.0, lista.get(0).getMontoTotal());
    }

    @Test
    void testObtenerPorId() {
        when(facturaRepository.findById(1L)).thenReturn(Optional.of(factura));

        Optional<Factura> resultado = facturaService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("PAGADO", resultado.get().getEstadoPago());
    }

    @Test
    void testGuardar() {
        when(facturaRepository.save(any(Factura.class))).thenReturn(factura);

        Factura guardada = facturaService.guardar(factura);

        assertNotNull(guardada);
        assertEquals("TRANSFERENCIA", guardada.getMetodoPago());
    }

    @Test
    void testEliminarPorId() {
        doNothing().when(facturaRepository).deleteById(1L);

        facturaService.eliminarPorId(1L);

        verify(facturaRepository, times(1)).deleteById(1L);
    }
}