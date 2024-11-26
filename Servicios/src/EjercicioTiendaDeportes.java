import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class EjercicioTiendaDeportes extends Thread{

	private static Semaphore TiendaDep = new Semaphore(10);
	private static Semaphore Caja1 = new Semaphore(1);
	private static Semaphore Caja2 = new Semaphore(1);
	private static Semaphore Caja3 = new Semaphore(1);
	private static int sumaTotal=0;
	private String nombre;
	private int dineroG;
	protected static EjercicioTiendaDeportes [] c;
		
	public EjercicioTiendaDeportes(String nombre, int dineroG) {
		this.nombre = nombre;
		this.dineroG = dineroG;
	}

	public void run() {
		int nAle=0;
		try {
			TiendaDep.acquire();
			System.out.println("El "+ this.nombre + " ha entrado a la Tienda de Deportes");
			Thread.sleep(3000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
		
		try {
			nAle=(int) (Math.random()*3+1);
			switch(nAle) {
			
			case 1:
				Caja1.acquire();
				break;
			case 2:
				Caja2.acquire();
				break;
			case 3:
				Caja3.acquire();
				break;
			}
			System.out.println("El "+ this.nombre + " ha entrado a la Caja "+nAle);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		switch(nAle) {
		case 1:
			Caja1.release();
		break;
		case 2:
			Caja2.release();
			break;
		case 3:
			Caja3.release();
			break;
		}
		System.out.println("El "+ this.nombre + " ha salido de la Caja "+nAle);
		TiendaDep.release();
		System.out.println("El "+ this.nombre + " ha salido de la Tienda de Deportes");
		System.out.println("Dinero recaudado por el "+ this.nombre + ": "+ this.dineroG);
	}
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Scanner leer = new Scanner(System.in);
		int nComp;
		int sumaDinero;
				
		System.out.println("Cuantos clientes van a entrar el dia de hoy");
		nComp=leer.nextInt();
		c = new EjercicioTiendaDeportes [nComp+1];
				
		for(int i=1;i<=nComp;i++) {
			sumaDinero=(int)(Math.random()*50+1);
			c[i] = new EjercicioTiendaDeportes("Cliente " +i,sumaDinero);
			sumaTotal=sumaTotal+sumaDinero;
			c[i].start();
		}
		
		System.out.println("Resumen del dia de hoy:");
		
		for(int i=1;i<=nComp;i++) {
			c[i].join();
		}
		
		System.out.println("Dinero recaudado el dia de hoy: "+ sumaTotal);
		
	}

}
