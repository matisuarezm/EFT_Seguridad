package com.duoc.EFTSeguridad.factura;

import com.duoc.EFTSeguridad.cliente.Cliente;
import com.duoc.EFTSeguridad.consulta.Consulta;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "facturas")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaEmision;
    private Double montoTotal;
    private String estadoPago; // Ej: "PENDIENTE", "PAGADO", "ANULADO"
    private String metodoPago; // Ej: "EFECTIVO", "TARJETA", "TRANSFERENCIA"

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "consulta_id")
    private Consulta consulta;

    public Factura() {}

    public Factura(Long id, LocalDateTime fechaEmision, Double montoTotal, String estadoPago, String metodoPago, Cliente cliente, Consulta consulta) {
        this.id = id;
        this.fechaEmision = fechaEmision;
        this.montoTotal = montoTotal;
        this.estadoPago = estadoPago;
        this.metodoPago = metodoPago;
        this.cliente = cliente;
        this.consulta = consulta;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(LocalDateTime fechaEmision) { this.fechaEmision = fechaEmision; }

    public Double getMontoTotal() { return montoTotal; }
    public void setMontoTotal(Double montoTotal) { this.montoTotal = montoTotal; }

    public String getEstadoPago() { return estadoPago; }
    public void setEstadoPago(String estadoPago) { this.estadoPago = estadoPago; }

    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Consulta getConsulta() { return consulta; }
    public void setConsulta(Consulta consulta) { this.consulta = consulta; }
}