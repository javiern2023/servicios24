package ireneAlonso;

public class Isla {
	
	private int totalNaufragos=0;
	private int numNaufragos=0;
	private boolean balsaEnLaIsla=false;
	
	
	
	//Constructor
	public Isla(int numNaufragos) {		
		
		this.totalNaufragos=numNaufragos;
		this.numNaufragos=numNaufragos;
	}

	//Getters
	public int getNaufragos() {
		
		return this.numNaufragos;
	}		
	
	
	//Método para hacer el rescate
	public synchronized void makeRescate(Balsa balsa) throws InterruptedException {		
			
		while(this.numNaufragos>0) {
			
			if(!this.balsaEnLaIsla) {
				
				this.balsaEnLaIsla=true; //la balsa está en la isla rescatando
				
				int numRescatados=(this.numNaufragos>balsa.getCapacidad()) ? balsa.getCapacidad() : this.numNaufragos;
				
				balsa.setCapacidad(numRescatados); //meto a los rescatados en la balsa
				
				this.numNaufragos=this.numNaufragos-balsa.getCapacidad(); //los quito del conteo de gente que queda en la isla
				
				System.out.println("La balsa "+balsa.getNum()+" rescata a "+numRescatados+" -> quedan "+this.numNaufragos);
				
				this.makeVaciarBalsa(balsa); //me voy a vaciar la balsa
				
			
				
			}				
			
		}			
		
	}
	
	
	//Método para volver con los rescatados
	public synchronized void makeVaciarBalsa(Balsa balsa) throws InterruptedException {	
		
		//System.out.println("hilo ->"+Thread.currentThread().getId());
		
		notify(); //la balsa llega al puerto -> despierto el siguiente hilo
			
		this.balsaEnLaIsla=false;
		
		System.out.println("Los náufragos llegaron a tierra -> balsa "+balsa.getNum()+"\n");	
		
		if(balsa.getCapacidad()>0) {
				
			wait();	// -> arranco el siguiente
			
		}	
		
	}
	
	
}
