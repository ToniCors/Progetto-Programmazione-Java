package strutturaSportiva;
import java.io.Serializable;
import java.util.*;
public class Biglietto implements Serializable{
	

/**
 * Questa classe rapprensenta un biglietto che va associato ad una partita 
 * 
 * @implements Serializable
 */
	
private int numPosto;
private int stato ; ///0 se disponibile , 1 se prenotato , 2 se acquistato
private Partita partita ;
private double prezzoBiglietto ;

/**
 * Crea un biglietto modellato da: posto, partita e stato ,prezzoBiglietto
 * 
 * @param np variabile di tipo int che rappresenta il numero di posto
 * @param p oggetto di tipo Partita che rappresenta una partita
 
 */

public Biglietto(int np, Partita p){
	numPosto=np;
	partita =p;
	stato =0;
	prezzoBiglietto =0;
	}	


/**
 * Restituisce il numero di posto associato al biglietto 
 * 
 * @return numposto variabile di tipo int che rappresenta il numero di posto del biglietto
 */


public int getPosto(){
	return numPosto;
}

/**
 * Restituisce lo stato del biglietto 
 * 
 * @return stato variabile di tipo int che rappresenta lo stato del biglietto (0 se disponibile,1 se prenotato, 2 se venduto)
 */

public int getStato(){
	return stato;
}

/**
 * Restituisce lo stato del biglietto 
 * 
 * @return una string che rappresenta lo stato del biglietto (0 se disponibile,1 se prenotato, 2 se venduto)
 */

public String getStatoStr(){
	if(this.stato == 0) return "disponibile";
       if(this.stato== 1) return "prenotato";
	else return "venduto";
}

/**
 * Restituisce la partita associata al biglietto
 * 
 * @return partita oggetto di tipo Partita che rappresenta la partita associata al biglietto
 */

public Partita getPartita(){
	return partita;
}


/** Modifica lo  stato di un biglietto in acquistato*/
public void setAcquistato(){
	stato = 2;
}
/** Modifica lo  stato di un biglietto in prenotato*/

public void setPrenotato(){
	stato = 1;
}
/** Modifica lo  stato di un biglietto in disponibile*/

public void setDisponibile(){
	stato=0;
}

/**
 * Restituisce il prezzo del biglietto
 * 
 * @return prezzo variabile double che rappresenta il prezzo del biglietto
 */

public double getPrezzo(){
	return prezzoBiglietto;
}


/**
 * Assegna il prezzo del biglietto
 * 
 * @param p variabile double che rappresenta il prezzo del biglietto
 */

public void setPrezzo(double p){
	prezzoBiglietto = p;
}

/**
 * Converte un oggetto di tipo Biglietto in una stringa
 */

public String toString() {
	return "Biglietto ["+partita.toString() + "\n"+
			    "numero Posto=" + numPosto + ", stato=" + this.getStatoStr() +", prezzo Bigliett="+prezzoBiglietto+ "]";
}



public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Biglietto other = (Biglietto) obj;
	if (numPosto != other.numPosto)
		return false;
	if (partita == null) {
		if (other.partita != null)
			return false;
	} else if (!partita.equals(other.partita))
		return false;
	
	return true;
}





}
