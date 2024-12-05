import java.time.LocalDate;
import java.time.LocalDateTime;
public class Movimiento {
    private static int contadorId = 1; // Para generar IDs unicos
    private int id;
    private LocalDateTime fechaHora;
    private Cliente cliente;
    private String detalle;
    private OpcionesMovimiento tipo; 

    public Movimiento(LocalDateTime fechaHora, Cliente cliente, String detalle, OpcionesMovimiento tipo) {
        if (detalle == null || detalle.trim().isEmpty()) {
            throw new IllegalArgumentException("El detalle no puede estar vacío.");
        }
        this.id = contadorId++;
        this.fechaHora = fechaHora;
        this.cliente = cliente;
        this.detalle = detalle;
        this.tipo = tipo;
    }

	// Getters y Setters
    public int getId() {
        return id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        if (detalle == null || detalle.trim().isEmpty()) {
            throw new IllegalArgumentException("El detalle no puede estar vacío.");
        }
        this.detalle = detalle;
    }

    public OpcionesMovimiento getTipo() {
        return tipo;
    }

    public void setTipo(OpcionesMovimiento tipo) {
        this.tipo = tipo;
    }

    

    
    @Override
    public String toString() {
        return "Movimiento ID: " + id + 
               "\nFecha y Hora: " + getFechaHora() + 
               "\nCliente: " + cliente.getNombre() + 
               "\nTipo: " + tipo +
               "\nDetalle: " + detalle;
    }
    }