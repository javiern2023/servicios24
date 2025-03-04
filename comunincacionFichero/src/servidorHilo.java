//package comunicacionFichero;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class servidorHilo extends Thread{
	private Socket sc;
	private DataInputStream in;
	private DataOutputStream out;
	private String nombreCliente;
	
	public servidorHilo(Socket sc, DataInputStream in, DataOutputStream out, String nombreCliente) {
		this.sc = sc;
		this.in = in;
		this.out = out;
		this.nombreCliente = nombreCliente;
	}
	
	public void run() {
		int opcion;
		File f = new File("numeros.txt");
		boolean salir = false;
		while(!salir) {
			try {
				opcion= in.readInt();
				switch(opcion) {
					case 1: int numeroAleatorio = in.readInt();
							out.writeUTF("Numero guardado");
							//Escribo el numero guardado
							escribirNumeroAleatorio(f, numeroAleatorio);
						break;
					case 2: //Recojo el número de líneas
							int numLineas = numeroLineasFichero(f);
							//Envío el número de líneas al cliente
							out.writeInt(numLineas);
						break;
					case 3: //Obtenemos el número de números del fichero
							ArrayList<Integer> numeros = listaNumeros(f);
							//Envío el numero de elementos que hay en el cliente
							out.writeInt(numeros.size());
							//Envío al cliente los números uno a uno
							for(int n:numeros) {
								out.writeInt(n);
							}
						break;
					case 4: //Recojo el número de líneas
							int numLineasCliente = numeroLineasFicheroCliente(f);
							//Envío el número de líneas al cliente
							out.writeInt(numLineasCliente);
						break;
					case 5: byte[] contenidoFichero= ficheroNumeroCliente(f);
							for(int i=0;i<contenidoFichero.length;i++) {
								out.writeByte(contenidoFichero[i]);
							}
						break;
					case 6: salir = true;
						break;
					default:out.writeUTF("Solo numeros del 1 al 6");
				}
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		}
		try {
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Conexion cerrada con el cliente "+nombreCliente);
	}
	
	public void escribirNumeroAleatorio(File f,int numero) {
		try {
			FileWriter fw = new FileWriter(f,true);
			fw.write(numero+":"+nombreCliente+"\n");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int numeroLineasFichero(File f) throws IOException {
		int numLineas = 0;
		BufferedReader br = new BufferedReader(new FileReader(f));
		String linea = "";
		while((linea = br.readLine()) != null) {
			numLineas++;
		}
		br.close();
		return numLineas;		
	}
	
	public ArrayList<Integer> listaNumeros(File f) throws NumberFormatException, IOException{
		ArrayList<Integer> numeros = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(f));
		String linea = "";
		while((linea = br.readLine()) != null) {
			//Corto la línea en dos partes
			String[] partes = linea.split(":");
			//Obtengo el número
			int numero = Integer.parseInt(partes[0]);
			//añado el número al array
			numeros.add(numero);
		}	
		br.close();
		return numeros;
	}
	
	public int numeroLineasFicheroCliente(File f) throws IOException {
		int numLineas = 0;
		BufferedReader br = new BufferedReader(new FileReader(f));
		String linea = "";
		while((linea = br.readLine()) != null) {
			String[] partes = linea.split(":");
			if(partes[1].equals(nombreCliente)) {
				numLineas++;
			}
		}
		br.close();
		return numLineas;		
	}
	
	public byte[] ficheroNumeroCliente(File f) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(f));
		String linea = "";
		String contenido="";
		while((linea = br.readLine()) != null) {
			String[] partes = linea.split(":");
			if(partes[1].equals(nombreCliente)) {
				contenido+=linea+"\n";
			}
		}
		br.close();
		return contenido.getBytes();
	}
}
