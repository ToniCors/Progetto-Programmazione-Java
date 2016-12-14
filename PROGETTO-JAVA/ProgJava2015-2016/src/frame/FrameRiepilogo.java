package frame;

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import strutturaSportiva.ScontoFasciaGiornaliera;
import strutturaSportiva.ScontoPartita;
import strutturaSportiva.ScontoStadio;
import strutturaSportiva.StrutturaSportiva;

/**
 * Classe che crea un interfaccia Gestore
 * */
public class FrameRiepilogo extends JFrame{
	private  StrutturaSportiva strutturaSportiva;
	private  JTextArea ta = new JTextArea();
	private  JScrollPane a = new JScrollPane(ta);
	private  JFrame frame = new JFrame("Riepilogo");
	

 public FrameRiepilogo(StrutturaSportiva s){
	strutturaSportiva = s;			
	frame.setSize(550, 700);		
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	frame.setLocation(816, 0);
		
	ta.setEditable(false);
	ta.append("STADI \n");
	for(int i = 0; i<strutturaSportiva.sizeStadi();i++){
		ta.append(strutturaSportiva.getStadio(i).toStringCompleto()+"\n");
	}
	
	ta.append("\n CALENDARIO \n");
	for(int j = 0; j<strutturaSportiva.sizePartite();j++){
		ta.append(strutturaSportiva.getPartita(j).toStringCompleto()+"\n");
	}
	
	ta.append("\n Clienti \n");
	
	for(int j = 0; j<strutturaSportiva.sizeClienti();j++){
		ta.append(strutturaSportiva.getCliente(j).toString()+"\n");
	}
	
	ta.append("\n Stadi Scontati \n");
	
	if(strutturaSportiva.sizeStadiScontati()==0)
		ta.append(ScontoStadio.toStringS()+"\n");
	
	for(int j = 0; j<strutturaSportiva.sizeStadiScontati();j++){
		ta.append(strutturaSportiva.getStadioScontato(j).toString()+"\n");
	}
	
	ta.append("\n Partite Scontate \n");
	
	
	if(strutturaSportiva.sizePartiteScontati()==0)
		ta.append(ScontoPartita.toStringS()+"\n");
		
	for(int j = 0; j<strutturaSportiva.sizePartiteScontati();j++){
		ta.append(strutturaSportiva.getPartitaScontato(j).toString()+"\n");
	}
	
	ta.append("\n Fascia Giornaliera Scontata \n");
	ta.append(ScontoFasciaGiornaliera.ToString()+"\n");
	
	 frame.add(a);
	frame.setVisible(true);
 }
	
		
	
	/*
	public static void AggiornaRiepilogo(StrutturaSportiva s){
		ta.setText("");
		 strutturaSportiva = s;			
		frame.setSize(550, 700);		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocation(816, 0);
			
		ta.setEditable(false);
		ta.append("STADI \n");
		for(int i = 0; i<strutturaSportiva.sizeStadi();i++){
			ta.append(strutturaSportiva.getStadio(i).toStringCompleto()+"\n");
		}
		
		ta.append("\n CALENDARIO \n");
		for(int j = 0; j<strutturaSportiva.sizePartite();j++){
			ta.append(strutturaSportiva.getPartita(j).toStringCompleto()+"\n");
		}
		
		ta.append("\n Clienti \n");
		
		for(int j = 0; j<strutturaSportiva.sizeClienti();j++){
			ta.append(strutturaSportiva.getCliente(j).toString()+"\n");
		}
		
		ta.append("\n Stadi Scontati \n");
		
		if(strutturaSportiva.sizeStadiScontati()==0)
			ta.append(ScontoStadio.toStringS()+"\n");
		
		for(int j = 0; j<strutturaSportiva.sizeStadiScontati();j++){
			ta.append(strutturaSportiva.getStadioScontato(j).toString()+"\n");
		}
		
		ta.append("\n Partite Scontate \n");
		
		
		if(strutturaSportiva.sizePartiteScontati()==0)
			ta.append(ScontoPartita.toStringS()+"\n");
			
		for(int j = 0; j<strutturaSportiva.sizePartiteScontati();j++){
			ta.append(strutturaSportiva.getPartitaScontato(j).toString()+"\n");
		}
		
		ta.append("\n Fascia Giornaliera Scontata \n");
		ta.append(ScontoFasciaGiornaliera.ToString()+"\n");
		
		 frame.add(a);
		// frame.setVisible(true);
		
	}*/

}
