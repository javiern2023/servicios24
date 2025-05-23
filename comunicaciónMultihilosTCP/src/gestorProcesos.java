//package comunicacionMultihilosTCP;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class gestorProcesos extends Thread{
	private Socket socket;
	private OutputStream os;
	
	public gestorProcesos(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		try {
			realizarProceso();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void realizarProceso() throws IOException {
		os = this.socket.getOutputStream();
		Random generadorNumerosAleatorios = new Random();
		int tiempoEspera = generadorNumerosAleatorios.nextInt(60);
		try {
			TimeUnit.SECONDS.sleep(tiempoEspera);
			os.write(tiempoEspera);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			os.close();
		}
	}
}
