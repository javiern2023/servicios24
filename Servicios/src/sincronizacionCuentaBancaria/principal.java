package sincronizacionCuentaBancaria;

/* Realiza una aplicación para que los alumnos de 2º de DAM puedan ingresar 
 * y retirar dinero para las gestiones de la clase.
   No se podrán solapar las tareas en el tiempo.
 * */
public class principal {
	
	
	public static void main(String[] args) {
		cuenta c1 = new cuenta("asert",45);
		
		usuario u1 = new usuario("123","Javier",c1);
		usuario u2 = new usuario("456","Maria",c1);
		usuario u3 = new usuario("789","Alfredo",c1);
		
		u1.start();
		u2.start();
		u3.start();
				

	}

}
