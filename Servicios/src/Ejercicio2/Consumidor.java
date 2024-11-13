package Ejercicio2;
public class Consumidor extends Thread {
    
    Monitor mon;
    
    Consumidor(Monitor mon){
        this.mon=mon;
    }
    public void run(){
        while(true){
        	try {
        		Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            mon.extraerElemento();
        }
    }
}
