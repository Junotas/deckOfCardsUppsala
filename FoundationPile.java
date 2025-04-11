public class FoundationPile extends Pile {

    public FoundationPile(int x, int y) {
        super(x, y);
    }

    // Endast ess får placeras i en tom hög och därefter måste kort byggas uppåt i samma svit.
    public boolean canAccept(Card c) {
        if (isEmpty()) {
            return c.getRank() == 0; // Ace är rank 0
        } else {
            Card top = top();
            return top.getSuit() == c.getSuit() && (c.getRank() == top.getRank() + 1);
        }
    }

    // Tillåter inte att kort flyttas utifrån (kan ej släppas)
    public boolean canRelease() {
        return false;
    }

    // Inga vertikala offset, korten placeras exakt på högens plats.
    void add(Card c) {
        super.add(c);
        c.move(getX(), getY());
    }
}
