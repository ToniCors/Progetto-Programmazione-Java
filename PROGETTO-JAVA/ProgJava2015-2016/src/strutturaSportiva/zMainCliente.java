package strutturaSportiva;
import java.util.*;

import exception.Incompatibilt‡PartitaException;

import java.io.*;
public class zMainCliente {

	public static void main(String[] args) throws Incompatibilt‡PartitaException {
		
		StrutturaSportiva s = new StrutturaSportiva();
		 
		Stadio sn= new Stadio("San Paolo",500);
	     s.addStadio(sn);
		Stadio sm= new Stadio("San Siro ",500);		
		s.addStadio(sm);
		Stadio sj= new Stadio("Juvenstus Stadium ",500);
		s.addStadio(sj);
		Stadio sr= new Stadio("Olimpico ",500);
		s.addStadio(sr);


		Partita pr_j= new Partita("Roma","Juve",sr,10,26,1,2016,0,0);
		s.addPartita(pr_j);		
		Partita pn_m= new Partita("Napoli","Milan",sn,10,1,0,2017,0,0);
		s.addPartita(pn_m);
		Partita pj_r= new Partita("Juve","Roma",sj,10,5,1,2017,0,0);
		s.addPartita(pj_r);
		Partita pm_n= new Partita("Milan","Napoli",sm,10,7,1,2017,0,0);
		s.addPartita(pm_n);
		

	Biglietto b = new Biglietto(1,pr_j);
    Biglietto b2 = new Biglietto(2,pr_j);
    
		Cliente c= new Cliente("a","b");
		s.addCliente(c);
		
		Cliente c1= new Cliente("c","b");
		s.addCliente(c1);
		
		System.out.println("\n-----------------------PRENOTAZIONI------------------------");
		for(int i = 0 ; i<c.getPrenotateSize();i++){
			System.out.println(""+c.getPrenotata(i));
		}System.out.println("----------------------------------------------------------\n");
		System.out.println("prenotazioni scadute "+ c.prenotazioniScadute());
		System.out.println("prenotazioni  "+ c.getPrenotateSize());
		System.out.println("acquisti "+ c.getAcquistateSize());
		
		c.prenotaBiglietto(pr_j, 1);
		System.out.println("prenotato prima");			
		System.out.println("\n-----------------------PRENOTAZIONI------------------------");
		for(int i = 0 ; i<c.getPrenotateSize();i++){
			System.out.println(""+c.getPrenotata(i));
		}System.out.println("----------------------------------------------------------\n");
		c1.prenotaBiglietto(pr_j, 2);			
		System.out.println("prenottato seconda");
		System.out.println("\n-----------------------PRENOTAZIONI------------------------");
		for(int i = 0 ; i<c.getPrenotateSize();i++){
			System.out.println(""+c.getPrenotata(i));
		}System.out.println("----------------------------------------------------------\n");
		
					
	    c.prenotaBiglietto(pr_j, 3);
		System.out.println("prenotato terza");
		System.out.println("\n-----------------------PRENOTAZIONI------------------------");
		for(int i = 0 ; i<c.getPrenotateSize();i++){
			System.out.println(""+c.getPrenotata(i));
		}System.out.println("----------------------------------------------------------\n");
		
		System.out.println("prenotazioni scadute "+ c.prenotazioniScadute());
		System.out.println("prenotazioni  "+ c.getPrenotateSize());
		System.out.println("acquisti "+ c.getAcquistateSize());
		
		System.out.println("prenotazioni scadute c1 "+ c1.prenotazioniScadute());
		System.out.println("prenotazioni c1 "+ c1.getPrenotateSize());
		System.out.println("acquisti c1 "+ c1.getAcquistateSize());
	
		
GregorianCalendar a = new GregorianCalendar(2016,1,15,17,0);
	 
		
		System.out.println(pr_j.getDataStr()+" modifica data");
		pr_j.setdata(a);
		System.out.println(pr_j.getDataStr()+" data modificata\n");
	
		//c.cancellaPrenotazioniScadute();
		
		System.out.println("prenotazioni scadute "+ c.prenotazioniScadute());
		System.out.println("prenotazioni scadute c1 "+ c1.prenotazioniScadute());
		
		s.controllaPrenotazioniScadute();
		
		System.out.println("prenotazioni  "+ c.getPrenotateSize());
		System.out.println("acquisti c1 "+ c.getAcquistateSize());
		
		System.out.println("prenotazioni  "+ c1.getPrenotateSize());
		System.out.println("acquisti c1 "+ c1.getAcquistateSize());
		
		
		
		
		System.out.println("-----------------------PRENOTAZIONI------------------------");
		for(int i = 0 ; i<c.getPrenotateSize();i++){
			System.out.println(""+c.getPrenotata(i));
		}System.out.println("----------------------------------------------------------");
	
	

		System.out.println("-----------------------ACQUISTI------------------------");
		for(int i = 0 ; i<c.getAcquistateSize();i++){
			System.out.println(""+c.getAcquistata(i));
		}System.out.println("-----------------------------------------------------");

	c.acquistaBiglietto(pr_j, 3);
	c.acquistaBiglietto(pr_j, 9);
	c.acquistaBiglietto(pr_j, 10);
			
	System.out.println("-----------------------PRENOTAZIONI------------------------");
	for(int i = 0 ; i<c.getPrenotateSize();i++){
		System.out.println(""+c.getPrenotata(i));
	}System.out.println("----------------------------------------------------------");


	System.out.println("-----------------------ACQUISTI------------------------");
	for(int i = 0 ; i<c.getAcquistateSize();i++){
		System.out.println(""+c.getAcquistata(i));
	}System.out.println("-----------------------------------------------------");
	

	
		System.out.println("\n-----------------------PRENOTAZIONI------------------------");
		for(int i = 0 ; i<c.getPrenotateSize();i++){
			System.out.println(""+c.getPrenotata(i));
		}System.out.println("----------------------------------------------------------\n");
	System.out.println("prenotazioni scadute "+ c.prenotazioniScadute());
	System.out.println("prenotazioni  "+ c.getPrenotateSize());
	System.out.println("acquisti "+ c.getAcquistateSize()+"\n");
	
	
	 GregorianCalendar ad = new GregorianCalendar(2016,1,15,17,0);
	 
		
		System.out.println(pr_j.getDataStr()+" modifica data");
		pr_j.setdata(ad);
		System.out.println(pr_j.getDataStr()+" data modificata\n");
		
		System.out.println("\n CONTROLLA LE PRENOTAZIONI \n"+ c.getPrenotateSize());
		
	s.controllaPrenotazioniScadute();	
	System.out.println("prenotazioni scadute "+ c.prenotazioniScadute());
	System.out.println("prenotazioni  "+ c.getPrenotateSize());
	System.out.println("acquisti "+ c.getAcquistateSize());
	
	System.out.println("\n-----------------------PRENOTAZIONI------------------------");
	for(int i = 0 ; i<c.getPrenotateSize();i++){
		System.out.println(""+c.getPrenotata(i));
	}System.out.println("----------------------------------------------------------\n");
	

	}

}
