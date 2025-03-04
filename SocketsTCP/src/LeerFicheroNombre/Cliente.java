package LeerFicheroNombre;

import java.io.*;
import java.net.*;

public class Cliente {
    private static final String SERVIDOR = "localhost";
    private static final int PUERTO = 5000;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVIDOR, PUERTO);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.print("Introduce tu nombre de usuario: ");
            String nombre = teclado.readLine();

            salida.println(nombre); // Enviar nombre al servidor
            String respuesta = entrada.readLine(); // Leer respuesta del servidor
            System.out.println("Servidor: " + respuesta);

        } catch (IOException e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }
}

