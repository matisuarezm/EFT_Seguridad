package com.duoc.EFTSeguridad.consultaTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.duoc.EFTSeguridad.consulta.Consulta;
import com.duoc.EFTSeguridad.consulta.ConsultaRepository;
import com.duoc.EFTSeguridad.consulta.ConsultaService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsultaServiceTest {

    @Mock
    private ConsultaRepository consultaRepository;

    @InjectMocks
    private ConsultaService consultaService;

    private Consulta consulta;

    @BeforeEach
    void setUp() {
        consulta = new Consulta();
        consulta.setId(1L);
        consulta.setFecha(LocalDateTime.now());
        consulta.setDiagnostico("Infección leve");
        consulta.setTratamiento("Antibióticos por 7 días");
    }

    @Test
    void testObtenerTodas() {
        when(consultaRepository.findAll()).thenReturn(Arrays.asList(consulta));

        List<Consulta> resultado = consultaService.obtenerTodas();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Infección leve", resultado.get(0).getDiagnostico());
        verify(consultaRepository, times(1)).findAll();
    }

    @Test
    void testObtenerPorIdExitoso() {
        when(consultaRepository.findById(1L)).thenReturn(Optional.of(consulta));

        Consulta resultado = consultaService.obtenerPorId(1L).orElse(null);

        assertNotNull(resultado);
        assertEquals("Antibióticos por 7 días", resultado.getTratamiento());
        verify(consultaRepository, times(1)).findById(1L);
    }

    @Test
    void testGuardarConsulta() {
        when(consultaRepository.save(any(Consulta.class))).thenReturn(consulta);

        Consulta resultado = consultaService.guardar(consulta);

        assertNotNull(resultado);
        assertEquals("Infección leve", resultado.getDiagnostico());
        verify(consultaRepository, times(1)).save(consulta);
    }

    @Test
    void testEliminarConsulta() {
        doNothing().when(consultaRepository).deleteById(1L);

        consultaService.eliminarPorId(1L);

        verify(consultaRepository, times(1)).deleteById(1L);
    }
}