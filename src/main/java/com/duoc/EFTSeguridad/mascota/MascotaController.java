package com.duoc.EFTSeguridad.mascota;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @GetMapping
    public ResponseEntity<List<Mascota>> listarTodas() {
        return ResponseEntity.ok(mascotaService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mascota> obtenerPorId(@PathVariable Long id) {
        return mascotaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Mascota> crear(@RequestBody MascotaDTO mascotaDTO) {
        Mascota nuevaMascota = mascotaService.guardar(convertirADominio(mascotaDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaMascota);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mascota> actualizar(@PathVariable Long id, @RequestBody MascotaDTO mascotaDTO) {
        return mascotaService.obtenerPorId(id)
                .map(existente -> {
                    Mascota mascota = convertirADominio(mascotaDTO);
                    mascota.setId(id);
                    return ResponseEntity.ok(mascotaService.guardar(mascota));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (mascotaService.obtenerPorId(id).isPresent()) {
            mascotaService.eliminarPorId(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private Mascota convertirADominio(MascotaDTO dto) {
        Mascota mascota = new Mascota();
        mascota.setNombre(dto.getNombre());
        mascota.setEspecie(dto.getEspecie());
        mascota.setRaza(dto.getRaza());
        mascota.setEdad(dto.getEdad());
        mascota.setNombreDuenio(dto.getNombreDuenio());
        return mascota;
    }
}