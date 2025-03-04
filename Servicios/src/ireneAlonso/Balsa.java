package ireneAlonso;

public class Balsa extends Thread{
	
	private int numBalsa=0;
	private int capacidad=0; //4 náufragos por balsa
	private Isla isla;
	
	
	//Constructor
	public Balsa(int numBalsa, int numRescatados, Isla isla) {
		
		this.numBalsa=numBalsa;
		this.capacidad=numRescatados;
		this.isla=isla;
		
	}
	
	
	//Getters
	public int getNum() {
		
		return this.numBalsa;
	}
	
	public int getNaufragos() {
		
		return this.isla.getNaufragos();
	}
	
	
	public int getCapacidad() {
		
		return this.capacidad;
	}
	
	
	//Setters
	public void setCapacidad(int capacidad) {
		
		this.capacidad=capacidad;
	}
	
	
	
	//Run
	public void run() {			
		
		
		try {
			this.isla.makeRescate(this);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
				
	}




}
