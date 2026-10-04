package com.duoc.EFTSeguridad.facturaTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.duoc.EFTSeguridad.factura.Factura;
import com.duoc.EFTSeguridad.factura.FacturaController;
import com.duoc.EFTSeguridad.factura.FacturaDTO;
import com.duoc.EFTSeguridad.factura.FacturaService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FacturaControllerTest {

    @Mock
    private FacturaService facturaService;

    @InjectMocks
    private FacturaController facturaController;

    private Factura factura;
    private FacturaDTO facturaDTO;

    @BeforeEach
    void setUp() {
        factura = new Factura();
        factura.setId(1L);
        factura.setFechaEmision(LocalDateTime.now());
        factura.setMontoTotal(45000.0);
        factura.setEstadoPago("PAGADO");
        factura.setMetodoPago("TARJETA");

        facturaDTO = new FacturaDTO();
        facturaDTO.setFechaEmision(factura.getFechaEmision());
        facturaDTO.setMontoTotal(45000.0);
        facturaDTO.setEstadoPago("PAGADO");
        facturaDTO.setMetodoPago("TARJETA");
    }

    @Test
    void testListar() {
        when(facturaService.obtenerTodas()).thenReturn(Arrays.asList(factura));

        ResponseEntity<List<Factura>> res = facturaController.listar();

        assertEquals(HttpStatus.OK, res.getStatusCode());
        assertNotNull(res.getBody());
        assertEquals(1, res.getBody().size());
    }

    @Test
    void testObtenerPorIdExitoso() {
        when(facturaService.obtenerPorId(1L)).thenReturn(Optional.of(factura));

        ResponseEntity<Factura> res = facturaController.obtenerPorId(1L);

        assertEquals(HttpStatus.OK, res.getStatusCode());
        assertNotNull(res.getBody());
        assertEquals(45000.0, res.getBody().getMontoTotal());
    }

    @Test
    void testObtenerPorIdNoEncontrado() {
        when(facturaService.obtenerPorId(1L)).thenReturn(Optional.empty());

        ResponseEntity<Factura> res = facturaController.obtenerPorId(1L);

        assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode());
    }

    @Test
    void testCrear() {
        when(facturaService.guardar(any(Factura.class))).thenReturn(factura);

        ResponseEntity<Factura> res = facturaController.crear(facturaDTO);

        assertEquals(HttpStatus.CREATED, res.getStatusCode());
        assertNotNull(res.getBody());
    }

    @Test
    void testActualizarExitoso() {
        when(facturaService.obtenerPorId(1L)).thenReturn(Optional.of(factura));
        when(facturaService.guardar(any(Factura.class))).thenReturn(factura);

        ResponseEntity<Factura> res = facturaController.actualizar(1L, facturaDTO);

        assertEquals(HttpStatus.OK, res.getStatusCode());
    }

    @Test
    void testActualizarNoEncontrado() {
        when(facturaService.obtenerPorId(1L)).thenReturn(Optional.empty());

        ResponseEntity<Factura> res = facturaController.actualizar(1L, facturaDTO);

        assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode());
    }

    @Test
    void testEliminarExitoso() {
        when(facturaService.obtenerPorId(1L)).thenReturn(Optional.of(factura));
        doNothing().when(facturaService).eliminarPorId(1L);

        ResponseEntity<Void> res = facturaController.eliminar(1L);

        assertEquals(HttpStatus.NO_CONTENT, res.getStatusCode());
        verify(facturaService, times(1)).eliminarPorId(1L);
    }

    @Test
    void testEliminarNoEncontrado() {
        when(facturaService.obtenerPorId(1L)).thenReturn(Optional.empty());

        ResponseEntity<Void> res = facturaController.eliminar(1L);

        assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode());
        verify(facturaService, never()).eliminarPorId(anyLong());
    }
}