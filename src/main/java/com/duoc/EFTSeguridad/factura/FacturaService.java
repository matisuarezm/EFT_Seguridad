package com.duoc.EFTSeguridad.factura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    public List<Factura> obtenerTodas() {
        return facturaRepository.findAll();
    }

    public Optional<Factura> obtenerPorId(Long id) {
        return facturaRepository.findById(id);
    }

    public Factura guardar(Factura factura) {
        if (factura.getFechaEmision() == null) {
            factura.setFechaEmision(LocalDateTime.now());
        }
        if (factura.getEstadoPago() == null) {
            factura.setEstadoPago("PENDIENTE");
        }
        return facturaRepository.save(factura);
    }

    public void eliminarPorId(Long id) {
        facturaRepository.deleteById(id);
    }
}