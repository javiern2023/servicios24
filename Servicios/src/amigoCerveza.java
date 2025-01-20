import java.text.CharacterIterator;

public class amigoCerveza extends Thread{
	private String nombre;
	private int botellines;
	private int litros=0;
	
	public amigoCerveza (String nom, int bot) {
		this.nombre=nom;
		this.botellines=bot;
	}
	
	public void run() {
		try {
			//Retardo aleatorio entre 1 segundo y 5 segundos
			Thread.sleep((long)(Math.random()*5000) + 100);
			for(int i=1;i<=this.botellines;i++) {
				this.litros+=1;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(this.nombre+" ha bebido "+this.litros+" litros");
	}
	
	public static void main(String[] args) throws InterruptedException {
		int totalLitros;
		amigoCerveza a1 = new amigoCerveza("Juan",2);
		amigoCerveza a2 = new amigoCerveza("Marco",5);
		amigoCerveza a3 = new amigoCerveza("Miguel",1);
		amigoCerveza a4 = new amigoCerveza("Sebastian",3);
		a1.start(); //Arrancar el proceso. Llamada al método run()
		a2.start();
		a3.start();
		a4.start();
		a1.join(); //Espera a que terminen todos los hilos en ejecución
		a2.join();
		a3.join();
		a4.join();
		totalLitros = (int) (a1.getLitros()+a2.getLitros()+a3.getLitros()+a4.getLitros());
		System.out.print("El numero total de litros es: "+totalLitros);
	}

	protected String getNombre() {
		return nombre;
	}

	protected void setNombre(String nombre) {
		this.nombre = nombre;
	}

	protected int getBotellines() {
		return botellines;
	}

	protected void setBotellines(int botellines) {
		this.botellines = botellines;
	}

	protected long getLitros() {
		return litros;
	}

	protected void setLitros(int litros) {
		this.litros = litros;
	}

}
