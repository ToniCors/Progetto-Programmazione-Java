package strutturaSportiva;

import java.io.Serializable;
import java.util.*;

import exception.PercentualeScontoException;
/**
 * Questa classe rappresenta una politica di sconto per partita
 *implements Serializable */

public class ScontoPartita implements Serializable{	
	 private  double percentuale;
	 private static boolean attivo = false;
	 private Partita partita ;
	 
	 /**
	  * Crea un nuvo sconto partita caratteizzato da una partita ,e dalla percentuale
	  * @param p indica la partita a cui si applica lo sconto
	  * @param pe indica la percentuale dello sconto
	  * @throws PercentualeScontoException se la percentuale di sconto è minore di 0 o maggiore uguale di 100*/
	 
	 public ScontoPartita (Partita p,double pe){
		 if(pe <=0 || pe>=100)
				throw new PercentualeScontoException();
		 partita = p ;
		 percentuale = pe;
		 p.setScontato();	
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
		 * @return attivo risulta true se c'è almeno una partita scontata  false altrimenti*/	
		
		public static  boolean IsAttivo() {	
			
		return attivo;
		}
		
		/**
		 * Modifica lo stato dell politica di sconto (attivo o non attivo)
		 * @param b indica il nuovo stato dello sconto */
		
		public static void SetAttivo(boolean b) {				
			attivo = b;
			}
		
		/**Disattiva la politica di sconto
		 * */
		
		public void disattivaSconto(){
			attivo =false;
		}
		
		/**
		 * Restituisc la partita scontata
		 * @return partita variabile di tipo partita che rappresenta la partita scontata*/
		
		
		public Partita getPartita(){
			return partita;
		}
		
		public static String toStringS (){
			
			return "Sconto_Partita [attivo "+attivo+" nessuno sconto attivo]";
		}
		
		/**
		 * Converte un oggetto di tipo ScontoPartita in una stringa*/
		
		
		public String toString (){
			
			return "Sconto_Partita [attivo "+attivo+" partita "+partita
			          +" percentuale " +percentuale+ "]";	
			
		}
	
	
	

	
	
}
