package Herencia;

import java.util.Random;

public class Galgo extends Thread {
    private String nombre;
    private int tiempoCarrera;
    private double tiempoTotalCarrera;
    private Random random = new Random();

    public Galgo(String nombre) {
        this.nombre = nombre;
        // Tiempo aleatorio entre 3 y 5 segundos
        this.tiempoCarrera = 3 + random.nextInt(3);
    }

    @Override
    public void run() {
        try {
            System.out.println("El galgo " + nombre + " ha comenzado la carrera con un tiempo de " + tiempoCarrera + " segundos.");
            Thread.sleep(tiempoCarrera * 1000);  // Simula la carrera
            System.out.println("El galgo " + nombre + " ha terminado la carrera.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Getter opcional si quieres usar el tiempo fuera
    public int getTiempoCarrera() {
        return tiempoCarrera;
    }
}
