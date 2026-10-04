package com.duoc.EFTSeguridad.consulta;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.duoc.EFTSeguridad.mascota.Mascota;

@Entity
@Table(name = "consultas")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha;
    private String diagnostico;
    private String tratamiento;

    @ManyToOne
    @JoinColumn(name = "mascota_id")
    private Mascota mascota;

    public Consulta() {}

    public Consulta(Long id, LocalDateTime fecha, String diagnostico, String tratamiento, Mascota mascota) {
        this.id = id;
        this.fecha = fecha;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.mascota = mascota;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }

    public String getTratamiento() { return tratamiento; }
    public void setTratamiento(String tratamiento) { this.tratamiento = tratamiento; }

    public Mascota getMascota() { return mascota; }
    public void setMascota(Mascota mascota) { this.mascota = mascota; }
}