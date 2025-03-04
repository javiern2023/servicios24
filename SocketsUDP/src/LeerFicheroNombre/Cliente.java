package LeerFicheroNombre;

import java.io.*;
import java.net.*;

public class Cliente {
    private static final String SERVIDOR = "localhost";
    private static final int PUERTO = 5000;
    private static final int TAMANO_BUFFER = 1024;

    public static void main(String[] args) {
        try (DatagramSocket socketCliente = new DatagramSocket();
             BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.print("Introduce tu nombre de usuario: ");
            String nombre = teclado.readLine();

            byte[] bufferEnvio = nombre.getBytes();
            InetAddress direccionServidor = InetAddress.getByName(SERVIDOR);

            DatagramPacket paqueteEnvio = new DatagramPacket(bufferEnvio, bufferEnvio.length, direccionServidor, PUERTO);
            socketCliente.send(paqueteEnvio);

            byte[] bufferRecepcion = new byte[TAMANO_BUFFER];
            DatagramPacket paqueteRecibido = new DatagramPacket(bufferRecepcion, bufferRecepcion.length);
            socketCliente.receive(paqueteRecibido);

            String respuesta = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
            System.out.println("Servidor: " + respuesta);

        } catch (IOException e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }
}

