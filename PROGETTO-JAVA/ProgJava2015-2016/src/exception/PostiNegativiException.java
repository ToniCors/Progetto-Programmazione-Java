package exception;

import javax.swing.JOptionPane;

public class PostiNegativiException extends RuntimeException {
	

	/**
	 * Eccezione lanciata se il posto � occupato*/
	public PostiNegativiException(){
		super ("Stai Togliendo Troppi Posti ");
		

	}

}
