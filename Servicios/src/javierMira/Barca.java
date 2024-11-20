package javierMira;
import java.util.Iterator;

public class Barca extends Thread {

	private String nombreBarca;
	private RescateNaufragos rescateNaufragos; // RECURSO COMPARTIDO.
	private static int capacidad = 4; // Todas las barcas pueden recoger como maximo a 4 naufragos.

	public String getNombreBarca() {
		return nombreBarca;
	}

	public void setNombreBarca(String nombreBarca) {
		this.nombreBarca = nombreBarca;
	}

	public static int getCapacidad() {
		return capacidad;
	}

	public static void setCapacidad(int capacidad) {
		Barca.capacidad = capacidad;
	}

	public Barca(String nombreBarca, RescateNaufragos rescateNaufragos) {

		this.nombreBarca = nombreBarca;
		this.rescateNaufragos = rescateNaufragos;

	}

	public void run() {

		try {

			// CADA BARCA HACE UNA LLAMADA AL METODO SINCRONIZADO rescatarNaufrago.

			while (true) {
				rescateNaufragos.rescatarNaufrago(getNombreBarca());

				// Ponemos el hilo en espera y dejamos que acceda otro.

				try {
					wait();

				} catch (Exception e) {

				}
			}

		} catch (Exception e) {
			System.out.println("Error en la llamada al metodo por parte del hilo.");
		}
	}

}
