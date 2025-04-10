public class UpPile extends Pile {

    // This pile only accepts cards that have a rank that is one step
    // higher.
    
    static final int offset = 20;

    public UpPile (int x, int y) {
        super(x,y);
    }

    public boolean canAccept(Card c) {
        return isEmpty()
            || top().getRank() + 1 == c.getRank()
            || top().isKing() && c.isAce();
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
