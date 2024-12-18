import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        // Crear listas iniciales
        Usuario.setUsuarios(new LinkedList<Cliente>());
        Usuario.setAdmins(new LinkedList<Admin>());
        Cliente juan = new Cliente("Juan", "12345", "1234", "chill", new Cuenta(1000));
        Admin admin = new Admin("admin","admin","admin",01);


        // Opciones del menú
        String[] opciones = {
            "Registrar cliente",
            "Registrar administrador",
            "Iniciar sesión cliente",
            "Iniciar sesión administrador",
            "Salir"
        };

        int opcion;

        do {
            // Mostrar menú
            opcion = JOptionPane.showOptionDialog(
                null, "Menú Principal", "", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, 
                null, opciones, opciones[0]
            );

            switch (opcion) {
                case 0: // Registrar cliente
                    Cliente nuevoCliente = new Cliente();
                    nuevoCliente.registrarCliente();
                    break;

                case 1: // Registrar administrador
                    Admin nuevoAdmin = new Admin();
                    nuevoAdmin.registrarAdmin();
                    break;

                case 2: // Iniciar sesión cliente
                    Cliente clienteLogueado = (Cliente) new Cliente().login();  // Iniciar sesión cliente
                    if (clienteLogueado != null) {
                        clienteLogueado.menucliente(clienteLogueado);
                    }
                    break;

                case 3: // Iniciar sesión administrador
                    Admin adminLogueado = (Admin) new Admin().login();  // Iniciar sesión administrador
                    if (adminLogueado != null) {
                        Admin.menuAdmin(adminLogueado, null); 
                    }
                    break;

                case 4: // Salir
                    JOptionPane.showMessageDialog(null, "Hasta luego.");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        } while (opcion != 4);
    }
}
