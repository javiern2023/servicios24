package sincronizacionCuentaBancaria;

public class cuenta {
	protected String IBAN;
	protected static double saldo;
	protected cuenta(String iBAN, double saldo) {
		IBAN = iBAN;
		this.saldo = saldo;
	}
	protected String getIBAN() {
		return IBAN;
	}
	protected void setIBAN(String iBAN) {
		IBAN = iBAN;
	}
	protected double getSaldo() {
		return saldo;
	}
	protected void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	
	
}
