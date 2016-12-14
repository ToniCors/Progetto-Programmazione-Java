package strutturaSportiva;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Questa classe rappresenta una struttura sportiva.
 *  contiene una lista di stadi ,una lista di partite, una lista di clienti,
 *  una lista di stadi scontati , una lista di partite scontate
 * 
 *@implements Serializable
 */




public class StrutturaSportiva implements Serializable {

private  ArrayList<Stadio> stadi ;
private  ArrayList<Cliente> clienti;	
private  ArrayList<Partita> calendario;	
private  ArrayList<Partita>elencoSettimana;
private  ArrayList <ScontoStadio> stadiScontati;
private  ArrayList <ScontoPartita> partiteScontate;


/**
 * Crea una struttura sportiva inizializzando la lista di clienti, la lista di stadi , la lista di clienti,
 * una lista di sconti stadi e sconti partite

 */

public StrutturaSportiva (){
	stadi = new ArrayList<Stadio>();
	clienti = new ArrayList<>();	
	calendario= new ArrayList<>();		
	stadiScontati = new ArrayList<ScontoStadio>();	
	partiteScontate = new ArrayList<ScontoPartita>();	
	elencoSettimana= new ArrayList<Partita>();
}

/**
 * Metodo che controlla e annulla le prenotazioni scadute*/

public void controllaPrenotazioniScadute(){			
			for(Cliente c :clienti){
				c.cancellaPrenotazioniScadute();	
	}}
	
/**
 * Metodo che aggiunge una partita al calendario della struttura
 * @param p indica la partita da aggiungere
 */

public   void  addPartita(Partita p){
	calendario.add(p);	
}

/**
 * Restituisce il numero di partite del calendario
 * @return calendario.size() variabile di tipo int indica le partite del calendario
 * */

public  int sizePartite(){
	return calendario.size();
}
/**
 * Restituisce una partita del calendario
 * @param i indica quale partite si vuole
 * @return calendario.get(i) restituisce la partita desiderata*/
public  Partita getPartita(int i){
	return calendario.get(i);
}

/**
 * Metodo che aggiunge un cliente alla struttura
 * @param c indica il cliente da aggiungere
 */
public void addCliente(Cliente c){
	clienti.add(c);
}

/**
 * Restituisce un cliente ricevendo l' username e la password
 * @param u indica l' username
 * @param p indica la passsword
 * @return clienti.get(i) variabile di tipo cliente se esiste null altrimenti
 * */

public  Cliente findCliente(String u,String p){
	
	for(int i =0; i<clienti.size();i++)
		if(clienti.get(i).getUsername().equals(u) && clienti.get(i).getPassword().equals(p))
	return clienti.get(i);
	return null;
}

/**
 * Restituisce un cliente iscritto alla struttura
 * @param i indica quale cliente si vuole
 * @return clienti.get(i) restituisce il cliente desiderato*/


public  Cliente getCliente(int i){
	return clienti.get(i);
}


/**
 * Restituisce il numero di clienti iscritti alla struttura
 * @return clienti.size() variabile di tipo int indica i clienti iscritti
 * */

public  int sizeClienti(){
	return clienti.size();
}

/**
 * Restituisc il numero di partite attive
 * @return c rappresenta il nummero di partite attive
 * */

public int partiteAttive(){
	int c=0;
	for(int i =0 ; i<calendario.size();i++){
		if(!calendario.get(i).isIniziata())
			c++;
	}
	return c;
}

/**
 * Metodo che aggiunge uno stadio alla struttura
 * @param s indica lo stadio da aggiungere
 */


public  void addStadio(Stadio s){
	this.stadi.add(s);
}

/**
 * Restituisce il numero di stadi della struttura
 * @return stadi.size() variabile di tipo int indica gli stadi
 * */

public  int sizeStadi(){
	return stadi.size();
}

/**
 * Restituisce uno stadio della struttura
 * @param i indica quale stadio si vuole
 * @return stadi.get(i) restituisce lo stadio desiderato*/

public  Stadio getStadio(int i){
	return stadi.get(i);
}

/**
 * Restituisce il numero di partite della settimana selezionata
 * @return elencoSettimana.size() numero di partite della settimana selezionata*/

public  int sizeSettimana(){
	return elencoSettimana.size();
}
/**
 * Restituisce una partita della settimana selezionata
 * @param i indica l indice della partite desiderata
 * @return elencoSettimana.get(i) rappresenta la partita nell posizione scelta*/

public  Partita getPartitaSettimana(int i){
	return elencoSettimana.get(i);
}

/**
 * Aggiunge uno scontoStadio alla struttura
 * @param sc indica lo sconto aggiunto
 * */

public void addScontoStadio(ScontoStadio sc){
	stadiScontati.add(sc);
	
}

/**
 * Restituisce il numero di stadi scontati
 * @return stadiScontati.size() indica il numero di stadi scontati*/
public int sizeStadiScontati (){
	return stadiScontati.size();
}

/**
 * Restituisce uno scontoStadio
 * @param i indica lo sconto desiderato
 * @return stadiScontati.get(i) indica lo sconto stadio desiderato*/

public ScontoStadio getStadioScontato(int i){
	return stadiScontati.get(i);
}

/**
 * Disattiva uno scontoStadio
 * @param s indica lo stadio a cui si vuole togliere lo scconto*/

public void disattivaScontoStadio(Stadio s){
	
for(int i =0 ; i<stadiScontati.size();i++){
		if(stadiScontati.get(i).getStadio().equals(s)){
			if(stadiScontati.size()==1){
				stadiScontati.get(i).disattivaSconto();	
								}
			
			stadiScontati.remove(stadiScontati.get(i));			
		     s.remScontato();}
	}
}

/**
 * Aggiunge uno scontoPartita alla struttura
 * @param sp indica lo scontoPartita da aggiungere
 * */

public void addScontoPartia(ScontoPartita sp){
	partiteScontate.add(sp);
	
}
/**
 *Restituisce il numero di partite scontate
 *@return partiteScontate.size() indica il numero di partite 
 * */
public int sizePartiteScontati (){
	return partiteScontate.size();
}
/**
 * Restituisce uno scontoPartita
 * @param i indica lo sconto desiderato
 * @return partiteScontate.get(i) indica lo sconto partita desiderato*/

public ScontoPartita getPartitaScontato(int i){
	return partiteScontate.get(i);
}

/**
 * Disattiva uno scontoPartita
 * @param p indica la partita a cui si vuole togliere lo scconto*/

public void disattivaScontoPartita(Partita p){
	
	for(int i =0 ; i<partiteScontate.size();i++){
		if(partiteScontate.get(i).getPartita().equals(p)){
			if(partiteScontate.size()==1){
				partiteScontate.get(i).disattivaSconto();}
			partiteScontate.remove(i);
			p.remScontato();}
		
	}}
/** Verifica se la politica di sconto partite è attiva
 * 
 * @return false se non è attiva true altrimenti
 */

public boolean ScontoPartitaIsAttivo(){
	if(partiteScontate.size()==0)
		return false;
		else return true;
}

/** Verifica se la politica di sconto stadi è attiva
 * 
 * @return false se non è attiva true altrimenti
 */

public boolean ScontoStadioIsAttivo(){
	if(stadiScontati.size()==0)
		return false;
		else return true;
}

/**Calcola il meglio prezzo tenendo conto di tutti gli sconti attivi
 * @param p indica la partita a cui si vuole calcolare il prezzo
 * @return min indica il miglior prezzo
 * */
	
	public double CalcolaMigliorPrezzo(Partita p){
		double pz=p.getPrezzoPartita(),
		         pp=pz,ps=pz,pg=pz;	
		
		if(ScontoFasciaGiornaliera.IsAttivo()){		
			 int f = ScontoFasciaGiornaliera.GetFascia();
		    if (f==1 && p.getData().get(11)>=15 && p.getData().get(11)<18){	    	
			    pg = pz -((pz*ScontoFasciaGiornaliera.GetPercentuale())/100);}
		    else if (f==2 && p.getData().get(11)>=18 && p.getData().get(11)<21){	    	
		    	pg = pz -((pz*ScontoFasciaGiornaliera.GetPercentuale())/100);}
		    else if (f==3 && p.getData().get(11)>=21 && p.getData().get(11)<24){	    	
		    	pg = pz -((pz*ScontoFasciaGiornaliera.GetPercentuale())/100);}
		    }
	    
		if(ScontoPartita.IsAttivo()){
			for(int i =0 ; i<partiteScontate.size();i++){
			if(p.equals(partiteScontate.get(i).getPartita()))
				pp = pz -((pz*partiteScontate.get(i).getPercentuale())/100);}}
		
		if(ScontoStadio.IsAttivo()){
			for(int i =0 ; i<stadiScontati.size();i++){
				if(p.getStadio().equals(stadiScontati.get(i).getStadio()))
					ps = pz -((pz*stadiScontati.get(i).getPercentuale())/100);}}
					
		
		//System.out.println(" "+ps+pp+pg);
		double min =(Math.min(pg, Math.min(pp, ps)));
		return min;
		
	}		

/**
 * Ordina alfabeticamente il calendario in base al nome dello stadio*/


public  void ordineStadio(){
	Partita temp ;
	for(int i =0; i<calendario.size();i++){
		for(int j=0; j<calendario.size();j++){
			if(calendario.get(i).getStadio().getNomeStadio().compareToIgnoreCase(calendario.get(j).getStadio().getNomeStadio())<0)
			{temp = calendario.get(i);
			calendario.set(i, calendario.get(j));
			calendario.set(j, temp);}}}		
				
}
/**
 * Ordina il calendario in base alla capienza degli stadi*/
public  void ordineCapienza(){
	Partita temp ;
	for(int i =0; i<calendario.size();i++){
		for(int j=0; j<calendario.size();j++){
			if(calendario.get(i).getStadio().getNumPosti()<calendario.get(j).getStadio().getNumPosti())
			{temp = calendario.get(i);
			calendario.set(i, calendario.get(j));
			calendario.set(j, temp);
			
				}
			}
		}
	
}
/**
 * Ordina il calendario in base alla data delle partite*/

public  void ordineCronologico(){
	Partita temp ;
	for(int i =0; i<calendario.size();i++){
		for(int j=0; j<calendario.size();j++){
			if(calendario.get(i).getData().before(calendario.get(j).getData()))
			{temp = calendario.get(i);
			calendario.set(i, calendario.get(j));
			calendario.set(j, temp);
			
				}
			}
		}	

}
/**
 * Ordina il calendario in base al nome delle squadre*/

public  void ordineSquadra(){
	Partita temp ;
	for(int i =0; i<calendario.size();i++){
		for(int j=0; j<calendario.size();j++){
			if((calendario.get(i).getSquadraCasa().concat(calendario.get(i).getSquadraTrasferta()).
		compareToIgnoreCase(calendario.get(j).getSquadraCasa().concat(calendario.get(j).getSquadraTrasferta()))<0))
			
			{temp = calendario.get(i);
			calendario.set(i, calendario.get(j));
			calendario.set(j, temp);
			}}}
}

/**
 * Filtra le partite del calendario selezionato quelle di una settimana
 * @param gg indica il giorno di inizio settimana
 * @param mm indica il giorno di fine settimana
 * @param aa indica l anno di inizio settiman*/

public  void partiteSettimana (int gg,int mm,int aa){
for(int j =0 ;j<elencoSettimana.size();j++ ) 
	elencoSettimana.remove(j--);
	     
	
GregorianCalendar dataA  = new GregorianCalendar(aa,mm,gg);
int g = dataA.get(5)+7;
GregorianCalendar dataB = new GregorianCalendar(dataA.get(1),dataA.get(2),g);
System.out.println(""+dataB.get(5)+" "+dataB.get(2)+" "+dataB.get(1));

	for (int i=0 ; i<calendario.size();i++){
		if( calendario.get(i).getData().equals(dataA)){
			elencoSettimana.add(calendario.get(i));
			System.out.println(""+calendario.get(i).getData());}
			
		if (calendario.get(i).getData().after(dataA) && calendario.get(i).getData().before(dataB))
			elencoSettimana.add(calendario.get(i));		
		}
	}


/**
 * Stampa il calendario su schermo*/
public   void stampaCalendario(){
	System.out.println("-----calendario-------------");
	for(int i=0; i<calendario.size();i++)
		{System.out.println(calendario.get(i).toString());}
	System.out.println("----------------------------");
}








}