
import java.io.*;

public class Ficheros {
	public static synchronized void escribirNumero(int numero) {
        String nombreFichero = (numero % 2 == 0) ? "pares.txt" : "impares.txt";
        File fichero = new File(nombreFichero);

        try {
            // Si no existe, lo crea
            if (!fichero.exists()) {
                fichero.createNewFile();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichero, true))) {
                writer.write(numero + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized String leerFichero(String nombreFichero) {
        StringBuilder contenido = new StringBuilder();
        File fichero = new File(nombreFichero);

        if (!fichero.exists()) {
            return "El archivo no existe.";
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contenido.toString().isEmpty() ? "Archivo vacío." : contenido.toString();
    }
}

