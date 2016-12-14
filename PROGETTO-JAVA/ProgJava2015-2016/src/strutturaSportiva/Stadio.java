package strutturaSportiva;

import java.io.Serializable;
import java.util.*;

import exception.Incompatibilt‡PartitaException;
import exception.PostiNegativiException;


/**
 * Questa classe rappresenta uno stadio. Ogni stadio contiene un elenco di partite.
 *
 *@implements Serializable
 */

public class Stadio implements Serializable{

	private String nomeStadio;
	private int numPosti ;
	private ArrayList<Partita> partite = new ArrayList<Partita>();
	private double incasso =0;
	private boolean scontato= false;
	
	/**
	 * Crea uno stadio con nome e capienza.	 
	 * @param n variabile di tipo String che rappresenta il nome dello stadio
	 * @param np variabile di tipo int che rappresenta la capienza dello stadio
	 */
	public Stadio(String n, int np){
		nomeStadio=n;
		numPosti=np;
					
	}
		
	/**aggiunge una partita alla lista di partite giocate in uno stadio. 
	 * @param p indica la partita aggiunta
	 * @throws Incompatibilt‡PartitaException  eccezione che viene lanciata se lo stadio Ë gia occupato*/
	public void addPartita(Partita p) throws Incompatibilt‡PartitaException{
		boolean comp = true;
		int giorno= p.getData().get(GregorianCalendar.DAY_OF_MONTH);
		int mese= p.getData().get(GregorianCalendar.MONTH);
		int anno= p.getData().get(GregorianCalendar.YEAR);
		int ora= p.getData().get(GregorianCalendar.HOUR_OF_DAY);
		int minuti= p.getData().get(GregorianCalendar.MINUTE);
		
		for (Partita pp:partite){
			int giornop= pp.getData().get(GregorianCalendar.DAY_OF_MONTH);
			int mesep= pp.getData().get(GregorianCalendar.MONTH);
			int annop= pp.getData().get(GregorianCalendar.YEAR);
			int orap= pp.getData().get(GregorianCalendar.HOUR_OF_DAY);
			int minutip= pp.getData().get(GregorianCalendar.MINUTE);
		
			if(giornop==giorno && annop==anno && mesep==mese)
			{System.out.println("tutti uguali");
				
				
				if (ora-orap>-2 && ora-orap<2 )
					comp=false;
				else{
					if(ora-orap==2){
						if(minuti-minutip<0)
							comp=false;
					}
					else{
						if(ora-orap==-2)
							if(minuti-minutip>0)
								comp=false;
					}
				}
			}
			
		}
		if (!comp){
			throw new Incompatibilt‡PartitaException();}
		else{		
		partite.add(p);}
	}	

	/**restituisce il numero di posti di uno stadio. @return numPosti ritorna il numero dei posti
	 * @return numPosti rappresenta il numero dei posti dello stadio*/
	public int getNumPosti(){
		return  numPosti;
	}	

	/**restituisce il nome dello stadio. @return nomeStadio ritorna il nome dello stadio
	 * @return nomeStadio indica il nome dello stadio*/
	public String getNomeStadio(){
		return nomeStadio;
	}
	/**restituisce l'incasso dello stadio. @return incasso ritorna l incasso dello stadio
	 * @return incasso indica l incasso*/
	public double getIncasso(){
		return incasso;
	}
	/**aggiorna l'incasso dello stadio. @param i Ë la quota da aggiungere*
	 @param i indica la quota da aggiugere*/
	public void setIncasso(double i){
		incasso = incasso + i;
	}
	
	/**aggiunge posti ad uno stadio. @param n sono i posti da aggiungere da aggiungere
	 * @param n indica i posti da aggiungere*/
	public void aggiungiPosti(int n){		
		numPosti =numPosti+n;
		for(int i =0 ; i< partite.size();i++){			
			partite.get(i).addBiglietti(n);}
	}
	/**toglie posti ad uno stadio. @param n sono i posti da togliere da aggiungere
	 * @param n indica i posti da togliere*/
	public void togliPosti(int n){	
		if(numPosti-n<1)throw new PostiNegativiException();
		else{
		numPosti=numPosti-n;
		for(int i =0 ; i< partite.size();i++){
			partite.get(i).removeBiglietti(n);}}
	}

	/**restituisce un riferimento a una partita dello stadio. 
	 * @param i rappresenta l indice della partita da ritornare
	 * @return partite.get(i) rappresenta la partita desiderata*/	
 public Partita getPartita(int i){
	return partite.get(i);
}
 /**restituisce il numero di partite giocate in uno stadio.
  * @return partite.size() numero di partite*/
public int sizePartite(){
	return partite.size();
}
/**verifica se lo stadio Ë scontato
 * @return scontato true se Ë scontato, false altrimenti*/
public boolean isScontato(){
	return scontato;
}

/**setta lo sconto stadio a true */
public void setScontato(){
	scontato = true;
}
/**setta lo sconto stadio a false */
public void remScontato(){
	scontato = false;
}

/** retituisce una stringa con il nome dello stadio*/
public String toString() {
	return  nomeStadio ;
}
/** retituisce una stringa con tutte le informazioni dello stadio*/
public String toStringCompleto() {
	return "Stadio [nomeStadio=" + nomeStadio + ", numPosti=" + numPosti + ",\n partite=\n" + partite + ", incasso="
			+ incasso + "]\n";
}




}