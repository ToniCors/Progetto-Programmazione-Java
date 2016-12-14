package exception;

public class PercentualeScontoException extends RuntimeException {
	
	/**
	 * Eccezione lanciata se la percentuale di sconto � minore uguale di 0 o maggiore uguale di 100*/
	public PercentualeScontoException(){
		super ("");
	}

}