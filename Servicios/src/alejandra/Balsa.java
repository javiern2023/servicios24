package alejandra;

public class Balsa extends Thread{
	
	int identificador;
	PasoEstrecho paso;

	Balsa(PasoEstrecho paso, int identificador) {
		this.paso = paso;
		this.identificador = identificador;
	}

	@Override
	public void run() {	
			
			try {
				paso.entrarPorNaufrago(identificador);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
	}
}
