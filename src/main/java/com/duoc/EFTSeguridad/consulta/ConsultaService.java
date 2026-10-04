package com.duoc.EFTSeguridad.consulta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    public List<Consulta> obtenerTodas() {
        return consultaRepository.findAll();
    }

    public Optional<Consulta> obtenerPorId(Long id) {
        return consultaRepository.findById(id);
    }

    public Consulta guardar(Consulta consulta) {
        if (consulta.getFecha() == null) {
            consulta.setFecha(LocalDateTime.now());
        }
        return consultaRepository.save(consulta);
    }

    public void eliminarPorId(Long id) {
        consultaRepository.deleteById(id);
    }
}