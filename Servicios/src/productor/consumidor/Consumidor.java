
package productor.consumidor;


public class Consumidor extends Thread {
    
    Monitor mon;
    
    Consumidor(Monitor mon){
        this.mon=mon;
    }
    
    public void run(){
        while(true){
            System.out.println("Consumiendo elemento: "+mon.extraerElemento());
        }
    }
}
