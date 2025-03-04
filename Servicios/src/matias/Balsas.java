package matias;
public class Balsas {
    private String naufrago;
    private int naufragosMontados = 0;
    public int naufragosRescatados = 0;

    public Balsas() {}

    public synchronized void MontarEnLaBalsa(String naufrago) {
        while (naufragosMontados >= 5) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.naufrago = naufrago;
        System.out.println("Montando naufrago en la balsa... " + naufrago);
        naufragosMontados++;
        notify();
    }

    public synchronized String Rescatar() {
        while (naufragosRescatados >= 5) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        naufragosRescatados++;
        System.out.println("Naufrago rescatado con éxito");
        notify();
        return naufrago;
    }
}
