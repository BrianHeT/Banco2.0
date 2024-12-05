import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {

        // Crear instancias de clientes y administrador
        Cliente cliente1 = new Cliente();
        Cliente cliente2 = new Cliente();
        Admin admin = new Admin(null, null, null, 0);

        // Asignar cuenta a cliente1
        cliente1.setCuenta(new Cuenta(0));

        // Opciones del menú
        String[] opciones = {
            "Registrar cliente", 
            "Registrar administrador", 
            "Iniciar sesión cliente", 
            "Iniciar sesión administrador",  
            "Transacción", 
            "Movimientos", 
            "Salir"
        };

        int opcion = 0;

        do {
            // Mostrar el menú
            opcion = JOptionPane.showOptionDialog(null, "Menú", "", 0, 0, null, opciones, opciones[0]);
            switch (opcion) {
                case 0:
                    // Registrar cliente
                    cliente1.registrarCliente();
                    break;

                case 1:
                    // Registrar administrador
                    admin.registrarAdmin();
                    break;

                case 2:
                    // Iniciar sesión cliente
                    cliente1.login();
                    break;

                case 3:
                    // Iniciar sesión administrador
                    admin.login();
                    break;

                case 4:
                    // Realizar transacción
                    cliente2.setNombre("yo"); 
                    cliente1.Opciones(cliente2); 
                    break;

                case 5:
                    
                    admin.verMovimientos(cliente1);
                    break;

                case 6:
                    // Salir
                    JOptionPane.showMessageDialog(null, "Hasta luego");
                    break;
            }
        } while (opcion != 6);
    }
}
