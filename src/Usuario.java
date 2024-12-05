import java.util.LinkedList;
import javax.swing.JOptionPane;

public abstract class Usuario {
	private String nombre;
	private String dni;
	private String contrasena;
	private static LinkedList<Cliente> clientes = new LinkedList<Cliente>();
	private static LinkedList<Admin> admins = new LinkedList<Admin>();

	public Usuario(String nombre, String dni, String contrasena) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		this.contrasena = contrasena;
	}
	public Usuario() {
		
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public static LinkedList<Cliente> getUsuarios() {
		return clientes;
	}
	public static void setUsuarios(LinkedList<Cliente> usuarios) {
		Usuario.clientes = new LinkedList<>();
	}
	
	
	
	public static LinkedList<Admin> getAdmin() {
		return admins;
	}
	public static void setAdmins(LinkedList<Admin> admins) {
		Usuario.admins = admins;
	}
	public void registrarCliente() {
		this.setNombre(validarInfo("Ingrese el nombre del cliente:"));
		this.setDni(validarInfo("Ingrese el DNI del cliente:"));
		this.setContrasena(validarInfo("Ingrese la contraseña del cliente:"));
        Cliente clientee = new Cliente(getNombre(), getDni(), getContrasena(), "", null);
        clientes.add(clientee);
	}
	
	public void registrarAdmin() {
        Admin admin = new Admin(nombre, dni, contrasena, 0);

        admin.setNombre(validarInfo("Ingrese el nombre del admin:"));
        admin.setDni(validarInfo("Ingrese el dni del admin:"));
        admin.setContrasena(validarInfo("Ingrese la contraseña del admin:"));
        admins.add(admin);

	}
	public void login() {
	    String inputDni = validarInfo("Ingrese su DNI:");
	    String inputContrasena = validarInfo("Ingrese su contraseña:");
	    
	    // Buscar en clientes
	    for (Cliente cliente : clientes) {
	        if (cliente.getDni().equals(inputDni) && cliente.getContrasena().equals(inputContrasena)) {
	            JOptionPane.showMessageDialog(null, "Bienvenido, " + cliente.getNombre());
	            
	        }
	    }
	    
	    // Buscar en admins
	    for (Admin admin : admins) {
	        if (admin.getDni().equals(inputDni) && admin.getContrasena().equals(inputContrasena)) {
	            JOptionPane.showMessageDialog(null, "Bienvenido Administrador, " + admin.getNombre());
	          
	        }
	    }
	    
	    // Si no se encuentra
	    JOptionPane.showMessageDialog(null, "Usuario no encontrado o credenciales incorrectas.");
	}
	public static String validarInfo(String info) {
		String nashe1;
        do {
        	nashe1 = JOptionPane.showInputDialog(info);
            if (nashe1 == null || nashe1.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "La entrada no puede estar vacía. Intente de nuevo.");
            }
        } while (nashe1 == null || nashe1.trim().isEmpty());
        return nashe1.trim();
	}
	
	@Override
	public String toString() {
		return "Usuario nombre=" + nombre + "\n dni=" + dni + "\n contrasena=" + contrasena + "\n";
	}
	


}
