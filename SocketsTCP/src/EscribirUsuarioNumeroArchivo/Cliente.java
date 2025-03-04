package EscribirUsuarioNumeroArchivo;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    private static final String SERVIDOR = "127.0.0.1"; 
    private static final int PUERTO = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVIDOR, PUERTO);
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Ingresa tu nombre de usuario: ");
            String usuario = scanner.nextLine();

            System.out.print("Ingresa un número: ");
            String numero = scanner.nextLine();

            String mensaje = usuario + " | " + numero;
            salida.println(mensaje);
            System.out.println("Datos enviados al servidor.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
