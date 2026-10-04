package com.duoc.EFTSeguridad.consultaTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.duoc.EFTSeguridad.consulta.Consulta;
import com.duoc.EFTSeguridad.consulta.ConsultaController;
import com.duoc.EFTSeguridad.consulta.ConsultaDTO;
import com.duoc.EFTSeguridad.consulta.ConsultaService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsultaControllerTest {

    @Mock
    private ConsultaService consultaService;

    @InjectMocks
    private ConsultaController consultaController;

    private Consulta consulta;
    private ConsultaDTO consultaDTO;

    @BeforeEach
    void setUp() {
        consulta = new Consulta();
        consulta.setId(1L);
        consulta.setFecha(LocalDateTime.now());
        consulta.setDiagnostico("Revision dental");
        consulta.setTratamiento("Limpieza");

        consultaDTO = new ConsultaDTO();
        consultaDTO.setFecha(consulta.getFecha());
        consultaDTO.setDiagnostico("Revision dental");
        consultaDTO.setTratamiento("Limpieza");
    }

    @Test
    void testListar() {
        when(consultaService.obtenerTodas()).thenReturn(Arrays.asList(consulta));

        ResponseEntity<List<Consulta>> response = consultaController.listar();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testObtenerPorIdExitoso() {
        when(consultaService.obtenerPorId(1L)).thenReturn(Optional.of(consulta));

        ResponseEntity<Consulta> response = consultaController.obtenerPorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Revision dental", response.getBody().getDiagnostico());
    }

    @Test
    void testObtenerPorIdNoEncontrado() {
        when(consultaService.obtenerPorId(1L)).thenReturn(Optional.empty());

        ResponseEntity<Consulta> response = consultaController.obtenerPorId(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testCrear() {
        when(consultaService.guardar(any(Consulta.class))).thenReturn(consulta);

        ResponseEntity<Consulta> response = consultaController.crear(consultaDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Revision dental", response.getBody().getDiagnostico());
    }

    @Test
    void testActualizarExitoso() {
        when(consultaService.obtenerPorId(1L)).thenReturn(Optional.of(consulta));
        when(consultaService.guardar(any(Consulta.class))).thenReturn(consulta);

        ResponseEntity<Consulta> response = consultaController.actualizar(1L, consultaDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testActualizarNoEncontrado() {
        when(consultaService.obtenerPorId(1L)).thenReturn(Optional.empty());

        ResponseEntity<Consulta> response = consultaController.actualizar(1L, consultaDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testEliminarExitoso() {
        when(consultaService.obtenerPorId(1L)).thenReturn(Optional.of(consulta));
        doNothing().when(consultaService).eliminarPorId(1L);

        ResponseEntity<Void> response = consultaController.eliminar(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(consultaService, times(1)).eliminarPorId(1L);
    }

    @Test
    void testEliminarNoEncontrado() {
        when(consultaService.obtenerPorId(1L)).thenReturn(Optional.empty());

        ResponseEntity<Void> response = consultaController.eliminar(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(consultaService, never()).eliminarPorId(anyLong());
    }
}