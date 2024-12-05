import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Cuenta {
    private static int contadorCuentas = 1;

    private int nroCuenta;
    private double Saldo;
    private List<Movimiento> movimientos;

    public Cuenta(double saldo) {
        this.nroCuenta = contadorCuentas++;
        this.Saldo = saldo >= 0 ? saldo : 0; 
        this.movimientos = new ArrayList<>();
    }

    public int getNroCuenta() {
        return nroCuenta;
    }

    public double getSaldo() {
        return Saldo;
    }

    public static int getContadorCuentas() {
		return contadorCuentas;
	}

	public static void setContadorCuentas(int contadorCuentas) {
		Cuenta.contadorCuentas = contadorCuentas;
	}

	public void setNroCuenta(int nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	public void setSaldo(double saldo) {
		Saldo = saldo;
	}

	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void agregarMovimiento(Movimiento movimiento) {
        if (movimiento == null) {
            JOptionPane.showMessageDialog(null, "El movimiento no puede ser nulo.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.movimientos.add(movimiento);
    }

    public void mostrarMovimientos() {
        if (movimientos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay movimientos registrados.", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder detalles = new StringBuilder("Movimientos de la cuenta:\n");
            for (Movimiento mov : movimientos) {
                detalles.append(mov).append("\n");
            }
            JOptionPane.showMessageDialog(null, detalles.toString(), "Movimientos", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public String toString() {
        return "Cuenta #" + nroCuenta + "\nSaldo: $" + Saldo + "\nMovimientos:\n" +
               (movimientos.isEmpty() ? "No hay movimientos registrados." :
               movimientos.stream().map(Object::toString).reduce("", (a, b) -> a + "\n" + b));
    }
}
