package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class PostoPanel extends JPanel {

	 private static final int PIXEL_SIZE = 17;
	 private Color backgroundColor; 
	    private int id;
      	    
	    
	    public PostoPanel(int i,Color c) {	 
	    	
	    	id =i;
	    	//this.setBorder( new TitledBorder(new EtchedBorder(),""+this.getID()));
	        this.backgroundColor = c; 
	        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	        this.setPreferredSize(new Dimension(PIXEL_SIZE, PIXEL_SIZE)); 
	    }
	    
	    public PostoPanel() {
	    	
	        this.backgroundColor = Color.GREEN; 
	        
	        this.setBorder(BorderFactory.createLineBorder(Color.GREEN));
	        this.setPreferredSize(new Dimension(PIXEL_SIZE, PIXEL_SIZE)); 
	    }
	    
	    
	    
	    public Color getBackgroundColor() {
	        return backgroundColor;
	       
	    }

	    public void setBackgroundColor(Color backgroundColor) {
	        this.backgroundColor = backgroundColor;
	        this.repaint();
	    }
	  
	    
	    public int getID(){
	 	   return id;
	    }
	    
	  
	    
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);

	        g.setColor(getBackgroundColor());
	        g.fillRect(0, 0, getWidth(), getHeight());
	    }
	    
	    
	
}
