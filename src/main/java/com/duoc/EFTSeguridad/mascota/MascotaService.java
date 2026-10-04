package com.duoc.EFTSeguridad.mascota;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaService {

    private MascotaRepository mascotaRepository;

    public MascotaService(MascotaRepository mascotaRepository) {
        this.mascotaRepository = mascotaRepository;
    }

    public List<Mascota> obtenerTodas() {
        return mascotaRepository.findAll();
    }

    public Optional<Mascota> obtenerPorId(Long id) {
        return mascotaRepository.findById(id);
    }

    public Mascota guardar(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    public void eliminarPorId(Long id) {
        mascotaRepository.deleteById(id);
    }
}