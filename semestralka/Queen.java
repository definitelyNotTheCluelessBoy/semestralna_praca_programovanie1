/**
 * Trieda ktorá vymodeluje dámu pre grafické zobrazenie.
 * 
 * @author Matúš Kán
 */
public class Queen {
    private Trojuholnik body;
    private Kruh head;
    /**
     * Vytvorý dámu.
     * @param x X-ová súradnica na ktorej dáma vznikne.
     * @param y Y-ová súradnica na ktorej dáma vznikne.
     * @param color Farba dámy.
     */
    public Queen(int x, int y, String color) {
        this.body = new Trojuholnik(x + 15, y + 15, color);
        this.head = new Kruh(x + 2, y, color); 
    }

    /**
     * Zobrazí dámu.
     */
    
    public void showQueen() {
        this.head.zobraz();
        this.body.zobraz();
    }
    
    /**
     * Skryje dámu.
     */
    
    public void hideQueen() {
        this.head.skry();
        this.body.skry();
    }
    
    /**
     * Zmení farbu dámi.
     */
    
    public void changeColor(String color) {
        this.head.zmenFarbu(color);
        this.body.zmenFarbu(color);
    }
}
