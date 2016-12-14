package exception;

public class BigliettiTerminatiException extends Exception{
	
	/**
	 * Eccezione lanciata se finiscono i biglietti per una partita*/
	
	public BigliettiTerminatiException(){
		super("Biglietti Terminati");
	}

}
