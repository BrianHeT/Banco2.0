import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;

public class Cliente extends Usuario{
	private String tipo;
	private Cuenta cuenta;
	private CuentaInversion cuentaInversion;
	public Cliente(String nombre, String dni, String contrasena, String tipo, Cuenta cuenta) {
		super(nombre, dni, contrasena);
		this.tipo = tipo;
		this.cuenta = cuenta;
		this.cuentaInversion = null;
	}
	public Cliente() {
		super();
		this.cuenta = new Cuenta(0);
	
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
	
	
	public double menucliente(Cliente cliente) {
		  int opcion = 0;
		    do {
		        opcion = JOptionPane.showOptionDialog(null, cuenta, tipo, 0, 0, null, OpcionesCliente.values(), OpcionesCliente.values()[0]);

		        switch (opcion) {
		        case 0:  // Depositar dinero
	                String depositarStr = JOptionPane.showInputDialog("Ingrese cantidad que quiere depositar");
	                if (depositarStr != null && !depositarStr.isEmpty()) {
	                    double depositar = Double.parseDouble(depositarStr);
	                    if (depositar < 0) {
	                        JOptionPane.showMessageDialog(null, "Ingrese un monto");
	                    } else {
	                        this.cuenta.setSaldo(this.cuenta.getSaldo() + depositar);
	                        JOptionPane.showMessageDialog(null, "Ahora su saldo es de " + this.cuenta.getSaldo());
	                        this.cuenta.getMovimientos().add(new Movimiento(LocalDateTime.now(), this, "Depósito realizado", OpcionesMovimiento.DEPOSITO));
	                    }
	                } else {
	                    JOptionPane.showMessageDialog(null, "Por favor ingrese un monto válido.");
	                }
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

		            case 4:
		            	return this.cuenta.getSaldo();
		        }
		    } while (opcion != 4);

		    return this.cuenta.getSaldo();
		}

	public void gestionarInversiones() {
	    OpcionesInversion opcion = null;
	    do {
	        opcion = (OpcionesInversion) JOptionPane.showInputDialog(
	                null,
	                "Seleccione una opción de inversión:",
	                "Gestión de Inversiones",
	                JOptionPane.QUESTION_MESSAGE,
	                null,
	                OpcionesInversion.values(),
	                OpcionesInversion.REALIZAR_INVERSION
	        );

	        if (opcion == null) break;

	        switch (opcion) {
	            case REALIZAR_INVERSION:
	                if (this.cuentaInversion == null) {
	                    double montoInicial = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto a invertir:"));
	                    if (montoInicial > 0 && montoInicial <= cuenta.getSaldo()) {
	                        cuenta.setSaldo(cuenta.getSaldo() - montoInicial);
	                        this.cuentaInversion = new CuentaInversion(montoInicial);
	                        JOptionPane.showMessageDialog(null, "Inversión creada con éxito.");
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Monto inválido o saldo insuficiente.");
	                    }
	                } else {
	                    JOptionPane.showMessageDialog(null, "Ya tiene una cuenta de inversión activa.");
	                }
	                break;

	            case MODIFICAR_INVERSION:
	                if (this.cuentaInversion != null) {
	                    int dias = Integer.parseInt(JOptionPane.showInputDialog("Seleccione cantidad de dias para la inversion"));
	                    for (int i = 0; i < dias; i++) {
	                        this.cuentaInversion.simularDia();
	                    }
	                    JOptionPane.showMessageDialog(null, "el tiempo total de inversion es de:  " + dias + " días.");
	                } else {
	                    JOptionPane.showMessageDialog(null, "No tiene una cuenta de inversión activa.");
	                }
	                break;

	            case CANCELAR_INVERSION:
	                if (this.cuentaInversion != null) {
	                    double saldoFinal = this.cuentaInversion.getSaldo();
	                    cuenta.setSaldo(cuenta.getSaldo() + saldoFinal);
	                    JOptionPane.showMessageDialog(null, "Inversión cancelada. Saldo devuelto: $" + saldoFinal);
	                    this.cuentaInversion = null;
	                } else {
	                    JOptionPane.showMessageDialog(null, "No tiene una inversión activa para cancelar.");
	                }
	                break;

	            case SALIR:
	                JOptionPane.showMessageDialog(null, "Saliendo de la gestión de inversiones.");
	                break;
	        }
	    } while (opcion != OpcionesInversion.SALIR);
	}

}
