package exception;

import javax.swing.JOptionPane;

public class PostoIndisponibileException extends RuntimeException{
	
	/**
	 * Eccezione lanciata se il posto è occupato*/
	public PostoIndisponibileException(){
		super ("Posto Occupato ");
		JOptionPane.showMessageDialog(null,"Attenzione il posto selezionato è occupato",
				"POSTO OCCUPATO",JOptionPane.ERROR_MESSAGE);

	}

}
