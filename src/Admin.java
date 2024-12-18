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
    public Admin() {
        super();
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
    public static void menuAdmin(Admin admin, Cliente cliente1) {
        String[] opcionesAdmin = {
            "Ver movimientos de cliente", 
            "Cerrar sesión"
        };
        int opcion;
        do {
            opcion = JOptionPane.showOptionDialog(null, "Menú Administrador", "", 0, 0, null, opcionesAdmin, opcionesAdmin[0]);
            switch (opcion) {
                case 0:
                  
                    admin.verMovimientos(cliente1);
                    break;

                case 1:
                    JOptionPane.showMessageDialog(null, "Sesión cerrada.");
                    break;
            }
        } while (opcion != 1);
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
