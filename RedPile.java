public class RedPile extends Pile {

    // Only accepts red cards (hearts and diamonds).

    static final int offset = 10;

    public RedPile (int x, int y) {
        super(x,y);
    }

    public boolean canAccept(Card c) {
        return !c.isBlack();
    }

    public boolean canRelease() {
        return true;
    }

    void add(Card c) {
        int depth = size();
        super.add(c);
	c.move(getX(),getY()+depth*offset);
    }
}
