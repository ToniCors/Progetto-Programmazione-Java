package strutturaSportiva;

import java.io.Serializable;

/**
 * Questa classe rappresenta una politica di sconto per stadio
 *implements Serializable */

import java.util.ArrayList;

import exception.PercentualeScontoException;

public class ScontoStadio implements Serializable{
	private double percentuale;
	private static boolean attivo = false;
	private Stadio stadio ;
/**
 * Crea un nuvo sconto stadio caratteizzato dallo stadio ,e dall percentuale
 * @param s indica lo stadio a cui si applica lo sconto
 * @param p indica la percentuale dello sconto
  * @throws PercentualeScontoException se la percentuale di sconto è minore di 0 o maggiore uguale di 100*/

	
	
	public ScontoStadio(Stadio s,double p){
		if(p <=0 || p>=100)
			throw new PercentualeScontoException();
		stadio =s;
		percentuale = p ;
		s.setScontato();
		attivo=true;
	}
	
	/**
	 * Restituisce la percentuale di sconto
	 * @return percentuale indica la percentuale di sconto*/
	
	public double getPercentuale(){
		return percentuale ;
		}
	/**
	 * Modifica la percentuale di sconto
	 * @param p è la nuova percentuale
	 */
	
	public void setPercentuale(double p){
		percentuale = p ;
	}
	/**
	 * Verifica se lo politica di sconto è attiva
	 * @return attivo risulta true se c'è almo uno stadio scontato  false altrimenti*/	
	
	public  static boolean IsAttivo() {
		return attivo;		
	}
	
	/**
	 * Modifica lo stato dell politica di sconto (attivo o non attivo)
	 * @param b indica il nuovo stato dello sconto */
	public  static void SetAttivo(boolean b) {
		 attivo = b;		
	}
	
	/**Disattiva la politica di sconto
	 * */
	
	public void disattivaSconto(){
		attivo =false;
	}
	
	/**
	 * Restituisc lo stadio scontato
	 * @return stadio variabile di tipo stadio che rappresenta lo stadio scontato*/
	
	public Stadio getStadio(){
		return stadio;
	}
	
	public static String toStringS(){
		
		return "Sconto_Stadio [attivo "+attivo+" nessuno sconto attivo]";
	}
	
	/**
	 * Converte un oggetto di tipo ScontoStadio in una stringa*/
	
	public String toString (){
		
		return "Sconto_Stadio [attivo "+attivo+" stadio "+stadio
	          +" percentuale " +percentuale+ "]";	
	
	}
	


	

}
