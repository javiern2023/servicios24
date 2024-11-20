package javierMira;
public class RescateNaufragos {

	private int nNaufragos = 0;

	// CONSTRUCTOR. RECIBE POR PARAMETRO EL NUMERO DE NAUFRAGOS.

	public RescateNaufragos(int nNaufragos) {
		this.nNaufragos = nNaufragos;

	}

	public int getnNaufragos() {
		return nNaufragos;
	}

	public void setnNaufragos(int nNaufragos) {
		this.nNaufragos = nNaufragos;
	}

	// ACCESO AL RECURSO COMPARTIDO DE LOS HILOS. SOLO UN HILO LO EJECUTA.

	public synchronized void rescatarNaufrago(String nombreBarca) {

		try {

			if (nNaufragos > 0) {
				System.out.println("La " + nombreBarca + " esta recogiendo naufragos.");
				Thread.sleep(1000); // Tiempo que esta rescatando naufragos.
				nNaufragos -= Barca.getCapacidad();
				// Volvemos a meter un condicional para que no nos de dato negativo. //
				if (nNaufragos > 0) {
					System.out.println("Quedan " + nNaufragos + " por rescatar.");
				} else { //Salida del bucle.
					nNaufragos = 0;
					System.out.println("Todos los naufragos han sido rescatados. Volvemos a la base.");
				}
			}

		} catch (Exception e) {
			System.out.println("Error al acceder al recurso compartido.");

		}

	}

}
