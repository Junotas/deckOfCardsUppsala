class StockPile extends Pile {

    // Högen med de kvarvarande korten (Stock) håller ett referens till slänghögen.
    private ThrowPile wastePile;

    public StockPile(int x, int y, ThrowPile wastePile) {
        super(x, y);
        this.wastePile = wastePile;
    }

    public void setWastePile(ThrowPile wastePile) {
        this.wastePile = wastePile;
    }

    public void click(Game g) {
        Pile selected = g.getSelected();
        if (selected != null) {
            g.setSelected(null);
        }

        Card c = top();
        if (c != null) {
            c.flip(); // Vänd kortet så att det blir synligt
            wastePile.add(pop());
            g.setSelected(wastePile);
        } else {
            // Om Stock är tom, återvinn korten från wastePile
            wastePile.turn(this);
        }
    }
}
