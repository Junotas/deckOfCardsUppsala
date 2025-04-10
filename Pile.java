import java.awt.Graphics;
import java.awt.Color;
import java.util.*;
import java.awt.geom.Point2D;
import java.awt.*;

public abstract class Pile {
    private int x;
    private int y;

    private ArrayList<Card> cards = new ArrayList<Card>();
        
    static final int cardWidth = Card.width;
    static final int cardHeight = Card.height;

    public Pile (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
    
    public Card top() {
        if(cards.size()==0) {
            return null;
        }
        else {
            return cards.get(cards.size()-1);
        }
    }

    public Card pop() {
        int i = cards.size()-1;
        Card r = cards.get(i);
        cards.remove(i);
        return r;
    }

    public boolean isEmpty() {
        return cards.size() == 0;
    }

    public int size () {
        return cards.size();
    }

    
    public int crownX() {
        if (isEmpty()) {
            return x;
        }
        else {
            return top().getX();
        }
    }
        
    public int crownY() {
        if (isEmpty()) {
            return y;
        }
        else {
            return top().getY();
        }
    }

    
    public boolean canAccept(Card c) {
        return false;
    }
            
    public boolean contains (int x1, int y1) {
	if (crownX() <= x1 && x1 <= crownX() + Card.width
            && crownY() <= y1 && y1 <= crownY() + Card.height) {
            return true;
        }
        return false;
    }

    public boolean canRelease() {
        return false;
    }
    
    public boolean isCcompleted() {
        return false;
    }
    
    void add(Card c) {
	c.move(x,y);
        cards.add(c);
    }

    public void click(Game g) {
        Pile selected = g.getSelected();
        if (selected!=null) {
            Card c = selected.top();
            if (selected.canRelease() &&
                canAccept(c)) {
                System.out.println("Move successful to "+ this);
                selected.pop();
                add(c);
                g.setSelected(null);
                System.out.println("Pile size "+size());
            }
        }
        else {
            g.setSelected(this);
        }
    }

    void draw(Graphics g, Pile selected) {
	g.setColor(Color.YELLOW);
	g.fillRect(x, y, cardWidth, cardHeight);
	for (Card c : cards) {
	    c.draw(g);
	}
	if (selected==this) {
            Graphics2D g2 = (Graphics2D) g;
	    g2.setColor(Color.BLUE);
	    g2.setStroke(new BasicStroke(2.0f));
	    g2.drawRect(crownX()-1, crownY()-1,cardWidth+1, cardHeight+1);
        }
    }
}

