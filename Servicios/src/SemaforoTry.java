
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class SemaforoTry extends Thread{
    private Semaphore semaphore=new Semaphore(4);
    private static Scanner sc = new Scanner(System.in);
    private int id;
    
    public SemaforoTry(int id) {
    	this.id = id;
    }

	public void run() {
	     if (semaphore.tryAcquire()) {
	          try {
	              System.out.println("El cliente "+ this.id + " ha entrado a la tienda.");
	              // Simular el tiempo que pasa comprando
	              Thread.sleep(1000);
	              System.out.println("El cliente "+ this.id + " ha salido de la tienda.");
	          } catch (InterruptedException e) {
	              Thread.currentThread().interrupt();
	          } finally {
	              semaphore.release();
	          }
	     } 
	     else {
	            System.out.println("El cliente "+ this.id + " no pudo entrar a la tienda, está llena.");
	     }
	 }

	 public static void main(String[] args) {
		int clientes=0;
		System.out.println("Cuantos clientes van a entrar en el día de hoy: ");
		clientes=sc.nextInt();
			
		ArrayList<Thread> hilos = new ArrayList<>();

	    // Crear y almacenar hilos en el ArrayList
	    for (int i = 1; i <= clientes; i++) {
	        Thread hilo = new SemaforoTry(i);
	        hilos.add(hilo);
	        hilo.start(); // Iniciar el hilo
	    }

	    // Opcional: Esperar a que todos los hilos terminen
	    for (Thread hilo : hilos) {
	        try {
	            hilo.join();
	        } catch (InterruptedException e) {
	            System.out.println("Error al esperar al hilo.");
	        }
	    }
		 
		 
	 }
}


