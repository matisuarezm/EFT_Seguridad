package com.duoc.EFTSeguridad.mascotasTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.duoc.EFTSeguridad.mascota.Mascota;
import com.duoc.EFTSeguridad.mascota.MascotaController;
import com.duoc.EFTSeguridad.mascota.MascotaDTO;
import com.duoc.EFTSeguridad.mascota.MascotaService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MascotaControllerTest {

    @Mock
    private MascotaService mascotaService;

    @InjectMocks
    private MascotaController mascotaController;

    private Mascota mascota;
    private MascotaDTO mascotaDTO;

    @BeforeEach
    void setUp() {
        mascota = new Mascota(1L, "Luna", "Gato", "Persa", 3, "Maria Silva");

        mascotaDTO = new MascotaDTO();
        mascotaDTO.setNombre("Luna");
        mascotaDTO.setEspecie("Gato");
        mascotaDTO.setRaza("Persa");
        mascotaDTO.setEdad(3);
        mascotaDTO.setNombreDuenio("Maria Silva");
    }

    @Test
    void testListar() {
        when(mascotaService.obtenerTodas()).thenReturn(Arrays.asList(mascota));

        ResponseEntity<List<Mascota>> res = mascotaController.listarTodas();

        assertEquals(HttpStatus.OK, res.getStatusCode());
        assertNotNull(res.getBody());
        assertEquals(1, res.getBody().size());
    }

    @Test
    void testObtenerPorIdExitoso() {
        when(mascotaService.obtenerPorId(1L)).thenReturn(Optional.of(mascota));

        ResponseEntity<Mascota> res = mascotaController.obtenerPorId(1L);

        assertEquals(HttpStatus.OK, res.getStatusCode());
        assertNotNull(res.getBody());
        assertEquals("Luna", res.getBody().getNombre());
    }

    @Test
    void testObtenerPorIdNoEncontrado() {
        when(mascotaService.obtenerPorId(1L)).thenReturn(Optional.empty());

        ResponseEntity<Mascota> res = mascotaController.obtenerPorId(1L);

        assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode());
    }

    @Test
    void testCrear() {
        when(mascotaService.guardar(any(Mascota.class))).thenReturn(mascota);

        ResponseEntity<Mascota> res = mascotaController.crear(mascotaDTO);

        assertEquals(HttpStatus.CREATED, res.getStatusCode());
        assertNotNull(res.getBody());
    }

    @Test
    void testActualizarExitoso() {
        when(mascotaService.obtenerPorId(1L)).thenReturn(Optional.of(mascota));
        when(mascotaService.guardar(any(Mascota.class))).thenReturn(mascota);

        ResponseEntity<Mascota> res = mascotaController.actualizar(1L, mascotaDTO);

        assertEquals(HttpStatus.OK, res.getStatusCode());
    }

    @Test
    void testActualizarNoEncontrado() {
        when(mascotaService.obtenerPorId(1L)).thenReturn(Optional.empty());

        ResponseEntity<Mascota> res = mascotaController.actualizar(1L, mascotaDTO);

        assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode());
    }

    @Test
    void testEliminarExitoso() {
        when(mascotaService.obtenerPorId(1L)).thenReturn(Optional.of(mascota));
        doNothing().when(mascotaService).eliminarPorId(1L);

        ResponseEntity<Void> res = mascotaController.eliminar(1L);

        assertEquals(HttpStatus.NO_CONTENT, res.getStatusCode());
        verify(mascotaService, times(1)).eliminarPorId(1L);
    }

    @Test
    void testEliminarNoEncontrado() {
        when(mascotaService.obtenerPorId(1L)).thenReturn(Optional.empty());

        ResponseEntity<Void> res = mascotaController.eliminar(1L);

        assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode());
        verify(mascotaService, never()).eliminarPorId(anyLong());
    }
}