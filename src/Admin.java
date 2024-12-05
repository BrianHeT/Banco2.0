import java.util.LinkedList;
import javax.swing.JOptionPane;

public class Admin extends Usuario {
    private int idAdmin;
    private LinkedList<Movimiento> movimientos = new LinkedList<>();


    public Admin(String nombre, String dni, String contrasena, int idAdmin) {
        super(nombre, dni, contrasena);
        this.idAdmin = idAdmin;
    }


    public Admin(String nombre, String dni, String contrasena) {
        super(nombre, dni, contrasena);
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }


    public LinkedList<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(LinkedList<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }


    @Override
    public String toString() {
        return "Administrador [nroAdmin=" + idAdmin + ", movimientos=" + movimientos + "]";
    }


    @Override
    public void login() {
        String nombre = validarInfo("Ingrese su nombre");
        int nroAdmin = Integer.parseInt(validarInfo("Ingrese su número de administrador"));

        for (Admin admin : Usuario.getAdmin()) {
            if (admin.getNombre().equals(nombre) && admin.getIdAdmin() == nroAdmin) {
                JOptionPane.showMessageDialog(null, "Bienvenido, " + nombre);
                return; // Salimos del método si se encuentra el administrador
            }
        }

        JOptionPane.showMessageDialog(null, "Error: administrador no encontrado.");
    }


    public void verMovimientos(Cliente cliente) {
        if (cliente.getCuenta().getMovimientos().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tiene movimientos en su cuenta");
        } else {
            StringBuilder movimientosDetalle = new StringBuilder("Movimientos realizados:\n");
            for (Movimiento movimiento : cliente.getCuenta().getMovimientos()) {
                movimientosDetalle.append("Fecha: ").append(movimiento.getFechaHora())
                                   .append(", Detalle: ").append(movimiento.getDetalle())
                                   .append(", Cliente: ").append(movimiento.getCliente().getNombre())
                                   .append("\n");
            }
            JOptionPane.showMessageDialog(null, movimientosDetalle.toString());
        }
    }
}
