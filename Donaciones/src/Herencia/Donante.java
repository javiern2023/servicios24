package Herencia;

import java.util.Random;

public class Donante extends Thread {
    private String nombre;
    private double cantidad;
    static double totalRecaudado;
    private Random random = new Random();

    // Variable estática para acumular la recaudación total
    // public static double totalRecaudado = 0;
    
    public Donante(String nombre) {
        this.nombre = nombre;
        // Cantidad aleatoria entre 5 y 50 euros
        this.cantidad = 5 + random.nextInt(46); // 5 a 50
    }

    @Override
    public void run() {
        try {
            // Simulamos tiempo antes de donar (1 a 3 segundos)
            int tiempo = 1 + random.nextInt(3);
            Thread.sleep(tiempo * 1000);

            System.out.println(nombre + " ha donado " + cantidad + " euros.");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public double getCantidad() {
        return cantidad;
    }
}
