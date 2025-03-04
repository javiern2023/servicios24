
public class Raton3 implements Runnable{
	private String nombre;
	private int tiempoAlimentacion;
	
	public Raton3(String nombre, int tiempo){
		this.nombre=nombre;
		this.tiempoAlimentacion=tiempo;
	}
	
	public void comer(){
		try{
			System.out.printf("El raton %s ha comenzado a alimentarse%n", nombre);
			Thread.sleep(tiempoAlimentacion * 1000);//pausa la ejecuci�n del hilo. Milisegundos.
			System.out.printf("El raton %s ha terminado de alimentarse%n",nombre);
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void run(){//M�todo de ejecuci�n
		this.comer();
	}
	
	public static void main(String[] args) {
		Raton3 fievel = new Raton3("Fievel", 4);
		Raton3 jerry = new Raton3("Jerry", 5);
		Raton3 pinky = new Raton3("Pinky", 3);
		Raton3 mickey = new Raton3("Mickey", 6);
		new Thread(fievel).start();
		new Thread(jerry).start();
		new Thread(pinky).start();
		new Thread(mickey).start();
		//Cada rat�n comienza a comer de inmediato.
	}

}
