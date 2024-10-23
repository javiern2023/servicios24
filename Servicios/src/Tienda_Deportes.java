//package PSP;

import java.util.concurrent.Semaphore;

public class Tienda_Deportes extends Thread{
	
	private String cliente;
	private int caja;
	private double paga;
	private static double total=0;
	private static Semaphore tienda = new Semaphore(10);
	private static Semaphore caja1 = new Semaphore(1);
	private static Semaphore caja2 = new Semaphore(1);
	private static Semaphore caja3 = new Semaphore(1);

	public Tienda_Deportes(String cliente, double paga) {
		this.cliente = cliente;
		this.paga = paga;
		
	}
	
	public String getCliente() {
		return cliente;
		
	}
	
	public double getPaga() {
		return paga;
		
	}
	
	public static int getRandomNumber(int min, int max) {
		return (int)(Math.random() * 3) + 1;
		
	}
	
	public void run() {
		try {
			tienda.acquire();
			System.out.println(this.cliente + " ha entrado a la tienda");
			Thread.sleep(1000);
			caja=getRandomNumber(1, 3);
			switch(caja) {
				case 1:
					caja1.acquire();
					System.out.println(this.cliente + " esta siendo atendido en la caja 1");
					Thread.sleep(1000);
					System.out.println(this.getCliente()+" ha pagado "+this.getPaga());
					total= total + this.getPaga();
					caja1.release();
					System.out.println(this.cliente + " ha dejado la caja 1");
					Thread.sleep(1000);

				break;
				case 2:
					caja2.acquire();
					System.out.println(this.cliente + " esta siendo atendido en la caja 2");
					Thread.sleep(1000);
					System.out.println(this.getCliente()+" ha pagado "+this.getPaga());
					total= total + this.getPaga();
					caja2.release();
					System.out.println(this.cliente + " ha dejado la caja 2");
					Thread.sleep(1000);
				break;
				case 3:
					caja3.acquire();
					System.out.println(this.cliente + " esta siendo atendido en la caja 3");
					Thread.sleep(1000);
					System.out.println(this.getCliente()+" ha pagado "+this.getPaga());
					total= total + this.getPaga();
					caja3.release();
					System.out.println(this.cliente + " ha dejado la caja 3");
					Thread.sleep(1000);
				break;
			
			}
			tienda.release();
			System.out.println(this.cliente + " ha salido de la tienda");
			Thread.sleep(1000);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Se han ganado "+total+" euros");				
	}
	
	public static void main(String[] args) {
		Tienda_Deportes c1 = new Tienda_Deportes("Pedro", 5);
		Tienda_Deportes c2 = new Tienda_Deportes("Juan", 10);
		Tienda_Deportes c3 = new Tienda_Deportes("Marco", 32);
		Tienda_Deportes c4 = new Tienda_Deportes("Cristoph", 50);
		Tienda_Deportes c5 = new Tienda_Deportes("Goku", 200);
		Tienda_Deportes c6 = new Tienda_Deportes("Vegetta", 11);
		Tienda_Deportes c7 = new Tienda_Deportes("David", 9);
		Tienda_Deportes c8 = new Tienda_Deportes("Perro", 8);
		Tienda_Deportes c9 = new Tienda_Deportes("Furro", 28);
		Tienda_Deportes c10 = new Tienda_Deportes("Piedra", 26);
		Tienda_Deportes c11 = new Tienda_Deportes("Lucia", 15);
		Tienda_Deportes c12 = new Tienda_Deportes("No Lucia", 32);
		Tienda_Deportes c13 = new Tienda_Deportes("Sara", 25);
		Tienda_Deportes c14 = new Tienda_Deportes("Windows", 29);
		Tienda_Deportes c15 = new Tienda_Deportes("Virus", 99);
		
		c1.start();
		c2.start();
		c3.start();
		c4.start();
		c5.start();
		c6.start();
		c7.start();
		c8.start();
		c9.start();
		c10.start();
		c11.start();
		c12.start();
		c13.start();
		c14.start();
		c15.start();
		
	}

}
