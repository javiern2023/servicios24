import java.io.*;
import java.net.*;
import java.util.*;

// Clase Servidor
public class Servidor {
    public static void main(String[] args) {
        int puerto = 5000;
                
        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor en ejecución en el puerto " + puerto);
            while (true) {
                Socket socket = servidor.accept();
                new ServidorHilos(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
