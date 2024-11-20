package alejandra;

public class PasoEstrecho {
	public static int naufragos = 10;
	private boolean rescatando;
	private int contadorBalsa1;
	private int contadorBalsa2;
	private int contadorBalsa3;	

	public PasoEstrecho() {
		rescatando = false;
	}

	public synchronized void entrarPorNaufrago(int num) throws InterruptedException{
		while(naufragos>0) {		
			if (!rescatando) {
				rescatando=true;
				System.out.println("La balsa numero " + num + " esta esperando a poder acceder");
			} else {
					System.out.println("La balsa numero " + num + " ha accedido a la zona de rescate");				
					salirConNaufrago(num);
			}
		}
		if(num==1) {
			System.out.println("Rescate finalizado para la balsa numero " + num + " ha rescatado un total de " + contadorBalsa1 + " naufragos");
		}
		if(num==2) {
			System.out.println("Rescate finalizado para la balsa numero " + num + " ha rescatado un total de " + contadorBalsa2 + " naufragos");
		}
		if(num==3) {
			System.out.println("Rescate finalizado para la balsa numero " + num + " ha rescatado un total de " + contadorBalsa3 + " naufragos");
		}
	}

	public synchronized void salirConNaufrago(int num) throws InterruptedException{
		System.out.println("La balsa numero "+ num + " se lleva a uno de los naufragos al barco de rescate");
		if(num==1) {			
			contadorBalsa1++;
		}if(num==2) {			
			contadorBalsa2++;			
		}if(num==3) {			
			contadorBalsa3++;
		}
		naufragos--;
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		 
		System.out.println("Quedan:" + naufragos + " naufragos por ser rescatados");
		rescatando = false; 
		notify();
		//Si no pones esa condición el último hilo se queda parado.
		if (naufragos!=0) {
			wait();
		}
		
				
	}
}
