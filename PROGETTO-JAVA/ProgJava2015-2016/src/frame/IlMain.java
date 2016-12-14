
package frame;

import java.awt.Color;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.Border;

import exception.Incompatibilt‡PartitaException;
import strutturaSportiva.*;


public class IlMain {

	public static void main(String[] args) throws IOException, Incompatibilt‡PartitaException, ClassNotFoundException {
		///*
		StrutturaSportiva s = null;
				
		if((StrutturaSportiva) GestioneFile.ReadStrutturaSportiva()==null){
			 s = new StrutturaSportiva();
		}
		else{
			s =(StrutturaSportiva) GestioneFile.ReadStrutturaSportiva();
		}
		
		///*
		//StrutturaSportiva s = new StrutturaSportiva();
		/*
		Stadio sn= new Stadio("San Paolo",500);
	     s.addStadio(sn);
		Stadio sm= new Stadio("San Siro ",500);		
		s.addStadio(sm);
		Stadio sj= new Stadio("Juvenstus Stadium ",500);
		s.addStadio(sj);
		Stadio sr= new Stadio("Olimpico ",500);
		s.addStadio(sr);


		Partita pr_j= new Partita("Roma","Juve",sr,10,1,1,2016,0,0);
		s.addPartita(pr_j);
		Partita pn_m= new Partita("Napoli","Milan",sn,10,1,0,2017,0,0);
		s.addPartita(pn_m);
		Partita pj_r= new Partita("Juve","Roma",sj,10,5,1,2017,0,0);
		s.addPartita(pj_r);
		Partita pm_n= new Partita("Milan","Napoli",sm,10,7,1,2017,0,0);
		s.addPartita(pm_n);
		 
		Cliente c = new Cliente("toni","cors");
		s.addCliente(c);	
		c.acquistaBiglietto(pn_m, 1);
		
		s.addScontoStadio(new ScontoStadio(sn, 50));		
		ScontoFasciaGiornaliera.AttivaSconto(2, 30);
		
		GestioneFile.WriteStrutturaSportiva(s);*/
		
		
		System.out.println("sc "+ScontoStadio.IsAttivo());
		System.out.println("sp "+ScontoPartita.IsAttivo());		
        System.out.println("sg "+ScontoFasciaGiornaliera.IsAttivo()+"per "+ScontoFasciaGiornaliera.GetPercentuale()+
        		"fas "+ScontoFasciaGiornaliera.GetFascia());	
        
for(int i =0;i< s.sizeStadiScontati();i++){
	System.out.println(" "+s.getStadioScontato(i).getStadio());	}


		
		
	//new FrameStadio(pr_j,c,s);
		
		
Cliente c = new Cliente("toni","cors");
		//new FrameMain(s);
		//new FrameRegistrazione(s);
		//new FrameGestore(s);
		new FrameCliente(c,s);
	}

}
