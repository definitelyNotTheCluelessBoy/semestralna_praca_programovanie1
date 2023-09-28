/**
 * Nástroj na overenie bezpečnosti (je naň možne položiť dámu) políčka.
 * 
 * @author Mátúš Kán
 */
public class Inspector {
    private int chessboardSize; 
    /**
     * Inicializuje parameter ktorý si pamätá rozmer šachovnice.
     * @param chessboardSize Rozmer šachovnice.
     */
    
    public Inspector(int chessboardSize) {
        this.chessboardSize = chessboardSize;
    }

    /**
     * Prehľadá celú šachovnicu a zistí či je políčko v bezpečí alebo je ohrozená.
     * @param chessborad Šachovnica s aktuálnym umiestnením dám.
     * @param line Riadok v ktorom s nachádza políčko ktorého bezpečnosť chceme overiť.
     * @param column Stĺpec v ktorom s nachádza políčko ktorého bezpečnosť chceme overiť.
     * @return Vráti či je políčko ohrozéné alebo nie.
     */
    
    public boolean isBoxUnderThreat(ChessboardBox[][] chessboard, int line, int column) {
        return this.searchThroughLine(chessboard, line) | this.searchThroughColumn(chessboard, column) 
            | this.searchThroughDiagonal(chessboard, line, column) | this.searchThroughReverseDiagonal(chessboard, line, column);
    }

    /**
     * Prehľadávane po riadku.
     * @param chessborad Šachovnica s aktuálnym umiestnením dám.
     * @param line Riadok v ktorom s nachádza políčko ktorého bezpečnosť chceme overiť.
     * @return Vráti či je políčko ohrozéné alebo nie.
     */
    
    private boolean searchThroughLine(ChessboardBox[][] chessboard, int line) {
        for (ChessboardBox box : chessboard[line]) {
            if (box.hasQueen()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Prehľadávanie stĺpca.
     * @param chessborad Šachovnica s aktuálnym umiestnením dám.
     * @param column Stĺpec v ktorom s nachádza políčko ktorého bezpečnosť chceme overiť.
     * @return Vráti či je políčko ohrozéné alebo nie.
     */
    
    private boolean searchThroughColumn(ChessboardBox[][] chessboard, int column) {
        for (int i = 0; i < this.chessboardSize; i++) {
            if (chessboard[i][column].hasQueen()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Prehľadávanie diagonáli.
     * @param chessborad Šachovnica s aktuálnym umiestnením dám.
     * @param line Riadok v ktorom s nachádza políčko ktorého bezpečnosť chceme overiť.
     * @param column Stĺpec v ktorom s nachádza políčko ktorého bezpečnosť chceme overiť.
     * @return Vráti či je políčko ohrozéné alebo nie.
     */
    
    private boolean searchThroughDiagonal(ChessboardBox[][] chessboard, int line, int column) {
        while (line - 1 >= 0 & column - 1 >= 0) {
            line--;
            column--;
        }

        while (line < this.chessboardSize & column < this.chessboardSize) {
            if (chessboard[line][column].hasQueen()) {
                return true;
            }
            line++;
            column++;
        }

        return false; 
    }

    /**
     * Prehľadávanie opačnej diagonáli.
     * @param chessborad Šachovnica s aktuálnym umiestnením dám.
     * @param line Riadok v ktorom s nachádza políčko ktorého bezpečnosť chceme overiť.
     * @param column Stĺpec v ktorom s nachádza políčko ktorého bezpečnosť chceme overiť.
     * @return Vráti či je políčko ohrozéné alebo nie.
     */
    
    private boolean searchThroughReverseDiagonal(ChessboardBox[][] chessboard, int line, int column) {
        while (line + 1 < this.chessboardSize & column - 1 >= 0) {
            line++;
            column--;
        }

        while (line >= 0 & column < this.chessboardSize) {
            if (chessboard[line][column].hasQueen()) {
                return true;
            }
            line--;
            column++;
        }

        return false; 
    }
}
