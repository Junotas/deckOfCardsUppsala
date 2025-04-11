import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class SimpleGame extends JPanel implements MouseListener, Game {

    // Konstanter för layouten
    static final int GAP = 10;
    static final int MARGIN = 20;
    static final int CARD_WIDTH = Card.width;
    static final int CARD_HEIGHT = Card.height;

    // Högar som används i spelet
    private StockPile stockPile;
    private ThrowPile wastePile; // Slänghög
    private ArrayList<FoundationPile> foundationPiles = new ArrayList<>();
    private ArrayList<TableauPile> tableauPiles = new ArrayList<>();
    private ArrayList<Pile> allPiles = new ArrayList<>();

    private Pile selected;

    public SimpleGame() {
        // Placering: Stock och Waste
        int stockX = MARGIN;
        int stockY = MARGIN;
        stockPile = new StockPile(stockX, stockY, null); // Waste referens sätts senare
        int wasteX = stockX + CARD_WIDTH + GAP;
        int wasteY = MARGIN;
        wastePile = new ThrowPile(wasteX, wasteY);
        stockPile.setWastePile(wastePile);

        // Esshögar (Foundation) – 8 stycken, placeras från vänster till höger på övre raden
        int foundationStartX = wasteX + CARD_WIDTH + GAP;
        for (int i = 0; i < 8; i++) {
            int fx = foundationStartX + i * (CARD_WIDTH + GAP);
            int fy = MARGIN;
            FoundationPile fPile = new FoundationPile(fx, fy);
            foundationPiles.add(fPile);
        }

        // Bygghögar (Tableau) – 10 stycken, placeras på en rad nedanför stock/grundraden
        int tableauY = MARGIN + CARD_HEIGHT + 2 * GAP;
        for (int i = 0; i < 10; i++) {
            int tx = MARGIN + i * (CARD_WIDTH + GAP);
            TableauPile tPile = new TableauPile(tx, tableauY);
            tableauPiles.add(tPile);
        }

        // Samla alla högar för att underlätta ritning och klickhantering
        allPiles.add(stockPile);
        allPiles.add(wastePile);
        allPiles.addAll(foundationPiles);
        allPiles.addAll(tableauPiles);

        // Skapa och blanda kortleken (104 kort)
        Deck deck = new Deck();
        deck.shuffle();
        ArrayList<Card> cards = deck.cardSet();

        // Dela ut de 40 översta korten (4 kort per Tableau) – dessa vänds upp
        for (int round = 0; round < 4; round++) {
            for (int i = 0; i < tableauPiles.size(); i++) {
                Card c = cards.remove(0);
                c.turnUp(); // Kortet blir synligt
                tableauPiles.get(i).add(c);
            }
        }

        // Resterande 64 kort går till Stock (ligger med baksidan upp)
        while (!cards.isEmpty()) {
            Card c = cards.remove(0);
            if(c.isFaceUp()) {  // Se till att korten i Stock är nedvända.
                c.flip();
            }
            stockPile.add(c);
        }

        setBackground(Color.GREEN);
        addMouseListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Rita alla högar
        for (Pile p : allPiles) {
            p.draw(g, selected);
        }
    }

    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        // Kolla vilken hög som klickats
        for (Pile p : allPiles) {
            if (p.contains(x, y)) {
                p.click(this);
                repaint();
                return;
            }
        }
        // Om man klickar utanför en hög avmarkeras den valda
        setSelected(null);
        repaint();
    }

    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}

    public void setSelected(Pile p) {
        selected = p;
    }

    public Pile getSelected() {
        return selected;
    }

    public static void main(String[] args) {
        SimpleGame gamePanel = new SimpleGame();
        JFrame frame = new JFrame("De 40 rövarna");
        frame.getContentPane().setLayout(new BorderLayout());

        // Lägg eventuellt till en kontrollpanel om så önskas
        frame.getContentPane().add(new Control(), BorderLayout.NORTH);
        frame.getContentPane().add(gamePanel, BorderLayout.CENTER);

        frame.setBounds(100, 100, 1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
