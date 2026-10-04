package com.duoc.EFTSeguridad.consulta;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

    private ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @GetMapping
    public ResponseEntity<List<Consulta>> listar() {
        return ResponseEntity.ok(consultaService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> obtenerPorId(@PathVariable Long id) {
        return consultaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Consulta> crear(@RequestBody ConsultaDTO consultaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(consultaService.guardar(convertirADominio(consultaDTO)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> actualizar(@PathVariable Long id, @RequestBody ConsultaDTO consultaDTO) {
        return consultaService.obtenerPorId(id)
                .map(existente -> {
                    Consulta consulta = convertirADominio(consultaDTO);
                    consulta.setId(id);
                    return ResponseEntity.ok(consultaService.guardar(consulta));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (consultaService.obtenerPorId(id).isPresent()) {
            consultaService.eliminarPorId(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private Consulta convertirADominio(ConsultaDTO dto) {
        Consulta consulta = new Consulta();
        consulta.setFecha(dto.getFecha());
        consulta.setDiagnostico(dto.getDiagnostico());
        consulta.setTratamiento(dto.getTratamiento());
        return consulta;
    }
}