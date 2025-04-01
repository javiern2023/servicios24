package comunicacionFichero;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class servidor {
	
	public static void main (String[] args) {
		try {
			ServerSocket server = new ServerSocket(5000);
			Socket sc;
			System.out.println("Servidor iniciado");
			while (true) {
				sc = server.accept();
				DataInputStream in = new DataInputStream(sc.getInputStream());
				DataOutputStream out = new DataOutputStream(sc.getOutputStream());
				out.writeUTF("Indica tu nombre");
				String nombreCliente = in.readUTF();
				servidorHilo hilo = new servidorHilo(sc,in,out,nombreCliente);
				hilo.start();
				System.out.println("Creada la conexion con el cliente "+nombreCliente);
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
}
