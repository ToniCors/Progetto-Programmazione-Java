package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.Border;

import exception.PostoIndisponibileException;
import strutturaSportiva.*;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;

/**
 * Classe che crea un interfaccia Stadio*/

public class FrameStadio extends JFrame {


	public FrameStadio(Partita p,Cliente c,StrutturaSportiva s){
		frame= new JFrame();
		strutturaSportiva=s;
		partita = p ;
		cliente =c;
		frame.setSize(1366, 740);
		frame.setLayout(new BorderLayout());		
		menubar = new JMenuBar();
		exit = new JMenuItem("SALVA ED ESCI");
		exit.addActionListener(escListener);
		menubar.add(exit);
		frame.setJMenuBar(menubar);
		JSplitPane top = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,createClient(),createPosti(p.getStadio().getNumPosti()));
		//frame.add(createPosti(p.getStadio().getNumPosti()),BorderLayout.CENTER);
		//frame.add(createClient(),BorderLayout.WEST);	
		frame.add(top);
		frame.setLocationRelativeTo(null); //fa uscire il frame al centro 
        frame. setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		InputMap frameInputMap = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	    ActionMap frameActionMap = this.getRootPane().getActionMap();
	    frameInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_CONTEXT_MENU, 0), "azioneContext");
	    
	    frameActionMap.put("azioneContext", new AbstractAction() {
	        public void actionPerformed(ActionEvent e) {
	        	new FrameRiepilogo(strutturaSportiva);
	        //	FrameRiepilogo.AggiornaRiepilogo(strutturaSportiva);	            FrameRiepilogo.V();
	            System.out.println("pressed menu");
	        }
	    });
		
		
	}
	
	
	
	
	private JPanel createPosti(int pos){
		int erba = 64;
	    int	posti = pos;
	    int campo = erba +posti;
	   // int b = (int) Math.sqrt(campo/2);
	   // int h=b*2;
	  int h = (int) Math.sqrt(campo);
	   int b = h;
	    int resto =campo-b*h;
	   	
	        int x = b/2-4;
	        int y = h/2-4; 
	       int x1 =x+7;
	       int y1 =y+7;
	      
	       

	        JPanel panel = new JPanel();       
	        panel.setLayout(new GridLayout(h+(resto/b)+1,b)); 
	     
	int count =0;
	        for (int row = 0; row <h; row++) {
	            for (int column = 0; column <b; column++) {
	         if((row>=x && row<=x1) && (column>=y && column<=y1))
	                {PostoPanel prato = new PostoPanel();  panel.add(prato); }
	            	else {PostoPanel pixelPanel ;
	            		if(partita.getBiglietto(count).getStato()==0)
	            			pixelPanel = new PostoPanel(count,Color.white);
	            		else if(partita.getBiglietto(count).getStato()==1)
	            			pixelPanel = new PostoPanel(count,Color.YELLOW);
	            		   else 
	            			  pixelPanel = new PostoPanel(count,Color.red);
	            		
	           
	            		pixelPanel.addMouseListener(postoList);
	                panel.add(pixelPanel);
	                count++;
	                }
	          
	            }
	        }
	        for(int i =1 ; i<=resto;i++){
	        	 PostoPanel pixelPanel;
	        	 
	        	 if(partita.getBiglietto(count).getStato()==0)
         			pixelPanel = new PostoPanel(count,Color.WHITE);
         		else if(partita.getBiglietto(count).getStato()==1)
         			pixelPanel = new PostoPanel(count,Color.YELLOW);
         		   else 
         			  pixelPanel = new PostoPanel(count,Color.RED);
	        	
	        	 pixelPanel.addMouseListener(postoList);
	             panel.add(pixelPanel);
	        	 count++;
	        }
	      
	            
	        return panel;
	}

   private 	JPanel createClient(){
	   //acquista.setPreferredSize(new Dimension(5,5));
	JPanel panel = new JPanel();
	panel.setMinimumSize(new Dimension(150,150));
	panel.setLayout(new GridLayout(20,1));
	acquista.addActionListener(buy);
	prenota.addActionListener(pren);
	   lc = new JLabel("BUONGIORNO  "+cliente.getUsername());
	   l = new JLabel("PARTITA");	
	   lp = new JLabel(""+partita.toString());	   
	   l0 = new JLabel("POSTO SELEZIONATO");
	   lpos = new JLabel("");
	   l1 = new JLabel("PREZZO");
	   lpr = new JLabel(" "+partita.getPrezzoScontato());
	   panel.add(lc);
	   panel.add(l);
	   panel.add(lp);
	   panel.add(l0);
	   panel.add(lpos);
	   panel.add(l1);
	   panel.add(lpr);	   
       
      panel.add(acquista);
      panel.add(prenota);
	   return panel;
   }
   
   
	class PostoListener implements MouseListener {
			    	    
	    public void mousePressed(MouseEvent event) {
	    	PostoPanel panel;
	    	panel =(PostoPanel)event.getComponent();	    	
	    	System.out.println("event "+panel.getID());
	    	lpos.setText(""+ panel.getID());
	    	
	    	if(panel.getBackgroundColor().equals(Color.YELLOW)
	    			||panel.getBackgroundColor().equals(Color.RED))
	    	{ lpos.setText(""); selected.setBackgroundColor(Color.WHITE); 
	    	throw new PostoIndisponibileException();}	    	
	    		    	
	    	else if(c==0|| selected.equals(panel) ){
	     	
	    		panel.setBackgroundColor(Color.CYAN);	    		
	    		 selected=panel;
	    		  c=c+1;  	
	    	}
	    	
	     	else {
	     		
		    	 panel.setBackgroundColor(Color.CYAN);
		    	 selected.setBackgroundColor(Color.WHITE); 
		    	 selected=panel;
	    		    	}
	    	    	     
	}
		public void mouseClicked(MouseEvent arg0) {}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}	
}

	
class AcquistaListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		System.out.println("acquista");
		cliente.acquistaBiglietto(partita,Integer.parseInt(lpos.getText()) );
		selected.setBackgroundColor(Color.RED);
		c=0;selected=null;
		
	}
}
   
   
   class PrenotaListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			cliente.prenotaBiglietto(partita,Integer.parseInt(lpos.getText()) );
			selected.setBackgroundColor(Color.YELLOW);
			c=0;selected=null;
			
		}}
   
   class EsciListener implements ActionListener{

	public void actionPerformed(ActionEvent arg0) {
		frame.setVisible(false);
		new FrameCliente(cliente,strutturaSportiva);
		
	}
	   
   }

   private Partita partita ;
   private Cliente cliente;

   private  PostoPanel selected ;int c=0;
   private JFrame frame;
   private JLabel lc ;
   private JLabel l ;
   private JLabel lp ;
   private JLabel l0 ;
   private JLabel lpos;
   private JLabel l1 ;
   private JLabel lpr ;
   private JButton acquista = new JButton("ACQUISTA");
   private ActionListener buy= new AcquistaListener();
   private ActionListener escListener= new EsciListener();
   private JButton prenota = new JButton("PRENOTA");	
   private ActionListener pren= new PrenotaListener();
   private MouseListener postoList = new PostoListener();
   private JMenuBar menubar ;
   private JMenuItem exit;
   private StrutturaSportiva strutturaSportiva;  
   
}
		   
	   
   
   