package com.duoc.EFTSeguridad.consulta;

import java.time.LocalDateTime;

public class ConsultaDTO {
    private LocalDateTime fecha;
    private String diagnostico;
    private String tratamiento;

    // Getters y Setters
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }

    public String getTratamiento() { return tratamiento; }
    public void setTratamiento(String tratamiento) { this.tratamiento = tratamiento; }
}