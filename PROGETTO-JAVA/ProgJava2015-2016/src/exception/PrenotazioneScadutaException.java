package exception;

import javax.swing.JOptionPane;

public class PrenotazioneScadutaException extends RuntimeException{
	/**
	 * Eccezione lanciata se è mancano meno di 12 ore all inizio della partita*/
	
	public PrenotazioneScadutaException(){
		super ("E' scaduto il tempo della prenotazione ");
		JOptionPane.showMessageDialog(null,"devi prenotare la paritita almeno 12 ore prima",
				"PRENOTAZIONE SCADUTA",JOptionPane.ERROR_MESSAGE);
	}

}
