package frame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import strutturaSportiva.Cliente;
import strutturaSportiva.GestioneFile;
import strutturaSportiva.StrutturaSportiva;

/**
 * Classe che crea un interfaccia di loggin*/

public class FrameMain extends JFrame{
	private StrutturaSportiva strutturaSportiva;
	
	public FrameMain(StrutturaSportiva s){		
		strutturaSportiva=s;
		JFrame frame = new JFrame();	
		frame.setSize(400,350);		
		frame.setTitle("Struttura Sportiva");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		
/////////////////////////////////////CHIDERE L APPLICAZIONE/////////////////////////////////////////////////	
		JMenuBar menuBar = new JMenuBar() ;
		JMenu exit = new JMenu("Exit");
		JMenuItem esc= new JMenuItem("chiudi app");
		class Litener8 implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				try {
					GestioneFile.WriteStrutturaSportiva(strutturaSportiva);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null,"Errore Apertura File","OPS NON DOVEVA CAPITARE",JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				frame.setVisible(false);
				System.exit(0); //chiude il programma
							}}
		Litener8 l8 = new Litener8();
		esc.addActionListener(l8);
		exit.add(esc);
		menuBar.add(exit);
		
/////////////////////////////////////INIZIO PANNELLO CLIENTI/////////////////////////////////////////////////
		JPanel panelSX = new JPanel();		
		panelSX.setLayout(new BorderLayout());
		JLabel l1= new JLabel("    CLIENTI");
		panelSX.add(l1,BorderLayout.NORTH);
/////////////////////////////////////LOGIN/////////////////////////////////////////////////
		JPanel panelSX2 = new JPanel();	
		panelSX2.setLayout(new GridLayout());
		panelSX2.setLayout(new GridLayout(5, 5));
		panelSX2.setBorder(new TitledBorder(new EtchedBorder(),"LOGIN"));
		JLabel l2= new JLabel("USERNAME");
		JTextField t1=new JTextField(10);
		JLabel l3= new JLabel("PASSWORD");
		JTextField t2=new JTextField(10);		
      JButton button2=new JButton("login");		
		class ButtonListener2 implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				File cliente = new File("clienti.txt");
				
				try {
					Scanner sc = new Scanner(cliente);
					boolean f = false;
					String p,u;
					u=t1.getText();
					p=t2.getText();
					while(sc.hasNext()&& f== false)
					{if(u.equals(sc.next())&& p.equals(sc.next()))
						f=true;						
					}
					
					if(f==true)	{	
						
						new FrameCliente(s.findCliente(t1.getText(), t2.getText()),s);
						strutturaSportiva.controllaPrenotazioniScadute();
						frame.setVisible(false);	}
						else{
				JOptionPane.showMessageDialog(frame,"PassWord o Username Errati","ERRORE DI LOGIN",JOptionPane.ERROR_MESSAGE);
						}
					
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(null,"Errore Apertura File","OPS NON DOVEVA CAPITARE",JOptionPane.ERROR_MESSAGE);
				}
				
			}			
		}
		ActionListener listener2=new ButtonListener2();
		button2.addActionListener(listener2);		
		panelSX2.add(l2);
		panelSX2.add(t1);
		panelSX2.add(l3);
		panelSX2.add(t2);
		panelSX2.add(button2);
		panelSX.add(panelSX2,BorderLayout.CENTER);
		
		
/////////////////////////////////////REGISTRAZIONE/////////////////////////////////////////////////
		JPanel panelSX3 = new JPanel();	
		panelSX3.setBorder(new TitledBorder(new EtchedBorder(),"REGISTRAZIONE"));
		JButton button1=new JButton("Registrati");
		class ButtonListener1 implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				new FrameRegistrazione(s);
				frame.setVisible(false);			
			}			
		}
		ActionListener listener1=new ButtonListener1();
		button1.addActionListener(listener1);
		
		panelSX3.add(button1);
		panelSX.add(panelSX3,BorderLayout.SOUTH);
		
/////////////////////////////////////PANNELLO AMMINISTRATORE/////////////////////////////////////////////////
		JPanel panelDX = new JPanel();
		panelDX.setLayout(new BorderLayout());
		JLabel l4= new JLabel("GESTORE        ");
		JPanel panelDX1 = new JPanel();
		panelDX1.setLayout(new GridLayout());
		panelDX1.setLayout(new GridLayout(6,6));
		panelDX1.setBorder(new TitledBorder(new EtchedBorder(),"LOGIN"));
		
		
		JLabel l5= new JLabel("PASSWORD");
		JTextField t3=new JTextField(10);
		
		JButton button3=new JButton("login");
		
		class ButtonListener3 implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				File gestore = new File("gestore.txt");
				try {
					Scanner sg = new Scanner(gestore);
					if(t3.getText().equals(sg.next())){				
						new FrameGestore(s);
						frame.setVisible(false);
					
							}	
					else {
						JOptionPane.showMessageDialog(frame,"PassWord Errata","ERRORE DI LOGIN",JOptionPane.ERROR_MESSAGE);					
									}
				} catch (FileNotFoundException e2) {}
				
				
			}			
		}
		ActionListener listener3=new ButtonListener3();
		button3.addActionListener(listener3);
		
		panelDX1.add(l5);
		panelDX1.add(t3);
		panelDX1.add(button3);
		panelDX.add(l4,BorderLayout.NORTH);
		panelDX.add(panelDX1);	
		frame.setJMenuBar(menuBar);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		
		frame.add(panelSX,BorderLayout.WEST);
		frame.add(panelDX,BorderLayout.EAST);		
		frame.setVisible(true);
		
		InputMap frameInputMap = frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	    ActionMap frameActionMap = frame.getRootPane().getActionMap();
	    frameInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_CONTEXT_MENU, 0), "azioneContext");
	    
	    frameActionMap.put("azioneContext", new AbstractAction() {
	        public void actionPerformed(ActionEvent e) {
	        	new FrameRiepilogo(strutturaSportiva);
	        //	FrameRiepilogo.AggiornaRiepilogo(strutturaSportiva);	            FrameRiepilogo.V();
	            System.out.println("pressed menu");
	        }
	    });
		}
		
		

	

}
