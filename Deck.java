import java.util.*;

public class Deck {
    
    private static final String back = "img/b1fv.gif";
    private static final String[] suites = {"s", "h", "d", "c"};
    private static final String[] ranks =
    {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "j", "q", "k"};
    
    private ArrayList<Card> cardSet = new ArrayList<Card>();
    private Random randomGenerator=new Random();
    
    public Deck () {
        for (int s = 0; s<4; s++) {
	    for (int r = 0; r<13; r++) {
		String front = "img/"+suites[s]+ranks[r]+".gif";
		Card c = new Card(s, r, front, back);
		cardSet.add(c);
	    }
	}
    }

    public void shuffle() {
        for (int i = 0; i<52; i++) {
            int j = i+randomGenerator.nextInt(52-i);
            swap(i, j);
        }
    }
    
    private void swap(int i, int j) {
        Card t = cardSet.get(i);
        cardSet.set(i, cardSet.get(j));
        cardSet.set(j, t);
    }

    public ArrayList<Card> cardSet() {
        return new ArrayList<Card> (cardSet); //why?
    }
}

