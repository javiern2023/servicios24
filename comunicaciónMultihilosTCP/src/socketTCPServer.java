//package comunicacionMultihilosTCP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class socketTCPServer {
	private ServerSocket serverSocket;
	
	public socketTCPServer (int puerto) throws IOException {
		serverSocket = new ServerSocket(puerto);
		while(true) {
			Socket socket = serverSocket.accept();
			System.out.println("(Servidor) Conexión establecida...");
			new gestorProcesos(socket).start();
		}
	}
	
	public void stop() throws IOException {
		serverSocket.close();
	}
	
	public static void main(String[] args) {
		try {
			socketTCPServer servidor = new socketTCPServer(49171);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
