
/**
 * Vymodeluje políčko s dámou.
 * 
 * @author Matúš Kán
 */
public class TileWithQueen {
    private Queen queen;
    private Stvorec tile;
    private String color;
    /**
     * Vymodeluje políčko a postaví naň neviditeľnú královnu.
     * @param x X-ová súradnica na ktorej políčko vznikne.
     * @param y Y-ová súradnica na ktorej políčko vznikne.
     * @param color Farba políčka.
     */
    public TileWithQueen(int x, int y, String color) {
        this.color = color;
        this.tile = new Stvorec(50, x, y, color);
        if (color.equals("black")) {
            this.queen = new Queen(x + 10, y + 2, "white");
        } else {
            this.queen = new Queen( x + 10, y + 2, "black");
        }
        this.tile.zobraz();
    }
    
    /**
     * Zobrazí dámu.
     */
    
    public void showQueen() {
        this.queen.showQueen();
    }
    
    /**
     * @return Vráti farbu políčka.
     */
    
    public String getColor() {
        return this.color;
    }
    
    /**
     * Skryje dámu.
     */
    
    public void hideQueen() {
        this.queen.hideQueen();
    }
    
    /**
     * Zmení farbu políčka.
     */
    
    public void changeColor(String color) {
        this.tile.zmenFarbu(color);
    }
}
