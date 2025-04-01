package comunicacionFichero;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class cliente {
	
	public static void main (String[] args) {
		
		try {
			Scanner sn = new Scanner(System.in);
			sn.useDelimiter("\n");
			Socket sc = new Socket("127.0.0.1", 5000);
			DataInputStream in = new DataInputStream(sc.getInputStream());
			DataOutputStream out = new DataOutputStream(sc.getOutputStream());
			String mensaje = in.readUTF();
			System.out.println(mensaje);
			String nombre = sn.next();
			out.writeUTF(nombre);
			clienteHilo hilo = new clienteHilo(in,out);
			hilo.start();
			
			try {
				hilo.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
