package comunicacionSocketsTCP;
/*Ejemplo para el intercambio básico de información: el cliente envía un entero
 * con el valor 100 al servidory este le responde a su vez con otro entero con
 * el valor 200.*/
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class socketTCPServer {
	private ServerSocket serverSocket;
	private Socket socket;
	private InputStream is;
	private OutputStream ou;
	
	public socketTCPServer(int puerto) throws IOException {
		serverSocket = new ServerSocket();
		InetSocketAddress addr = new InetSocketAddress ("localhost",puerto);
		//Asocia el serverSocket a una direccion
		serverSocket.bind(addr);
	}
	
	public void start() throws IOException {
		System.out.println("(Servidor) Esperando conexiones...");
		
		//Una vez recibida la petición de conexión se crea el objeto socket
		socket = serverSocket.accept();
		//Flujos de entrada y salida de datos. Permiten leer bytes.
		is = socket.getInputStream();
		ou = socket.getOutputStream();
		System.out.println("(Servidor) Conexión establecida...");
	}
	
	public void stop() throws IOException {
		System.out.println("(Servidor) Cerrando conexiones...");
		is.close();
		ou.close();
		socket.close();
		serverSocket.close();
		System.out.println("(Servidor) Conexion cerrada...");
	}

	public static void main(String[] args) {
		try {
			/*El serverSocket estará asociado a la dirección IP del host, es decir
			 * la dirección IP de mi máquina y a un puerto que se le pasa como 
			 * parámetro.*/
			socketTCPServer servidor = new socketTCPServer(49171);
			/*El método start indica al socket que quede a la espera de peticiones
			 * mediante el método accept del objeto ServerSocket creado en el 
			 * constructor. En este punto la ejecución quedará detenida hasta que
			 * llegue una petición de conexión por parte de un cliente.*/
			servidor.start();
			//Al usar el método read sólo podrá leer enteros entre 0 y 255 bytes.
			//Con el método read el servidor lee el número entero que le manda cliente
			System.out.println("Mensaje del cliente: "+servidor.is.read());
			//Con el método write manda el número al cliente
			servidor.ou.write(200);
			servidor.stop();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
	}

}
