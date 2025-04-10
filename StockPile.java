class StockPile extends Pile {

    // The pile containing all cards at the start of the game.

    private ThrowPile throwPile;

    public StockPile(int x, int y, ThrowPile throwPile) {
        super(x,y);
        this.throwPile = throwPile;
    }
    
    public void click(Game g) {
        Pile selected = g.getSelected();
        if (selected!=null) {
            g.setSelected(null);
        }

        Card c = top();
        if (c!=null) {
            c.flip();
            throwPile.add(pop());
            g.setSelected(throwPile);
        }
        else {
            throwPile.turn(this);
        }
    }
}

