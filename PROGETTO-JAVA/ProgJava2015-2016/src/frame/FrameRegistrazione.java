package frame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;

import javax.swing.*;

import strutturaSportiva.Cliente;
import strutturaSportiva.StrutturaSportiva;

/**
 * Classe che crea un interfaccia di registrazione*/

public class FrameRegistrazione extends JFrame {
	
	private StrutturaSportiva strutturaSportiva;
	
	public FrameRegistrazione(StrutturaSportiva s){		
		strutturaSportiva=s;
		JFrame frame = new JFrame();
		frame.setTitle("Registrazione Cliente");
		frame.setSize(400, 200);
		frame.setLayout(new BorderLayout());
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		JPanel p1 = new JPanel();
		
		JLabel l2= new JLabel("USERNAME");
		JTextField t1=new JTextField(10);
		JLabel l3= new JLabel("PASSWORD");
		JTextField t2=new JTextField(10);
		JButton b1 = new JButton("invia");
		
		class ButtonListener1 implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				
				try {
					File cliente1 = new File("clienti.txt");
					Scanner sc = new Scanner(cliente1);
					boolean f = false;
					String p,u;
					u=t1.getText();
					p=t2.getText();
					while(sc.hasNext()&& f== false)
					{if(u.equals(sc.next()))
						f=true;						
					}
					sc.close();
					if(f==true)	{
			JOptionPane.showMessageDialog(frame,"Username Gia Esistente","ERRORE DI SALVATAGGIO",JOptionPane.ERROR_MESSAGE);
							}		
					
					else{
					FileWriter  out = new FileWriter("clienti.txt",true);					
							Cliente c = new Cliente (t1.getText(),t2.getText());
							strutturaSportiva.addCliente(c);
						out.write(""+t1.getText()+" ");
						out.write(""+t2.getText()+"\n");						
						JOptionPane.showMessageDialog(frame,"Registrazione Avvenuta,\n "
								+ " La Tua Username e': "+u
								+ "\n La Tua Password e' : "+p,"SALVATAGGIO...",JOptionPane.INFORMATION_MESSAGE);
						
						frame.setVisible(false);
						out.close();
						new FrameMain(s);
						}
				}
					
				catch (IOException e1) {
			JOptionPane.showMessageDialog(frame,"Errore Apertura File","OPS NON DOVEVA CAPITARE",JOptionPane.ERROR_MESSAGE);
				}
				}}						
		
		ActionListener listener1=new ButtonListener1();
		b1.addActionListener(listener1);
		
		JButton b2 = new JButton ("indietro");
		
		class ButtonListener2 implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new FrameMain(s);				
				
			}}
		ActionListener listener2=new ButtonListener2();
		b2.addActionListener(listener2);
		
		
		
		p1.add(l2);
		p1.add(t1);
		p1.add(l3);
		p1.add(t2);
		p1.add(b1);
		p1.add(b2);
		p.add(p1,BorderLayout.CENTER);
		frame.add(p,BorderLayout.CENTER);		
		
		frame.setVisible(true);
		InputMap frameInputMap = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	    ActionMap frameActionMap = this.getRootPane().getActionMap();
	    frameInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_CONTEXT_MENU, 0), "azioneContext");
	    
	    frameActionMap.put("azioneContext", new AbstractAction() {
	        public void actionPerformed(ActionEvent e) {
	        	new FrameRiepilogo(strutturaSportiva);
	       //     System.out.println("pressed menu");
	        }
	    });
	
			
}}
