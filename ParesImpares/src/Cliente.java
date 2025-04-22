import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 5000;

        try (
            Socket socket = new Socket(host, puerto);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in)
        ) {
            String opcion;
            do {
                System.out.println("\n1. Enviar número");
                System.out.println("2. Mostrar pares");
                System.out.println("3. Mostrar impares");
                System.out.println("4. Salir");
                System.out.print("Elige una opción: ");
                opcion = scanner.nextLine();

                switch (opcion) {
                    case "1":
                        System.out.print("Introduce un número positivo: ");
                        String numero = scanner.nextLine();
                        out.println(numero);
                        System.out.println(in.readLine());
                        break;
                    case "2":
                        out.println("pares");
                        System.out.println("Números pares:\n" + in.readLine());
                        break;
                    case "3":
                        out.println("impares");
                        System.out.println("Números impares:\n" + in.readLine());
                        break;
                    case "4":
                        out.println("salir");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } while (!opcion.equals("4"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

