public class TableauPile extends Pile {
    static final int verticalOffset = 20;

    public TableauPile(int x, int y) {
        super(x, y);
    }

    // Om högen är tom kan vilket kort som helst läggas in.
    // Annars måste det nya kortet ha exakt en lägre rank än översta kortet och färgen ska vara alternerande.
    public boolean canAccept(Card c) {
        if (isEmpty()) {
            return true;
        } else {
            Card topCard = top();
            return (topCard.getRank() == c.getRank() + 1) && (topCard.isBlack() != c.isBlack());
        }
    }

    public boolean canRelease() {
        return true;
    }

    // Vid tillägg visas korten med en vertikal offset
    void add(Card c) {
        int depth = size();
        super.add(c);
        c.move(getX(), getY() + depth * verticalOffset);
    }
}
