package com.duoc.EFTSeguridad.mascotasTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.duoc.EFTSeguridad.mascota.Mascota;
import com.duoc.EFTSeguridad.mascota.MascotaRepository;
import com.duoc.EFTSeguridad.mascota.MascotaService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MascotaServiceTest {

    @Mock
    private MascotaRepository mascotaRepository;

    @InjectMocks
    private MascotaService mascotaService;

    private Mascota mascota;

    @BeforeEach
    void setUp() {
        mascota = new Mascota(1L, "Rocky", "Perro", "Boxer", 5, "Pedro Soto");
    }

    @Test
    void testObtenerTodas() {
        when(mascotaRepository.findAll()).thenReturn(Arrays.asList(mascota));

        List<Mascota> lista = mascotaService.obtenerTodas();

        assertNotNull(lista);
        assertEquals(1, lista.size());
        assertEquals("Rocky", lista.get(0).getNombre());
    }

    @Test
    void testObtenerPorId() {
        when(mascotaRepository.findById(1L)).thenReturn(Optional.of(mascota));

        Optional<Mascota> res = mascotaService.obtenerPorId(1L);

        assertTrue(res.isPresent());
        assertEquals("Pedro Soto", res.get().getNombreDuenio());
    }

    @Test
    void testGuardar() {
        when(mascotaRepository.save(any(Mascota.class))).thenReturn(mascota);

        Mascota guardada = mascotaService.guardar(mascota);

        assertNotNull(guardada);
        assertEquals("Boxer", guardada.getRaza());
    }

    @Test
    void testEliminarPorId() {
        doNothing().when(mascotaRepository).deleteById(1L);

        mascotaService.eliminarPorId(1L);

        verify(mascotaRepository, times(1)).deleteById(1L);
    }
}