package frame;


import java.awt.*;
import java.awt.event.*;
import java.io.*;
import strutturaSportiva.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
/**
 * Classe che crea un interfaccia Cliente*/

public class FrameCliente extends JFrame{
	
	private Cliente cliente;
	private StrutturaSportiva strutturaSportiva;
	
	public FrameCliente(Cliente c,StrutturaSportiva s){
		strutturaSportiva=s;
		cliente =c;
		this.setTitle("CLIENTE");
		this.setSize(900,450);
		this.setJMenuBar(creaMenu());
		this.add(creaPannelloPrincipale(), BorderLayout.CENTER);
		cl.show(p,"benvenuto");
		//this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		InputMap frameInputMap = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	    ActionMap frameActionMap = this.getRootPane().getActionMap();
	    frameInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_CONTEXT_MENU, 0), "azioneContext");
	    
	    frameActionMap.put("azioneContext", new AbstractAction() {
	        public void actionPerformed(ActionEvent e) {
	        	new FrameRiepilogo(strutturaSportiva);
	      //      System.out.println("pressed menu");
	        }
	    });
		
	}
	
	private JMenuBar creaMenu(){
		menu= new JMenuBar();
		
		bigl = new JMenu("Biglietto");	
		
		visP= new JMenuItem("visualizza prenotazioni");		
		bigl.add(visP);				
		visP.addActionListener(prenotazione);
		
		visA= new JMenuItem("visualizza acquisti");
		bigl.add(visA);		
		visA.addActionListener(acquisto);
		
				
		vis = new JMenu("Visualizzza");
		
		std= new JMenuItem("partite per stadio");		
		sett = new JMenuItem("Cerca...");
		alf = new JMenuItem("partite in ordine alfabetico");
		cro= new JMenuItem("partite in ordine cronologico");
		vis.add(std);		
		vis.add(alf);
		vis.add(cro);
		vis.add(sett);
		
		
		sett.addActionListener(set);		
		std.addActionListener(sta);		
		alf.addActionListener(alfa);		
		cro.addActionListener(crono);	
		
		exit = new JMenu("Exit");
		
		esc= new JMenuItem("log out");			
		esc.addActionListener(logout);
		exit.add(esc);	
			
		menu.add(bigl);
		menu.add(vis);
		menu.add(exit);	
		
		return menu;
	}
	
	private JPanel creaPannelloPrincipale(){
	
		p = new JPanel(cl);	
		pb=new JPanel(new BorderLayout());
		JLabel l = new JLabel("         BENVENUTO    "+cliente.getUsername());		
	      l.setFont(new Font("DialogInput", Font.BOLD, 30));
	      JPanel pb1=new JPanel(new GridLayout(4,1));
	      pb1.setBorder(new EtchedBorder());
	      
	      JLabel a2 = new JLabel("  Le Partie Prenotate Scadute : "+ cliente.prenotazioniScadute());
	      a2.setFont(new Font("sansserif", Font.ITALIC, 20));
            strutturaSportiva.controllaPrenotazioniScadute();
	      JLabel a = new JLabel("  Le Partie Prenotate Sono : "+ cliente.getPrenotateSize());
	      a.setFont(new Font("sansserif", Font.ITALIC, 20));
	      JLabel b = new JLabel("  Le Partite Nel Calendario Sono : "+ strutturaSportiva.partiteAttive());
	      b.setFont(new Font("sansserif", Font.ITALIC, 20));
	      JLabel c = new JLabel(" Le Partie Acquistate Sono : "+ cliente.getAcquistateSize());
	      c.setFont(new Font("sansserif", Font.ITALIC, 20));
	      
	      pb1.setBackground(Color.white);
	     pb1.add(a);pb1.add(a2);pb1.add(b);pb1.add(c);
	     pb.add(pb1,BorderLayout.CENTER); 
		pb.add(l,BorderLayout.NORTH);
		
		p.add(pb,"benvenuto");
		p.add(creaPannelloNomeStadio(), "nomeStadio");
		p.add(creaPannelloSettimana(), "settimana");
		p.add(creaPannelloOrdineAlfabetico(), "alfabetico");
		p.add(creaPannelloOrdineCronologico(), "cronologico");			
		p.add(creaPannelloSettimana(), "settimana");
		p.add(creaPannelloAcquistate(), "acquistate");	
		p.add(creaPannelloPrenotate(), "prenotate");
			
		return p;
	}
	
	private JPanel creaPannelloNomeStadio(){
		
		JPanel r = new JPanel ();
		r.setBorder(new TitledBorder (new EtchedBorder(),"Seleziona Partita"));
		
		
		
		combostadio = new JComboBox<Partita>();	
		strutturaSportiva.ordineStadio();
		for(int i =0; i<strutturaSportiva.sizePartite();i++)
			if(!strutturaSportiva.getPartita(i).isIniziata())
			     combostadio.addItem(strutturaSportiva.getPartita(i));	
		
		
	      
	    p1 = new JPanel();	
	    JLabel l = new JLabel("Lista Partite");			
	      l.setFont(new Font("DialogInput", Font.BOLD, 30));
	      p1.add(l);
	      
		p1.setLayout(new BoxLayout(p1,BoxLayout.PAGE_AXIS));
		r.add(combostadio);
		r.add(confermastd);	
		confermastd.addActionListener(conf);
	
		p1.add(r);
		
		return p1;
	}
	
	private JPanel creaPannelloSettimana(){
						
		JPanel s = new JPanel();
		
		s.setLayout(new GridLayout(1, 3));
		
		 g = new JComboBox<Integer>();
		for (int i=1;i<=31;i++)
			g.addItem(i);	
		m = new JComboBox<Integer>();
		
		 for(int i=1;i<=12;i++){
				switch(i){
				case 1: m.addItem("Gennaio"); break;
				case 2: m.addItem("Febbraio"); break;
				case 3: m.addItem("Marzo"); break;
				case 4: m.addItem("Aprile"); break;
				case 5: m.addItem("Maggio"); break;
				case 6: m.addItem("Giugno"); break;
				case 7: m.addItem("Luglio"); break;
				case 8: m.addItem("Agosto"); break;
				case 9: m.addItem("Settembre"); break;
				case 10:m.addItem("Ottobre"); break;
				case 11:m.addItem("Novembre"); break;
				case 12:m.addItem("Dicembre"); break;
				default :;break;
				}
			}
		
		
		/* m = new JComboBox<Integer>();
		for (int i=1;i<=12;i++)
			m.addItem(i);	*/
		
		a = new JComboBox<Integer>();
		for (int i=2016;i<=2020;i++)
			a.addItem(i);
		m.addActionListener(list);	
		a.addActionListener(list);	
		
		s.add(a);
		s.add(m);
		s.add(g);
		JPanel s1 = new JPanel();
		s1.add(s);
		s1.setBorder(new TitledBorder(new EtchedBorder(), "Inserisci Inizio Settimana"));
		JPanel s2 = new JPanel();
		troSett.addActionListener(trov);
		s2.add(troSett);	
		JPanel s3 = new JPanel();
		s3.setBorder(new TitledBorder(new EtchedBorder(), "Cerca Per Settimana"));
		s3.setLayout(new GridLayout(2, 1));
		 s3.add(s1);
		 s3.add(s2);
		
		
		JPanel st = new JPanel();
		st.setBorder(new TitledBorder(new EtchedBorder(), "Seleziona Lo Stadio"));
		comboStadio = new JComboBox<Stadio>();
		st.add(comboStadio);
		JPanel st2 = new JPanel();
		troStd.addActionListener(trov2);
		st2.add(troStd);
		JPanel st3 = new JPanel();
		st3.setBorder(new TitledBorder(new EtchedBorder(), "Cerca Per Stadio"));
		st3.setLayout(new GridLayout(2, 1));
		 st3.add(st);
		 st3.add(st2);
		 
		 JPanel tgl = new JPanel();
		 tgl.setBorder(new TitledBorder(new EtchedBorder(), "Scegli..."));
		 JPanel tgl2 = new JPanel();
		 togleSett = new  JButton("CERCA SETTIMANA");
		 tgl2.add(togleSett);
		 JPanel tgl3 = new JPanel();
		 togleStad = new  JButton("CERCA STADIO");
		 tgl3.add(togleStad);
		tgl.add(tgl2);
		tgl.add(tgl3);
		
		JPanel nord = new JPanel();
		nord.setLayout(new GridLayout(1, 3));
		nord.add(s3);
		//nord.add(tgl);
		nord.add(st3);
		
		
	JPanel p = new JPanel();
	  comboset = new JComboBox<Partita>();		
			p.add(comboset);
			
	JPanel p2 = new JPanel();
	confermasett.addActionListener(conf1);
	p2.add(confermasett);
	
	JPanel sud = new JPanel();
	sud.setLayout(new GridLayout(2,1));
	sud.setBorder(new TitledBorder(new EtchedBorder(), "Risultato Ricerca..."));
	sud.add(p);
	sud.add(p2);
	
	JPanel tot = new JPanel ();
	
	tot.setLayout(new GridLayout(2, 1));
	tot.add(nord);
	tot.add(sud);
	
		
		return tot;
		
	}
	
	private JPanel creaPannelloOrdineCronologico(){
		JPanel r = new JPanel ();
		r.setBorder(new TitledBorder (new EtchedBorder(),"Seleziona Partita"));
		
		combocrono = new JComboBox<Partita>();		
		strutturaSportiva.ordineCronologico();
		for(int i =0; i<strutturaSportiva.sizePartite();i++)
			if(!strutturaSportiva.getPartita(i).isIniziata())
			combocrono.addItem(strutturaSportiva.getPartita(i));		
		
		p3 = new JPanel();
		p3.setLayout(new BoxLayout(p3,BoxLayout.PAGE_AXIS));
		JLabel l = new JLabel("Lista Partite");			
	      l.setFont(new Font("DialogInput", Font.BOLD, 30));
	      p3.add(l);
		
		r.add(combocrono);
		r.add(confermacrono);
		confermacrono.addActionListener(conf3);
		p3.add(r);
	
		return p3;
	}
	
	private JPanel creaPannelloOrdineAlfabetico(){
		
		JPanel r = new JPanel ();
		r.setBorder(new TitledBorder (new EtchedBorder(),"Seleziona Partita"));
		
		 comboalfa = new JComboBox<Partita>();		
		 strutturaSportiva.ordineSquadra();
		for(int i =0; i<strutturaSportiva.sizePartite();i++)
			if(!strutturaSportiva.getPartita(i).isIniziata())
			comboalfa.addItem(strutturaSportiva.getPartita(i));		
		
		
		p4 = new JPanel();
		p4.setLayout(new BoxLayout(p4,BoxLayout.PAGE_AXIS));
		JLabel l = new JLabel("Lista Partite");			
	      l.setFont(new Font("DialogInput", Font.BOLD, 30));
	      p4.add(l);
	      
		r.add(comboalfa);
		r.add(confermaalfa);
		p4.add(r);
		confermaalfa.addActionListener(conf2);
		
		
		return p4;
	}
	
	
	private JPanel creaPannelloPrenotate(){
		JPanel p  = new JPanel ();
		p.setLayout(new GridLayout(4,1));
		
		p5 = new JPanel();		
		
		JLabel l =new JLabel("PARTITE PRENOTATE");		
		l.setFont(new Font("sansserif", Font.BOLD, 30));
		p5.add(l);
		JPanel p4 = new JPanel();
		comboPrenotate = new JComboBox<Biglietto>();
		p4.add(comboPrenotate);
		
		JPanel p3 = new JPanel();		
		acqPren.addActionListener(acquPrenListener);
		p3.add(acqPren);
		
		JPanel p2 = new JPanel();
		cancPren.addActionListener(cancPrenListener);
		p2.add(cancPren);
		
		p.add(p5);p.add(p4);p.add(p3);
		p.add(p2);	
		
		return p;
	}
	
	private JPanel creaPannelloAcquistate(){
		
		JLabel l =new JLabel("PARTITE ACQUISTATE");		
		l.setFont(new Font("sansserif", Font.BOLD, 30));
		ta = new JTextArea(8,65);
		scrollPane = new JScrollPane(ta);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportBorder(new TitledBorder("LE TUE PARTITE")) ;
		p6 = new JPanel();		
		p6.setLayout(new BorderLayout());
		 JPanel p61 = new JPanel();
		
		 ta.setEditable(false);
		 
		 p61.add(scrollPane);
		p6.add(l,BorderLayout.NORTH);
	
		//p61.add(scrollPane,BorderLayout.CENTER);
		p6.add(p61,BorderLayout.CENTER);
		
		return p6;
	}

//////////////////////////LISTENER//////////////////////////////////////////////////////////////////////////////////////	
	
	class StdL implements ActionListener{
		public void actionPerformed(ActionEvent e) {			
			cl.show(p,"nomeStadio");
			
		}}
	
	class SettL implements ActionListener{
		public void actionPerformed(ActionEvent e) {			
			cl.show(p,"settimana");
			comboset.removeAllItems();
			comboStadio.removeAllItems();
			for(int i =0 ; i<strutturaSportiva.sizeStadi();i++)				
			comboStadio.addItem(strutturaSportiva.getStadio(i));
			
		}}
	
	class AlfL implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		
			cl.show(p,"alfabetico");
			
		}}
	
	class CroL implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			cl.show(p,"cronologico");
			
			
		}}
	
	class PrenotazioneListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			cl.show(p,"prenotate");
			comboPrenotate.removeAllItems();
			for(int i =0;i<cliente.getPrenotateSize();i++){
				comboPrenotate.addItem(cliente.getPrenotata(i));
			}
			pack();
			
		}
		
	}
	
	class AcquistoListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			cl.show(p,"acquistate");
			 for(int i =0;i<cliente.getAcquistateSize();i++){	
				 ta.append("- "+cliente.getAcquistata(i).getPartita()+"\n");
				 ta.append("      Posto: "+cliente.getAcquistata(i).getPosto()+"\n");
				 
				 			 }
		}
		
	}
			
	class ExitL implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			setVisible(false);
			new FrameMain(strutturaSportiva);
			}
	}
	
	class ComboSettimanaInizioListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			g.removeAllItems();
			int anno= (int) a.getSelectedItem();
			int mese= (int) m.getSelectedIndex();
			int giorni=31;
			boolean bisestile= false;
			
			switch (anno % 100){
			case 0:if (anno % 400 ==0)
						bisestile=true;
						break;
			default: if (anno % 4==0)
						bisestile=true;
						break;
			}
			if (bisestile){
				if (mese==2)
					giorni=29;
			}
			else{
				if (mese==2)
					giorni=28;
			}
			if (mese==4 || mese==6 || mese==9 | mese==11)
				giorni=30;
			for (int i=1;i<=giorni;i++)
				g.addItem(i);
		}
	}

	class TrovaListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {			
			int gg = (int)g.getSelectedItem();
			int mm = (int)m.getSelectedIndex();
			int aa = (int)a.getSelectedItem();
			
			comboset.removeAllItems();
			strutturaSportiva.partiteSettimana(gg, mm, aa);
			for(int i =0; i<strutturaSportiva.sizeSettimana();i++)
				if(!strutturaSportiva.getPartitaSettimana(i).isIniziata())
				comboset.addItem(strutturaSportiva.getPartitaSettimana(i));
			
			
		}
		
	}
	
	class TrovaStdListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			comboset.removeAllItems();
		Stadio s = (Stadio)comboStadio.getSelectedItem();		
		System.out.println(""+s.sizePartite());
			for(int j=0 ; j<s.sizePartite();j++){
				
					Partita p =s.getPartita(j);
					if(!p.isIniziata())
						comboset.addItem(p);
					}			
				}		
	}
	
	class ConfermaStadioListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
		Partita p = (Partita)combostadio.getSelectedItem();
		FrameCliente.this.setVisible(false);
		FrameCliente.this.setVisible(false);
		new FrameStadio(p,cliente,strutturaSportiva);
		}
		
	}

	class ConfermaSetListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
		Partita p = (Partita)comboset.getSelectedItem();
		FrameCliente.this.setVisible(false);
		FrameCliente.this.setVisible(false);
		new FrameStadio(p,cliente,strutturaSportiva);
		}
		
	}
	
	
	
	
	
	class ConfermaAlfaListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
		Partita p = (Partita)comboalfa.getSelectedItem();	
		FrameCliente.this.setVisible(false);
		FrameCliente.this.setVisible(false);
		new FrameStadio(p,cliente,strutturaSportiva);
		
		}
		
	}
	
	class ConfermaCronoListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
		Partita p = (Partita)combocrono.getSelectedItem();
		FrameCliente.this.setVisible(false);
		FrameCliente.this.setVisible(false);
		new FrameStadio(p,cliente,strutturaSportiva);
		}
		
	}
	
	class AcquistaPrenListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			Biglietto bacq = (Biglietto) comboPrenotate.getSelectedItem();
			cliente.acquistaPrenotazione(bacq);
			comboPrenotate.removeItem(bacq);
         JOptionPane.showMessageDialog(null,"Acquisto Avvenuto","ACQUISTO PRENOTAZIONE",JOptionPane.INFORMATION_MESSAGE);
        	}		
	}
	
	class CancellaPrenListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			Biglietto bcanc = (Biglietto) comboPrenotate.getSelectedItem();
			cliente.cancellaPrenotazione(bcanc);	
			comboPrenotate.removeItem(bcanc);
         JOptionPane.showMessageDialog(null,"Cancellazione Avvenuta","CANCELLA PRENOTAZIONE",JOptionPane.INFORMATION_MESSAGE);
      		}		
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	ActionListener cancPrenListener = new CancellaPrenListener();
	ActionListener acquPrenListener = new AcquistaPrenListener();
	ActionListener list = new ComboSettimanaInizioListener() ;
	ActionListener prenotazione = new PrenotazioneListener();
	ActionListener acquisto = new AcquistoListener();	
	ActionListener sta = new StdL();
	ActionListener set = new SettL();
	ActionListener alfa = new AlfL();
	ActionListener crono = new CroL();
	ActionListener logout = new ExitL();
	ActionListener trov = new TrovaListener();
	ActionListener trov2 = new TrovaStdListener();
	ActionListener conf = new ConfermaStadioListener();
	ActionListener conf1 = new ConfermaSetListener();	
	ActionListener conf2 = new ConfermaAlfaListener();	
	ActionListener conf3 = new ConfermaCronoListener();
	
	
	
	
	private CardLayout cl= new CardLayout();
	private JButton confermasett = new JButton("CONFERMA");
	private JButton confermaalfa = new JButton("CONFERMA");
	private JButton confermacrono = new JButton("CONFERMA");
	private JButton confermastd = new JButton("CONFERMA");	
	private JButton acqPren = new JButton("ACQUISTA PRENOTAZIONE");	
	private JButton cancPren = new JButton("CANCELLA PRENOTAZIONE");	
	
	private JTextArea  ta;
	private JScrollPane scrollPane ;
	private JButton troSett = new JButton("TROVA");
	private JButton troStd = new JButton("TROVA");
	
	private JComboBox<Partita> combostadio,combocrono,comboalfa,comboset;
	private JComboBox<Biglietto> comboPrenotate;	
	private JPanel p,pb, p1,p2,p3,p4,p5,p6;
	private JComboBox<Integer> g,a;
	private JComboBox m ;
	private JMenu bigl,vis,exit;
	private JMenuBar menu;
	private JLabel l = new JLabel("");
	private JMenuItem visP,visA,sett,alf,std,cro,esc;
	private JComboBox<Stadio> comboStadio;
	private JButton togleSett,togleStad;
	
	
	

}
