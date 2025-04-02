import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class amigoCervezaSemaforo extends Thread{
	private String nombre;
	private int botellines=0;
	private static int litros=0;
	private static boolean seguir = true;
	private static Semaphore barril = new Semaphore(2);
	private static Scanner sc = new Scanner(System.in);
	
	public amigoCervezaSemaforo(String nombre) {
		this.nombre = nombre;
	}
	
	public void run() {
		while(seguir) {
			try {
				Thread.sleep((long)(Math.random()*10000) + 100);
				barril.acquire();
					/*System.out.print("Cuantos botellines quieres: ");
					botellines=sc.nextInt();*/
					litros++;
				barril.release();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(this.nombre+" ha pedido");
		}
		
	}

	public static void main(String[] args) {
		int amigos=0;
		String nombre;
		System.out.println("Cuantos amigos venís a beber hoy: ");
		amigos=sc.nextInt();
		for(int i=1; i<=amigos;i++) {
			System.out.println("Nombre del amigo: ");
			nombre=sc.next();
			new amigoCervezaSemaforo(nombre).start();
		}

	}

}
