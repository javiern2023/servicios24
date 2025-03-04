
package productor.consumidor;


public class Monitor {
    
    private int elemento;
    private boolean hayElemento;
    
    Monitor(){
        elemento=0;
        hayElemento=false;
    }
    
    public synchronized void insertarElemento(int elemento){
        while(hayElemento){
            try{
                 wait();
            }catch(Exception e){}
        }
        this.elemento=elemento;
        System.out.println("insertando elemento: "+elemento);
        hayElemento=true;
        notify();// Despertar el siguiente hilo
    }
    
    public synchronized int extraerElemento(){
        while(!hayElemento){
            try{
                 wait();
            }catch(Exception e){}
           
        }
        hayElemento=false;
        notify();
        return elemento;
    }
    
}
