
import java.util.concurrent.Semaphore;

public class Semaforos extends Thread {

	private static int contador = 0;
	private static int contador_interno = 0;
	private static Semaphore semaforo = new Semaphore(2);
	private static Semaphore semaforo2 = new Semaphore(1);

	int identificador=0;

	public Semaforos(int identificador) {
		this.identificador = identificador;
	}
	
	public void run() {
		for (int i = 1; i <= 10; i++) {
			try {
				semaforo.acquire();//Hilo adquiera el semáforo
				Thread.sleep((long)(Math.random()*2000) + 100);//hilo dormido
				System.out.println("El semaforo "+ this.identificador + " modifica el contador a " + contador++);
				
					semaforo2.acquire();
					Thread.sleep((long)(Math.random()*1000) + 100);
					System.out.println("El semaforo "+ this.identificador + " modifica el contadorinterno a " + contador_interno++);
					semaforo2.release();
				
				semaforo.release();// Hilo suelta el semáforo
			} catch (InterruptedException ex) {System.out.println(ex.getMessage());}
		}//fin del bucle for
	}//fin del método run
	
	public static void main(String[] args) {
		new Semaforos(1).start();
		new Semaforos(2).start();
		new Semaforos(3).start();
		new Semaforos(4).start();
		new Semaforos(5).start();
		new Semaforos(6).start();
		new Semaforos(7).start();
		new Semaforos(8).start();
		new Semaforos(9).start();
		new Semaforos(10).start();
		new Semaforos(11).start();
		new Semaforos(12).start();
	}
}
