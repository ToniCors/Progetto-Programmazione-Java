package strutturaSportiva;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import exception.BigliettiTerminatiException;

import exception.Incompatibilt‡PartitaException;

/**
 * Questa classe rappresenta una partita. Ogni partita contiene un elenco di biglietti.
 *
 *@implements Serializable
 */

public class Partita implements Serializable{

private String squadraCasa, squadraTrasferta;
private double prezzoPartita;
private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
private GregorianCalendar data;
private Stadio stadio;
private ArrayList <Biglietto> biglietti = new ArrayList<Biglietto>();
private double prezzoScontato ;
private boolean scontato = false;
private int bigliettiVenduti =0;
private int bigliettiPrenotati=0;
private double incassoPartita =0;

/**
 * Crea una partita composta da: squadra casa, squadra ospite, stadio, prezzo, data e ora.
 * Al momento della creazione, ad ogni partita viene associata una lista di biglietti .
 * 
 * @param s1 variabile di tipo String che rappresenta la squadra che gioca in casa
 * @param s2 variabile di tipo String che rappresenta la squadra ospite
 * @param s oggetto di tipo Stadio che rappresenta lo stadio in cui sar‡ disputata la partita
 * @param pz variabile di tipo double che rappresenta il prezzo
 * @param g variabile di tipo int che rappresenta il giorno
 * @param m variabile di tipo int che rappresenta il mese
 * @param a variabile di tipo int che rappresetna l' anno
 * @param hh variabile di tipo int che rappresenta l'ora
 * @param mm variabile di tipo int che rappresenta i minuti
 * @throws Incompatibilt‡PartitaException  eccezione che viene lanciata se lo stadio Ë gia occupato per quella data*/

 

public Partita(String s1, String s2, Stadio s,double pz, int g,int m,int a, int hh,int mm) throws Incompatibilt‡PartitaException{
	squadraCasa=s1;
	squadraTrasferta=s2;
	data = new GregorianCalendar(a,m,g,hh,mm); 	// in gregorian calendar i mesi partono da zero
	stadio =s ;
	prezzoPartita =pz;	
	stadio.addPartita(Partita.this);	
	for(int i =0; i< s.getNumPosti();i++)
		biglietti.add(new Biglietto(i,Partita.this));	
	prezzoScontato =pz;

}


/**
 * Restituisce la squadra che gioca in casa
 * 
 * @return squadraCasa variabile di tipo String che rappresenta la squadra che gioca in casa
 */

public String getSquadraCasa(){
	return squadraCasa;
}

/**
 * Restituisce la squadra che gioca fuori casa
 * 
 * @return squadraOspite variabile di tipo String che rappresenta la squadra che gioca fuori casa
 */

public String getSquadraTrasferta(){
	return squadraTrasferta;
}


/**
 * Restituisce il prezzo di una partita
 * 
 * @return prezzo variabile di tipo double che rappresenta il prezzo della partita
 */
public double getPrezzoPartita(){
	return prezzoPartita;
}
/**
 * Restituisce il prezzo di una partita
 * 
 * @return prezzo variabile di tipo double che rappresenta il prezzo scontato della partita
 */

public double getPrezzoScontato(){
	return prezzoScontato;
}

/**
 * Permette di modificare il prezzo scontato di una partita
 * 
 * @param pz variabile di tipo double che rappresenta il nuovo prezzo della partita
 */

public void setPrezzoScontato(double pz){
	 prezzoScontato = pz;
}


	
/**
 * Restituisce la data e l'ora di una partita
 * 
 * @return data oggetto di tipo GregorianCalendar che rappresenta la data e l'ora della partita
 */
public GregorianCalendar getData(){
	return data;
}

/**
 * Modifica la data della partita 
 * @param a indica la nuova data*/
public void setdata(GregorianCalendar a){
	data = a;
	System.out.println("data modificata ");
}

/**
 * Restituisce la data e l'ora di una partita
 * 
 * @return dataEora oggetto di tipo String che rappresenta la data e l'ora della partita
 */

public String getDataStr(){
	return  sdf.format( data.getTime() );
}
/**
 * Permette di modificare il prezzo di una partita
 * 
 * @param pz variabile di tipo double che rappresenta il nuovo prezzo della partita
 */
public void setPrezzoPartita(double pz){
	prezzoPartita = pz ;
}

/**
 * Restituisce lo stadio associato alla partita
 * 
 * @return stadio oggetto di tipo Stadio che rappresenta lo stadio associato alla partita
 */


public Stadio getStadio(){	
	return stadio;	
}
 
/**
 * Restituisce lo stato della partita
 * 
 * @return statoPartita true se la partita Ë iniziata; false altrimenti
 */

public boolean isIniziata(){
	GregorianCalendar dataOdierna= new GregorianCalendar();
	if(data.before(dataOdierna))
		return true;
	
	return false;
}

/** 
 * Restituisce il biglietto del posto richiesto
 * @param i indicca il posto richiesto
 * @return biglietti.get(i) variabile di tipo Biglietto che rappresenta il bglietto richiesto
 * */

public Biglietto getBiglietto(int i){
	return biglietti.get(i);
}
/**
 * Aggiunge biglietti alla partita  
 *@param plus indica il numero di biglietti da aggiungere
 
 */


public void addBiglietti(int plus){
	
	int k=biglietti.size()+plus;	
	for (int i=biglietti.size(); i<k; i++){
		System.out.println(""+i);
		biglietti.add(new Biglietto(i,Partita.this));}
}

/**
 * Rimuove biglietti alla partita  
 *@param malus indica il numero di biglietti da sottrarre
 
 */

public void removeBiglietti(int malus){
	
	int k=biglietti.size()-malus;
	for (int i=biglietti.size()-1 ;i>=k;i--){
		biglietti.remove(i);}
}

/**
 * Restituisce il numero di Biglietti di una partita
 * @return biglietti.size() indica il numero di biglietti*/

public int sizeBiglietti(){
	return biglietti.size();
}

/**
 * Converte un oggetto di tipo Partita in una stringa
 */

public String toString() {
	if(ScontoStadio.IsAttivo()||ScontoFasciaGiornaliera.IsAttivo()||ScontoPartita.IsAttivo()){
		return "Partita [squadraCasa=" + squadraCasa + ", squadraTrasferta=" + squadraTrasferta + ", PrezzoPartita="
		+ prezzoScontato+ ", data=" + this.getDataStr()+ ", stadio=" + stadio.getNomeStadio() + "]";
}
	else
	return "Partita [squadraCasa=" + squadraCasa + ", squadraTrasferta=" + squadraTrasferta + ", PrezzoPartita="
			+ prezzoPartita + ", data=" + this.getDataStr()+ ", stadio=" + stadio.getNomeStadio() + "]";
}

/**
 * Converte un oggetto di tipo Partita in una stringa includendo anche il prezzo scontato
 */

public String toStringCompleto() {
	
		return "Partita [squadraCasa=" + squadraCasa + ", squadraTrasferta=" + squadraTrasferta + ", PrezzoPartita="
		+ prezzoPartita+"PrezzoScontato"+prezzoScontato +", data=" + this.getDataStr()+ ", stadio=" + stadio.getNomeStadio() + "]";

}


/**
 * Indica se la partita Ë scontata o meno
 * 
 * @return scontato true se la partita Ë scontata; false altrimenti
 */


public boolean isScontato(){
	return scontato;
}

/**
 * Setta lo sconto della partita a true*/

public void setScontato(){
	scontato = true;
}
/**
 * Setta lo sconto della partita a false*/
public void remScontato(){
	scontato = false;
}

/**
 * Restituisce il numero di Biglietti venduti
 * @return bigliettiVenduti indica il numerro di biglietti venduti*/

public int getVenduti (){
	return bigliettiVenduti;
}

/**
 * Incrementa di uno il numero di bilgietti venduti*/
public void addVenduto(){
	bigliettiVenduti ++;
}


/**
 * Restituisce il numero di Biglietti prenotati
 * @return bigliettiPrenotati indica il numero di biglietti prenotati*/

public int getPrenotati (){
	return bigliettiPrenotati;
}

/**
 * Incrementa di uno il numero di bilgietti prenotati*/

public void addPrenotato(){
	bigliettiPrenotati ++;
}
/**
 * Decrementa di uno il numero di bilgietti prenotati
 * */
public void remPrenotati (){
	 bigliettiPrenotati--;
}
/**
 * Restituisce l'incasso di una partita
 * @return incassoPartita variabile di tipo double che indica l incasso della partita
 * */

public double getIncassoPartita(){
	return incassoPartita;
}
/**
 * Aggiorna l' incasso di una partita
 * @param p indica la quota da aggiungere
 * */
public void setIncassoPartita(double p){
	incassoPartita = incassoPartita+p;
}




}