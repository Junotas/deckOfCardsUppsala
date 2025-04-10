import java.awt.*;
import javax.swing.*;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.lang.RuntimeException;

class Card {
    private int x, y;
    private boolean faceUp;
    private Image front, back;
    private static Color background = Color.WHITE;

    private int rank; // Note that ace is zero, two is 1 etc
    private int suit; // Clubs=0, Diamonds=1, Hearts=2, Spades=3

    public boolean isBlack() {
        return suit == 0 || suit == 3;
    }
    
    private static String[] rankName = {"1", "2", "3", "4", "5", "6", "7",
                                        "8", "9", "10", "j", "q", "k"};
                                
    static String[] suitName = {"c", "d", "h", "s"};
    
    static int width = 71;
    static int height = 96;
    
    public Card (int s, int r, 
                 String frontFile, String backFile) {
        suit = s;
        rank = r;

        try {
            back = ImageIO.read(new File(backFile));
            front = ImageIO.read(new File(frontFile));
        }
        catch (IOException e) {
            System.out.println("Could not load image file");

            System.out.println("front="+frontFile);
            System.out.println("back="+backFile);

        }
    }
    
    public int getX() { return x; }

    public int getY() { return y; }

    public int getRank() {
        return rank;
    }

    public int getSuit() {
        return suit;
    }

    public boolean isAce () {
	return rank==0;
    }

    public boolean isSix () {
	return rank==5;
    }

    public boolean isSeven () {
	return rank==6;
    }

    public boolean isKing () {
	return rank==12;
    }
    
    public int getWidth() { return width; }

    public int getHeight() { return height; }

    public boolean contains(int x1, int y1) {
        return x <= x1 && x1 <= x + Card.width
            && y <= y1 && y1 <= y + Card.height;
    }
    
    public void draw(Graphics g) {

        // Draw a card. If we can't load the image files, draw
        // draw some letters and numbers.

        g.setColor(background);
        g.fillRect(x,y,width, height);        

        // Get the image, if available
        Image image;
        if (faceUp) {
            image = front;
        }
        else {
            image = back;
        }

        if (image == null) {
            // there is no image: we need to manage without
            
            if (faceUp) {

                if (isBlack()) {
                    g.setColor(Color.BLACK);
                }
                else {
                    g.setColor(Color.RED);
                }

                String name = name();
                
                g.setFont(new Font("Sans Serif", Font.BOLD, 12));
                g.drawString(name, x, y+10);
                g.drawString(name, x+width-15, y+height-10);
                g.setFont(new Font("Sans Serif", Font.BOLD, 32));
                g.drawString(name, x+width/2-20, y+height/2+15);
            }
            else {
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(x,y,width, height);   
            }
        }
        else {
            // there is an image
        
            g.drawImage(image, x, y, null);
        }
    }
    
    public void move(int x1, int y1) {
	x = x1;
	y = y1;
    }
    
    public void flip () {
        faceUp = !faceUp;
    }

    public void turnUp () {
	if (!faceUp) {
	    flip();
	}
    }
    
    public boolean isFaceUp () {
        return faceUp;
    }


    public String toString() {
        // In case you want to print a card.
        return "Card["+suit+" "+rank+" faceUp="+faceUp+"]";
    }

    public String name() {
        return suitName[suit]+rankName[rank];
    }

    static public String name(int suit, int rank) {
        return suitName[suit]+rankName[rank];
    }
    
}
