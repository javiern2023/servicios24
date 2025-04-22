package gestorPeticionesHTTP2;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class gestorPeticionesHTTP {
	public StringBuilder getContenidoMetodoGet (String direccion) throws Exception {
		StringBuilder respuesta = new StringBuilder();
		URL url = new URL (direccion);
		HttpURLConnection conexion = (HttpURLConnection)url.openConnection();
		//Proporciona el método de ejecución
		conexion.setRequestMethod("GET");
		//Indica formato de devolución de la petición. 
		conexion.setRequestProperty("Content-Type", "text/plain");
		//Codificación de caracteres en la devolución de la petición.
		conexion.setRequestProperty("charset", "utf-8");
		//Identifica la aplicación que usa
		conexion.setRequestProperty("User-Agent", "Mozilla/5.0");
		//Proporciona el código de retorno HTTP enviado por el servidor.
		int estado = conexion.getResponseCode();
		Reader streamReader = null;
		if(estado==HttpURLConnection.HTTP_OK) {
			streamReader = new InputStreamReader(conexion.getInputStream());
			int caracter;
			while ((caracter=streamReader.read()) != -1) {
				 respuesta.append((char)caracter);
			}
		}
		else {
			throw new Exception("Error HTTP " + estado);
		}
		conexion.disconnect();
		return respuesta;
	}
	
	public static void writeFile(String strPath, String contenido) throws IOException {
		Path path = Paths.get(strPath);
		byte[] strToBytes = contenido.getBytes();
		Files.write(path, strToBytes);
	}
	public static void main(String[] args) {
		try {
			String esquema = "https://";
			String servidor = "www.imdb.com";
			String path = "/find";
			String texto = URLEncoder.encode("Tiburón",StandardCharsets.UTF_8.name());
			String parametros = "?q=" + texto;
			gestorPeticionesHTTP gp = new gestorPeticionesHTTP();
			String direccion = esquema + servidor + path + parametros;
			StringBuilder resultado = gp.getContenidoMetodoGet(direccion);
			gestorPeticionesHTTP.writeFile("C:\\Users\\yo\\Desktop\\Navazo\\tiburon_movie.html", resultado.toString());
			System.out.println("Descarga finalizada");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
