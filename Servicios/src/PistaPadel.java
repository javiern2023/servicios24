//package Package;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class PistaPadel extends Thread{
	private static Semaphore PistaPadel = new Semaphore(200);
	private static Semaphore pista1 = new Semaphore(1);
	private static Semaphore pista2 = new Semaphore(1);
	private static Semaphore pista3 = new Semaphore(1);
	private static Semaphore pista4 = new Semaphore(1);
	static int jugadores;
	static int eleccion;
	static int pago=20;

	static int totalpago1=0;
	static int totalpago2=0;
	static int totalpago3=0;
	static int totalpago4=0;
	public static int sumapagos=0;
	int i;

	protected PistaPadel(int i, int eleccion) {
		this.i=i;
		this.eleccion=eleccion;
	}

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);

		System.out.println("¿cuantas grupos van a jugar al padel?");
		jugadores= sc.nextInt();
		for (int i=0;i<jugadores;i++) {
			eleccion =  (int) (Math.random() * 4 + 1);
			new PistaPadel(i,eleccion).start();
		}



	}
	public void run() {

		try {
			PistaPadel.acquire();
			System.out.println("el grupo "+this.i+ " ha entrado al polideportivo");
			if(eleccion==1) {
				pista1.acquire();
				System.out.println("el grupo "+this.i+ " ha entrado a la pista "+eleccion);
				totalpago1+=pago;
				System.out.println("El grupo "+this.i+ "ha pagado 20 euros");
				System.out.println("Total de ganancias de la pista 1: "+totalpago1);
				pista1.release();
				System.out.println("el grupo "+this.i+" ha salido de la pista 1");
			}
			if(eleccion==2) {
				pista2.acquire();
				System.out.println("el grupo "+this.i+ " ha entrado a la pista "+eleccion);
				totalpago2+=pago;
				System.out.println("El grupo "+this.i+ "ha pagado 20 euros");
				System.out.println("Total de ganancias de la pista 1: "+totalpago2);				
				pista2.release();
				System.out.println("el grupo "+this.i+" ha salido de la pista 2");
			}
			if(eleccion==3) {
				pista3.acquire();
				System.out.println("el grupo "+this.i+ " ha entrado a la pista "+eleccion);
				totalpago3+=pago;
				System.out.println("El grupo "+this.i+ "ha pagado 20 euros");
				System.out.println("Total de ganancias de la pista 1: "+totalpago3);	

				pista3.release();
				System.out.println("el grupo "+this.i+" ha salido de la pista 3");
			}
			if(eleccion==4) {
				pista4.acquire();
				System.out.println("el grupo "+this.i+ " ha entrado a la pista "+eleccion);
				totalpago4+=pago;
				System.out.println("El grupo "+this.i+ " ha pagado 20 euros");
				System.out.println("Total de ganancias de la pista 4: "+totalpago4);	
				pista4.release();
				System.out.println("el grupo "+this.i+" ha salido de la pista 4");
			}
			PistaPadel.release();
			System.out.println("el grupo "+this.i+"ha salido del polideportivo");

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		sumapagos=totalpago1+totalpago2+totalpago3+totalpago4;
		if (Thread.activeCount() == 2)
			System.out.println("BENEFICIO: "+sumapagos);

	}




}
