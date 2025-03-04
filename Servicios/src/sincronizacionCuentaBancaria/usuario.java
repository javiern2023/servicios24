package sincronizacionCuentaBancaria;

public class usuario extends Thread{
	protected String dni, nombre;
	protected int cantidad;
	protected cuenta c;
	
	protected usuario(String dni, String nombre, cuenta c) {
		this.dni = dni;
		this.nombre = nombre;
		this.c = c;
	}
	protected String getDni() {
		return dni;
	}
	protected void setDni(String dni) {
		this.dni = dni;
	}
	protected String getNombre() {
		return nombre;
	}
	protected void setNombre(String nombre) {
		this.nombre = nombre;
	}
	protected cuenta getC() {
		return c;
	}
	protected void setC(cuenta c) {
		this.c = c;
	}
	
	protected synchronized void ingresar(cuenta c, double cantidad) {
		c.setSaldo(c.getSaldo()+cantidad);
	}
	
	protected void retirar(cuenta c, double cantidad) {
		c.setSaldo(c.getSaldo()-cantidad);
	}
	
	public void run() {
		ingresar(c,cantidad);
	}
}
