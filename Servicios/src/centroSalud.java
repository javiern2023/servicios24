import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class centroSalud extends Thread{

	private static Semaphore centSal = new Semaphore(5);//Numero de hilos que adquieren dicho recurso y se ejecutan concurrentemente. 10 pacientes esperando
	protected int identificador=0;
	private static Scanner sc = new Scanner(System.in);
	
	public centroSalud (int identificador) {
		this.identificador=identificador;
	}
	
	public void run(){
				try {
					centSal.acquire();//Hilo adquiere el semáforo o testigo
						System.out.println("El paciente "+ this.identificador + " entra en el centro de salud");
						Thread.sleep((long)(Math.random()*2000) + 100);//hilo dormido
					centSal.release();//Paciente sale del centro de salud
					
					System.out.println("El paciente "+ this.identificador + " sale del centro de salud");
				} catch (InterruptedException ex) {System.out.println(ex.getMessage());}			
		}//fin del método run
	
	public static void main(String[] args) {
		int pacientes=0;
		System.out.println("Cuantos pacientes tienen cita en el día de hoy: ");
		pacientes=sc.nextInt();
		for(int i=1; i<=pacientes;i++) {
			new centroSalud(i).start();
		}
	}

}
