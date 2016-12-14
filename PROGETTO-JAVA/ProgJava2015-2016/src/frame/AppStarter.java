package frame;

import java.io.IOException;

import exception.Incompatibilt‡PartitaException;
import strutturaSportiva.GestioneFile;
import strutturaSportiva.*;
import strutturaSportiva.StrutturaSportiva;

public class AppStarter {
public static void main(String[] args) throws ClassNotFoundException, IOException, Incompatibilt‡PartitaException {
//	/*
	StrutturaSportiva s = null;
	
	if((StrutturaSportiva) GestioneFile.ReadStrutturaSportiva()==null){
		 s = new StrutturaSportiva();
	}
	else{
		s =(StrutturaSportiva) GestioneFile.ReadStrutturaSportiva();
	}
	new FrameMain(s);
	//*/
	
	/*
	StrutturaSportiva s = new StrutturaSportiva();
	Stadio sn= new Stadio("San Paolo",500);
    s.addStadio(sn);
	//Stadio sm= new Stadio("San Siro ",500);		
	//s.addStadio(sm);
	Stadio sj= new Stadio("Juvenstus Stadium ",500);
	s.addStadio(sj);
	Stadio sr= new Stadio("Olimpico ",500);
	s.addStadio(sr);


	Partita pr_j= new Partita("Roma","Juve",sr,10,1,4,2016,16,0);
	s.addPartita(pr_j);
	Partita pn_m= new Partita("Napoli","Milan",sn,10,1,4,2017,20,0);
	s.addPartita(pn_m);
	Partita pj_r= new Partita("Juve","Roma",sj,10,1,4,2017,23,0);
	s.addPartita(pj_r);
	//Partita pm_n= new Partita("Milan","Napoli",sm,10,7,1,2017,0,0);
	//s.addPartita(pm_n);
	 
	Cliente c = new Cliente("toni","cors");
	s.addCliente(c);	
	new FrameMain(s);*/
	
}
}
