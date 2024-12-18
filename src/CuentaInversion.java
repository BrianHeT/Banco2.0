import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class CuentaInversion {
    private double saldo; 
    public List<String> getHistorial() {
		return historial;
	}


	public void setHistorial(List<String> historial) {
		this.historial = historial;
	}


	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}


	public void setRendimientoAcumulado(double rendimientoAcumulado) {
		this.rendimientoAcumulado = rendimientoAcumulado;
	}


	private List<String> historial; 
    private double rendimientoAcumulado; 

    public CuentaInversion(double montoInicial) {
        this.saldo = montoInicial;
        this.historial = new ArrayList<>();
        this.rendimientoAcumulado = 0.0;

        
        historial.add("Día 0: Saldo inicial: $" + String.format("%.2f", saldo));
    }

    
    public void simularDia() {
        double tasaInteres = generarTasaAleatoria(); 
        double rendimientoDiario = saldo * tasaInteres; 
        saldo += rendimientoDiario; 
        rendimientoAcumulado += rendimientoDiario;

      
        String registro = String.format("Tasa: %.2f%%, Rendimiento: $%.2f, Saldo: $%.2f",
                tasaInteres * 100, rendimientoDiario, saldo);
        historial.add(registro);
    }

   
    private double generarTasaAleatoria() {
        return (Math.random() * 0.10) - 0.05; 
    }
    public void mostrarHistorial() {
    	JOptionPane.showMessageDialog(null, "Historial de inversion: ");
    	for(String registro : historial) {
    		JOptionPane.showMessageDialog(null, registro);
    	}
    }

    public double getRendimientoAcumulado() {
        return rendimientoAcumulado;
    }

    
    public double getSaldo() {
        return saldo;
    }
}


