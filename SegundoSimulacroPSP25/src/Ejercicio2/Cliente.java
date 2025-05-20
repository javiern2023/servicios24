package Ejercicio2;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    private static final String HOST = "localhost";
    private static final int PUERTO = 5000;

    public static void main(String[] args) {
        try (
            Socket socket = new Socket(HOST, PUERTO);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);
        ) {
            System.out.println(entrada.readLine()); // Mensaje de bienvenida
            String codigo = scanner.nextLine();
            salida.println(codigo);
            System.out.println("Respuesta del servidor: " + entrada.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

