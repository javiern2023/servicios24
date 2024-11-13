package Ejercicio2;
public class Monitor {
        
    private static int nivel; //Nivel del deposito
    boolean vaciando; //False si esta llenando, True si esta vaciando.
    int nivel_maximo=1000; // Batería llena
    int nivel_minimo=0; // Batería vacía
    
    public Monitor(){
        nivel=nivel_minimo; // Se vacia el deposito
        vaciando=false; // Se comienza llenando el deposito
    }
    
    public synchronized void insertarElemento() {
      if (!vaciando) { //Mientras se esta llenando el deposito
         nivel=nivel+10; // Se van añadiendo de 10 en 10 litros
      	 System.out.println("Añadiendo 10 % carga, Nivel: "+nivel);
         if (nivel>=nivel_maximo) { // Si se llega al nivel maximo
              try{
                	System.out.println("Se para la recarga ");
                 	vaciando=true; // Se invierte el sentido y se comienza a vaciar rapidamente
                    wait(); // Se detiene la recarga
              }catch(Exception e){}
          }
          else if (nivel==900) {// Si se llega al nivel 900
             notify(); // Se despierta al proceso de descarga
          }
         }
         else if (nivel<=100) { // Si se esta descargado y se llega al 100% o menos
           	nivel=nivel+5; // Se comienza a cargar lentamente.
        	System.out.println("Añadiendo 5 % carga, Nivel: "+nivel);
        }
        
    }
    
    public synchronized void extraerElemento() {
        if (vaciando) {  //Mientras se esta vaciando el deposito
        	nivel=nivel-10; // Se van extrayendo de 10 en 10 litros
        	System.out.println("Descargado 10 % carga, Nivel: "+nivel);
        	if (nivel<=nivel_minimo) { // Si se llega al nivel minimo
        		try{
                	System.out.println("Se para la descarga ");
                 	vaciando=false; // Se invierte el sentido y se comienza a llenar rapidamente
                    wait(); // Se detiene el vaciado
              }catch(Exception e){}
          }
          else if (nivel==100) { // Si se llega al nivel 100
              notify(); // Se despierta al proceso de llenado
          }
        }
      else if (nivel>=900) { // Si se esta cargando y se llega al nivel 900% o mas
        	nivel=nivel-5; // Se comienza a descargar lentamente.
        	System.out.println("Descargado 5 % batería, Nivel: "+nivel);
      } 
    }
    
}
