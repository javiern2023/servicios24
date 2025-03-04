//package comunicacionFichero;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class clienteHilo extends Thread{
	private DataInputStream in;
	private DataOutputStream out;
	public clienteHilo(DataInputStream in, DataOutputStream out) {
		super();
		this.in = in;
		this.out = out;
	}
	
	public void run() {
		Scanner sn = new Scanner(System.in);
		String mensaje;
		int opcion;
		boolean salir = false;
		while (!salir) {
			try {
				System.out.println("1. Almacenar numero en el archivo");
				System.out.println("2. Numeros almacenados hasta el momento");
				System.out.println("3. Lista de numeros almacenados");
				System.out.println("4. El numero de numeros almacenados por el cliente");
				System.out.println("5. Archivo con numeros del cliente");
				System.out.println("6. Salir");
				opcion = sn.nextInt();
				out.writeInt(opcion);
				switch(opcion) {
					case 1: int numeroAleatorio = generarNumeroAleatorio(0,100);
							System.out.println("Numero generado: "+numeroAleatorio);
							out.writeInt(numeroAleatorio);
							mensaje = in.readUTF();
							System.out.println(mensaje);
						break;
					case 2: int numLineas = in.readInt();
							System.out.println("Hay "+numLineas+" numeros");
						break;
					case 3: int limite = in.readInt();
							for (int i=0;i<limite;i++) {
								System.out.println(in.readInt());
							}
						break;
					case 4: int numLineasCliente = in.readInt();
							System.out.println("Hay "+numLineasCliente+" numeros de este cliente");
						break;
					case 5: int limiteFichero = in.readInt();
							byte[] contenidoFichero=new byte[limiteFichero];
							for (int i=0;i<limiteFichero;i++) {
								contenidoFichero[i]=in.readByte();
							}
							String contenido = new String(contenidoFichero);
							FileWriter fw = new FileWriter("numeros_cliente.txt");
							fw.write(contenido);
							fw.close();
						break;
					case 6: salir = true;
						break;
					default: mensaje = in.readUTF();
							System.out.println(mensaje);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	public int generarNumeroAleatorio(int minimo, int maximo) {
		int num=(int)Math.floor(Math.random()*(maximo-minimo)+(minimo));
		return num;
	}
}
