
/**
 * Základná stavebná častica šachovnice. Pamätá si či na nej je alebo nieje dáma a aké má súradnice.
 * 
 * @author Matúš Kán 
 */
public class ChessboardBox {
    
    private boolean hasQueen;
    private int locatedInLine;
    private int locatedInColumn;
    
    /**
     * Inicializácia políčka. V základe je políčko prázdne (bez dámy).
     * @param locatedInLine Riadok v ktorom sa políčko nachádza.
     * @param locatedInColumn Stĺpec v ktorom sa políčko nachádza.
     */
    public ChessboardBox(int locatedInLine, int locatedInColumn) {
        this.locatedInLine = locatedInLine;
        this.locatedInColumn = locatedInColumn;
        this.hasQueen = false;
    }
    
    /**
     * Nastavý políčku dámu.
     */
      
    public void placeQueen() {
        this.hasQueen = true;
    }
    
    /**
     * Odstráni z políčka dámu.
     */
    
    public void removeQueen() {
        this.hasQueen = false;
    }
    
    /**
     * @return Vráti či políčko má alebo nemá na sebe položenú dámu.
     */
    
    public boolean hasQueen() {
        return this.hasQueen; 
    }
    
    /**
     * Metóda ktorá vracia súradnice políčka.
     * 
     * @return Vráti pole tipu int ktoré má na prvej pozícií X-ovú súradnicu a na druhej pozícií Y-ovú súradnicu.
     */
    
    public int[] giveCoordinatesOfBox() {
        int[] coordinates = {this.locatedInLine, this.locatedInColumn};
        return coordinates;
    } 
}
