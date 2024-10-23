import java.util.concurrent.Semaphore;
public class ejemploSemaforo extends Thread{

	private static int contador = 0;//Todos los hilos tienen acceso a esa variable desde fuera del objeto
	private static Semaphore semaforo = new Semaphore(1);//Le damos valor inicial al contador interno del semáforo
	
	public ejemploSemaforo() { //No sería necesario crear el constructor porque no tiene propiedades
		
	}
	
	public void run() {
		for (int i = 1; i <= 10; i++) {
			try {
				semaforo.acquire();//Adquiere el control del semáforo
				System.out.println("Hilo numero: "+this.getId()+" "+contador++);
				semaforo.release();//Libera el semáforo
			} catch (InterruptedException ex) {System.out.println(ex.getMessage());}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		ejemploSemaforo ej1 = new ejemploSemaforo();
		ej1.start();
		new ejemploSemaforo().start();
		new ejemploSemaforo().start();
		new ejemploSemaforo().start();
	}
}
