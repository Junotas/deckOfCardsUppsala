public class OtherPile extends Pile {

    // Only accepts cards that are not of the same suit as the top
    // card.

    static final int offset = 20;

    public OtherPile (int x, int y) {
        super(x,y);
    }

    public boolean canAccept(Card c) {
        return isEmpty() || top().getSuit() != c.getSuit();
    }

    void add(Card c) {
        int depth = size();
        super.add(c);
	c.move(getX()+depth*offset,getY());
    }

    public boolean canRelease() {
        return true;
    }


}
