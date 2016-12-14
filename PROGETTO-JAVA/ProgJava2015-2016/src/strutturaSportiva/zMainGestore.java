package strutturaSportiva;

import exception.Incompatibilt‡PartitaException;

public class zMainGestore {
	public static void main(String[] args) throws Incompatibilt‡PartitaException {
	StrutturaSportiva s = new StrutturaSportiva();
		
		Stadio sn= new Stadio("San Paolo",50);
		s.addStadio(sn);
		Stadio sm= new Stadio("San Siro ",50);
		s.addStadio(sm);
		Stadio sj= new Stadio("Juvenstus Stadium ",50);
		s.addStadio(sj);
		Stadio sr= new Stadio("Olimpico ",50);
		s.addStadio(sr);


		Partita pr_j= new Partita("Roma","Juve",sn,10,1,1,2017,0,0);
		s.addPartita(pr_j);
	
		Partita pn_m= new Partita("Napoli","Milan",sn,10,1,0,2017,0,0);
		s.addPartita(pn_m);
		Partita pj_r= new Partita("Juve","Roma",sj,10,5,1,2017,0,0);
		s.addPartita(pj_r);
		Partita pm_n= new Partita("Milan","Napoli",sm,10,7,1,2017,0,0);
		s.addPartita(pm_n);



		
		
		
		//sottrarre posti
			
		System.out.println("prima "+sn.getNumPosti()+"bigl "+pn_m.sizeBiglietti());
		sn.togliPosti(3);
		System.out.println("dopo "+sn.getNumPosti()+"bigl "+pn_m.sizeBiglietti());
		
		
		 //visualizzare incasso per ogni stadio
		
		for(int i =0 ; i<s.sizeStadi();i++){
			System.out.println(s.getStadio(i).getIncasso());
		}
		
//aumentare o ridurre la capienza degli stadi
	sn.aggiungiPosti(10);  System.out.println("a "+sn.getNumPosti());
	sn.getNumPosti();
	sm.togliPosti(5);   System.out.println(" b "+sm.getNumPosti());
	sm.getNumPosti();
		

		
		//stampa calendario		
		   s.stampaCalendario();
		  s.ordineCronologico();
		  s.stampaCalendario();
		 s.ordineCapienza();
		  s.stampaCalendario();
		
	
		
	}
}