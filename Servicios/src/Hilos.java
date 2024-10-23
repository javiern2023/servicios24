
public class Hilos extends Thread{
	String letra="";
	String cadena="";
	int contador=0;
	
	public Hilos (String Text, String busca){
		this.letra=busca;
		this.cadena=Text;
	}
	
	public void run(){

		System.out.println("Nombre inicial: " + this);
		try {
			//Retardo aleatorio entre 1 segundo y 5 segundos
			Thread.sleep((long)(Math.random()*5000) + 100);
			//Thread.sleep(2000);
			if(cadena.equals(this.letra)) {
				contador++;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setName(this.letra);
		System.out.println("Una vez cambiado el nombre: " + this);
	}

	public static void main (String[] args){
		String Texto="opqjwehprkjnldkfjnpqiowejrl�qknweopuiqwejanklma�lkabklsdjfopiweurpeowh�ajsdfnpiog";
		Hilos Primero =new Hilos(Texto,"a");
		Hilos Segundo =new Hilos(Texto,"b");
		Primero.start();
		Segundo.start();
	}

}
