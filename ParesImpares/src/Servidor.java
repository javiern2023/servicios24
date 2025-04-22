
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 5000;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto + "...");

            while (true) {
                Socket socketCliente = servidor.accept();
                System.out.println("Nuevo cliente conectado.");
                new Thread(new ClienteHilos(socketCliente)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

