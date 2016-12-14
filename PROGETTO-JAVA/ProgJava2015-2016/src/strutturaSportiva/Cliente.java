package strutturaSportiva;
import java.io.Serializable;
import java.util.*;
import exception.*;

/**
 * Questa classe rappresenta un cliente.
 * Un cliente rappresenta un utente della struttura sportiva, a cui accede tramite il login e la password.
 * 
 * @implements Serializable
 */

public class Cliente implements Serializable{
	private String username;
	private String password;	
	private  ArrayList<Biglietto> partiteAcquistate=new ArrayList<>();
	private  ArrayList<Biglietto> partitePrenotate=new ArrayList<>();
	
	/**
	 * Crea un cliente modellato da: nome, cognome, data di nascita, login, password e stato sociale.
	 * @param u variabile di tipo String che rappresenta l'user name
	 * @param p variabile di tipo String che rappresenta la password 
	 */	
	
 public Cliente ( String u, String p){
		username=u;
		password=p;
		
		
	}

 /**
	 * Restituisce l'username del cliente
	 * 
	 * @return username variabile di tipo String che rappresenta il usrname del cliente
	 */
	
 public String getUsername(){
	 return username;
 }
 
 /**
	 * Restituisce la password del cliente
	 * 
	 * @return password variabile di tipo String che rappresenta la password del cliente
	 */
 
 
 public String getPassword(){
	 return password;
 }
 
 /** Prenota un biglietto
  * @param p indica la partita di cui si vuole prenotare un biglietto
  * @param i indica il posto del biglietto
  * @throws PostoIndisponibileException se il posto è gia occupato
  * */


 public void prenotaBiglietto(Partita p, int i){
	 if(i<0 ||p.getBiglietto(i).getStato()==1  ||  p.getBiglietto(i).getStato()==2)
			throw new PostoIndisponibileException();
	 
	 
	 GregorianCalendar d = new GregorianCalendar(p.getData().get(1),p.getData().get(2),p.getData().get(5),p.getData().get(11)-12,p.getData().get(12)); 
		GregorianCalendar now = new GregorianCalendar();
		
	 if(now.after(d))throw new PrenotazioneScadutaException();
	 else{
	 partitePrenotate.add(p.getBiglietto(i));	 
	 p.getBiglietto(i).setPrezzo(p.getPrezzoScontato());
	 p.getBiglietto(i).setPrenotato();
	 p.addPrenotato();}
	 }
	 
	 public int prenotazioniScadute(){
		 
		 GregorianCalendar ora = new GregorianCalendar();
		 int sc =0;
		 GregorianCalendar d;
		 for(int i =0; i<partitePrenotate.size();i++){			 
			 d = new GregorianCalendar(
					 partitePrenotate.get(i).getPartita().getData().get(1),
					 partitePrenotate.get(i).getPartita().getData().get(2),
					 partitePrenotate.get(i).getPartita().getData().get(5),
					 partitePrenotate.get(i).getPartita().getData().get(11)-12,
					 partitePrenotate.get(i).getPartita().getData().get(12)); 
			 if(ora.after(d))sc++;	 }
	 
	 return sc;
 }
 
 /** Acquista un biglietto
  * @param p indica la partita di cui si vuole prenotare un biglietto
  * @param i indica il posto del biglietto
  * @throws PostoIndisponibileException se il posto è gia occupato
  * */
 
public void acquistaBiglietto(Partita p,int i){
	if(i<0 ||p.getBiglietto(i).getStato()==1  ||  p.getBiglietto(i).getStato()==2)
		throw new PostoIndisponibileException();

	else{	
	double prez =p.getPrezzoScontato();
	partiteAcquistate.add(p.getBiglietto(i));
	p.getBiglietto(i).setPrezzo(prez);
	p.getBiglietto(i).setAcquistato();
	p.getStadio().setIncasso(prez);
	p.addVenduto();
	p.setIncassoPartita(prez);
	System.out.println(" "+p.getStadio().getIncasso());}
	
}

/** Cancella una prenotazione 
@param b indica il biglietto del quale si vuole annullare la prenotazione
 */

public void cancellaPrenotazione(Biglietto b){	
	
	partitePrenotate.remove(b);
	b.setDisponibile();
	b.getPartita().remPrenotati();
	        }

/** Cancella le prenotazioni scadute

 */
public void cancellaPrenotazioniScadute(){
	GregorianCalendar now = new GregorianCalendar();
	GregorianCalendar d;
	for(int i =partitePrenotate.size()-1; i>=0;i--){
		
		d = new GregorianCalendar(partitePrenotate.get(i).getPartita().getData().get(1),
				partitePrenotate.get(i).getPartita().getData().get(2),
				partitePrenotate.get(i).getPartita().getData().get(5),
				partitePrenotate.get(i).getPartita().getData().get(11)-12,
				partitePrenotate.get(i).getPartita().getData().get(12)); 
		
		if(now.after(d)){	
		this.cancellaPrenotazione(partitePrenotate.get(i));}
		
	}
}
	


/** Acquista una prenotazione 
 * @param b indica il biglietto prenotato che si vuole acquistare
 
 * */

public void acquistaPrenotazione(Biglietto b){
	this.cancellaPrenotazione(b);
	partiteAcquistate.add(b);
	b.setAcquistato();
	double prez =b.getPrezzo();
	System.out.println("  "+prez);
	b.getPartita().getStadio().setIncasso(prez);
	b.getPartita().addVenduto();
	b.getPartita().remPrenotati();
	b.getPartita().setIncassoPartita(prez);
	System.out.println(" "+b.getPartita().getStadio().getIncasso());
	
	
	}

/**Restituisce il numero di partite acquistate di un cliente
 * @return partiteAcquistate.size() rappresenta il numero di partite acquistate di un cliente
 * */

public int getAcquistateSize(){
	return partiteAcquistate.size();
}

/**Restituisce il numero di partite prenotate di un cliente
 * @return partiteAcquistate.size() rappresenta il numero di partite prenotate di un cliente
 * */

public int getPrenotateSize(){
	return partitePrenotate.size();
}

/**Restituisce un biglietto prenotato
 * @param i posto del biglietto che si vuole ricevere
 * @return partitePrenotate.get(i) restituisce il biglietto 
 * */
public Biglietto getPrenotata(int i){
	return partitePrenotate.get(i);
}

/**Restituisce un biglietto acquistato
 * @param  i posto del biglietto che si vuole ricevere
 * @return partitePrenotate.get(i) restituisce il biglietto 
 * */

public Biglietto getAcquistata(int i){
	return partiteAcquistate.get(i);
}

/**
 * Converte un oggetto di tipo Cliente in una stringa 
 */

public String toString() {
	return "Cliente [username=" + username + ", password=" + password + ",\n partiteAcquistate=\n " + partiteAcquistate
			+ "\n partitePrenotate=\n " + partitePrenotate + "]";
}












}
