package strutturaSportiva;

import exception.*;

/**
 * Classe statica che rappresenta lo sconto fascia giornaliera*/

public class ScontoFasciaGiornaliera {
	private static boolean attivo = false;
	private static  int fasciaAttiva =0;//1 15-17:59 ,2 18-20:59 ,3 21-23:59
	 private static  double percentuale=0.0;
	 
	/**
	 * Attiva uno sconto fascia giornaliera
	 * @param f indica la fascia da scontare
	 * @param p indica la percentuale di sconto*/
	
	public  static void AttivaSconto(int f,double p){
		if(p <=0 || p>=100)
			throw new PercentualeScontoException();
		fasciaAttiva =f ;
		attivo = true;	
		percentuale = p;
		}
	/**
	 * Disattiva lo sconto fascia giornaliera*/
	
	public static void DisattivaSconto(){
		fasciaAttiva =0;		
			attivo = false;
			percentuale =0.0;
			}	
	/**
	 * Restituisce la percentuale di socnto
	 * @return percentuale indica la percentuale di sconto*/
	
	public static double GetPercentuale(){
		return percentuale ;
		}
	/**
	 * Modifica la percentuale di sconto
	 * @param p è la nuova percentuale
	 */
	public static void SetPercentuale(double p){
		percentuale = p ;
		}	
	/**
	 * Restituisce la fascia scontata
	 * @return fasciaAttiva indica la fascia di sconto attiva  (0 nessuna,1 pomeridiana,2 serale,3 notturna)*/
	public static int GetFascia(){
		return fasciaAttiva ;
		}
	
	/**
	 * Restituisce la fascia scontata in formato Stringa
	 * @return  la fascia di sconto attiva  nessuna, pomeridiana, serale, notturna)*/
	public static String GetFasciaStr(){
		if (fasciaAttiva ==0)
			return "Nessuan Fascia Attiva";
		if (fasciaAttiva ==1)
			return "Pomeridiana";
		else if (fasciaAttiva ==2)
			return "Serale";
		else return "Notturna";
	}
	
	/**
	 * Verifica se lo politica di sconto è attiva
	 * @return attivo risulta true se è attivo false altrimenti*/	
	public static boolean IsAttivo() {
		return attivo;		
	}
	
	/**
	 * Converte un oggetto di tipo sconto fascia giornaliera in una stringa 
	 * @return ritorna l' oggetto convertito in stringa
	 */

	public static String ToString (){
		return "Sconto_Fascia_Giornaliera [attivo "+attivo+" fascia attiva "+GetFasciaStr()
		          +" percentuale " +percentuale+ "]";		
	}
	

}
