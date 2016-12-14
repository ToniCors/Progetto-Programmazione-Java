package frame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.RenderingHints.Key;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.BorderUIResource;

import exception.Incompatibilt‡PartitaException;
import exception.PercentualeScontoException;
import exception.PostiNegativiException;
import frame.FrameCliente.ComboSettimanaInizioListener;
import strutturaSportiva.GestioneFile;
import strutturaSportiva.Partita;
import strutturaSportiva.ScontoFasciaGiornaliera;
import strutturaSportiva.ScontoPartita;
import strutturaSportiva.ScontoStadio;
import strutturaSportiva.Stadio;
import strutturaSportiva.StrutturaSportiva;

/**
 * Classe che crea un interfaccia Gestore*/

public class FrameGestore extends JFrame {
	private StrutturaSportiva strutturaSportiva;
	
	public FrameGestore(StrutturaSportiva s){
		strutturaSportiva=s;
		setTitle("Gestore");
		setSize(800, 600);
		setJMenuBar(creaBarraMenu());		
		this.add(creaPannelloPrincipale(), BorderLayout.CENTER);
		 cl.show(panel,"Benvenuto");
	    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	    //setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setVisible(true);
	    InputMap frameInputMap = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	    ActionMap frameActionMap = this.getRootPane().getActionMap();
	    frameInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_CONTEXT_MENU, 0), "azioneContext");
	    
	    frameActionMap.put("azioneContext", new AbstractAction() {
	        public void actionPerformed(ActionEvent e) {
	        	new FrameRiepilogo(strutturaSportiva);
	       	        
	            System.out.println("pressed menu");
	        }
	    });
	    
		
		
	}
		
		
	
	private JMenuBar creaBarraMenu(){
		JMenuBar menuBar = new JMenuBar();
		
		edit = new JMenu("Edit");	
		insp= new JMenuItem("Inserisci partite nel calendario");
		insp.addActionListener(insL);
		inss= new JMenuItem("Aggiungi stadi alla struttura");
		inss.addActionListener(instdL);
		asse= new JMenuItem("Assegna prezzo");
		asse.addActionListener(prezL);
		modposti = new JMenu("Modifica Posti");
		 plus= new JMenuItem("Aumenta posti");
		 plus.addActionListener(aumL);
		 malus= new JMenuItem("Diminuisci posti");
		 malus.addActionListener(toglL);
		cangePW= new JMenuItem("Cambia password");
		cangePW.addActionListener(cpL);
		sconto = new JMenu("Sconto");
		scontop = new JMenuItem("Attiva sconto partita");
		scontop.addActionListener(spL);
		scontos = new JMenuItem("Attiva sconto stadio");
		scontos.addActionListener(ssL);
		scontog = new JMenuItem("Attiva sconto fascia giornaliera");
		scontog.addActionListener(sgL);
		edit.add(insp);
		edit.add(inss);
		edit.addSeparator();
		edit.add(asse);
		edit.add(modposti);
		modposti.add(plus);
		modposti.add(malus);
		edit.add(sconto);
		sconto.add(scontop);
		sconto.add(scontos);
		sconto.add(scontog);
		
		edit.addSeparator();
		edit.add(cangePW);
		
		
		visual = new JMenu("Visualizzza");
		cro= new JMenuItem("Partite in ordine cronologico");
		cro.addActionListener(cronoL);
		cap= new JMenuItem("Partite in ordine per capienza stadio");
		cap.addActionListener(capieL);
		inc = new JMenuItem("Incasso stadi");
		inc.addActionListener(incassL);
		visual.add(cro);
		visual.add(cap);
		visual.add(inc);
		
		exit = new JMenu("Exit");
		esc= new JMenuItem("Logout");
		esc.addActionListener(loutL);
		exit.add(esc);		
		
			
		menuBar.add(edit);
		menuBar.add(visual);
		menuBar.add(exit);	
		
		
		
		return menuBar;
	}
	
	
	private JPanel creaPannelloPrincipale(){
		panel = new JPanel(cl);	
		
		 //	panel.addKeyListener(k);
		pb=new JPanel(new BorderLayout());
		JLabel l = new JLabel("         BENVENUTO AMMINISTRATORE");		
	      l.setFont(new Font("DialogInput", Font.BOLD, 30));
	      JPanel pb1=new JPanel(new GridLayout(3,1));
	      pb1.setBorder(new EtchedBorder());
	      JLabel a = new JLabel("  Gli Stadi Della Struttura Sono : "+ strutturaSportiva.sizeStadi());
	      a.setFont(new Font("sansserif", Font.ITALIC, 20));
	      JLabel b = new JLabel("  Le Partite Nel Calendario Sono : "+ strutturaSportiva.sizePartite());
	      b.setFont(new Font("sansserif", Font.ITALIC, 20));
	      JLabel c = new JLabel(" I Clienti Registrati Sono : "+ strutturaSportiva.sizeClienti());
	      c.setFont(new Font("sansserif", Font.ITALIC, 20));
	      pb1.setBackground(Color.white);
	     pb1.add(a);pb1.add(b);pb1.add(c);
	     pb.add(pb1,BorderLayout.CENTER); 
		pb.add(l,BorderLayout.NORTH);
		
		
		panel.add(pb,"home");
		panel.add(creaPannelloInserisci(),"inserisci");
		panel.add(creaPannelloAggStadio(),"aggiungi");
		panel.add(creaPannelloprezzo(),"prezzo");
		panel.add(creaPannelloAumenta(),"aumenta");
		panel.add(creaPannelloTogli(),"togli");
		panel.add(creaPannelloScontoP(),"scontop");
		panel.add(creaPannelloScontoS(),"scontos");
		panel.add(creaPannelloScontoG(),"scontog");
		panel.add(creaPannelloPass(),"pass");
		
		panel.add(creaPannelloOrdineCronologico(),"cronologico");
		panel.add(creaPannelloCapienza(),"capienza");
		panel.add(creaPannelloIncasso(),"incasso");
		return panel;
		
	}
	
	
	private JPanel creaPannelloInserisci(){
		JPanel p = new JPanel(new BorderLayout());		
		
		JPanel pp = new JPanel();	
		JLabel l = new JLabel("Inserisci Partite");
		l.setFont(new Font("DialogInput",Font.ITALIC,30));
		pp.add(l);
		
		JPanel ppp = new JPanel(new GridLayout(3,3));		
		JPanel p1 = new JPanel(new GridLayout(3,2));
		JLabel Pcasa= new JLabel("Squadra Casa");
		 fieldPcasa  = new JTextField(20);
		 JLabel Pfuori= new JLabel("Squadra Trasferta");
		 fieldPfuori = new JTextField(20);
		 JLabel Pprez= new JLabel("Prezzo Partita");
		 fieldPprez  = new JTextField(20);
		 p1.add(Pcasa);
		 p1.add(fieldPcasa);
		 p1.add(Pfuori);
		 p1.add(fieldPfuori);
		 p1.add(Pprez);
		 p1.add(fieldPprez);
		 
		 JPanel p2 = new JPanel();
		 p2.setBorder(new TitledBorder(new EtchedBorder(),"Seleziona Uno Stadio"));
		 comPstd = new JComboBox<Stadio>();
		 p2.add(comPstd);
		 
		 JPanel p3 = new JPanel();
		 p3.setBorder(new TitledBorder(new EtchedBorder(),"Seleziona La Data"));
		 comPgg= new JComboBox<Integer>();		
		 comPmmm= new JComboBox<Integer>();
		 comPaa= new JComboBox<Integer>();
		 p3.add(comPaa);  p3.add(comPmmm); p3.add(comPgg);
		 
		 for (int i=1;i<=31;i++)
			 comPgg.addItem(i);				
		 for(int i=1;i<=12;i++){
				switch(i){
				case 1: comPmmm.addItem("Gennaio"); break;
				case 2: comPmmm.addItem("Febbraio"); break;
				case 3: comPmmm.addItem("Marzo"); break;
				case 4: comPmmm.addItem("Aprile"); break;
				case 5: comPmmm.addItem("Maggio"); break;
				case 6: comPmmm.addItem("Giugno"); break;
				case 7: comPmmm.addItem("Luglio"); break;
				case 8: comPmmm.addItem("Agosto"); break;
				case 9: comPmmm.addItem("Settembre"); break;
				case 10: comPmmm.addItem("Ottobre"); break;
				case 11: comPmmm.addItem("Novembre"); break;
				case 12: comPmmm.addItem("Dicembre"); break;
				}
			}
		 for (int i=2016;i<=2020;i++)
				comPaa.addItem(i);
		 
		 comPaa.addActionListener(settList);
		 comPmmm.addActionListener(settList);
		 
		 JPanel p4 = new JPanel();
		 p4.setBorder(new TitledBorder(new EtchedBorder(),"Seleziona L'Ora"));
		 comPhh= new JComboBox<Integer>();
		 comPmin= new JComboBox<Integer>();
		 p4.add(comPhh);
		 p4.add(comPmin);
		 
		 JPanel p5 = new JPanel();
		 confPagg = new JButton("CONFERMA");
		 confPagg.addActionListener(confPins);
		 p5.add(confPagg);
		
		
		 for(int i =0; i< 60;i++)
			{comPmin.addItem(i);
			if(i>=15 && i<=23 )
				comPhh.addItem(i);
					}
		
		 
		ppp.add(p1);
		ppp.add(p2);
		ppp.add(p3);
		ppp.add(p4);
		p.add(p5,BorderLayout.SOUTH);
		p.add(pp,BorderLayout.NORTH);
		p.add(ppp,BorderLayout.CENTER);
		
		
		return p ;
	}
	
	
	private JPanel creaPannelloAggStadio(){
		
		JPanel p = new JPanel(new BorderLayout());
		JLabel l1 = new JLabel("              Inserisci Stadio");
		l1.setFont(new Font("DialogInput",Font.ITALIC,30));
		p.add(l1,BorderLayout.NORTH);
		JPanel pp = new JPanel(new GridLayout(2,2));
		
		JPanel pp1 =new JPanel ();
		pp1.setBorder(new EtchedBorder());		
		JLabel l = new JLabel ("Nome Stadio  ");
		fieldSnome = new JTextField(20);
		
		JPanel pp2 =new JPanel ();
		pp2.setBorder(new EtchedBorder());
		JLabel ll = new JLabel ("Numero Posti ");		
		fieldSnposti = new JTextField(20);
		
		JPanel pp3 =new JPanel ();
		pp3.setBorder(new EtchedBorder());
		confSagg = new JButton("AGGIUNGI STADIO");
		confSagg.addActionListener(confSins);
		pp1.add(l);
		pp1.add(fieldSnome);
		pp2.add(ll);
		pp2.add(fieldSnposti);
		pp3.add(confSagg);
		pp.add(pp1);pp.add(pp2);
		p.add(pp3,BorderLayout.SOUTH);
		p.add(pp,BorderLayout.CENTER);
		return p ;
	}
	
	private JPanel creaPannelloprezzo(){
		JPanel p = new JPanel(new BorderLayout());
		JLabel l = new JLabel("            Assegna Prezzo Partita");	
		l.setFont(new Font("DialogInput", Font.BOLD, 30));
		
		JPanel pp1 = new JPanel(new GridLayout(10,1));
		pp1.setBorder(new EtchedBorder());
		fieldPprezz = new JTextField(20);
		comPprez = new JComboBox<Partita>();
		confprezz = new JButton("CONFERMA");
		confprezz.addActionListener(confPprezz);
		
		JPanel pp2 = new JPanel();
		pp2.setBorder(new EtchedBorder());	
		
		pp1.add(comPprez);
		pp2.add(fieldPprezz);
		pp2.add(confprezz);
		p.add(l,BorderLayout.NORTH);
		p.add(pp1,BorderLayout.CENTER);
		p.add(pp2,BorderLayout.SOUTH);
		return p ;
	}
	
	private JPanel creaPannelloAumenta(){
		JPanel p = new JPanel(new BorderLayout());
		JLabel l = new JLabel("            Aumenta Posti");	
		l.setFont(new Font("DialogInput", Font.BOLD, 30));
		
		JPanel pp = new JPanel(new GridLayout(10, 1));
		pp.setBorder(new EtchedBorder());
		comSadd = new JComboBox<Stadio>();
		
		pp.add(comSadd);
		
		JPanel ppp = new JPanel();
		fieldadd = new JTextField(20);
		confadd = new JButton("Conferma");	
		confadd.addActionListener(confaddL);
		ppp.add(fieldadd);
		ppp.add(confadd);	
		
		p.add(l,BorderLayout.NORTH);
		p.add(pp,BorderLayout.CENTER);
		p.add(ppp,BorderLayout.SOUTH);
		return p ;
	}
	
	private JPanel creaPannelloTogli(){
		JPanel p = new JPanel(new BorderLayout());
		JLabel l = new JLabel("            Diminuisci Posti");	
		l.setFont(new Font("DialogInput", Font.BOLD, 30));
		
		JPanel pp = new JPanel(new GridLayout(10, 1));
		pp.setBorder(new EtchedBorder());
		comSrem = new JComboBox<Stadio>();
		pp.add(comSrem);
		
		JPanel ppp = new JPanel();
		fieldrem = new JTextField(20);
		confrem = new JButton("Conferma");
		confrem.addActionListener(confremL);
		
		ppp.add(fieldrem);
		ppp.add(confrem);
		p.add(l,BorderLayout.NORTH);
		p.add(pp,BorderLayout.CENTER);
		p.add(ppp,BorderLayout.SOUTH);
		
		return p ;
	}
	
	private JPanel creaPannelloScontoP(){
		JPanel p = new JPanel(new BorderLayout());
		JLabel l = new JLabel("                Sconto Partite");
		l.setFont(new Font("DialogInput",Font.ITALIC,30));
		JPanel p1 = new JPanel(new GridLayout(2, 1));
		nPstdM = new DefaultListModel<Partita>();
		yPstdM = new DefaultListModel<Partita>();	
		
		
		for (int i =0;i<strutturaSportiva.sizePartite();i++){
			if(!strutturaSportiva.getPartita(i).isIniziata()){
			if(strutturaSportiva.getPartita(i).isScontato())
				yPstdM.addElement(strutturaSportiva.getPartita(i));
			else
				nPstdM.addElement(strutturaSportiva.getPartita(i));
			}
		}
	
		JPanel  no =new JPanel(new BorderLayout());
		no.setBorder(new  TitledBorder(new EtchedBorder(),"Partite Non Scontate"));
		fieldPCs= new JTextField(10);
		confPs = new JButton("Applica Sconto   >> ");
		confPs.addActionListener( addlSPL);
		JPanel  no1 =new JPanel();
		//no1.setBorder(new  TitledBorder(new EtchedBorder(),"Conferma"));
		
		nPstd = new JList<Partita>(nPstdM);
		JScrollPane nlistScroller = new JScrollPane (nPstd);
		nlistScroller.setBounds(1, 30, 400, 400);
		no.add(nlistScroller,BorderLayout.CENTER);
		no1.add(fieldPCs);
		no1.add(confPs);
		no.add(no1,BorderLayout.SOUTH);
		
		
		JPanel yes = new JPanel(new BorderLayout());		
		yes.setBorder(new  TitledBorder(new EtchedBorder(),"Partite Scontati"));
		togliPs = new JButton(" <<  Disattiva Sconto");
		togliPs.addActionListener(remlSPL);
		JPanel yes1 = new JPanel();		
		//yes1.setBorder(new  TitledBorder(new EtchedBorder(),"Conferma"));
		
		
		yPstd = new JList<Partita>(yPstdM);
		JScrollPane ylistScroller = new JScrollPane (yPstd);
		yes.add(ylistScroller);
		yes1.add(togliPs);
		yes.add(yes1,BorderLayout.SOUTH);
		
		p.add(l,BorderLayout.NORTH);
		p.add(p1,BorderLayout.CENTER);
		p1.add(no);
		p1.add(yes);
		
		return p;
	}
	

	private JPanel creaPannelloScontoS(){
		JPanel p = new JPanel(new BorderLayout());
		JLabel l = new JLabel("                Sconto Stadio");
		l.setFont(new Font("DialogInput",Font.ITALIC,30));
		JPanel p1 = new JPanel(new GridLayout(1, 2));
		nSstdM = new DefaultListModel<Stadio>();
		ySstdM = new DefaultListModel<Stadio>();	
		
		
		for (int i =0;i<strutturaSportiva.sizeStadi();i++){
			if(strutturaSportiva.getStadio(i).isScontato())
				ySstdM.addElement(strutturaSportiva.getStadio(i));
			else
				nSstdM.addElement(strutturaSportiva.getStadio(i));
		}
	
		JPanel  no =new JPanel(new BorderLayout());
		no.setBorder(new  TitledBorder(new EtchedBorder(),"Stadi Non Scontati"));
		fieldSCs= new JTextField(10);
		confSs = new JButton("Applica Sconto   >> ");
		confSs.addActionListener(addlSSL);
		JPanel  no1 =new JPanel();
		no1.setBorder(new  TitledBorder(new EtchedBorder(),"Conferma"));
		
		nSstd = new JList<Stadio>(nSstdM);
		JScrollPane nlistScroller = new JScrollPane (nSstd);
		nlistScroller.setBounds(1, 30, 400, 400);
		no.add(nlistScroller,BorderLayout.CENTER);
		no1.add(fieldSCs);
		no1.add(confSs);
		no.add(no1,BorderLayout.SOUTH);
		
		
		JPanel yes = new JPanel(new BorderLayout());		
		yes.setBorder(new  TitledBorder(new EtchedBorder(),"Stadi Scontati"));
		togliSs = new JButton(" <<  Disattiva Sconto");
		togliSs.addActionListener(remlSSL);
		JPanel yes1 = new JPanel();		
		yes1.setBorder(new  TitledBorder(new EtchedBorder(),"Conferma"));
		
		
		ySstd = new JList<Stadio>(ySstdM);
		JScrollPane ylistScroller = new JScrollPane (ySstd);
		yes.add(ylistScroller);
		yes1.add(togliSs);
		yes.add(yes1,BorderLayout.SOUTH);
		
		p.add(l,BorderLayout.NORTH);
		p.add(p1,BorderLayout.CENTER);
		p1.add(no);
		p1.add(yes);
		
		return p ;
	}
	
	private JPanel creaPannelloScontoG(){
		JPanel p = new JPanel(new GridLayout(3, 1));
		//JPanel p = new JPanel(new BorderLayout());
		JLabel l = new JLabel("           Sconto Fascia Oraria");
		l.setFont(new Font("DialogInput",Font.ITALIC,30));
		pom = new JRadioButton("pomeridiana");
		ser = new  JRadioButton("Serale");
		not = new JRadioButton("Notturna");
		grup = new ButtonGroup();
		togliSg = new JButton("Annula Sconto");
		togliSg.addActionListener(toglSgL);
		confSg = new JButton("Applica sconto");
		confSg.addActionListener(addlSgL);
		fieldSCg1 = new JTextField(20);
		fieldSCg1.setEditable(false);
		grup.add(pom);grup.add(ser);grup.add(not);
		
		fieldSCg = new JTextField(30);
		fieldSCg.setEditable(false);
		fieldSCg2  = new JTextField(10);;
		JPanel r = new JPanel();
		r.setBorder(new TitledBorder(new EtchedBorder(),"Fasci Attualmente Attiva"));
		r.add(fieldSCg);
		r.add(fieldSCg1);
		r.add(togliSg);
		
		JPanel r1 = new JPanel();
		r1.setBorder(new TitledBorder(new EtchedBorder(),"Attiva Fascia"));
		r1.add(pom);r1.add(ser);r1.add(not);
		r1.add(fieldSCg2);
		r1.add(confSg);
		p.add(l);
		p.add(r);
		p.add(r1);
		return p ;
	}
	
	private JPanel creaPannelloPass(){
		JPanel p = new JPanel(new BorderLayout());
		JLabel l1 = new JLabel("            Cambia PassWord");	
		l1.setFont(new Font("DialogInput", Font.BOLD, 30));
		
		JPanel pp = new JPanel();
		JLabel l = new JLabel("Inserisci La Nuova Password");
		fieldpass = new JTextField(20);
		p.add(l1,BorderLayout.NORTH);
		pp.add(l);
		pp.add(fieldpass);
		confpass = new JButton("CONFERMA PASSWORLD");
		pp.add(confpass);
		confpass.addActionListener(confPwL);	
		p.add(pp);
		return p ;
	}
	
	

	private JPanel creaPannelloCapienza(){
		JPanel r = new JPanel ();
		r.setLayout(new GridLayout(3, 1));
		
		JPanel p1 = new JPanel ();
		JLabel l1 = new JLabel("PARTITE ORDINE CAPIENZA STADIO");	
		l1.setFont(new Font("DialogInput", Font.BOLD, 30));
		p1.add(l1);
		
		JPanel p = new JPanel ();
		p.setBorder(new TitledBorder(new EtchedBorder(),"Partite Attive"));
		areacap = new JTextArea();		
		areacap.setEditable(false);
		p.add(areacap);
		
		 JPanel p2 = new JPanel();
		 p2.setBorder(new TitledBorder(new EtchedBorder(),"Partite Scadute O Iniziate"));
		 areacap2 = new JTextArea();
		 areacap2.setEditable(false);
			p2.add(areacap2);
			
			r.add(p1);
			r.add(p);
			r.add(p2);
			
		return r;
	}
		
	private JPanel creaPannelloOrdineCronologico(){
		JPanel r = new JPanel ();
		 r.setLayout(new BorderLayout());
		 JLabel l = new JLabel("       PARTITE ORDINE CRONOLOGICO");		
	      l.setFont(new Font("DialogInput", Font.ITALIC, 30));
	      r.add(l,BorderLayout.NORTH);
		 Dimension minumumSize = new Dimension (100,50);
		 plistModel = new DefaultListModel<Partita> ();
		 /*
		 strutturaSportiva.ordineCronologico();
		 
		 for(int i=0;i<strutturaSportiva.sizePartite();i++)
			 plistModel.addElement(strutturaSportiva.getPartita(i));*/
		 
		 listp  = new JList <Partita>(plistModel);
		 JScrollPane plistScroller = new JScrollPane (listp);
			plistScroller.setPreferredSize (new Dimension (20, 20));
			  plistScroller.setMinimumSize(minumumSize);
				
		areacrono = new JTextArea();		
		areacrono.setEditable(false);
		areacrono.setFont(new Font ("DialogInput",Font.ITALIC,20));
		
		JSplitPane splitPanep = new JSplitPane (JSplitPane.VERTICAL_SPLIT,plistScroller,areacrono);
		splitPanep.setOneTouchExpandable(true);
		splitPanep.setDividerLocation(150); 
		
		  r.add(splitPanep);    
			 listp.addListSelectionListener(partitaListL);
			
		return r;
	}
	
 private JPanel creaPannelloIncasso(){
	 JPanel r = new JPanel ();
	 JPanel pp= new JPanel(new BorderLayout());
	
	    r.setLayout(new BorderLayout());
	Dimension minumumSize = new Dimension (100,50);
	
	listModel = new DefaultListModel<Stadio> ();
	
	for(int i=0;i<strutturaSportiva.sizeStadi();i++)
		listModel.addElement(strutturaSportiva.getStadio(i));
		
		list  = new JList <Stadio>(listModel);
		listScroller = new JScrollPane (list);
		listScroller.setPreferredSize (new Dimension (20, 20));
		  listScroller.setMinimumSize(minumumSize);	
		
	
		
				
		areadx  = new JTextArea();
		areadx2  = new JTextArea();
		 pp.add(areadx,BorderLayout.CENTER);
		 pp.add(areadx2,BorderLayout.NORTH);
		areadx.setEditable(false);
		areadx.setFont(new Font ("DialogInput",Font.BOLD,25));
		areadx2.setFont(new Font ("DialogInput",Font.ITALIC,20));
		areadx.setText("");
		areadx.setMinimumSize(minumumSize);
		
		
		areasud = new JTextArea();
		JScrollPane sb = new JScrollPane(areasud);
		areasud.setEditable(false);
		areasud.setFont(new Font ("DialogInput",Font.BOLD,12));
		areasud.setText("");
		areasud.setMinimumSize(minumumSize);
			
		topSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listScroller,pp);
		    topSplit.setOneTouchExpandable(true);
		    topSplit.setDividerLocation(150); 
     
        
        
		splitPane = new JSplitPane (JSplitPane.VERTICAL_SPLIT,topSplit,sb);
              splitPane.setOneTouchExpandable(true);
              splitPane.setDividerLocation(350);        
       
        
        r.add(splitPane);           
        
				
        
    
     list.addListSelectionListener(stadListL);
	 
        
	 
	
	 
	 return r;
 }
//////////////////////////LISTENER//////////////////////////////////////////////////////////////////////////////////////
 
 class ListListenerS implements ListSelectionListener {
	  	public void valueChanged(ListSelectionEvent e) {  	  
	  		
	  	areasud.setText("   PARTITE PROGRAMMATE NELLO STADIO SELEZIONATO \n");
	  	
	  	Stadio  s =list.getSelectedValue();
	  	areadx.setText(" INFORMAZIONI DELLO STADIO SELEZIONATO  " +"\n\n"+
	  	                    "  Nome Stadio = "  +s.getNomeStadio()+"\n"+
	  	                    "  Incasso = "      +s.getIncasso()   +"\n"+
	  			            "  Numero Posti = " +s.getNumPosti()  +"\n"+
	  	                    "  Sconto Attivo =  " +s.isScontato()
	  	                    );	
	  	
	  	
	     for(int i =0;i<s.sizePartite();i++){
	    	 areasud.append("\n"+s.getPartita(i)+"\n");
	     }  

	    }

	}
 
 class ListListenerP implements ListSelectionListener {
	  	public void valueChanged(ListSelectionEvent e) {  	  
	  		
	  		
	  	//int i =list.getSelectedIndex();
	  	Partita  p =listp.getSelectedValue();
	  	if(p!=null){
	  areacrono.setText(" INFORMAZIONI DELLA PARTITA SELEZIONATA  " +"\n\n"+
	  	                    "  Squadra Casa = "  +p.getSquadraCasa()+"\n"+
	  	                    "  Squadra Casa = "      +p.getSquadraTrasferta()   +"\n"+
	  			            "  Stadio = " +p.getStadio()  +"\n"+
	  	                    "  Prezzo originale =  " +p.getPrezzoPartita()  +"\n"+	  	                    
	  	                    "  Data =  " +p.getDataStr()  +"\n\n"+
	  	                  	  	                  
	  	              "  Incasso Partita =  " +p.getIncassoPartita()  +"\n"+
	  	                  "  Biglietti Venduti =  " +p.getVenduti()  +"\n"+
	  	                "  Biglietti Prenotati =  " +p.getPrenotati() +"\n"+
	  	              "  Biglietti Disponibili =  " +(p.getStadio().getNumPosti()- (p.getVenduti()-p.getVenduti())) +"\n\n"  	            
	  	                
			  );
	  if(!p.isIniziata())		  
            
		  areacrono.append("  Miglior Prezzo =  " +p.getPrezzoScontato()+"\n" +
				           "  Sconto Partita Attivo =  " +p.isScontato());
	  else if(p.isIniziata())
		  areacrono.append("  Miglior Prezzo =  Partita Scaduta O Gia Iniziata"  +"\n" +
				  " Sconto Partita Attivo =  Partita Scaduta O Gia Iniziata" );
	  
	  	}
	  	
	   

	    }

	}
 
 class ComboSettimanaInizioListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			comPgg.removeAllItems();
			int anno= (int) comPaa.getSelectedItem();
			int mese= (int) comPmmm.getSelectedIndex()+1;
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
				comPgg.addItem(i);
		}
	}
 
 class InspPListener implements ActionListener {

	public void actionPerformed(ActionEvent  e) {
		comPstd.removeAllItems();
		 for(int j =0 ; j<strutturaSportiva.sizeStadi();j++){
			 comPstd.addItem(strutturaSportiva.getStadio(j));
		 }
		cl.show(panel,"inserisci");		
	}
	
}
 
class ConfInsPListener implements ActionListener {
	public void actionPerformed(ActionEvent  t) {
		Partita p=null;
		int f =0;
		int g=comPgg.getSelectedIndex()+1,
			m=comPmmm.getSelectedIndex(),
			a=(int)comPaa.getSelectedItem();
		GregorianCalendar now = new GregorianCalendar();
		GregorianCalendar nowP = new GregorianCalendar(a,m,g);
		if(nowP.before(now)|| nowP.equals(now) ) f=1;
		try{
		p = new Partita (fieldPcasa.getText(),fieldPfuori.getText(),
				                 (Stadio)comPstd.getSelectedItem(),Double.parseDouble(fieldPprez.getText()),
				                 g,m,a,
				                 (int)comPhh.getSelectedItem(),comPmin.getSelectedIndex());}
		catch (Incompatibilt‡PartitaException e) {
			f=2;
			JOptionPane.showMessageDialog(null,"Lo Stadio Ë Occupato Per Quest'Ora","ERRORE DATA",JOptionPane.WARNING_MESSAGE);
		}
		if(f==1){JOptionPane.showMessageDialog(null,"La data deve essere almeno di domani","ERRORE DATA",JOptionPane.WARNING_MESSAGE);}

		if (f==0){
		JOptionPane.showMessageDialog(null,"Modifica Effettuata","AGGIUNGI PARTITA",JOptionPane.INFORMATION_MESSAGE);
		strutturaSportiva.addPartita(p);
		fieldPcasa.setText("");fieldPfuori.setText("");fieldPprez.setText("");
		 }
	
	}
}
 
 class InsSListener implements ActionListener {

	public void actionPerformed(ActionEvent  e) {
		cl.show(panel,"aggiungi");	
		
	}
	
} 
 
 class ConfInsSListener implements ActionListener {

	public void actionPerformed(ActionEvent  a) {
		int f=0,l=0;
		
		try{String h = fieldSnome.getText();}		
		catch(NumberFormatException e ){f=1;}	
		
        try{ int k =Integer.parseInt(fieldSnposti.getText());}		
		catch(NumberFormatException e ){l=1;}	
		
		finally{	
		
		if(f==1 || fieldSnome.getText().trim().equals("") ){
		JOptionPane.showMessageDialog(fieldSnome,"Il Campo Richiede Una Stringa","ERRORE INSERIEMENTO CAMPI",JOptionPane.ERROR_MESSAGE);
		fieldSnome.setBackground(Color.red);}
		
		else if(f==0 && l==0 ){	
			Stadio s = new Stadio (fieldSnome.getText(),Integer.parseInt(fieldSnposti.getText()));	
			strutturaSportiva.addStadio(s);
			listModel.addElement(s);
		JOptionPane.showMessageDialog(null,"Modifica Effettuata","AGGIUNGI STADIO",JOptionPane.INFORMATION_MESSAGE);
		fieldSnposti.setBackground(Color.white);
		fieldSnome.setBackground(Color.white);}
		
		fieldSnome.setText("");
		 fieldSnposti.setText("");}     
		
		if(l==1){		
		JOptionPane.showMessageDialog(fieldSnposti,"Il Campo Richiede Un Numero","ERRORE INSERIEMENTO CAMPI",JOptionPane.ERROR_MESSAGE);
		fieldSnposti.setBackground(Color.red);}
		 
		
	}
	
}
 
 class ConfAssPrezListener implements ActionListener{
		public void actionPerformed(ActionEvent  a) {	
		int	f=0,l=0;
		Partita p = null;
		
		try{  p =(Partita) comPprez.getSelectedItem();			}
		catch(ClassCastException e){
			f=1;
					}	
		
		try{p.setPrezzoPartita(Integer.parseInt(fieldPprezz.getText()));	}
         catch(NumberFormatException ee){
		          l=1;	
		          		}
		finally{			
		if(f==0 && l==0){
			JOptionPane.showMessageDialog(null,"Modifica Effettuata","AGGIORNA PREZZO",JOptionPane.INFORMATION_MESSAGE);
			p.setPrezzoScontato(strutturaSportiva.CalcolaMigliorPrezzo(p));			
			fieldPprezz.setBackground(Color.white);
			comPprez.setBackground(Color.WHITE);}
		 if(f==1){
			JOptionPane.showMessageDialog(null,"Non Hai Selezionato Uno Stadio","ERRORE INSERIEMENTO CAMPI ",JOptionPane.ERROR_MESSAGE);		
                comPprez.setBackground(Color.red);
			}
		 if (l==1) {
			JOptionPane.showMessageDialog(null,"Il Campo Richiede Un Numero ","ERRORE INSERIEMENTO CAMPI",JOptionPane.ERROR_MESSAGE);		
			fieldPprezz.setBackground(Color.red); }
		}
	}}

class AssPrezListener implements ActionListener{
	public void actionPerformed(ActionEvent  e) {
		comPprez.removeAllItems();
		comPprez.addItem("SELEZIONA UNA PARTIA");
		for(int i =0; i<strutturaSportiva.sizePartite();i++){
			if(!strutturaSportiva.getPartita(i).isIniziata())
			comPprez.addItem(strutturaSportiva.getPartita(i)); }
		cl.show(panel,"prezzo");		
	}
}

class AddPosListener implements ActionListener{
	public void actionPerformed(ActionEvent  e) {
		comSadd.removeAllItems();
		cl.show(panel,"aumenta");	
		for(int i =0; i<strutturaSportiva.sizeStadi();i++)
		comSadd.addItem(strutturaSportiva.getStadio(i));
	}
}

class ConfAddPosListener implements ActionListener{
	public void actionPerformed(ActionEvent  a) {
		int f =0;
		try {int k =Integer.parseInt(fieldadd.getText());		}
      catch(NumberFormatException e){    	f=1;        }		
		
		finally{
			if(f==0){
		JOptionPane.showMessageDialog(null,"Modifica Effettuata","AGGIUNGI POSTI",JOptionPane.INFORMATION_MESSAGE);
		Stadio s = (Stadio) comSadd.getSelectedItem();
		s.aggiungiPosti(Integer.parseInt(fieldadd.getText()));
		fieldadd.setBackground(Color.white);
		fieldadd.setText("");
		}
			
		else if(f==1){
			JOptionPane.showMessageDialog(fieldadd,"Il Campo Richiede Un Numero ","ERRORE INSERIEMENTO CAMPI",JOptionPane.ERROR_MESSAGE);		
			fieldadd.setBackground(Color.red);		}
			}		
	}
}

class ToglPosListener implements ActionListener{
	public void actionPerformed(ActionEvent  e) {
		comSrem.removeAllItems();
		for(int i =0; i<strutturaSportiva.sizeStadi();i++)
			comSrem.addItem(strutturaSportiva.getStadio(i));
		cl.show(panel,"togli");		
		
	}
}

class ConfToglPosListener implements ActionListener{
	public void actionPerformed(ActionEvent  e) {
		int f=0;
		Stadio s = (Stadio) comSrem.getSelectedItem();
		try {s.togliPosti(Integer.parseInt(fieldrem.getText()));}
		catch(NumberFormatException ee){    	f=1;        }	
		catch(PostiNegativiException ee2){
			f=2;
		}

		finally{
		if(f==0){
		JOptionPane.showMessageDialog(null,"Modifica Effettuata","DIMINUISCI POSTI",JOptionPane.INFORMATION_MESSAGE);
		fieldrem.setBackground(Color.white);		}
		else if (f==1){JOptionPane.showMessageDialog(fieldadd,"Il Campo Richiede Un Numero ","ERRORE INSERIEMENTO CAMPI",JOptionPane.ERROR_MESSAGE);		
		fieldrem.setBackground(Color.red);}
		else if (f==2){
			JOptionPane.showMessageDialog(fieldadd,"Vuoi Togliere Troppi Posti \nPosti  ancora disponibili : "+s.getNumPosti(),"ERRORE INSERIEMENTO CAMPI",JOptionPane.ERROR_MESSAGE);		

		}
		}
	}
}

class ScontoPListener implements ActionListener{
	public void actionPerformed(ActionEvent  e) {
		cl.show(panel,"scontop");		
	}
}

class AddScontoPListener implements ActionListener{
	public void actionPerformed(ActionEvent  a) {
		int f=0;
		double d = 1;
		Partita p =null;
		
			try{
		
		p= (Partita) nPstd.getSelectedValue();			
		d=Double.parseDouble(fieldPCs.getText());
		new ScontoPartita(p,d);
		double z =strutturaSportiva.CalcolaMigliorPrezzo(p);		
		p.setPrezzoScontato(z);
		
		}
		
			catch(NullPointerException ee){
				JOptionPane.showMessageDialog(fieldPCs,"Non Hai Selezionata Una Partita","ERRORE INSERIEMENTO CAMPI",JOptionPane.ERROR_MESSAGE);
				nPstd.setBackground(Color.RED);
				f=1;			}
			 catch(NumberFormatException e){
					JOptionPane.showMessageDialog(fieldPCs,"Il Campo Richiede Un Numero","ERRORE INSERIEMENTO CAMPI",JOptionPane.ERROR_MESSAGE);
					fieldPCs.setBackground(Color.RED);
					f=1;
				}			
			 catch(PercentualeScontoException eee){			
			JOptionPane.showMessageDialog(fieldPCs,"Percentuale di sconto non valida","ERRORE INSERIEMENTO CAMPI",JOptionPane.ERROR_MESSAGE);
			fieldPCs.setBackground(Color.red);
			f=1;}
			
			finally{
				
			if(f==0){				
			nPstdM.removeElement(p);
			yPstdM.addElement(p);
			strutturaSportiva.addScontoPartia(new ScontoPartita(p,d));	
			p.setPrezzoScontato(strutturaSportiva.CalcolaMigliorPrezzo(p));			
			fieldPCs.setBackground(Color.white);
			nPstd.setBackground(Color.white);
			for(int i=0;i<strutturaSportiva.sizePartiteScontati();i++ )
			{
				System.out.println(""+strutturaSportiva.getPartitaScontato(i).getPartita().toString());
			}
			
			}}
		
	}}

class RemScontoPListener implements ActionListener{
	public void actionPerformed(ActionEvent  a) {
		Partita p = (Partita)yPstd.getSelectedValue();		
		nPstdM.addElement(p);
		yPstdM.removeElement(p);
		strutturaSportiva.disattivaScontoPartita(p);
		p.setPrezzoScontato(strutturaSportiva.CalcolaMigliorPrezzo(p));
	}}


class ScontoSListener implements ActionListener{
	public void actionPerformed(ActionEvent  e) {
		cl.show(panel,"scontos");		
	}
}

class AddScontoSListener implements ActionListener{
	public void actionPerformed(ActionEvent  a) {
		int f=0;
		double d = 1;
		Stadio s=null;
		
		try{
		d=Double.parseDouble(fieldSCs.getText());
		s = (Stadio) nSstd.getSelectedValue();
		new ScontoStadio(s,d);
		for(int i =0 ; i<s.sizePartite();i++){
			s.getPartita(i).setPrezzoScontato(strutturaSportiva.CalcolaMigliorPrezzo(s.getPartita(i)));
		}
		}
		catch(NullPointerException eee){
			JOptionPane.showMessageDialog(fieldSCs,"Non Hai Selezionata Uno Stadio","ERRORE INSERIEMENTO CAMPI",JOptionPane.ERROR_MESSAGE);
			nSstd.setBackground(Color.RED);
			f=1;			}
		
		catch(NumberFormatException e){
				f=1;
				JOptionPane.showMessageDialog(fieldSCs,"Il Campo Richiede Un Numero","ERRORE INSERIEMENTO CAMPI",JOptionPane.ERROR_MESSAGE);
				fieldSCs.setBackground(Color.RED);
			}
		catch(PercentualeScontoException ee){
		   f=1;
			fieldSCs.setBackground(Color.red);
			JOptionPane.showMessageDialog(fieldSCs,"Percentuale di sconto non valida","ERRORE INSERIEMENTO CAMPI",JOptionPane.ERROR_MESSAGE);
             }
		finally{
			if(f==0){
			nSstdM.removeElement(s);
			ySstdM.addElement(s);
			strutturaSportiva.addScontoStadio(new ScontoStadio(s,d));			
			for(int i =0; i<s.sizePartite();i++){
				s.getPartita(i).setPrezzoScontato(strutturaSportiva.CalcolaMigliorPrezzo(s.getPartita(i)));
			}
			System.out.println("\n");
			for(int i =0;i< strutturaSportiva.sizeStadiScontati();i++){
				System.out.println(" "+strutturaSportiva.getStadioScontato(i).getStadio());
				
			}
			
			fieldSCs.setBackground(Color.white);
			nSstd.setBackground(Color.white);}}
		
	}}

class RemScontoSListener implements ActionListener{
	public void actionPerformed(ActionEvent  a) {
		Stadio s =(Stadio) ySstd.getSelectedValue();
		nSstdM.addElement(s);
		ySstdM.removeElement(s);
		strutturaSportiva.disattivaScontoStadio(s);
		for(int i =0 ; i<s.sizePartite();i++){
			s.getPartita(i).setPrezzoScontato(strutturaSportiva.CalcolaMigliorPrezzo(s.getPartita(i)));
		}
				
	}}

class ScontoGListener implements ActionListener{
	public void actionPerformed(ActionEvent  e) {		
		fieldSCg.setText(ScontoFasciaGiornaliera.GetFasciaStr());
		fieldSCg2.setBackground(Color.WHITE);
		fieldSCg1.setText(""+ScontoFasciaGiornaliera.GetPercentuale());
		fieldSCg2.setText("");
		if(!ScontoFasciaGiornaliera.IsAttivo()){
			togliSg.setEnabled(false);		
			pom.setEnabled(true);
			ser.setEnabled(true);
			not.setEnabled(true);}
		
		if(ScontoFasciaGiornaliera.IsAttivo()){
			togliSg.setEnabled(true);
			pom.setEnabled(true);
			ser.setEnabled(true);
			not.setEnabled(true);
			if(ScontoFasciaGiornaliera.GetFascia()==1){
				pom.setEnabled(false);
			  				}
			else if(ScontoFasciaGiornaliera.GetFascia()==2){
				ser.setEnabled(false);
			   				}
			else if(ScontoFasciaGiornaliera.GetFascia()==3) {
				not.setEnabled(false);			  
				}		}
		cl.show(panel,"scontog");	}
}

class AddScontoGListener implements ActionListener{
	public void actionPerformed(ActionEvent  a) {
		double i=1;
		int f =0;		
		
		try{
			 i =Integer.parseInt(fieldSCg2.getText());
		   if(pom.isSelected()){
			ScontoFasciaGiornaliera.AttivaSconto(1,i);
			JOptionPane.showMessageDialog(null,"Modifica Effettuata Ora La Fascia Attiva Ë Pomeridiana","APPLICA SCONTO",JOptionPane.INFORMATION_MESSAGE);
}
			else if(ser.isSelected()){
			ScontoFasciaGiornaliera.AttivaSconto(2,i);
			JOptionPane.showMessageDialog(null,"Modifica Effettuata Ora La Fascia Attiva Ë Serale","APPLICA SCONTO",JOptionPane.INFORMATION_MESSAGE);
}
			else if(not.isSelected()){
			ScontoFasciaGiornaliera.AttivaSconto(3,i);
			JOptionPane.showMessageDialog(null,"Modifica Effettuata Ora La Fascia Attiva Ë Notturna","APPLICA SCONTO",JOptionPane.INFORMATION_MESSAGE);
}
			else{
				JOptionPane.showMessageDialog(null,"Non Hai Selezionato Nessuna Fascia","ERRORE INSERIEMENTO CAMPI",JOptionPane.WARNING_MESSAGE);
			}
			scontog.doClick();
			for(int j =0 ; j<strutturaSportiva.sizePartite();j++){
				strutturaSportiva.getPartita(j).setPrezzoScontato(strutturaSportiva.CalcolaMigliorPrezzo(strutturaSportiva.getPartita(j)));
			}}
		catch(NumberFormatException e){
			JOptionPane.showMessageDialog(fieldSCg2,"Il Campo Richiede Un Numero","ERRORE INSERIEMENTO CAMPI",JOptionPane.ERROR_MESSAGE);
			fieldSCg2.setBackground(Color.RED);
			f=1;	}			
		catch(PercentualeScontoException ee){
			f=1;
			fieldSCg2.setBackground(Color.red);
			JOptionPane.showMessageDialog(fieldSCg2,"Percentuale Non Valida","ERRORE INSERIEMENTO CAMPI",JOptionPane.ERROR_MESSAGE);}
			
		finally{
			if(f==0){			
			fieldSCg2.setBackground(Color.white);
			}}	
	
}}

class ToglScontoGListener implements ActionListener{
	public void actionPerformed(ActionEvent  e) {		
		ScontoFasciaGiornaliera.DisattivaSconto();		
		scontog.doClick();
		for(int j =0 ; j<strutturaSportiva.sizePartite();j++){
			strutturaSportiva.getPartita(j).setPrezzoScontato(strutturaSportiva.CalcolaMigliorPrezzo(strutturaSportiva.getPartita(j)));
		}		
		JOptionPane.showMessageDialog(null,"Modifica Effettuata","DISATTIVA SCONTO",JOptionPane.INFORMATION_MESSAGE);

}}
 
class CambiaPassListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		cl.show(panel,"pass");		
	}
}

class  CronoListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		areacrono.setText("");	
		cl.show(panel,"cronologico");	
		FrameGestore.this.setSize(800,740);
		plistModel.removeAllElements();
          strutturaSportiva.ordineCronologico();		 
		 for(int i=0;i<strutturaSportiva.sizePartite();i++)
			 plistModel.addElement(strutturaSportiva.getPartita(i));
		
		
	}
}

class CapieListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		areacap.setText("");
		areacap2.setText("");
		strutturaSportiva.ordineCapienza();
		for(int i =0; i<strutturaSportiva.sizePartite();i++)
			if(!strutturaSportiva.getPartita(i).isIniziata())
			areacap.append(strutturaSportiva.getPartita(i)+"\n");
			else
			areacap2.append(strutturaSportiva.getPartita(i)+"\n");
		
		cl.show(panel,"capienza");			
	}
}

class IncassoListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		cl.show(panel,"incasso");	
		double incasso=0;
	  	for(int i=0;i< strutturaSportiva.sizeStadi();i++){
	  		incasso=incasso+ strutturaSportiva.getStadio(i).getIncasso();
	  	}
	  	areadx2.setText("  INCASSO TOTALE DELLA STRUTTURA "+incasso+"\n\n");
	  	areadx.setText(" ");		
	}
}

class LogOutListener implements ActionListener {
	public void actionPerformed(ActionEvent  e) {
				
			FrameGestore.this.setVisible(false);
			new FrameMain(strutturaSportiva);
	}
}

class ConfPassListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		File nome = new File("gestore.txt");	
		int f =0;						
			if(fieldpass.getText().trim().length()!=0)
			{
				try {
					PrintStream out = new PrintStream (nome);	
					out.println(fieldpass.getText());
					out.close();
					JOptionPane.showMessageDialog(null,"Modifica Effettuata","CAMBIA PASSWORD",JOptionPane.INFORMATION_MESSAGE);
					fieldpass.setBackground(Color.white);	
				} 
				catch (FileNotFoundException e1) {					
				JOptionPane.showMessageDialog(null,"Errore Apertura File","ERRORE FILE",JOptionPane.ERROR_MESSAGE);
				fieldpass.setBackground(Color.blue);  }
			}
			else{
				JOptionPane.showMessageDialog(null,"il campo Ë vuoto","ERRORE RIEMPIMENTO CAMPO",JOptionPane.ERROR_MESSAGE);
				fieldpass.setBackground(Color.red);
			}
		}}





 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
ActionListener insL = new InspPListener();
ActionListener instdL = new InsSListener();
ActionListener prezL = new AssPrezListener();
ActionListener aumL = new AddPosListener();
ActionListener toglL = new ToglPosListener();
ActionListener spL = new ScontoPListener();
ActionListener ssL = new ScontoSListener();
ActionListener sgL = new ScontoGListener();
ActionListener cpL = new CambiaPassListener();
ActionListener cronoL = new CronoListener();
ActionListener capieL = new CapieListener();
ActionListener incassL = new IncassoListener();
ActionListener loutL = new LogOutListener();
ActionListener confPwL= new ConfPassListener();
ActionListener confaddL = new ConfAddPosListener();
ActionListener confremL = new ConfToglPosListener();
ActionListener confPprezz = new ConfAssPrezListener();
ActionListener confSins = new ConfInsSListener();
ActionListener confPins = new ConfInsPListener();
ActionListener settList = new ComboSettimanaInizioListener() ;
ActionListener toglSgL = new ToglScontoGListener();
ActionListener addlSgL = new AddScontoGListener();

ActionListener addlSSL = new AddScontoSListener();
ActionListener remlSSL = new RemScontoSListener();
ActionListener addlSPL = new AddScontoPListener();
ActionListener remlSPL = new RemScontoPListener();

ListSelectionListener stadListL = new  ListListenerS();
ListSelectionListener partitaListL = new  ListListenerP();





	private  CardLayout cl= new CardLayout();
	private JPanel pb;
	private JMenu edit, visual,exit,sconto,modposti;	 
	private JMenuItem cangePW,asse,insp,inss,cro,cap,esc,inc,plus,malus,scontop,scontos,scontog;
	private JPanel panel;	
	private JList <Stadio> list,
	nSstd,ySstd;
	private JList <Partita>  nPstd,yPstd,listp;
	private DefaultListModel <Stadio> listModel ,
	nSstdM,ySstdM;
	private DefaultListModel <Partita> nPstdM,yPstdM,plistModel;
	private JScrollPane listScroller;
	 
	private JTextField fieldpass,fieldadd,fieldrem,fieldPprezz,fieldSnposti,fieldSnome,fieldPcasa,fieldPfuori,fieldPprez,fieldSCg,fieldSCg1,fieldSCg2,
	           fieldSCs,fieldPCs;
	private JTextArea areacrono,areacap,areacap2,areadx,areadx2,areasud;
	private JSplitPane topSplit,splitPane;
	
	private JButton confpass,confadd,confrem,confprezz,confSagg,confPagg,confSg,togliSg,
	confSs,togliSs,confPs,togliPs;
	private JComboBox comboStad,comSadd,comSrem,comPprez,comPstd,comPgg,comPmmm,comPaa,comPhh,comPmin,comSCfa,comSCs,comSCp;
	private JRadioButton pom,ser,not;
	private ButtonGroup grup;


}
