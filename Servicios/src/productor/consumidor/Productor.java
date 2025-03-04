
package productor.consumidor;


public class Productor extends Thread{
 
    Monitor mon;
    
    Productor(Monitor mon){
        this.mon=mon;
    }
    public void run(){
        
        while(true){
                //producir elemento
                mon.insertarElemento((int)(Math.random()*100));
        }
    }
}
