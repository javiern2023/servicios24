import java.util.Random;

public class Raton4 extends Thread{

	private String nombre;
	private static int tiempoAlimentacion=0;
	// Crear un objeto de la clase Random
    Random random = new Random();
    
	public Raton4(String nombre){
		this.nombre=nombre;		
	}
	
	public void comer(){
		try{
			System.out.println("El raton "+nombre+" ha comenzado a alimentarse");
						
	        // Generar un número aleatorio entre 10 y 100 (inclusive)
	        int randomMilisegundos = 10 + random.nextInt(91); // nextInt(91) genera un número entre 0 y 90
			Thread.sleep(randomMilisegundos * 100);//pausa la ejecuci�n del hilo. Milisegundos.
	        tiempoAlimentacion+=randomMilisegundos;
			
	        System.out.println("El raton "+nombre+" ha terminado de alimentarse y ha tardado "+randomMilisegundos);
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void run(){//M�todo de ejecuci�n
		this.comer();
	}
	
	public static void main(String[] args) {
		Raton4 fievel = new Raton4("Fievel");
		Raton4 jerry = new Raton4("Jerry");
		Raton4 pinky = new Raton4("Pinky");
		Raton4 mickey = new Raton4("Mickey");
		fievel.start();
		jerry.start();
		pinky.start();
		mickey.start();
		//Cada rat�n comienza a comer de inmediato.
		
		try {
			fievel.join();
			jerry.join();
			pinky.join();
			mickey.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("Tiempo empleado por todos los ratones en comer es: "+tiempoAlimentacion);

	}

}
