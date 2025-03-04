package ireneAlonso;

import java.util.ArrayList;

public class Main {
	
	
	//Main
	public static void main(String[] args) throws InterruptedException{
		
		//Tengo una isla con un número x de náufragos que le paso al constructor del objeto "isla"
		Isla isla = new Isla(49);
		
		//Y Tres balsas que son las que van a rescatar a los náufragos
		int numRescatados=4; //número de personas que serán rescatadas en cada balsa
		
		
		//Mientras que tenga náufragos en la isla -> creo las balsas y voy a rescatarlos		
		ArrayList<Balsa> balsas = makeBalsas(numRescatados, isla);
		
		//Hago el rescate		
		makeRescate(balsas);
		
				
		
	}
		
	
	
	//Método para crear las balsas
	public static ArrayList<Balsa> makeBalsas(int numRescatados, Isla isla) {
		
		ArrayList <Balsa> aux = new ArrayList();
				
		for(int i=0; i<3; i++) {
			
			aux.add(new Balsa(i+1, numRescatados, isla));
		}
		
		return aux;
	}
	
	
	//Método para hacer el rescate
	public static void makeRescate(ArrayList<Balsa> balsas) throws InterruptedException{		
		
		//Voy a por los náufragos
		for(int i=0; i<3; i++) {
			
			balsas.get(i).start();				
			
		}	
		
		
		
		
	}

		
		
		

}
