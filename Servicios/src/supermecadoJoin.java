import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class supermecadoJoin extends Thread{
	protected static int caja1, caja2, caja3;
	Random random = new Random();
    
	private int id;
	private static Semaphore tienda = new Semaphore(20);
	private static Semaphore c1 = new Semaphore(1);
	private static Semaphore c2 = new Semaphore(1);
	private static Semaphore c3 = new Semaphore(1);
	private static Scanner sc = new Scanner(System.in);
	
	protected supermecadoJoin(int id) {
		this.id = id;
	}
	
	public void run(){
		int nAle=0;
		try {
			tienda.acquire();
			System.out.println("El cliente "+ this.id + " ha entrado a el supermercado");
			Thread.sleep(3000);
			nAle=(int) (Math.random()*3+1);
			switch(nAle) {
				case 1:
					c1.acquire();
					caja1 += random.nextInt(100) + 1;
					break;
				case 2:
					c2.acquire();
					caja2 += random.nextInt(100) + 1;
					break;
				case 3:
					c3.acquire();
					caja3 += random.nextInt(100) + 1;
					break;
			}
			
			System.out.println("El "+ this.id + " ha entrado a la Caja "+nAle);
			
			switch(nAle) {
			case 1:
				c1.release();
			break;
			case 2:
				c2.release();
				break;
			case 3:
				c3.release();
				break;
			}
			System.out.println("El "+ this.id + " ha salido de la Caja "+nAle);
			
			tienda.release();
			
			System.out.println("El "+ this.id + " ha salido del supermercado");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
					
	}//fin del método run

	public static void main(String[] args) throws InterruptedException {
		int clientes=0;
		System.out.println("Cuantos clientes van a entrar en el día de hoy: ");
		clientes=sc.nextInt();
		
		ArrayList<Thread> hilos = new ArrayList<>();

        // Crear y almacenar hilos en el ArrayList
        for (int i = 1; i <= clientes; i++) {
            Thread hilo = new supermecadoJoin(i);
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

		/*for(int i=1; i<=clientes;i++) {
			new supermecadoJoin(i).start();
		}
		for(int i=1; i<=clientes;i++) {
			new supermecadoJoin(i).join();
		}*/
        
		System.out.println("Total de la caja1 es: "+caja1);
		System.out.println("Total de la caja2 es: "+caja2);
		System.out.println("Total de la caja3 es: "+caja3);
		System.out.println("Total de las cajas es: "+(caja1+caja2+caja3));
		
	}
	

}
