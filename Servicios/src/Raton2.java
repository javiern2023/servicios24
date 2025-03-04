//Programaci�n concurrente o multihilo. Convertimos cada objeto de rat�n en un hilo.

public class Raton2 extends Thread{
	
	String nombre;
	int tiempoAlimentacion;
	
	public Raton2(String nombre, int tiempo){
		this.nombre=nombre;
		this.tiempoAlimentacion=tiempo;
	}
	
	public void run(){
		try{
			System.out.printf("El raton %s ha comenzado a alimentarse%n", nombre);
			Thread.sleep(tiempoAlimentacion * 1000);//pausa la ejecuci�n del hilo. Milisegundos.
			System.out.printf("El raton %s ha terminado de alimentarse%n",nombre);
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Raton2 fievel = new Raton2("Fievel", 4);
		Raton2 jerry = new Raton2("Jerry", 5);
		Raton2 pinky = new Raton2("Pinky", 3);
		Raton2 mickey = new Raton2("Mickey", 6);
		fievel.start();
		jerry.start();
		pinky.start();
		mickey.start();
		//Cada rat�n comienza a comer de inmediato.
	}

}
