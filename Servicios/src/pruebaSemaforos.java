import java.util.concurrent.Semaphore;

public class pruebaSemaforos extends Thread{
	private int contador=0;
	private static Semaphore semaforo = new Semaphore(3);
	
	public pruebaSemaforos(int contador) {
		this.contador = contador;
	}
	
	public void run() {
		try {
			semaforo.acquire();
			contador++;
			System.out.println("Contador es: "+contador);
			semaforo.release();//Libera el semáforo
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		pruebaSemaforos ej1 = new pruebaSemaforos(0);
		ej1.start();
		new pruebaSemaforos(1).start();
		new pruebaSemaforos(5).start();
		new pruebaSemaforos(1).start();
		new pruebaSemaforos(5).start();
		new pruebaSemaforos(1).start();
		new pruebaSemaforos(5).start();
		new pruebaSemaforos(1).start();
		new pruebaSemaforos(5).start();
		new pruebaSemaforos(1).start();
		new pruebaSemaforos(5).start();
		new pruebaSemaforos(1).start();
		new pruebaSemaforos(5).start();
		

	}

}
