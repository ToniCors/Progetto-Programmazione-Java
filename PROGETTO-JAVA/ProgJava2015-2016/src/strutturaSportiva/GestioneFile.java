package strutturaSportiva;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;


/**
 * Questa classe statica  fornisce i metodi per la gestione dei file 
 */
public class GestioneFile {
private static File nomeFile = new File ("file.dat"); 
private static File nomeFileFG = new File("file2.txt");


/**
 * Modifica i file a cui accedere
 * @param f indica il file dove avverra la serealizzazione
 
 */
public static void ChangeFile(String f,String f2){
	nomeFile = new File (f+".dat");
	nomeFileFG  = new File (f2+".txt");
}

/**
 * Scrive un oggetto sul file presente nell'oggetto di tipo GestioneFile
 * 
 * @param s oggetto di tipo StrutturaSportiva che rappresenta l'oggetto da scrivere su file
 * 
 * @throws IOException errore che puo essere lanciato durante la scrittura del file*/

public static void WriteStrutturaSportiva (StrutturaSportiva s) throws IOException{
	PrintStream ps =new PrintStream(nomeFileFG);
	ps.println(""+ScontoFasciaGiornaliera.IsAttivo());
	ps.println(""+ScontoFasciaGiornaliera.GetPercentuale());
	ps.println(""+ScontoFasciaGiornaliera.GetFascia());
	ps.println(""+ScontoStadio.IsAttivo());
	ps.println(""+ScontoPartita.IsAttivo());
	ps.close();
	System.out.println("scritto il primo");
	
	
	PrintWriter pw= new PrintWriter(nomeFile);
	ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream(nomeFile));
	out.writeObject(s);
	out.close();
	pw.close();
	System.out.println("scritto il secondo");
}

/**
 * Legge il contenuto del file di GestioneFile, se il file esiste
 *  
 * @throws IOException errore che puo essere lanciato durante la lettura del file
 * @throws ClassNotFoundException errore che puo essere lanciato durante la lettura del file
 * 
 * @return object oggetto di tipo Object che rappresenta l'oggetto letto da file
 */

public static Object ReadStrutturaSportiva () throws ClassNotFoundException, IOException{
	if(nomeFileFG.length()!=0){		    
		Scanner in = new  Scanner(nomeFileFG);
		boolean a=false,b=false ,c=false;
		double d=0.0;
		int i =0;
		
		     a= in.nextBoolean();
			 d =Double.parseDouble(in.next());
			 i=in.nextInt();
			 b=in.nextBoolean();
			 c=in.nextBoolean();
			 in.close();	
			 System.out.println("letto il primo");
		ScontoStadio.SetAttivo(b);	
		 ScontoPartita.SetAttivo(c);
		    
	     if(a)
	       ScontoFasciaGiornaliera.AttivaSconto(i,d);
	     else
	    	 ScontoFasciaGiornaliera.DisattivaSconto();
	}
	
	
	
	if(nomeFile.length()==0){		
		System.out.println("file vuoto");
		return null;}	
	else{
	ObjectInputStream in= new ObjectInputStream(new FileInputStream(nomeFile));	
	System.out.println("letto il secondo");
	return in.readObject();
	}
}
	
}
