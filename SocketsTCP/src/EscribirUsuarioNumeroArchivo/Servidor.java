package EscribirUsuarioNumeroArchivo;

import java.io.*;
import java.net.*;

public class Servidor {
    private static final int PUERTO = 12345;
    private static final String ARCHIVO = "datos.txt";

    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor escuchando en el puerto " + PUERTO);

            while (true) {
                Socket socket = servidor.accept();
                new Thread(new ManejadorCliente(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ManejadorCliente implements Runnable {
    private Socket socket;

    public ManejadorCliente(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter escritor = new BufferedWriter(new FileWriter("datos.txt", true))) {

            String datos = entrada.readLine();
            if (datos != null) {
                String ipCliente = socket.getInetAddress().getHostAddress();
                escritor.write("Usuario: " + datos + ", IP: " + ipCliente + "\n");
                escritor.flush();
                System.out.println("Datos guardados: " + datos + ", IP: " + ipCliente);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

