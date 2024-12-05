import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;

public class Cliente extends Usuario{
	private String tipo;
	private Cuenta cuenta;
	public Cliente(String nombre, String dni, String contrasena, String tipo, Cuenta cuenta) {
		super(nombre, dni, contrasena);
		this.tipo = tipo;
		this.cuenta = cuenta;
	}
	public Cliente() {
		super();
	
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Cuenta getCuenta() {
		return cuenta;
	}
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	@Override
	public String toString() {
		return "Cliente [tipo=" + tipo + ", cuenta=" + cuenta + ", toString()=" + super.toString() + "\n]";
	}
	
	@Override
	public void login() {
		for (Usuario cliente : Usuario.getUsuarios()) {
			String nombre = validarInfo("Ingrese su nombre");
			String dni = validarInfo("ingrese su dni");
			if (cliente.getNombre().equalsIgnoreCase(nombre) && cliente.getDni().equals(dni)) {
                JOptionPane.showMessageDialog(null, "Bienvenido, " + nombre);
			} else {
				JOptionPane.showMessageDialog(null, "error");
			}
		}
	}
	
	
	
	public double Opciones(Cliente cliente) {
		  int opcion = 0;
		    do {
		        opcion = JOptionPane.showOptionDialog(null, cuenta, tipo, 0, 0, null, OpcionesGeneral.values(), OpcionesGeneral.values()[0]);

		        switch (opcion) {
		            case 0: 
		                double depositar = Double.parseDouble(JOptionPane.showInputDialog("Ingrese cantidad que quiere depositar"));
		                this.cuenta.setSaldo(this.cuenta.getSaldo() + depositar);
		                JOptionPane.showMessageDialog(null, "Ahora su saldo es de " + this.cuenta.getSaldo());
		                this.cuenta.getMovimientos().add(new Movimiento(LocalDateTime.now(), this, "Depósito realizado", OpcionesMovimiento.DEPOSITO));
		                break;

		            case 1: 
		                double transferencia = Double.parseDouble(JOptionPane.showInputDialog("Ingrese cantidad que quiere transferir a " + cliente.getNombre()));
		                if (transferencia > this.cuenta.getSaldo() || transferencia <= 0) {
		                    JOptionPane.showMessageDialog(null, "No se puede hacer la transferencia");
		                } else {
		                    this.cuenta.setSaldo(this.cuenta.getSaldo() - transferencia);
		                    JOptionPane.showMessageDialog(null, "Transferencia enviada a " + cliente.getNombre());
		                    JOptionPane.showMessageDialog(null, "Saldo actual: " + this.cuenta.getSaldo());
		                    this.cuenta.getMovimientos().add(new Movimiento(LocalDateTime.now(), this, "Pago realizado a " + cliente.getNombre(), OpcionesMovimiento.PAGO));
		                    this.cuenta.getMovimientos().add(new Movimiento(LocalDateTime.now(), cliente, "Recibo de dinero", OpcionesMovimiento.RECIBO));
		                }
		                break;

		            case 2: 
		                double retiro = Double.parseDouble(JOptionPane.showInputDialog("Ingresar cantidad a retirar"));
		                if (retiro > this.cuenta.getSaldo()) {
		                    JOptionPane.showMessageDialog(null, "Saldo insuficiente");
		                } else {
		                    this.cuenta.setSaldo(this.cuenta.getSaldo() - retiro);
		                    JOptionPane.showMessageDialog(null, "Retiro exitoso, ahora tiene " + this.cuenta.getSaldo());
		                    this.cuenta.getMovimientos().add(new Movimiento(LocalDateTime.now(), this, "Retiro realizado", OpcionesMovimiento.RETIRO));
		                }
		                break;

		            case 3: 
		                this.gestionarInversiones();
		                break;

		            default:
		                JOptionPane.showMessageDialog(null, "Adios");
		                break;
		        }
		    } while (opcion != 3);

		    return this.cuenta.getSaldo();
		}

	public void gestionarInversiones() {
	    OpcionesInversion opcion = null;
	    do {
	        // Mostrar opciones al cliente
	        opcion = (OpcionesInversion) JOptionPane.showInputDialog(
	                null,
	                "Seleccione una opción de inversión:",
	                "Gestión de Inversiones",
	                JOptionPane.QUESTION_MESSAGE,
	                null,
	                OpcionesInversion.values(),
	                OpcionesInversion.REALIZAR_INVERSION
	        );

	        if (opcion == null) {
	            break; 
	        }

	        switch (opcion) {
	            case REALIZAR_INVERSION:
	                realizarInversion();
	                break;

	            case CANCELAR_INVERSION:
	                cancelarInversion();
	                break;

	            case MODIFICAR_INVERSION:
	                modificarInversion();
	                break;

	            case SALIR:
	                JOptionPane.showMessageDialog(null, "Saliendo de la gestión de inversiones.");
	                break;
	        }
	    } while (opcion != OpcionesInversion.SALIR);
	}

private void realizarInversion() {
    double monto = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto a invertir:"));
    if (monto <= 0) {
        JOptionPane.showMessageDialog(null, "El monto debe ser mayor a cero.");
    } else {
        JOptionPane.showMessageDialog(null, "Inversión realizada por un monto de: $" + monto);
    }
}

private void cancelarInversion() {
    JOptionPane.showMessageDialog(null, "Inversión cancelada.");
}

private void modificarInversion() {
    JOptionPane.showMessageDialog(null, "Inversión modificada.");
}
}
/* public void depositar(double cantidad) {
if (cantidad <= 0) {
    JOptionPane.showMessageDialog(null, "El monto a depositar debe ser mayor a cero.", "Error", JOptionPane.ERROR_MESSAGE);
    return;
}
this.Saldo += cantidad;
agregarMovimiento(new Movimiento(LocalDateTime.now(), null, "Depósito de " + cantidad, OpcionesMovimiento.DEPOSITO));
JOptionPane.showMessageDialog(null, "Depósito realizado con éxito. Nuevo saldo: $" + this.Saldo);
}

public void retirar(double cantidad) {
if (cantidad <= 0) {
    JOptionPane.showMessageDialog(null, "El monto a retirar debe ser mayor a cero.", "Error", JOptionPane.ERROR_MESSAGE);
    return;
}
if (cantidad > Saldo) {
    JOptionPane.showMessageDialog(null, "Fondos insuficientes. Saldo actual: $" + this.Saldo, "Error", JOptionPane.ERROR_MESSAGE);
    return;
}
this.Saldo -= cantidad;
agregarMovimiento(new Movimiento(LocalDateTime.now(), null, "Retiro de " + cantidad, OpcionesMovimiento.RETIRO));
JOptionPane.showMessageDialog(null, "Retiro realizado con éxito. Nuevo saldo: $" + this.Saldo);
}*/