package comunicacionSocketsTCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class socketTCPCliente {
	private String serverIP;
	private int serverPort;
	private Socket socket;
	private InputStream in;
	private OutputStream ou;
	
	public socketTCPCliente(String serverIP, int serverPort) {
		this.serverIP = serverIP;
		this.serverPort = serverPort;
	}
	
	public void start () throws UnknownHostException, IOException {
		System.out.println( "(Cliente) Estableciendo conexión...");
		socket = new Socket();
		InetSocketAddress addr = new InetSocketAddress("localhost",49171);
		socket.connect(addr);
		ou = socket.getOutputStream();
		in = socket.getInputStream();
		System.out.println("(Cliente) Conexión establecida...");
	}
	
	public void stop () throws IOException {
		System.out.println( "(Cliente) Cerrando conexiones...");
		in.close();
		ou.close();
		socket.close();
		System.out.println( "(Cliente) Conexiones cerradas...");
	}
	
	public static void main(String[] args) {
		/*Socket con la dirección IP y el puerto del servidor. Se puede usar un
		 * nombre o una dirección IP para identificar al servidor. Si se usa servidor
		 * y cliente en la misma máquina podrá usarse localhost como nombre de host.*/
		socketTCPCliente cliente = new socketTCPCliente("localhost", 49171);
		
		try {
			cliente.start();
			cliente.ou.write(100);
			System.out.println("Mensaje del servidor: "+cliente.in.read());
			cliente.stop();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	

}
