package exception;

public class Incompatibilt�PartitaException extends Exception{

	/**
	 * Eccezione lanciata se lo stadio � gia occupato */
	public Incompatibilt�PartitaException(){
		super("L'orario della partita non � compatibile");
	}
	
}

