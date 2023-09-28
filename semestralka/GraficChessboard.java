/**
 * Grafická interpretácia šachovnice. Odohráva sa tu všetko grafické vykresľovanie a prekresľovanie.
 * 
 * @author Matúš Kán 
 */
public class GraficChessboard {
    private TileWithQueen[][] tilesWithQueens;
    private Stvorec background;
    
    /**
     * Vytvorý šachovnicu z políčok tipu TileWithQueen.
     * @param size Veľkosť šachovnice.
     */
    
    public GraficChessboard(int size) {
        this.background = new Stvorec(size * 50 + 20, 0, 0, "red");
        this.background.zobraz();
        
        this.tilesWithQueens = new TileWithQueen[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                String color = "";
                if (i % 2 == 0 && j % 2 == 0) {
                    color = "black";
                } else if (i % 2 == 1 && j % 2 == 0) {
                    color = "white";
                } else if (i % 2 == 0 && j % 2 == 1) {
                    color = "white";
                } else if (i % 2 == 1 && j % 2 == 1) {
                    color = "black";
                }
                    
                this.tilesWithQueens[i][j] = new TileWithQueen(10 + (i * 50), 10 + (j * 50), color);
                
            }
        }
    }
    
    /**
     * @return Vráti šachovnicu vo forme dvojrozmerného poľa políčok tipu TileWithQueen.
     */
    
    public TileWithQueen[][] getTilesWithQueens() {
        return this.tilesWithQueens;
    }
    
    /**
     * Prejde celú šachovnicu a každému políčku povie nech skrýje dámu.
     */
    
    public void hideAllQueens() {
        for (TileWithQueen[] line : this.tilesWithQueens) {
            for (TileWithQueen tile : line) {
                tile.hideQueen();
            }
        }
    }
}
