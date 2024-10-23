import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Robots {
	 static int tam = 10;
	 static int [] cadena = new int [tam];
	 
	 static Semaphore coloco=new Semaphore(tam, true);;
	 static Semaphore [] puedoemp =  {new Semaphore(0), new Semaphore(0), new Semaphore(0)};
	 static int nemp = 0; 
	 static Semaphore mutex = new Semaphore(1, true);
	 static Random r = new Random();
	 
	 public static class Colocador extends Thread{
	  public void run(){
	   int i, n;
	   while(true){
	    try {
	     n = r.nextInt(3)+1;
	     System.out.println("Produciendo tipo "+ n);
	     sleep(r.nextInt(1000));
	     coloco.acquire();
	     i=0;
	     while(cadena[i] != 0) i++;
	     //cuando encuentra un sitio vacío, sale del bucle y puede colocar
	     cadena[i]  = n;
	     System.out.println("Coloco un producto "+ n + " en la posición "+ i +"\n"+ Arrays.toString(cadena));
	     puedoemp[n-1].release();

	    } catch (InterruptedException e) {e.printStackTrace(); }
	   }
	  }
	 }
	 
	 public static class Empaquetador extends Thread {
	  private int tipo;
	 
	  public Empaquetador (int tipo){
	   this.tipo = tipo;
	 
	  }
	  public void run(){
	   int i;
	   while(true){
	   try {
	    puedoemp[tipo-1].acquire();
	    
	     i =0;
	    while(cadena[i] != tipo) i++;
	    sleep(r.nextInt(1000));
	    System.out.println("Robot de tipo "+ tipo + " empaquetando el producto de la posición "+ i);
	    cadena[i] = 0; //dejo el hueco
	    coloco.release();
	    sleep(2000);
	    mutex.acquire();  //aumentar nemp
	    nemp++;
	    
	    System.out.println("Aumenta el número de empaquetados: "+ nemp);
	    
	    mutex.release(); // 
	    
	    
	   } catch (InterruptedException e) {e.printStackTrace();}
	   }
	  }
	 }
	 public static void main(String[] args){
	  System.out.println("El tamaño de la cadena será de " + tam);
	  Empaquetador  empaquetador1 = new Empaquetador(1);
	  Empaquetador  empaquetador2 = new Empaquetador(2);
	  Empaquetador  empaquetador3 = new Empaquetador(3);
	  Colocador colocador = new Colocador();
	  colocador.start();
	  empaquetador1.start();
	  empaquetador2.start();
	  empaquetador3.start();
	  
	 }
}
