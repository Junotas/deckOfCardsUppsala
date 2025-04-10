import java.awt.*;
import java.util.*;
import javax.swing.*;

class CardTest extends JPanel {
    // Draw some cards, just to check that image files are available
    // and Card class works.

    private static final int indent = 80;

    String back = "img/b1fv.gif";
    
    Card ace    = new Card(0,0, "img/"+Card.name(0,0)+".gif",   back);
    Card four   = new Card(1,3, "img/"+Card.name(1,3)+".gif",   back);
    Card knight = new Card(2,10, "img/"+Card.name(2,10)+".gif", back);
    Card queen  = new Card(3,11, "img/"+Card.name(3,11)+".gif", back);

    public CardTest () {
        setBackground( Color.GREEN );

        ace.flip(); knight.flip(); queen.flip();
        
        ace.move(indent,      indent);
        four.move(indent*2,   indent);
        knight.move(indent*3, indent);
        queen.move(indent*4,  indent);
    
    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        
        ace.draw(g);
        four.draw(g);
        knight.draw(g);
        queen.draw(g);
    }
    
    static public void main(String[] args) { 
        JPanel p = new CardTest();
        JFrame f = new JFrame();
        
        f.getContentPane().add(p);
                
	f.setBounds(100, 100, 800, 500);
        
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        f.setVisible(true);

    }
}
    
