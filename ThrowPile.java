public class ThrowPile extends Pile {

    // This pile accepts all cards. Method "turn" flips all cards in
    // the pile and moves them to pile p.

    public ThrowPile(int x, int y) {
        super(x, y);
    }
    
    public void turn(Pile p) {
        while(!isEmpty()) {
            top().flip();
            p.add(pop());
        }
    }

    public boolean canRelease() {
        return true;
    }
}
