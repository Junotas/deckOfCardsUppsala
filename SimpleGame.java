import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

class SimpleGame extends JPanel implements MouseListener,Game {

    private ArrayList<Pile> piles = new ArrayList<Pile>();

    private Pile stockPile;
    private ThrowPile throwPile;
    private Pile redPile;
    private Pile otherPile;
    private Pile upPile;
    
    static final int cardWidth = Card.width;
    static final int cardHeight = Card.height;

    static final int xSpace = cardWidth + 10;
    static final int ySpace = cardHeight + 10;
    static final int tab = 10;
    
    private Pile selected;
    
    public SimpleGame () {

        throwPile = new ThrowPile(xSpace*2, ySpace);
        stockPile = new StockPile(xSpace*1, ySpace, throwPile);
        redPile = new RedPile(xSpace*1, ySpace*2);
        otherPile = new OtherPile(xSpace*2, ySpace*2);
        upPile =    new UpPile(xSpace*2, ySpace*3);
        
        piles.add(stockPile);
        piles.add(throwPile);
        piles.add(redPile);
        piles.add(otherPile);
        piles.add(upPile);
        
        Deck d = new Deck ();
        d.shuffle();
        for (Card c : d.cardSet()) {
            stockPile.add(c);
        }
    }
            
    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        g.setColor( Color.GREEN );
        
        g.fillRect( 0, 0, getWidth(), getHeight() );

        for (Pile p : piles) {
            p.draw(g, selected);
        }
    }
    
    public void mouseClicked (MouseEvent e) { 
	    
        int x = e.getX();
        int y = e.getY();

        for (Pile p : piles) {
            if (p.contains(x,y)) {
                p.click(this);
                repaint();
                return; // only click on one pile
            }
        }
        // Allow the user to unselect by clicking on the board
        setSelected(null); 
        repaint();
    }                            


    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
            
    public void setSelected(Pile p) {
        selected = p;
    }

    public Pile getSelected() {
        return selected;
    }

    
    static public void main(String[] args) { 
        SimpleGame p = new SimpleGame();
        JFrame f = new JFrame();

        f.getContentPane().setLayout(new BorderLayout());
        f.getContentPane().add(new Control(), BorderLayout.NORTH);
        f.getContentPane().add(p, BorderLayout.CENTER);

        p.addMouseListener(p);
        
	f.setBounds(100, 100, 800, 500);
        
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        f.setVisible(true);

    }
}
