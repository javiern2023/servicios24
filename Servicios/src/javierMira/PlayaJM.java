package javierMira;
/*Tenemos a unos pobres náufragos esperando ser rescatados por 3 balsas, 
 * pero el hueco es muy estrecho y sólo una de ellas puede acceder a la vez a la zona donde están.*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.event.AncestorEvent;

public class PlayaJM {

	static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) throws InterruptedException {

		Guardacostas guardacostas = new Guardacostas();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int nNaufragos = 0;

		System.out.println("Pulsa Enter para continuar la conversacion.");

		try {
			System.out.println(guardacostas.getTexto1());
			;
			reader.readLine();
			System.out.println(guardacostas.getTexto2());
			;
			reader.readLine();
			System.out.println(guardacostas.getTexto3());
			;
			reader.readLine();
			System.out.println(guardacostas.getTexto4());
			;
			reader.readLine();
			System.out.println(guardacostas.getTexto5());
			nNaufragos = teclado.nextInt();
			System.out.println(guardacostas.getTexto6());
			;

		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (Exception e2) {

			}
		}

		System.out.println(
				"El servicio de rescate manda 3 barcas de salvamento con capacidad para cuatro personas a la zona.");

//		
//		Tenemos que buscar la manera de que nNaufragos cambie de valor dentro del metodo porque si cada barca parte con el total naufragos desde el principio

		RescateNaufragos rescateNaufragos = new RescateNaufragos(nNaufragos);

		// Cada barca recibe un nombre en el constructor y el objeto del recurso
		// compartido, en este caso rescateNaufragos que incluye el numero de Naufragos
		// a rescatar.

		Barca barca1 = new Barca("Barca 1", rescateNaufragos);
		Barca barca2 = new Barca("Barca 2", rescateNaufragos);
		Barca barca3 = new Barca("Barca 3", rescateNaufragos);

		// Lanzamos las barcas a rescate.

		barca1.start();
		barca2.start();
		barca3.start();

	}

}
