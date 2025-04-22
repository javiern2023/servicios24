
import java.io.*;
import java.net.Socket;

public class ClienteHilos implements Runnable {
    private Socket socket;

    public ClienteHilos(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String mensaje;
            while ((mensaje = in.readLine()) != null) {
                if (mensaje.equalsIgnoreCase("salir")) {
                    break;
                } else if (mensaje.equalsIgnoreCase("pares")) {
                    out.println(Ficheros.leerFichero("pares.txt"));
                } else if (mensaje.equalsIgnoreCase("impares")) {
                    out.println(Ficheros.leerFichero("impares.txt"));
                } else {
                    try {
                        int numero = Integer.parseInt(mensaje);
                        if (numero >= 0) {
                            Ficheros.escribirNumero(numero);
                            out.println("Número procesado correctamente.");
                        } else {
                            out.println("Introduce solo números positivos.");
                        }
                    } catch (NumberFormatException e) {
                        out.println("Comando o número no válido.");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

